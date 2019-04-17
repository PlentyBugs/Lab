package Lab.Windows;

import javax.swing.*;

public class MenuButton extends JButton {

    private CarWindow carWindow;
    private String name;
    private String command;

    public CarWindow getCarWindow() {
        return carWindow;
    }

    @Override
    public void setName(String name) {
        this.name = name;
        switch (name){
            case "Вставить": carWindow = new CarWindow(this).setIsVisible(false); break;
            case "Удалить равные": carWindow = new CarWindow(this).setIsVisible(false); break;
            case "Удалить по ключу": carWindow = new CarWindow(this).setIsVisible(false); break;
            case "Удалить превышающие": carWindow = new CarWindow(this).setIsVisible(false); break;
        }
    }

    @Override
    public String getName() {
        return name;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }
}
