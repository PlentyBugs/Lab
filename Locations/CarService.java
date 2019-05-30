package Lab.Locations;

import Lab.ServerClient.PostgreSQL;
import Lab.ServerClient.Server;
import Lab.Things.Car;
import Lab.Things.Details;
import Lab.Windows.Console;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class CarService implements Serializable{
    private Map<String, Car> cars = new ConcurrentHashMap<>();
    private String loadFile;
    private String dateOfCreating;
    private ArrayList<String> changeList;
    private Console console;
    private boolean firstTime;
    private String wayOut = "";
    private String user;

    public CarService(){
        firstTime = true;
        changeList = new ArrayList<>();
    }

    public Map<String, Car> getCars() {
        return cars;
    }

    public void addCar(Car car){
        cars.put(car.getName(), car);
    }

    public void readFromCsvFileByUser(String file, String user) throws IOException {
        loadFile = file;
        if(firstTime){
            firstTime = false;
            Date date = new Date();
            dateOfCreating = date.toString();
        } else {
            Date date = new Date();
            changeList.add(date.toString());
        }
        Reader inputStreamReader = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);

        int data = inputStreamReader.read();
        this.user = user;
        while(data != -1) {
            readObjectByUser(inputStreamReader, (char)data);
            data = inputStreamReader.read();
        }

        inputStreamReader.close();
    }

    public void readFromCsvFile(String file) throws IOException {
        loadFile = file;
        if(firstTime){
            firstTime = false;
            Date date = new Date();
            dateOfCreating = date.toString();
        } else {
            Date date = new Date();
            changeList.add(date.toString());
        }
        Reader inputStreamReader = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);

        int data = inputStreamReader.read();
        while(data != -1) {
            readObject(inputStreamReader, (char)data);
            data = inputStreamReader.read();
        }

        inputStreamReader.close();
    }

    public CarService readFromCsvFile(File file) throws IOException {
        Reader inputStreamReader = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);

        int data = inputStreamReader.read();
        while(data != -1) {
            readObject(inputStreamReader, (char)data);
            data = inputStreamReader.read();
        }

        inputStreamReader.close();
        return this;
    }

    private void readObjectByUser(Reader inputStreamReader, char firstChar) throws IOException {
        Car car = new Car();

        int data = inputStreamReader.read();
        String dataObject = Character.toString(firstChar);
        while(data != -1 && data != 10) {
            char theChar = (char) data;
            dataObject += Character.toString(theChar);
            data = inputStreamReader.read();
        }
        String[] objectsOld = dataObject.split(",");
        if(objectsOld[0].equals(user)){

            String[] objects = new String[objectsOld.length-1];

            for(int i = 1; i < objectsOld.length; i++){
                objects[i-1] = objectsOld[i];
            }
            try {
                car.setName(objects[0]);
                car.setProperty(objects[1]);
            } catch (Exception e){}
            try{
                car.setWheelBottomLeft(Boolean.valueOf(objects[2]), Integer.parseInt(objects[3]), Integer.parseInt(objects[4]));
            } catch (Exception e){}
            try{
                car.setWheelBottomRight(Boolean.valueOf(objects[5]), Integer.parseInt(objects[6]), Integer.parseInt(objects[7]));
            } catch (Exception e){}
            try{
                car.setWheelTopLeft(Boolean.valueOf(objects[8]), Integer.parseInt(objects[9]), Integer.parseInt(objects[10]));
            } catch (Exception e){}
            try{
                car.setWheelTopRight(Boolean.valueOf(objects[11]), Integer.parseInt(objects[12]), Integer.parseInt(objects[13]));
            } catch (Exception e){}
            try{
                car.setBrake(Boolean.valueOf(objects[14]), Integer.parseInt(objects[15]), Integer.parseInt(objects[16]));
            } catch (Exception e){}
            try{
                car.setCabin(Boolean.valueOf(objects[17]), Integer.parseInt(objects[18]), Integer.parseInt(objects[19]));
            } catch (Exception e){}
            try{
                car.setEngine(Boolean.valueOf(objects[20]), Integer.parseInt(objects[21]), Integer.parseInt(objects[22]));
            } catch (Exception e){}


            addCar(car);
        }
    }

    private void readObject(Reader inputStreamReader, char firstChar) throws IOException {
        Car car = new Car();

        int data = inputStreamReader.read();
        String dataObject = Character.toString(firstChar);
        while(data != -1 && data != 10) {
            char theChar = (char) data;
            dataObject += Character.toString(theChar);
            data = inputStreamReader.read();
        }
        String[] objects = dataObject.split(",");

        try {
            car.setName(objects[0]);
            car.setProperty(objects[1]);
        } catch (Exception e){}
        try{
            car.setWheelBottomLeft(Boolean.valueOf(objects[2]), Integer.parseInt(objects[3]), Integer.parseInt(objects[4]));
        } catch (Exception e){}
        try{
            car.setWheelBottomRight(Boolean.valueOf(objects[5]), Integer.parseInt(objects[6]), Integer.parseInt(objects[7]));
        } catch (Exception e){}
        try{
            car.setWheelTopLeft(Boolean.valueOf(objects[8]), Integer.parseInt(objects[9]), Integer.parseInt(objects[10]));
        } catch (Exception e){}
        try{
            car.setWheelTopRight(Boolean.valueOf(objects[11]), Integer.parseInt(objects[12]), Integer.parseInt(objects[13]));
        } catch (Exception e){}
        try{
            car.setBrake(Boolean.valueOf(objects[14]), Integer.parseInt(objects[15]), Integer.parseInt(objects[16]));
        } catch (Exception e){}
        try{
            car.setCabin(Boolean.valueOf(objects[17]), Integer.parseInt(objects[18]), Integer.parseInt(objects[19]));
        } catch (Exception e){}
        try{
            car.setEngine(Boolean.valueOf(objects[20]), Integer.parseInt(objects[21]), Integer.parseInt(objects[22]));
        } catch (Exception e){}

        addCar(car);
    }

    public void readObjectFromJson(String json) throws IOException {
        Car car = new Car();

        String[] objects = json.split(",");

        try {
            car.setName(objects[0]);
            car.setProperty(objects[1]);
        } catch (Exception e){}
        try{
            car.setWheelBottomLeft(Boolean.valueOf(objects[2]), Integer.parseInt(objects[3]), Integer.parseInt(objects[4]));
        } catch (Exception e){}
        try{
            car.setWheelBottomRight(Boolean.valueOf(objects[5]), Integer.parseInt(objects[6]), Integer.parseInt(objects[7]));
        } catch (Exception e){}
        try{
            car.setWheelTopLeft(Boolean.valueOf(objects[8]), Integer.parseInt(objects[9]), Integer.parseInt(objects[10]));
        } catch (Exception e){}
        try{
            car.setWheelTopRight(Boolean.valueOf(objects[11]), Integer.parseInt(objects[12]), Integer.parseInt(objects[13]));
        } catch (Exception e){}
        try{
            car.setBrake(Boolean.valueOf(objects[14]), Integer.parseInt(objects[15]), Integer.parseInt(objects[16]));
        } catch (Exception e){}
        try{
            car.setCabin(Boolean.valueOf(objects[17]), Integer.parseInt(objects[18]), Integer.parseInt(objects[19]));
        } catch (Exception e){}
        try{
            car.setEngine(Boolean.valueOf(objects[20]), Integer.parseInt(objects[21]), Integer.parseInt(objects[22]));
        } catch (Exception e){}

        addCar(car);
    }

    /**
     * Удаляет все объекты из HashTable cars, которые выше входного параметра
     * @param car
     */
    public void removeGreater(Car car){

        cars = cars
                .entrySet()
                .stream()
                .filter(e -> car.compareTo(e.getValue()) == -1)
                .collect(Collectors.toConcurrentMap(ConcurrentHashMap.Entry::getKey, ConcurrentHashMap.Entry::getValue));
        /*
        for(String str : cars.keySet()){
            if(car.compareTo(cars.get(str)) == -1){
                cars.remove(str);
            }
        }
        */
    }

    /**
     * Удаляет все объекты из HashTable cars, котоыре равны входному параметру
     * @param car
     */
    public void removeAll(Car car){
        cars = cars
                .entrySet()
                .stream()
                .filter(e -> car.compareTo(e.getValue()) != 0)
                .collect(Collectors.toConcurrentMap(Map.Entry::getKey, Map.Entry::getValue));
        /*
        for(String str : cars.keySet()){
            if(car.compareTo(cars.get(str)) == 0){
                cars.remove(str);
            }
        }
        */
    }

    /**
     * Удаление по ключу, который передается как входной параметр, из HashTable cars
     * @param key
     */
    public void remove(String key){
        cars.remove(key);
    }

    /**
     * Вставляет по ключу key значение car, передаваемые как входные параметры, в HashTable cars
     * @param key
     * @param car
     */
    public void insert(String key, Car car) {
        addCar(car);
    }

    /**
     * Выводит на экран описание объектов HashTable cars
     */
    public void show(){
        clearWayOut();
        addToWayOut("Ваши машины: ");
        cars.forEach((k, v) -> addToWayOut(v.toString()));
        /*
        for(String str : cars.keySet()){
            addToWayOut(cars.get(str).toString());
        }
        */
    }

    /**
     * Загружает из loadFile новый набор HashTable cars
     * @throws IOException
     */
    public void load() throws IOException {
        cars.clear();
        readFromCsvFile(loadFile);
    }

    /**
     * Выводит на экран описание HashTable cars
     */
    public void info(){
        clearWayOut();
        addToWayOut("Тип коллекции: ConcurrentHashMap");
        addToWayOut("Ключи - строки-названия машины");
        addToWayOut("Значения - машины");
        addToWayOut("Количество элементов: " + cars.size());
        wayOut += "\nВаши машины: \n";
        cars.forEach((k, v) -> addToWayOut( "\tНазвание: " + v.getName() + " Состояние: " + v.getState() + "%"));

        addToWayOut("");
        addToWayOut("Создан: " + dateOfCreating);
        addToWayOut("Изменялся " + changeList.size() + " раз");
        for(int i = 0; i < changeList.size(); i++){
            addToWayOut("Дата изменения #" + i + ": " + changeList.get(i));
        }
    }

    private String getAllCarsStringObject(){
        String carsStringObject = "";
        for(String str : cars.keySet()){
            Car car = cars.get(str);
            carsStringObject += car.getName() + ",";
            carsStringObject += car.getProperty() + ",";
            for(Details detail : car.getDetails()){
                carsStringObject += detail.getIsSkiilNeed() + ",";
                carsStringObject += detail.getDegree_of_breakage() + ",";
                carsStringObject += detail.getQuality() + ",";
            }
            carsStringObject = carsStringObject.substring(0, carsStringObject.length()-1);
            carsStringObject += "\n";
        }
        return carsStringObject;
    }

    public void setConsole(Console console) {
        this.console = console;
    }

    private void addToWayOut(String str){
        wayOut += str + "\n";
    }

    public void buildSaveJson(){
        addToWayOut(getAllCarsStringObject());
    }

    public void buildTable(){
        ArrayList<Car> cars = PostgreSQL.getCarsNonUser("");
        for(Car car : cars){
            Details[] details = car.getDetails();
            addToWayOut(
                    car.getOwner() + "," + car.getName() + "," +
                    car.getProperty() + "," + car.getLocalDateTime() + "," + car.getCostForRepair() + "," +
                            details[6].getIsSkiilNeed() + "," + details[6].getDegree_of_breakage() + "," + details[6].getQuality() + "," +
                            details[4].getIsSkiilNeed() + "," + details[4].getDegree_of_breakage() + "," + details[4].getQuality() + "," +
                            details[5].getIsSkiilNeed() + "," + details[5].getDegree_of_breakage() + "," + details[5].getQuality() + "," +
                            details[1].getIsSkiilNeed() + "," + details[1].getDegree_of_breakage() + "," + details[1].getQuality() + "," +
                            details[0].getIsSkiilNeed() + "," + details[0].getDegree_of_breakage() + "," + details[0].getQuality() + "," +
                            details[3].getIsSkiilNeed() + "," + details[3].getDegree_of_breakage() + "," + details[3].getQuality() + "," +
                            details[2].getIsSkiilNeed() + "," + details[2].getDegree_of_breakage() + "," + details[2].getQuality()
            );
        }
    }

    private void addToWayOutWithoutN(String str){
        wayOut += str;
    }

    public void clearWayOut(){
        wayOut = "";
    }

    public String getWayOut() {
        return wayOut;
    }

    public Map<String, Car> sortByName(){
        cars = cars
                .entrySet()
                .stream()
                .sorted((e1, e2) -> {
                    if(e1.getValue().getName().length() > e1.getValue().getName().length())
                        return 1;
                    if(e1.getValue().getName().length() == e1.getValue().getName().length())
                        return 0;
                    return -1;
                })
                .collect(Collectors.toConcurrentMap(Map.Entry::getKey, Map.Entry::getValue));
        return cars;
    }

    public static void printTestParse(Car car){

        System.out.println("Name: " + car.getName());
        System.out.println("Property: " + car.getProperty());

        for(Details detail: car.getDetails()){

            System.out.println("Name of detail: " + detail.getName());
            System.out.println("Is skill need: " + detail.getIsSkiilNeed());
            System.out.println("Degree of breakage: " + detail.getDegree_of_breakage());
            System.out.println("Quality: " + detail.getQuality());
        }
    }

    public void setUser(String user) {
        this.user = user;
    }
    
    public void writeByUser(String user){
        new Thread(() -> {
            for(Car car : cars.values()){
                if(car.getY() > -1 && car.getX() > -1)
                    Server.getPositions().add(car.getX() + "-" + car.getY());
            }
            for(Car car : cars.values()){
                Details[] details = car.getDetails();
                if (car.getY() == -1 || car.getX() == -1){
                    boolean exit = false;
                    for(int y = 1; y < 1000000; y++){
                        for(int x = 1; x < 1000000; x++){
                            if(!Server.getPositions().contains(x + "-" + y)){
                                exit = true;
                                car.setY(y);
                                car.setX(x);
                            }
                            if (exit){
                                break;
                            }
                        }
                        if(exit){
                            break;
                        }
                    }
                }
                PostgreSQL.insertCar(user,
                        car.getName(),
                        car.getProperty(),
                        String.valueOf(car.getLocalDateTime()),
                        (int)car.getCostForRepair(),
                        car.getX(),
                        car.getY(),
                        car.getId(),
                        details[0].getIsSkiilNeed(),
                        details[0].getDegree_of_breakage(),
                        details[0].getQuality(),
                        details[1].getIsSkiilNeed(),
                        details[1].getDegree_of_breakage(),
                        details[1].getQuality(),
                        details[2].getIsSkiilNeed(),
                        details[2].getDegree_of_breakage(),
                        details[2].getQuality(),
                        details[3].getIsSkiilNeed(),
                        details[3].getDegree_of_breakage(),
                        details[3].getQuality(),
                        details[4].getIsSkiilNeed(),
                        details[4].getDegree_of_breakage(),
                        details[4].getQuality(),
                        details[5].getIsSkiilNeed(),
                        details[5].getDegree_of_breakage(),
                        details[5].getQuality(),
                        details[6].getIsSkiilNeed(),
                        details[6].getDegree_of_breakage(),
                        details[6].getQuality());
            }
        }).start();
    }

    public void writeByNonUser(ArrayList<String> users){
        for(String user : users){
            for(Car car : cars.values()){
                if(car.getOwner().equals(user)){
                    Details[] details = car.getDetails();
                    PostgreSQL.insertCar(user,
                            car.getName(),
                            car.getProperty(),
                            String.valueOf(car.getLocalDateTime()),
                            (int)car.getCostForRepair(),
                            car.getX(),
                            car.getY(),
                            car.getId(),
                            details[0].getIsSkiilNeed(),
                            details[0].getDegree_of_breakage(),
                            details[0].getQuality(),
                            details[1].getIsSkiilNeed(),
                            details[1].getDegree_of_breakage(),
                            details[1].getQuality(),
                            details[2].getIsSkiilNeed(),
                            details[2].getDegree_of_breakage(),
                            details[2].getQuality(),
                            details[3].getIsSkiilNeed(),
                            details[3].getDegree_of_breakage(),
                            details[3].getQuality(),
                            details[4].getIsSkiilNeed(),
                            details[4].getDegree_of_breakage(),
                            details[4].getQuality(),
                            details[5].getIsSkiilNeed(),
                            details[5].getDegree_of_breakage(),
                            details[5].getQuality(),
                            details[6].getIsSkiilNeed(),
                            details[6].getDegree_of_breakage(),
                            details[6].getQuality());
                }
            }
        }
    }

    public void readByUser(String user){
        ArrayList<Car> cars = PostgreSQL.getCars(user);
        for(Car car : cars){
            addCar(car);
        }
    }

    public void readByNonUser(ArrayList<String> users){
        for(String user : users){
            ArrayList<Car> cars = PostgreSQL.getCarsNonUser(user);
            for(Car car : cars){
                addCar(car);
            }
        }
    }

    public void popByNonUser(ArrayList<String> users){
        for(String user : users){
            ArrayList<Car> cars = PostgreSQL.pop(user);
            for(Car car : cars){
                addCar(car);
            }
        }
    }
}