package Lab.Locations;

import Lab.Live.Person;
import Lab.Live.PersonAction;
import Lab.Live.Sex;
import Lab.Things.Thing;
import Lab.Windows.Console;

import java.util.ArrayList;

public class Location {
    protected String name;
    protected Console console;

    protected ArrayList<Location> inner_Locations = new ArrayList<Location>();
    protected ArrayList<LocationAction> locationActions = new ArrayList<LocationAction>();
    protected ArrayList<Person> inner_Persons = new ArrayList<Person>();
    protected ArrayList<Thing> inner_Things = new ArrayList<Thing>();

    public void addLocations(Location ... locations){
        for(Location loc : locations){
            inner_Locations.add(loc);
        }
    }

    public void addPersons(Person ... persons){
        for(Person per : persons){
            inner_Persons.add(per);
            console.writeToConsole(per.getName() + " вошел в локацию " + name);
        }
    }

    public void addAction(LocationAction... locationactions){
        for(LocationAction locAct : locationactions){
            locationActions.add(locAct);
        }
    }

    public void addThings(Thing ... things){
        for(Thing thing : things){
            inner_Things.add(thing);
        }
    }

    public void removePerson(Person ... persons){
        for(Person per : persons){
            inner_Persons.remove(per);
            console.writeToConsole(per.getName() + " вышел из локации " + name);
        }
    }

    public void moteToAnotherLocation(Location location, Person ... persons){
        for(Person person : persons){
            if(inner_Persons.contains(person)){
                removePerson(person);
                location.addPersons(person);
            }
        }
    }

    public void useAction(String ... actionNames){
        for (String action : actionNames){
            for (LocationAction locAct : locationActions){
                if (locAct.getName().equals(action)){
                    console.writeToConsoleNoN(name);
                    locAct.run();
                }
            }
        }
    }

    public void useAction(){
        for (LocationAction locAct : locationActions){
            console.writeToConsoleNoN(name);
            locAct.run();
        }
    }

    public void usePersonAction(String actonName, String property, String operand, Person ... persons){
        String personList = "";
        int counter = 0;
        for (Person person : persons){
            if (inner_Persons.contains(person)){
                for (PersonAction perAct : person.getActions()){
                    if (perAct.getName().equals(actonName)) {
                        personList += person.getName() + " и ";
                        counter ++;
                        break;
                    }
                }
            }
        }
        personList = personList.substring(0, personList.length()-3);
        if (counter > 0){
            console.writeToConsoleNoN(personList);
            if (counter > 1){
                persons[0].useAction(Sex.SPECIAL, " " + property, " " + operand, actonName);
            }
        }
    }

    public void setConsole(Console console) {
        this.console = console;
    }
}
