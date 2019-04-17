package Lab.Locations;

import Lab.Live.Sex;
import Lab.Windows.Console;

public abstract class LocationAction {

    protected String name;
    protected String property;
    protected String operand;
    protected Sex sex;
    protected String action;
    protected Console console;

    LocationAction(Sex sex){
        this.sex = sex;
    }
    LocationAction(String operand, Sex sex){
        this.operand = " " + operand;
        this.sex = sex;
    }
    LocationAction(String property, String operand, Sex sex){
        this.property = " " + property;
        this.operand = operand;
        this.sex = sex;
    }

    public void run(){
        String word_ending = "";
        switch (sex){
            case MALE:
                word_ending = "";
                break;
            case FEMALE:
                word_ending = "а";
                break;
            case NEUTRAL:
                word_ending = "о";
                break;
            case MULTI:
                word_ending = "и";
                break;
        }
        console.writeToConsole(" " + action + word_ending + property + operand);
    }

    public static class gotJealous extends LocationAction{
        gotJealous(Sex sex){
            super(sex);
            property = "";
            operand = "";
            action = "завиднел";
            name = "Завиднеться";
        }
        gotJealous(String operand, Sex sex){
            super(operand, sex);
            property = "";
            action = "завиднел";
            name = "Завиднеться";
        }
        public gotJealous(String property, String operand, Sex sex){
            super(property, operand, sex);
            action = "завиднел";
            name = "Завиднеться";
        }
    }

    String getName(){
        return name;
    }

    void setName(String name){
        this.name = name;
    }

    Sex getSex(){
        return sex;
    }

    void setSex(Sex sex){
        this.sex = sex;
    }

    String getOperand(){
        return operand;
    }

    void setOperand(String operand){
        this.operand = operand;
    }

    String getProperty(){
        return property;
    }

    void setProperty(String property){
        this.property = property;
    }

    public LocationAction setConsole(Console console) {
        this.console = console;
        return this;
    }
}


