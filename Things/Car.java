package Lab.Things;

import java.io.Serializable;
import java.time.DateTimeException;
import java.time.LocalDateTime;

public class Car extends Thing implements Comparable<Car>, Serializable {
    protected String name;
    protected String property;
    protected Details[] details;
    // cost in rubles
    protected double costForRepair;
    protected String owner;
    protected int x;
    protected int id;
    protected int y;
    protected LocalDateTime localDateTime;

    public Car(){
        super("автомобиль");
        property = "";
        x = -1;
        y = -1;
        name = super.name;
        details = new Details[]{new Wheel(false, 2, 20),
                new Wheel(false, (int) (Math.random() * 15), (int) (Math.random() * 100)),
                new Wheel(false, (int)(Math.random() * 15), (int)(Math.random() * 100)),
                new Wheel(false, (int)(Math.random() * 15), (int)(Math.random() * 100)),
                new Brake(true, (int)(Math.random() * 15), (int)(Math.random() * 100)),
                new Cabin(false, (int)(Math.random() * 15), (int)(Math.random() * 100)),
                new Engine(true, (int)(Math.random() * 15), (int)(Math.random() * 100))};
        costForRepair = 7*7*100*15*15 - getFullDegreeOfBreakage()*getFullDegreeOfBreakage()*getAverageQuality();
        localDateTime = LocalDateTime.now();
    }

    public Car(String name){
        super(name);
        name = super.name;
        property = "";
    }

    public Car(String name, String property){
        super(name);
        this.name = super.name;
        this.property = property;
    }

    @Override
    public int compareTo(Car o) {
        if(name.equals(o.getName())
                && property.equals(o.getProperty())
                && costForRepair == o.getCostForRepair()
                && localDateTime.equals(o.getLocalDateTime())){
            return 0;
        }
        if(name.length() > o.getName().length()){
            return -1;
        }
        if(name.length() < o.getName().length()){
            return 1;
        }
        return 1;
    }

    private static class Brake extends Details{

        Brake(boolean isSkillNeed, int degree_of_breakage, int quality){
            this.degree_of_breakage = degree_of_breakage;
            this.quality = quality;
            this.isSkillNeed = isSkillNeed;
            name = "tableBrake";
        }
    }

    private class Wheel extends Details{

        Wheel(boolean isSkillNeed, int degree_of_breakage, int quality){
            this.degree_of_breakage = degree_of_breakage;
            this.quality = quality;
            this.isSkillNeed = isSkillNeed;
            name = "tableWheel";
        }
    }

    private static class Engine extends Details{

        Engine(boolean isSkillNeed, int degree_of_breakage, int quality){
            this.degree_of_breakage = degree_of_breakage;
            this.quality = quality;
            this.isSkillNeed = isSkillNeed;
            name = "tableEngine";
        }
    }

    private static class Cabin extends Details{

        Cabin(boolean isSkillNeed, int degree_of_breakage, int quality){
            this.degree_of_breakage = degree_of_breakage;
            this.quality = quality;
            this.isSkillNeed = isSkillNeed;
            name = "tableCabin";
        }
    }

