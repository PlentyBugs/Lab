package Lab.Live;

import Lab.Exceptions.BrakeCar;
import Lab.Things.Car;
import Lab.Things.Details;
import Lab.Windows.Console;

public abstract class PersonAction {

    protected String name;
    protected Console console;
    protected String property;
    protected String operand;
    protected Sex sex;
    protected String action;

    public PersonAction(Sex sex){
        this.sex = sex;
    }
    public PersonAction(String operand, Sex sex){
        this.operand = " " + operand;
        this.sex = sex;
    }
    public PersonAction(String property, String operand, Sex sex){
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

    public static class See extends PersonAction{
        See(Sex sex){
            super(sex);
            property = "";
            operand = "";
            action = "увидел";
            name = "Видеть";
        }
        See(String operand, Sex sex){
            super(operand, sex);
            property = "";
            action = "увидел";
            name = "Видеть";
        }
        See(String property, String operand, Sex sex){
            super(property, operand, sex);
            action = "увидел";
            name = "Видеть";
        }
    }

    public static class Ride extends PersonAction{
        Ride(Sex sex){
            super(sex);
            property = "";
            operand = "";
            action = "ездил";
            name = "Ездить";
        }
        Ride(String operand, Sex sex){
            super(operand, sex);
            property = "";
            action = "ездил";
            name = "Ездить";
        }
        Ride(String property, String operand, Sex sex){
            super(property, operand, sex);
            action = "ездил";
            name = "Ездить";
        }
    }

    public static class Lay extends PersonAction{
        Lay(Sex sex){
            super(sex);
            property = "";
            operand = "";
            action = "лежал";
            name = "Лежать";
        }
        Lay(String operand, Sex sex){
            super(operand, sex);
            property = "";
            action = "лежал";
            name = "Лежать";
        }
        Lay(String property, String operand, Sex sex){
            super(property, operand, sex);
            action = "лежал";
            name = "Лежать";
        }
    }

    public static class Search extends PersonAction{
        Search(Sex sex){
            super(sex);
            property = "";
            operand = "";
            action = "искал";
            name = "Искать";
        }
        Search(String operand, Sex sex){
            super(operand, sex);
            property = "";
            action = "искал";
            name = "Искать";
        }
        Search(String property, String operand, Sex sex){
            super(property, operand, sex);
            action = "искал";
            name = "Искать";
        }
    }

    public static class GetAround extends PersonAction{
        GetAround(Sex sex){
            super(sex);
            property = "";
            operand = "";
            action = "обошел";
            name = "Обойти";
        }
        GetAround(String operand, Sex sex){
            super(operand, sex);
            property = "";
            action = "обошел";
            name = "Обойти";
        }
        GetAround(String property, String operand, Sex sex){
            super(property, operand, sex);
            action = "обошел";
            name = "Обойти";
        }
    }

    public static class Found extends PersonAction{
        Found(Sex sex){
            super(sex);
            property = "";
            operand = "";
            action = "нашел";
            name = "Найти";
        }
        Found(String operand, Sex sex){
            super(operand, sex);
            property = "";
            action = "нашел";
            name = "Найти";
        }
        Found(String property, String operand, Sex sex){
            super(property, operand, sex);
            action = "нашел";
            name = "Найти";
        }
    }

    public static class BeHappy extends PersonAction{
        BeHappy(Sex sex){
            super(sex);
            property = "";
            operand = "";
            action = "был рад";
            name = "БытьРадостным";
        }
        BeHappy(String operand, Sex sex){
            super(operand, sex);
            property = "";
            action = "был рад";
            name = "БытьРадостным";
        }
        BeHappy(String property, String operand, Sex sex){
            super(property, operand, sex);
            action = "был рад";
            name = "БытьРадостным";
        }
    }

    public static class Shake extends PersonAction{
        Shake(Sex sex){
            super(sex);
            property = "";
            operand = "";
            action = "пожимал";
            name = "Пожимать";
        }
        Shake(String operand, Sex sex){
            super(operand, sex);
            property = "";
            action = "пожимал";
            name = "Пожимать";
        }
        Shake(String property, String operand, Sex sex){
            super(property, operand, sex);
            action = "пожимал";
            name = "Пожимать";
        }
    }

    public static class Tell extends PersonAction{
        Tell(Sex sex){
            super(sex);
            property = "";
            operand = "";
            action = "рассказал";
            name = "Рассказать";
        }
        Tell(String operand, Sex sex){
            super(operand, sex);
            property = "";
            action = "рассказал";
            name = "Рассказать";
        }
        Tell(String property, String operand, Sex sex){
            super(property, operand, sex);
            action = "рассказал";
            name = "Рассказать";
        }
    }

    public static class Dive extends PersonAction{
        Dive(Sex sex){
            super(sex);
            property = "";
            operand = "";
            action = "нырнул";
            name = "Нырнуть";
        }
        Dive(String operand, Sex sex){
            super(operand, sex);
            property = "";
            action = "нырнул";
            name = "Нырнуть";
        }
        Dive(String property, String operand, Sex sex){
            super(property, operand, sex);
            action = "нырнул";
            name = "Нырнуть";
        }
    }

    public static class PoppedUp extends PersonAction{
        PoppedUp(Sex sex){
            super(sex);
            property = "";
            operand = "";
            action = "вынырнул";
            name = "Вынырнуть";
        }
        PoppedUp(String operand, Sex sex){
            super(operand, sex);
            property = "";
            action = "вынырнул";
            name = "Вынырнуть";
        }
        PoppedUp(String property, String operand, Sex sex){
            super(property, operand, sex);
            action = "вынырнул";
            name = "Вынырнуть";
        }
    }

    public static class PockedAround extends PersonAction{
        PockedAround(Sex sex){
            super(sex);
            property = "";
            operand = "";
            action = "поковырял";
            name = "Поковыряться";
        }
        PockedAround(String operand, Sex sex){
            super(operand, sex);
            property = "";
            action = "поковырял";
            name = "Поковыряться";
        }
        PockedAround(String property, String operand, Sex sex){
            super(property, operand, sex);
            action = "поковырял";
            name = "Поковыряться";
        }
    }

    public static class Inspect extends PersonAction{
        Inspect(Sex sex){
            super(sex);
            property = "";
            operand = "";
            action = "осмотрел";
            name = "Осмотреть";
        }
        Inspect(String operand, Sex sex){
            super(operand, sex);
            property = "";
            action = "осмотрел";
            name = "Осмотреть";
        }
        Inspect(String property, String operand, Sex sex){
            super(property, operand, sex);
            action = "осмотрел";
            name = "Осмотреть";
        }
    }

    public static class Release extends PersonAction{
        Release(Sex sex){
            super(sex);
            property = "";
            operand = "";
            action = "вышел";
            name = "Выйти";
        }
        Release(String operand, Sex sex){
            super(operand, sex);
            property = "";
            action = "вышел";
            name = "Выйти";
        }
        Release(String property, String operand, Sex sex){
            super(property, operand, sex);
            action = "вышел";
            name = "Выйти";
        }
    }

    public static class Kicked extends PersonAction{
        Kicked(Sex sex){
            super(sex);
            property = "";
            operand = "";
            action = "пнул";
            name = "Пнуть";
        }
        Kicked(String operand, Sex sex){
            super(operand, sex);
            property = "";
            action = "пнул";
            name = "Пнуть";
        }
        Kicked(String property, String operand, Sex sex){
            super(property, operand, sex);
            action = "пнул";
            name = "Пнуть";
        }
    }

    public static class Stay extends PersonAction{
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
        Stay(String property, String operand, Sex sex){
            super(property, operand, sex);
            action = "стоял";
            name = "Стоять";
        }
    }

    public static class Scratch extends PersonAction{
        Scratch(Sex sex){
            super(sex);
            property = "";
            operand = "";
            action = "чесал";
            name = "Чесать";
        }
        Scratch(String operand, Sex sex){
            super(operand, sex);
            property = "";
            action = "чесал";
            name = "Чесать";
        }
        Scratch(String property, String operand, Sex sex){
            super(property, operand, sex);
            action = "чесал";
            name = "Чесать";
        }
    }

    public static class Step extends PersonAction{
        Step(Sex sex){
            super(sex);
            property = "";
            operand = "";
            action = "зашагал";
            name = "Шагать";
        }
        Step(String operand, Sex sex){
            super(operand, sex);
            property = "";
            action = "зашагал";
            name = "Шагать";
        }
        Step(String property, String operand, Sex sex){
            super(property, operand, sex);
            action = "зашагал";
            name = "Шагать";
        }
    }

    public static class StuckOut extends PersonAction{
        StuckOut(Sex sex){
            super(sex);
            property = "";
            operand = "";
            action = "высунул";
            name = "Высунуть";
        }
        StuckOut(String operand, Sex sex){
            super(operand, sex);
            property = "";
            action = "высунул";
            name = "Высунуть";
        }
        StuckOut(String property, String operand, Sex sex){
            super(property, operand, sex);
            action = "высунул";
            name = "Высунуть";
        }
    }


    public static class RepairCar extends PersonAction{
        Car car_that_we_repair;
        Person person_who_repair;
        RepairCar(Sex sex, Car car_that_we_repair, Person person){
            super(sex);
            person_who_repair = person;
            this.car_that_we_repair = car_that_we_repair;
            property = "";
            operand = " " + car_that_we_repair.getName();
            action = "чинит";
            name = "Чинить";
        }
        RepairCar(String operand, Sex sex, Car car_that_we_repair, Person person){
            super(operand, sex);
            person_who_repair = person;
            this.car_that_we_repair = car_that_we_repair;
            property = "";
            action = "чинит";
            name = "Чинить";
        }
        RepairCar(String property, String operand, Sex sex, Car car_that_we_repair, Person person){
            super(property, operand, sex);
            person_who_repair = person;
            this.car_that_we_repair = car_that_we_repair;
            action = "чинит";
            name = "Чинить";
        }

        public void run(){
            for (Details obj : car_that_we_repair.getDetails()) {
                console.writeToConsole(person_who_repair.getName() + " " + action + property + operand);
                console.writeToConsole(person_who_repair.getName() + " приступил к части " + obj.getName());
                if(obj.getQuality() < 100){
                    if (person_who_repair.profession_lvl >= obj.getDegree_of_breakage() - 2) {
                        if(obj.getIsSkiilNeed()){
                            if (person_who_repair.profession.equals(obj.getSkillNeed())) {
                                obj.setQuality(person_who_repair.profession_lvl + 4 - obj.getDegree_of_breakage());
                                person_who_repair.addExp(4*person_who_repair.lvl + 5*person_who_repair.profession_lvl);
                                console.writeToConsole(person_who_repair.getName() + " получил " + Integer.toString(4*person_who_repair.lvl + 5*person_who_repair.profession_lvl) + " опыта");
                            }
                        } else {
                            obj.setQuality(person_who_repair.profession_lvl + 6 - obj.getDegree_of_breakage());
                            person_who_repair.addExp(3*person_who_repair.lvl + 2*person_who_repair.profession_lvl);
                            console.writeToConsole(person_who_repair.getName() + " получил " + Integer.toString(3*person_who_repair.lvl + 2*person_who_repair.profession_lvl) + " опыта");
                        }
                    } else {
                        person_who_repair.addExp(2*person_who_repair.lvl + person_who_repair.profession_lvl);
                        console.writeToConsole(person_who_repair.getName() + " получил " + Integer.toString(2*person_who_repair.lvl + person_who_repair.profession_lvl) + " опыта");
                    }
                }
                if (obj.getQuality() < 0){
                    throw new BrakeCar();
                }
            }
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

    void setReverseAction(){
        action = "не " + action;
    }

    public String getAction() {
        return action;
    }

    public PersonAction setConsole(Console console) {
        this.console = console;
        return this;
    }
}
