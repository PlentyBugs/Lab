package Lab.ServerClient;

import Lab.Live.Cog;
import Lab.Live.Driver;
import Lab.Live.Sex;
import Lab.Live.Shpuntick;
import Lab.Locations.CarService;
import Lab.Things.Car;
import Lab.Things.Details;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.LinkedList;

public class Server {

    public static final int PORT = 4004;
    public static LinkedList<ServerReceiver> serverList = new LinkedList<>();
    private static Thread repair;
    private static Cog cog = new Cog("Винтик", "механик", Sex.MALE){
        @Override
        public void addExp(int count){
            exp += Math.round(count*1.3);
            levelUp();
        }
    };
    private static Shpuntick shpuntick = new Shpuntick("Шпунтик", "", Sex.MALE){
        @Override
        public void addExp(int count){
            exp += Math.round(count*0.7);
            levelUp();
        }
    };
    private static Driver driver = new Driver("Водитель", "водитель", Sex.MALE){
        @Override
        public void addExp(int count){
            exp += Math.round(count*1.7);
            levelUp();
        }
    };

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(PORT));
        serverSocketChannel.configureBlocking(false);

        repair = new Thread(() -> {
            try {
                while (true){
                    synchronized (repair) {
                        repair.wait(10000);
                    }
                    ArrayList<String> userNames = new ArrayList<>();
                    for(ServerReceiver serverReceiver : serverList)
                        if(serverReceiver.getUserName() != null)
                            userNames.add(serverReceiver.getUserName());

                    CarService carService = new CarService();
                    carService.readFromCsvFileByNonUser("./server/database.csv", userNames);

                    for(Car car : carService.getCars().values()){
                        for (Details obj : car.getDetails()){
                            if (obj.getQuality() < 100){
                                obj.setQuality(obj.getQuality() + cog.getProfessionLvl() + cog.getLvl());
                                obj.setQuality(obj.getQuality() + shpuntick.getProfessionLvl() + shpuntick.getLvl());
                                obj.setQuality(obj.getQuality() + driver.getProfessionLvl() + driver.getLvl());
                            }
                            if(obj.getQuality() > 100)
                                obj.setQuality(100);
                        }
                    }

                    carService.writeToCSVFileByNonUser("./server/database.csv", userNames);
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });
        repair.start();

        try {
            while(true){
                SocketChannel socket = serverSocketChannel.accept();
                if(socket != null){
                    serverList.add(new ServerReceiver(socket));
                }
            }
        } finally {
            serverSocketChannel.close();
        }
    }
}