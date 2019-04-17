package Lab.Things;

import Lab.Live.Sex;
import Lab.Windows.Console;

import java.util.ArrayList;

public class Thing {
    protected String property;
    protected ArrayList<ThingAction> actions = new ArrayList<ThingAction>();
    protected String name;
    protected Console console;

    public Thing(String name){
        this.name = name;
    }

    public void addActions(ThingAction... thingActions){
        for(ThingAction thingAction : thingActions){
            actions.add(thingAction);
        }
    }

    public String getName(){
        return name;
    }

    public void useAction(String ... actionNames){
        for (String action : actionNames){
            for (ThingAction thAct : actions){
                if (thAct.getName().equals(action)){
                    console.writeToConsoleNoN(name);
                    thAct.run();
                }
            }
        }
    }

    public void useAction(){
        for (ThingAction thAct : actions){
            console.writeToConsoleNoN(name);
            thAct.run();
        }
    }


    public void useAction(Sex sex, String property, String operand, String ... actionNames){
        console.writeToConsoleNoN(name);
        for (String action : actionNames){
            for (ThingAction thAct : actions){
                if (thAct.getName().equals(action)){
                    thAct.setSex(sex);
                    if (!operand.equals("")) {
                        thAct.setOperand(" " + operand);
                    }
                    if (!property.equals("")) {
                        thAct.setProperty(" " + property);
                    }
                    thAct.run();
                }
            }
        }
    }

    public void setProperty(String property){
        this.property = property;
    }

    public ArrayList<ThingAction> getActions() {
        return actions;
    }

    public String getProperty() {
        return property;
    }

    public void setConsole(Console console) {
        this.console = console;
    }
}
