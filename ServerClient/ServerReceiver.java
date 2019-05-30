package Lab.ServerClient;

import Lab.Live.Person;
import Lab.Locations.CarService;
import Lab.Things.Car;
import Lab.Things.Details;

import java.io.*;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class ServerReceiver extends Thread {
    private SocketChannel socket;
    private CarService carService;
    private String userName;
    private Thread repair;

    public ServerReceiver(SocketChannel socket) {
        this.socket = socket;
        carService = new CarService();
        start();
    }

    @Override
    public void run(){
        boolean connected = false;
        while (!connected){
            connected = identifier();
            if(connected)
                break;
            try {
                String finalWord = "no connected";
                ByteBuffer bb = ByteBuffer.allocate(finalWord.getBytes().length);
                bb.clear();
                bb.put(finalWord.getBytes());
                bb.flip();
                socket.write(bb);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        try {
            String finalWord = "connected";
            ByteBuffer bb = ByteBuffer.allocate(finalWord.getBytes().length);
            bb.clear();
            bb.put(finalWord.getBytes());
            bb.flip();
            socket.write(bb);
            readByUser();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        repair = new Thread(() -> {
            try {
                while (true){
                    synchronized (repair) {
                        repair.wait(10000);
                    }
                    for(Car car : carService.getCars().values()){
                        for (Details obj : car.getDetails()){
                            if (obj.getQuality() < 100){
                                int repairPower =  -obj.getDegree_of_breakage();
                                if(obj.getIsSkiilNeed())
                                    for(Person person : Server.workers)
                                        if(person.getProfession().equals("механик") || person.getProfession().equals("водитель"))
                                            repairPower += person.getProfessionLvl();

                                for(Person person : Server.workers)
                                    repairPower += person.getLvl();

                                obj.setQuality(obj.getQuality() + Math.max(1, repairPower/5));
                            }
                            if(obj.getQuality() > 100)
                                obj.setQuality(100);
                        }
                    }

                    //writeByUser();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        repair.start();

        String word = "";
        try{
            while(true){
                ByteBuffer bffr = ByteBuffer.allocate(65536);
                socket.read(bffr);
                String str = "";
                for(byte b : bffr.array()){
                    str += (char)b;
                }
                word = str;
                carService.clearWayOut();
                carService.sortByName();
                if(word.contains("remove_all")){
                    word = word.substring("remove_all".length());
                    Car cr = new Car();
                    cr.jsonParser(word);
                    carService.removeAll(cr);
                    writeByUser();
                }
                if(word.contains("insert")){
                    word = word.substring("insert".length());
                    String key = "";
                    boolean isStartKey = false;
                    for(char k : word.toCharArray()){
                        if(Character.toString(k).equals("}")){
                            break;
                        }
                        if(isStartKey){
                            key += Character.toString(k);
                        }
                        if(Character.toString(k).equals("{")){
                            isStartKey = true;
                        }
                    }
                    Car cr = new Car();
                    cr.jsonParser(word);
                    carService.insert(key, cr);
                    writeByUser();
                }
                if(word.contains("show")){
                    carService.show();
                    word = word.substring("show".length());
                }
                if(word.contains("remove_greater")){
                    word = word.substring("remove_greater".length());
                    Car cr = new Car();
                    cr.jsonParser(word);
                    carService.removeGreater(cr);
                    writeByUser();
                }
                if(word.contains("load")){
                    readByUser();
                    word = word.substring("load".length());
                }
                if(word.contains("remove")){
                    word = word.substring("remove".length());
                    carService.remove(word.substring(1, word.length()-1).split(",")[0]);
                    writeByUser();
                }
                if(word.contains("info")){
                    carService.info();
                    word = word.substring("info".length());
                }
                if(word.contains("save")){
                    word = word.substring("save".length());
                    carService.buildSaveJson();
                }
                if(word.contains("delete")){
                    word = word.substring("delete".length());
                    String countS = "";
                    for(byte b : word.getBytes()){
                        if(b == 0)
                            break;
                        countS += (char)b;
                    }
                    PostgreSQL.deleteCar(Integer.parseInt(countS), userName);
                }
                if(word.contains("table")){
                    word = word.substring("table".length());
                    carService.buildTable();
                }
                if(word.contains("import")){
                    word = word.substring("import".length());
                    String countS = "";
                    for(byte b : word.getBytes()){
                        if(b == 0)
                            break;
                        countS += (char)b;
                    }
                    ByteBuffer importBuffer = ByteBuffer.allocate(Integer.parseInt(countS));
                    socket.read(importBuffer);
                    String importString = "";
                    for(byte b : importBuffer.array()){
                        importString += (char)b;
                    }
                    carService = new CarService();
                    for(String s : importString.split("\n")){
                        carService.readObjectFromJson(s.substring(1, s.length()-1));
                    }
                    writeByUser();
                }
                Thread thread = new Thread(()->{
                    try {
                        String finalWord = carService.getWayOut();
                        ByteBuffer bb = ByteBuffer.allocate(finalWord.getBytes().length);
                        bb.clear();
                        bb.put(finalWord.getBytes());
                        bb.flip();
                        socket.write(bb);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                });
                thread.start();
            }
        } catch (IOException e) {}
    }

    private boolean identifier() {

        ByteBuffer bffr = ByteBuffer.allocate(1024);
        try{
            socket.read(bffr);
        } catch (IOException ignored){}
        String str = "";
        for(byte b : bffr.array()){
            str += (char)b;
        }

        String[] strs = str.split(" ");

        if(strs.length > 3 || strs.length < 3)
            return false;

        if(strs[0].length() == 0 || strs[1].length() == 0 || strs[2].length() == 0)
            return false;

        String login = strs[1];
        String pwd = strs[2];
        String password = "";
        for(byte b : pwd.getBytes()){
            if(b != 0)
                password += (char)b;
        }

        String passwordWithoutEncryption = password;

        password = passwordEncryption(password);

        if (strs[0].equals("Reg")) {
            if(!PostgreSQL.checkUserOnExisting(login)){
                PostgreSQL.insertUser(login, password);
                Mail.sendMessage(login, "Вы успешно зарегистрировались, ваш пароль: " + passwordWithoutEncryption);
                userName = login;
                return true;
            }
        } else if(strs[0].equals("Log")){
            if(PostgreSQL.checkUser(login, password)){
                userName = login;
                return true;
            }
        }

        return false;
    }

    private void readByUser() {
        carService.readByUser(userName);
    }

    private void writeByUser() {
        carService.writeByUser(userName);
    }

    private String passwordEncryption(String password){
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] messageDigest = md.digest(password.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public String getUserName(){
        return userName;
    }
}