    // getters, setters
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name =  name;
    }
    public String getProperty(){
        return property;
    }
    public void setProperty(){
        this.property =  property;
    }

    public int getFullDegreeOfBreakage(){
        int degree = 0;
        for(Details detail : details){
            degree += detail.degree_of_breakage;
        }
        return degree;
    }

    public double getAverageDegreeOfBreakage(){
        double degree = 0.0;
        for(Details detail : details){
            degree += detail.degree_of_breakage;
        }
        return degree/details.length;
    }

    public double getAverageQuality(){
        double degree = 0.0;
        for(Details detail : details){
            degree += detail.quality;
        }
        return degree/details.length;
    }

    public Details[] getDetails() {
        return details;
    }

    public void setWheelTopLeft(boolean isSkillNeed, int deegreeOfBreakage, int quality){
        details[0] = new Wheel(isSkillNeed, deegreeOfBreakage, quality);
    }

    public void setWheelTopRight(boolean isSkillNeed, int deegreeOfBreakage, int quality){
        details[1] = new Wheel(isSkillNeed, deegreeOfBreakage, quality);
    }

    public void setWheelBottomLeft(boolean isSkillNeed, int deegreeOfBreakage, int quality){
        details[2] = new Wheel(isSkillNeed, deegreeOfBreakage, quality);
    }

    public void setWheelBottomRight(boolean isSkillNeed, int deegreeOfBreakage, int quality){
        details[3] = new Wheel(isSkillNeed, deegreeOfBreakage, quality);
    }

    public void setBrake(boolean isSkillNeed, int deegreeOfBreakage, int quality){
        details[4] = new Engine(isSkillNeed, deegreeOfBreakage, quality);
    }

    public void setCabin(boolean isSkillNeed, int deegreeOfBreakage, int quality){
        details[5] = new Cabin(isSkillNeed, deegreeOfBreakage, quality);
    }

    public void setEngine(boolean isSkillNeed, int deegreeOfBreakage, int quality){
        details[6] = new Engine(isSkillNeed, deegreeOfBreakage, quality);
    }

    public double getCostForRepair() {
        return costForRepair;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public void setLocalDateTime(String localDateTime) {
        this.localDateTime = LocalDateTime.parse(localDateTime);
    }

    public void setCostForRepair(double costForRepair) {
        this.costForRepair = costForRepair;
    }

    @Override
    public void setProperty(String property) {
        this.property = property;
    }

    public boolean equals(Car car){
        if(!car.getName().equals(name))
            return false;
        if(!car.getProperty().equals(property))
            return false;
        if(car.getDetails().length != details.length)
            return false;
        for(int i = 0; i < details.length; i++){
            if(car.getDetails()[i].getIsSkiilNeed() != details[i].isSkillNeed)
                return false;
            if(car.getDetails()[i].getDegree_of_breakage() != details[i].getDegree_of_breakage())
                return false;
            if(car.getDetails()[i].getQuality() != details[i].getQuality())
                return false;
        }
        return true;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String toString(){
        return "Название машины: " + name + "; Цена за починку: " + Integer.toString((int) costForRepair) + " рублей";
    }

    public void jsonParser(String str){
        String regex =  "[ \\[\\]{}\":,]+";
        String[] strings = str.split(regex);
        try {
            name = strings[1];
            property = strings[2];
            int numberOfDetail = 1;
            int j = 3;
            while(numberOfDetail <= details.length){
                if(numberOfDetail == 1){
                    setWheelTopRight(Boolean.valueOf(strings[j]), Integer.parseInt(strings[j+1]), Integer.parseInt(strings[j+2]));
                }
                if(numberOfDetail == 2){
                    setWheelTopLeft(Boolean.valueOf(strings[j]), Integer.parseInt(strings[j+1]), Integer.parseInt(strings[j+2]));
                }
                if(numberOfDetail == 3){
                    setWheelBottomRight(Boolean.valueOf(strings[j]), Integer.parseInt(strings[j+1]), Integer.parseInt(strings[j+2]));
                }
                if(numberOfDetail == 4){
                    setWheelBottomLeft(Boolean.valueOf(strings[j]), Integer.parseInt(strings[j+1]), Integer.parseInt(strings[j+2]));
                }
                if(numberOfDetail == 5){
                    details[details.length-3] = new Brake(Boolean.valueOf(strings[j]), Integer.parseInt(strings[j+1]), Integer.parseInt(strings[j+2]));
                }
                if(numberOfDetail == 6){
                    details[details.length-2] = new Cabin(Boolean.valueOf(strings[j]), Integer.parseInt(strings[j+1]), Integer.parseInt(strings[j+2]));
                }
                if(numberOfDetail == 7){
                    details[details.length-1] = new Engine(Boolean.valueOf(strings[j]), Integer.parseInt(strings[j+1]), Integer.parseInt(strings[j+2]));
                }
                numberOfDetail ++;
                j += 3;
            }
        }catch (Exception e){}

        // Use to check
        // CarService.printTestParse(this);
    }

    public void setX(int x) {
        this.x = x;
    }

    public String toJson(){
        String carsStringObject = "{";
        carsStringObject += name + ",";
        carsStringObject += property + ",";
        for(Details detail : details){
            carsStringObject += detail.getIsSkiilNeed() + ",";
            carsStringObject += detail.getDegree_of_breakage() + ",";
            carsStringObject += detail.getQuality() + ",";
        }
        carsStringObject = carsStringObject.substring(0, carsStringObject.length()-1);
        carsStringObject += "}";
        return carsStringObject;
    }

    public String getState(){
        return Integer.toString((int)getAverageQuality());
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
