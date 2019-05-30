package Lab.Windows;

import Lab.ServerClient.Window.ClientWindow;
import Lab.Things.Car;

import javax.swing.*;
import java.awt.*;
import java.util.ResourceBundle;

public class DescCar extends JFrame {

    public DescCar(Car car){

        ResourceBundle bundle;
        bundle = ResourceBundle.getBundle("resource.lab4",
                ClientWindow.getLoc());

        JLabel owner = new JLabel(bundle.getString("tableOwner") + ": " + car.getOwner());
        JLabel name = new JLabel(bundle.getString("tableName") + ": " + car.getName());
        JLabel property = new JLabel(bundle.getString("tableProperty") + ": " + car.getProperty());
        JLabel cost = new JLabel(bundle.getString("tableCost") + ": " + car.getCostForRepair());
        JLabel date = new JLabel(bundle.getString("tableDate") + ": " + car.getLocalDateTime());
        JLabel position = new JLabel(bundle.getString("tablePosition") + ": " + car.getX() + "-" + car.getY());
        JLabel avQuality = new JLabel(bundle.getString("tableQuality") + ": " + car.getAverageQuality());

        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridy = 0;
        constraints.gridx = 0;
        constraints.anchor = GridBagConstraints.NORTH;

        getContentPane().add(owner, constraints);
        constraints.gridy++;
        getContentPane().add(name, constraints);
        constraints.gridy++;
        getContentPane().add(property, constraints);
        constraints.gridy++;
        getContentPane().add(cost, constraints);
        constraints.gridy++;
        getContentPane().add(date, constraints);
        constraints.gridy++;
        getContentPane().add(position, constraints);
        constraints.gridy++;
        getContentPane().add(avQuality, constraints);
        pack();
        setVisible(true);
    }
}
