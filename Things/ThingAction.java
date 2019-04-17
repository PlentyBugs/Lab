package Lab.Things;

import Lab.Live.Sex;
import Lab.Windows.Console;

public abstract class ThingAction {

    protected String name;
    protected String property;
    protected String operand;
    protected Sex sex;
    protected Console console;
    protected String action;

    ThingAction(Sex sex){
        this.sex = sex;
    }
    ThingAction(String operand, Sex sex){
        this.operand = " " + operand;
        this.sex = sex;
    }
    ThingAction(String property, String operand, Sex sex){
        this.property = " " + property;
        this.operand = " " + operand;
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

    public static class Stay extends ThingAction{
        Stay(Sex sex){
            super(sex);
            property = "";
            operand = "";
            action = "стоял";
            name = "Стоять";
        }
        Stay(String operand, Sex sex){
            super(operand, sex);
            property = "";
            action = "стоял";
            name = "Стоять";
        }
        public Stay(String property, String operand, Sex sex){
            super(property, operand, sex);
            action = "стоял";
            name = "Стоять";
        }
    }

    public static class Hide extends ThingAction{
        Hide(Sex sex){
            super(sex);
            property = "";
            operand = "";
            action = "скрывается";
            name = "Скрываться";
        }
        Hide(String operand, Sex sex){
            super(operand, sex);
            property = "";
            action = "скрывается";
            name = "Скрываться";
        }
        public Hide(String property, String operand, Sex sex){
            super(property, operand, sex);
            action = "скрывается";
            name = "Скрываться";
        }
    }

    public static class Seem extends ThingAction{
        public Seem(Sex sex){
            super(sex);
            property = "";
            operand = "";
            action = "казалось";
            name = "Казаться";
        }
        Seem(String operand, Sex sex){
            super(operand, sex);
            property = "";
            action = "казалось";
            name = "Казаться";
        }
        Seem(String property, String operand, Sex sex){
            super(property, operand, sex);
            action = "казалось";
            name = "Казаться";
        }
    }

    public static class StickOut extends ThingAction{
        StickOut(Sex sex){
            super(sex);
            property = "";
            operand = "";
            action = "торчал";
            name = "Торчать";
        }
        StickOut(String operand, Sex sex){
            super(operand, sex);
            property = "";
            action = "торчал";
            name = "Торчать";
        }
        public StickOut(String property, String operand, Sex sex){
            super(property, operand, sex);
            action = "торчал";
            name = "Торчать";
        }
    }

    public static class Work extends ThingAction{
        public Work(Sex sex){
            super(sex);
            property = "";
            operand = "";
            action = "работал";
            name = "Работать";
        }
        Work(String operand, Sex sex){
            super(operand, sex);
            property = "";
            action = "работал";
            name = "Работать";
        }
        Work(String property, String operand, Sex sex){
            super(property, operand, sex);
            action = "работал";
            name = "Работать";
        }
    }

    public String getName(){
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

    public ThingAction setConsole(Console console) {
        this.console = console;
        return this;
    }
}
