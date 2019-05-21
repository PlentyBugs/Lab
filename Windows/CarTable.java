package Lab.Windows;

import Lab.Locations.CarService;
import Lab.ServerClient.Window.ClientWindow;
import Lab.Things.Car;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CarTable {

    public static JScrollPane getCarTable(ArrayList<Car> cars){

        ResourceBundle bundle;
        bundle = ResourceBundle.getBundle("resource.lab4",
                ClientWindow.getLoc());

        String[] columnsPrep = new String[]{"tableID", "tableOwner", "tableName", "tableProperty", "tableDate", "tableCost", "tableEngine", "tableBrake", "tableCabin", "tableRightTopWheel", "tableRightBottomWheel", "tableLeftTopWheel", "tableLeftBottomWheel"};
        String[] columns  = new String[27];
        int j = 0;
        for(int i = 0; i < columnsPrep.length; i++) {
            if(columnsPrep[i].equals("tableID") || columnsPrep[i].equals("tableOwner") || columnsPrep[i].equals("tableName") || columnsPrep[i].equals("tableProperty") || columnsPrep[i].equals("tableDate") || columnsPrep[i].equals("tableCost"))
                columns[i] = bundle.getString(columnsPrep[i]);
            else {
                columns[i+j*2] = bundle.getString(columnsPrep[i]) + "(" + bundle.getString("tableIsSkillNeed") + ")";
                columns[i+1+j*2] = bundle.getString(columnsPrep[i]) + "(" + bundle.getString("tableDegreeOfBreakage") + ")";
                columns[i+2+j*2] = bundle.getString(columnsPrep[i]) + "(" + bundle.getString("tableQuality") + ")";
                j ++;
            }
        }

        ArrayList<Object[]> lines = new ArrayList<>();

        int counter = 0;
        for(Car car : cars){
            lines.add(new Object[]{
                    counter, car.getOwner(), car.getName(),
                    car.getProperty(), car.getLocalDateTime(), car.getCostForRepair(),
                    car.getDetails()[6].getIsSkiilNeed(), car.getDetails()[6].getDegree_of_breakage(), car.getDetails()[6].getQuality(),
                    car.getDetails()[4].getIsSkiilNeed(), car.getDetails()[4].getDegree_of_breakage(), car.getDetails()[4].getQuality(),
                    car.getDetails()[5].getIsSkiilNeed(), car.getDetails()[5].getDegree_of_breakage(), car.getDetails()[5].getQuality(),
                    car.getDetails()[1].getIsSkiilNeed(), car.getDetails()[1].getDegree_of_breakage(), car.getDetails()[1].getQuality(),
                    car.getDetails()[0].getIsSkiilNeed(), car.getDetails()[0].getDegree_of_breakage(), car.getDetails()[0].getQuality(),
                    car.getDetails()[3].getIsSkiilNeed(), car.getDetails()[3].getDegree_of_breakage(), car.getDetails()[3].getQuality(),
                    car.getDetails()[2].getIsSkiilNeed(), car.getDetails()[2].getDegree_of_breakage(), car.getDetails()[2].getQuality()
            });
            counter ++;
        }

        Object[][] data = lines.toArray(new Object[0][]);

        JTable table = new JTable(data, columns);
        table.setAutoCreateRowSorter(true);
        for(int i = 0; i < columns.length; i++){
            table.getColumnModel().getColumn(i).setMinWidth(columns[i].length()*6);
        }

        JPanel panel = new JPanel();
        JScrollPane scrollPane = new JScrollPane(panel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        table.setFillsViewportHeight(true);
        panel.setLayout(new BorderLayout());
        panel.add(table.getTableHeader(), BorderLayout.PAGE_START);
        panel.add(table, BorderLayout.CENTER);
        int width = 720;
        int height = 460;
        scrollPane.setMaximumSize(new Dimension(width, height));
        scrollPane.setMinimumSize(new Dimension(width, height));
        scrollPane.setPreferredSize(new Dimension(width, height));

        return scrollPane;
    }
}
