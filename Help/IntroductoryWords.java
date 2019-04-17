package Lab.Help;

import Lab.Windows.Console;

public class IntroductoryWords implements IntrpductoryWordsAction {
    private Console console;
    public void lessThanOneHour(){console.writeToConsole("Не прошло и часа,");
    }

    public void asSee(){
        console.writeToConsole("Как видно,");
    }

    public void thatTime(){
        console.writeToConsole("В это время");
    }

    public void setConsole(Console console) {
        this.console = console;
    }
}
