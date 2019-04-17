package Lab.Live;

import Lab.Exceptions.IsNakedException;
import Lab.Things.Thing;
import Lab.Things.ThingAction;
import Lab.Windows.Console;

import java.util.ArrayList;

public class Person{

    protected String name;
    protected int lvl;
    protected String profession;
    protected int profession_lvl;
    protected Sex sex;
    protected int exp;
    protected int need_exp_to_next_lvl;
    protected String race;
    protected Console console;

    protected ArrayList<Thing> inventory = new ArrayList<Thing>();
    protected ArrayList<Thing> clothes = new ArrayList<Thing>();
    protected ArrayList<PersonAction> actions = new ArrayList<PersonAction>();
    protected ArrayList<PersonAction> shortStackOfLessNeededActions = new ArrayList<PersonAction>();
    protected ArrayList<PersonAction> ShortStackOfMoreNeededActions = new ArrayList<PersonAction>();

    public void addThings(Thing... things){
        for(Thing thing : things){
            inventory.add(thing);
        }
    }

    public void addClothes(Thing... things){
        for(Thing clothe : things){
            clothes.add(clothe);
        }
    }

    public void addActions(PersonAction... personActions){
        for(PersonAction personAction : personActions){
            actions.add(personAction);
        }
    }

    public void addLessNeededActions(PersonAction... personActions){
        for(PersonAction personAction : personActions){
            shortStackOfLessNeededActions.add(personAction);
        }
    }

    public void addMoreNeededActions(PersonAction... personActions){
        for(PersonAction personAction : personActions){
            ShortStackOfMoreNeededActions.add(personAction);
        }
    }

    public void useLessMoreNeededActions(){
        console.writeToConsole("Не столько");
        useLessNeededAction();
        console.writeToConsole("Сколько");
        useMoreNeededAction();
        console.writeToConsole("");
    }

    public void describeClothes() throws IsNakedException {
        if (clothes.size() == 0){
            throw new IsNakedException(name + " голый");
        }
        console.writeToConsole("На " + name + " надеты:");
        for(Thing clothe : clothes){
            console.writeToConsole("\t" + clothe.getName() + " " + clothe.getProperty());
        }
    }

    Person(String name, String skill, Sex sex){
        this.name = name;
        this.profession = skill;
        lvl = 0;
        profession_lvl = 0;
        this.sex = sex;
        exp = 0;
        need_exp_to_next_lvl = 10;
    }

    public String getName(){
        return name;
    }

    public void useAction(String ... actionNames){
        for (String action : actionNames){
            for (PersonAction perAct : actions){
                if (perAct.getName().equals(action)){
                    console.writeToConsoleNoN(name);
                    perAct.run();
                }
            }
        }
    }

    public void useAction(){
        for (PersonAction perAct : actions){
            console.writeToConsoleNoN(name);
            perAct.run();
        }
    }

    public void useLessNeededAction(){
        for (PersonAction perAct : shortStackOfLessNeededActions){
            console.writeToConsoleNoN(name);
            perAct.run();
        }
    }

    public void useMoreNeededAction(){
        for (PersonAction perAct : ShortStackOfMoreNeededActions){
            console.writeToConsoleNoN(name);
            perAct.run();
        }
    }

    public void useAction(Sex sex, String property, String operand, String ... actionNames){
        if (sex != Sex.SPECIAL) {
            console.writeToConsoleNoN(name);
        }
        for (String action : actionNames){
            for (PersonAction perAct : actions){
                if (perAct.getName().equals(action)){
                    perAct.setSex(sex);
                    if (!operand.equals("")) {
                        perAct.setOperand(" " + operand);
                    }
                    if (!property.equals("")) {
                        perAct.setProperty(" " + property);
                    }
                    perAct.run();
                }
            }
        }
    }

    public void useAction(Sex sex, String property, String operand, boolean reverse, String ... actionNames){
        console.writeToConsoleNoN(name);
        for (String action : actionNames){
            for (PersonAction perAct : actions){
                if (perAct.getName().equals(action)){
                    perAct.setSex(sex);
                    if (!operand.equals("")) {
                        perAct.setOperand(" " + operand);
                    }
                    if (!property.equals("")) {
                        perAct.setProperty(" " + property);
                    }
                    if (reverse){
                        perAct.setReverseAction();
                    }
                    perAct.run();
                }
            }
        }
    }

    public void levelUp(){

        class VeryUsefulLocalClass{
            int returnOne(){
                return 1;
            }
        }

        VeryUsefulLocalClass veryUsefulLocalClass = new VeryUsefulLocalClass();
        while (exp >= need_exp_to_next_lvl){
            lvl += veryUsefulLocalClass.returnOne();
            profession_lvl += veryUsefulLocalClass.returnOne();
            console.writeToConsole(name + " повысил уровень(" + Integer.toString(lvl-1) + "->" + Integer.toString(lvl) + ") и прокачал основной скилл(" + Integer.toString(profession_lvl-1) + "->" + Integer.toString(profession_lvl) + ")");
            need_exp_to_next_lvl += lvl*12 + profession_lvl*5;
        }
    }

    public void useThingAction(String actonName, String property, String operand, Thing... things){
        String thingList = "";
        int counter = 0;
        for (Thing thing : things){
            if (inventory.contains(thing) || clothes.contains(thing)){
                for (ThingAction thAct : thing.getActions()){
                    if (thAct.getName().equals(actonName)) {
                        thingList += thing.getName() + " и ";
                        counter ++;
                        break;
                    }
                }
            }
        }
        thingList = thingList.substring(0, thingList.length()-3);
        if (counter > 0){
            console.writeToConsoleNoN(thingList);
            if (counter > 1){
                things[0].useAction(Sex.SPECIAL, property, operand, actonName);
            } else {
                things[0].useAction(property, operand, actonName);
            }
        }
    }

    public int getExp(){
        return exp;
    }

    public void addExp(int count){
        exp += count;
        levelUp();
    }

    public int getLvl(){
        return lvl;
    }

    public int getProfessionLvl(){
        return profession_lvl;
    }

    public String getProfession(){
        return profession;
    }

    public String getRacePlayer(){
        return "персонаж расы: " + race;
    }

    public String getRace(){
        return race;
    }

    public void setConsole(Console console){
        this.console = console;
    }

    public ArrayList<PersonAction> getActions() {
        return actions;
    }
}
