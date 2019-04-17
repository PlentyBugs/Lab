package Lab.Locations;

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
    private static int X;
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
        X++;
        car.setX(X);
        cars.put(car.getName(), car);
    }

    public void readFromCsvFileByNonUser(String file, ArrayList<String> names) throws IOException {
        loadFile = file;
        Reader inputStreamReader = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);

        int data = inputStreamReader.read();
        while(data != -1) {
            readObjectByNonUser(inputStreamReader, (char)data, names);
            data = inputStreamReader.read();
        }

        inputStreamReader.close();
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

    private void readObjectByNonUser(Reader inputStreamReader, char firstChar, ArrayList<String> names) throws IOException {
        Car car = new Car();

        int data = inputStreamReader.read();
        String dataObject = Character.toString(firstChar);
        while(data != -1 && data != 10) {
            char theChar = (char) data;
            dataObject += Character.toString(theChar);
            data = inputStreamReader.read();
        }
        String[] objectsOld = dataObject.split(",");
        car.setOwner(objectsOld[0]);
        if(!names.contains(objectsOld[0])){

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

    public void writeToCSVFile(String file) {

        try(FileOutputStream out = new FileOutputStream(loadFile);
            BufferedOutputStream bos = new BufferedOutputStream(out)){
            byte[] buffer = getAllCarsStringObject().getBytes();
            bos.write(buffer, 0, buffer.length);
            bos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToCSVFileByUser(String file, String user) {

        StringBuilder sb = new StringBuilder();

        try (BufferedReader br = Files.newBufferedReader(Paths.get(file))) {

            String line;
            while ((line = br.readLine()) != null) {
                if(line.length() > user.length() && !line.substring(0, user.length()).equals(user)){
                    sb.append(line).append("\n");
                }
            }

        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }

        String data =  String.valueOf(sb);

        try(FileOutputStream out = new FileOutputStream(file);
            BufferedOutputStream bos = new BufferedOutputStream(out)){
            byte[] buffer = (data + getAllCarsStringObjectByUser(user)).getBytes();
            bos.write(buffer, 0, buffer.length);
            bos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToCSVFileByNonUser(String file, ArrayList<String> names) {

        StringBuilder sb = new StringBuilder();
        ArrayList<String> users = new ArrayList<>();

        try (BufferedReader br = Files.newBufferedReader(Paths.get(file))) {

            String line;
            while ((line = br.readLine()) != null) {
                for(String user : names){
                    if(line.substring(0, user.length()).equals(user)){
                        sb.append(line).append("\n");
                    } else {
                        users.add(user);
                    }
                }
            }

        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }

        String data =  String.valueOf(sb);

        try(FileOutputStream out = new FileOutputStream(file);
            BufferedOutputStream bos = new BufferedOutputStream(out)){
            byte[] buffer = (data + getAllCarsStringObjectByNonUser()).getBytes();
            bos.write(buffer, 0, buffer.length);
            bos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToCSVFile(){

        try(FileOutputStream out = new FileOutputStream(loadFile);
            BufferedOutputStream bos = new BufferedOutputStream(out)){
            byte[] buffer = getAllCarsStringObject().getBytes();
            bos.write(buffer, 0, buffer.length);
            bos.flush();
        } catch (IOException e) {
            e.printStackTrace();
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

    private String getAllCarsStringObjectByUser(String user){
        String carsStringObject = "";
        for(String str : cars.keySet()){
            Car car = cars.get(str);
            carsStringObject += user + ",";
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

    private String getAllCarsStringObjectByNonUser(){
        String carsStringObject = "";
        for(String str : cars.keySet()){
            Car car = cars.get(str);
            carsStringObject += car.getOwner() + ",";
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
}