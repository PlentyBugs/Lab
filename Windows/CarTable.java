package Lab.Windows;

import Lab.ServerClient.Client;
import Lab.ServerClient.PostgreSQL;
import Lab.ServerClient.Window.ClientWindow;
import Lab.Things.Car;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelListener;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;
import java.util.function.Function;
import javax.swing.RowSorter.SortKey;

import static Lab.ServerClient.Client.getCars;

public class CarTable {
    private static Function<ArrayList<Car>, ArrayList<Car>> function = cars -> cars;
    private static TableModelListener changeCell;
    private static TableModel model;
    private static Locale pastLocale;
    private static JTextArea statusText;
    private static JPanel fullPanel = new JPanel();
    private static JPanel filterPanel = new JPanel();
    private static JPanel tablePanel = new JPanel();
    private static JPanel content = new JPanel();
    private static JTable table;
    private static int index = 0;
    private static JTextField filterText;
    private static ArrayList<Car> cars = new ArrayList<>();
    private static TableRowSorter<TableModel> sorter;
    private static JScrollPane scrollTable = new JScrollPane(tablePanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

    public CarTable(){

        cars = function.apply(getCars());
        pastLocale = ClientWindow.getLoc();

        double koeff = 1;
        Locale locale = ClientWindow.getLoc();
        if (locale.toString().contains("US")){
            koeff = 65;
        } else if(locale.toString().contains("HR")){
            koeff = 9.76;
        } else if(locale.toString().contains("DE")){
            koeff = 72.48;
        } else if(locale.toString().contains("MK")){
            koeff = 1.17;
        } else if(locale.toString().contains("NZ")){
            koeff = 43;
        }

        double finalKoeff = koeff;
        changeCell = e -> {
            int row = e.getFirstRow();
            int column = e.getColumn();
            TableModel model = (TableModel)e.getSource();
            if(Client.getLogin().equals(model.getValueAt(row, 2))){
                String columnName = model.getColumnName(column);
                Object data = model.getValueAt(row, column);
                int x = Integer.parseInt(model.getValueAt(row, 1).toString().split("X: ")[1].split(";")[0].trim());
                int y = Integer.parseInt(model.getValueAt(row, 1).toString().split("Y: ")[1].split(";")[0].trim());
                int id = (int) model.getValueAt(row, 0);
                PostgreSQL.insertCar(
                        (String)model.getValueAt(row, 2), (String)model.getValueAt(row, 3), (String)model.getValueAt(row, 4), "0000",
                        (int)(((int) model.getValueAt(row, 6))* finalKoeff),
                        x,y,id,
                        (boolean) model.getValueAt(row, 7),  (int) model.getValueAt(row, 8),  (int) model.getValueAt(row, 9),
                        (boolean) model.getValueAt(row, 10), (int) model.getValueAt(row, 11), (int) model.getValueAt(row, 12),
                        (boolean) model.getValueAt(row, 13), (int) model.getValueAt(row, 14), (int) model.getValueAt(row, 15),
                        (boolean) model.getValueAt(row, 16), (int) model.getValueAt(row, 17), (int) model.getValueAt(row, 18),
                        (boolean) model.getValueAt(row, 19), (int) model.getValueAt(row, 20), (int) model.getValueAt(row, 21),
                        (boolean) model.getValueAt(row, 22), (int) model.getValueAt(row, 23), (int) model.getValueAt(row, 24),
                        (boolean) model.getValueAt(row, 25), (int) model.getValueAt(row, 26), (int) model.getValueAt(row, 27)
                        );
            }
        };

        ResourceBundle bundle;
        bundle = ResourceBundle.getBundle("resource.lab4",
                ClientWindow.getLoc());

        String[] columnsPrep = new String[]{"tableDelete", "tablePosition", "tableOwner", "tableName", "tableProperty", "tableDate", "tableCost", "tableEngine", "tableBrake", "tableCabin", "tableRightTopWheel", "tableRightBottomWheel", "tableLeftTopWheel", "tableLeftBottomWheel"};
        String[] c = new String[28];
        String[] columns  = new String[28];
        int j = 0;
        for(int i = 0; i < columnsPrep.length; i++) {
            if (columnsPrep[i].equals("tableDelete") || columnsPrep[i].equals("tableOwner") || columnsPrep[i].equals("tableName") || columnsPrep[i].equals("tableProperty") || columnsPrep[i].equals("tableDate") || columnsPrep[i].equals("tableCost") || columnsPrep[i].equals("tablePosition")){
                c[i] = columnsPrep[i];
                columns[i] = bundle.getString(columnsPrep[i]);
            } else {
                columns[i+j*2] = bundle.getString(columnsPrep[i]) + "(" + bundle.getString("tableIsSkillNeed") + ")";
                columns[i+1+j*2] = bundle.getString(columnsPrep[i]) + "(" + bundle.getString("tableDegreeOfBreakage") + ")";
                columns[i+2+j*2] = bundle.getString(columnsPrep[i]) + "(" + bundle.getString("tableQuality") + ")";
                c[i+j*2] = "tableIsSkillNeed";
                c[i+1+j*2] = "tableDegreeOfBreakage";
                c[i+2+j*2] = "tableQuality";
                j ++;
            }
            if(columnsPrep[i].equals("tableCost"))
                columns[i] += "(" + NumberFormat.getCurrencyInstance(ClientWindow.getLoc()).format(1) + ")";
        }

        ArrayList<Object[]> lines = new ArrayList<>();
        for(Car car : cars){
            lines.add(new Object[]{
                    car.getId(), "X: " + car.getX() + "; Y:  " + car.getY() + ";", car.getOwner(), car.getName(),
                    car.getProperty(), car.getLocalDateTime(), (int)(car.getCostForRepair()/koeff),
                    car.getDetails()[6].getIsSkiilNeed(), car.getDetails()[6].getDegree_of_breakage(), car.getDetails()[6].getQuality(),
                    car.getDetails()[4].getIsSkiilNeed(), car.getDetails()[4].getDegree_of_breakage(), car.getDetails()[4].getQuality(),
                    car.getDetails()[5].getIsSkiilNeed(), car.getDetails()[5].getDegree_of_breakage(), car.getDetails()[5].getQuality(),
                    car.getDetails()[1].getIsSkiilNeed(), car.getDetails()[1].getDegree_of_breakage(), car.getDetails()[1].getQuality(),
                    car.getDetails()[0].getIsSkiilNeed(), car.getDetails()[0].getDegree_of_breakage(), car.getDetails()[0].getQuality(),
                    car.getDetails()[3].getIsSkiilNeed(), car.getDetails()[3].getDegree_of_breakage(), car.getDetails()[3].getQuality(),
                    car.getDetails()[2].getIsSkiilNeed(), car.getDetails()[2].getDegree_of_breakage(), car.getDetails()[2].getQuality()
            });
        }

        Object[][] data = lines.toArray(new Object[0][]);

        model = new DefaultTableModel(data, columns) {
            public Class getColumnClass(int column) {
                Class returnValue = Object.class;
                try {
                    if ((column >= 0) && (column < getColumnCount())) {
                        returnValue = getValueAt(0, column).getClass();
                    } else {
                        returnValue = Object.class;
                    }
                } catch (Exception e){}
                return returnValue;
            }
        };

        model.addTableModelListener(changeCell);

        table = new JTable(model);
        sorter = new TableRowSorter<>(table.getModel());
        sorter.setComparator(0, Comparator.naturalOrder());
        sorter.setSortsOnUpdates(true);
        ArrayList<SortKey> sortKeys = new ArrayList<>();
        sortKeys.add(new SortKey(0, SortOrder.ASCENDING));
        sorter.setSortKeys(sortKeys);
        table.setRowSorter(sorter);

        TableColumn sportColumn = table.getColumnModel().getColumn(0);
        sportColumn.setCellRenderer(new ButtonRenderer());
        sportColumn.setCellEditor(new ButtonEditor(new JCheckBox()));
        for(int i = 0; i < columns.length; i++){
            table.getColumnModel().getColumn(i).setMinWidth(columns[i].length()*6);
        }

        table.setFillsViewportHeight(true);
        tablePanel.removeAll();
        tablePanel.setLayout(new BorderLayout());
        tablePanel.add(table.getTableHeader(), BorderLayout.PAGE_START);
        tablePanel.add(table, BorderLayout.CENTER);

        int width = 720;
        int height = 160;
        scrollTable.setMaximumSize(new Dimension(width, height));
        scrollTable.setMinimumSize(new Dimension(width, height));
        scrollTable.setPreferredSize(new Dimension(width, height));
        tablePanel.revalidate();
        tablePanel.repaint();
        scrollTable.revalidate();
        scrollTable.repaint();

        filterPanel.setMaximumSize(new Dimension(width, height));
        filterPanel.setMinimumSize(new Dimension(width, height));
        filterPanel.setPreferredSize(new Dimension(width, height));

        JLabel label = new JLabel("Filter Text: ");
        filterText = new JTextField();

        filterText.setPreferredSize(new Dimension(width-100, 30));
        filterText.setMinimumSize(new Dimension(width-100, 30));
        filterText.setMaximumSize(new Dimension(width-100, 30));

        JLabel status = new JLabel("Status: ");
        statusText = new JTextArea();

        statusText.setPreferredSize(new Dimension(width-100, 30));
        statusText.setMinimumSize(new Dimension(width-100, 30));
        statusText.setMaximumSize(new Dimension(width-100, 30));
        filterText.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                newFilter();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                newFilter();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                newFilter();
            }
        });

        table.getSelectionModel().addListSelectionListener(
                event -> {
                    int viewRow = table.getSelectedRow();
                    if (viewRow < 0) {
                        statusText.setText("");
                    } else {
                        int modelRow =
                                table.convertRowIndexToModel(viewRow);
                        statusText.setText(
                                String.format("Selected Row in view: %d. " +
                                                "Selected Row in model: %d.",
                                        viewRow, modelRow));
                    }
                }
        );



        JLabel filterBy = new JLabel("FilterBy");
        JComboBox<String> filters = new JComboBox<>(columns);
        filters.addItemListener(e -> index = filters.getSelectedIndex());

        filterPanel.setLayout(new BorderLayout());
        JPanel panel = new JPanel(new BorderLayout());
        JPanel pane2 = new JPanel(new BorderLayout());
        JPanel pane3 = new JPanel(new BorderLayout());

        panel.add(label, BorderLayout.WEST);
        panel.add(filterText, BorderLayout.EAST);
        pane3.add(filterBy, BorderLayout.WEST);
        pane3.add(filters, BorderLayout.EAST);
        pane2.add(status, BorderLayout.WEST);
        pane2.add(statusText, BorderLayout.EAST);
        filterPanel.add(panel, BorderLayout.NORTH);
        filterPanel.add(pane3, BorderLayout.CENTER);
        filterPanel.add(pane2, BorderLayout.SOUTH);

        new Thread(() -> {
            while (true){
                synchronized (this){
                    updateTable();
                    fullPanel.repaint();
                    fullPanel.revalidate();
                    content.repaint();
                    content.revalidate();
                    try {
                        wait(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        fullPanel.setLayout(new BorderLayout());
        fullPanel.add(scrollTable, BorderLayout.NORTH);
        fullPanel.add(filterPanel, BorderLayout.SOUTH);
    }

    public static JPanel getCarTable(){
        updateTable();
        return fullPanel;
    }

    public static void updateTable(){

        ArrayList<Car> cars2 = function.apply(getCars());

        boolean eq = true;

        if(cars.size() == cars2.size()){
            for(int i = 0; i < cars.size(); i++){
                if(cars.get(i).compareTo(cars2.get(i)) != 0){
                    eq = false;
                    break;
                }
            }
        } else {
            eq = false;
        }

        if(!pastLocale.equals(ClientWindow.getLoc()))
            eq = false;

        if(!eq){
            pastLocale = ClientWindow.getLoc();
            cars = cars2;
            ResourceBundle bundle;
            bundle = ResourceBundle.getBundle("resource.lab4",
                    ClientWindow.getLoc());

            String[] columnsPrep = new String[]{"tableDelete", "tablePosition", "tableOwner", "tableName", "tableProperty", "tableDate", "tableCost", "tableEngine", "tableBrake", "tableCabin", "tableRightTopWheel", "tableRightBottomWheel", "tableLeftTopWheel", "tableLeftBottomWheel"};
            String[] columns  = new String[28];
            int j = 0;
            for(int i = 0; i < columnsPrep.length; i++) {
                if(columnsPrep[i].equals("tableDelete") || columnsPrep[i].equals("tableOwner") || columnsPrep[i].equals("tableName") || columnsPrep[i].equals("tableProperty") || columnsPrep[i].equals("tableDate") || columnsPrep[i].equals("tableCost") || columnsPrep[i].equals("tablePosition"))
                    columns[i] = bundle.getString(columnsPrep[i]);
                else {
                    columns[i+j*2] = bundle.getString(columnsPrep[i]) + "(" + bundle.getString("tableIsSkillNeed") + ")";
                    columns[i+1+j*2] = bundle.getString(columnsPrep[i]) + "(" + bundle.getString("tableDegreeOfBreakage") + ")";
                    columns[i+2+j*2] = bundle.getString(columnsPrep[i]) + "(" + bundle.getString("tableQuality") + ")";
                    j ++;
                }
                if(columnsPrep[i].equals("tableCost"))
                    columns[i] += "(" + NumberFormat.getCurrencyInstance(ClientWindow.getLoc()).format(1) + ")";
            }

            ArrayList<Object[]> lines = new ArrayList<>();

            double koeff = 1;
            Locale locale = ClientWindow.getLoc();
            if (locale.toString().contains("US")){
                koeff = 65;
            } else if(locale.toString().contains("HR")){
                koeff = 9.76;
            } else if(locale.toString().contains("DE")){
                koeff = 72.48;
            } else if(locale.toString().contains("MK")){
                koeff = 1.17;
            } else if(locale.toString().contains("NZ")){
                koeff = 43;
            }
            for(Car car : cars){
                lines.add(new Object[]{
                        car.getId(), "X: " + car.getX() + "; Y:  " + car.getY() + ";", car.getOwner(), car.getName(),
                        car.getProperty(), car.getLocalDateTime().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).withLocale(ClientWindow.getLoc())), (int)(car.getCostForRepair()/koeff),
                        car.getDetails()[6].getIsSkiilNeed(), car.getDetails()[6].getDegree_of_breakage(), car.getDetails()[6].getQuality(),
                        car.getDetails()[4].getIsSkiilNeed(), car.getDetails()[4].getDegree_of_breakage(), car.getDetails()[4].getQuality(),
                        car.getDetails()[5].getIsSkiilNeed(), car.getDetails()[5].getDegree_of_breakage(), car.getDetails()[5].getQuality(),
                        car.getDetails()[1].getIsSkiilNeed(), car.getDetails()[1].getDegree_of_breakage(), car.getDetails()[1].getQuality(),
                        car.getDetails()[0].getIsSkiilNeed(), car.getDetails()[0].getDegree_of_breakage(), car.getDetails()[0].getQuality(),
                        car.getDetails()[3].getIsSkiilNeed(), car.getDetails()[3].getDegree_of_breakage(), car.getDetails()[3].getQuality(),
                        car.getDetails()[2].getIsSkiilNeed(), car.getDetails()[2].getDegree_of_breakage(), car.getDetails()[2].getQuality()
                });
            }

            Object[][] data = lines.toArray(new Object[0][]);

            model = new DefaultTableModel(data, columns) {
                public Class getColumnClass(int column) {
                    Class returnValue = Object.class;
                    try {
                        if ((column >= 0) && (column < getColumnCount())) {
                            returnValue = getValueAt(0, column).getClass();
                        } else {
                            returnValue = Object.class;
                        }
                    }catch (Exception e){}
                    return returnValue;
                }
            };
            table.setModel(model);

            table.getModel().addTableModelListener(changeCell);

            sorter = new TableRowSorter<>(table.getModel());
            sorter.setComparator(0, Comparator.naturalOrder());
            sorter.setSortsOnUpdates(true);
            ArrayList<SortKey> sortKeys = new ArrayList<>();
            sortKeys.add(new RowSorter.SortKey(6, SortOrder.ASCENDING));
            sorter.setSortKeys(sortKeys);
            table.setRowSorter(sorter);

            TableColumn sportColumn = table.getColumnModel().getColumn(0);
            sportColumn.setCellRenderer(new ButtonRenderer());
            sportColumn.setCellEditor(new ButtonEditor(new JCheckBox()));
            TableColumn xyColumn = table.getColumnModel().getColumn(1);
            xyColumn.setCellRenderer(new ButtonRenderer());
            xyColumn.setCellEditor(new ButtonEditor2(new JCheckBox()));
            for(int i = 0; i < columns.length; i++){
                table.getColumnModel().getColumn(i).setMinWidth(columns[i].length()*6);
            }

            table.setFillsViewportHeight(true);
            tablePanel.removeAll();
            tablePanel.setLayout(new BorderLayout());
            tablePanel.add(table.getTableHeader(), BorderLayout.PAGE_START);
            tablePanel.add(table, BorderLayout.CENTER);
            int width = 720;
            int height = 300;
            scrollTable.setMaximumSize(new Dimension(width, height));
            scrollTable.setMinimumSize(new Dimension(width, height));
            scrollTable.setPreferredSize(new Dimension(width, height));
            tablePanel.revalidate();
            tablePanel.repaint();
            scrollTable.revalidate();
            scrollTable.repaint();
        }
    }

    private static class ButtonRenderer extends JButton implements TableCellRenderer {

        public ButtonRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus, int row, int column) {
            if (isSelected) {
                setForeground(table.getSelectionForeground());
                setBackground(table.getSelectionBackground());
            } else {
                setForeground(table.getForeground());
                setBackground(UIManager.getColor("Button.background"));
            }
            setText((value == null) ? "" : value.toString());
            return this;
        }
    }

    static class ButtonEditor extends DefaultCellEditor {

        protected JButton button;
        private String label;
        private boolean isPushed;
        private int id;

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(e -> fireEditingStopped());
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value,
                                                     boolean isSelected, int row, int column) {

            if (isSelected) {
                button.setForeground(table.getSelectionForeground());
                button.setBackground(table.getSelectionBackground());
            } else {
                button.setForeground(table.getForeground());
                button.setBackground(table.getBackground());
            }
            label = (value == null) ? "" : value.toString();
            button.setText(label);
            id = Integer.parseInt(label);
            isPushed = true;
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            if (isPushed) {
                Client.write("delete" + id);
            }
            isPushed = false;
            return label;
        }
    }

    public static void setContent(JPanel content) {
        CarTable.content = content;
    }
    private void newFilter() {
        RowFilter<TableModel, Object> rf;
        try {
            rf = RowFilter.regexFilter(filterText.getText(), index);
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        sorter.setRowFilter(rf);
    }

    static class ButtonEditor2 extends DefaultCellEditor {

        protected JButton button;
        private String label;
        private boolean isPushed;
        private int x;
        private int y;

        public ButtonEditor2(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(e -> fireEditingStopped());
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value,
                                                     boolean isSelected, int row, int column) {

            if (isSelected) {
                button.setForeground(table.getSelectionForeground());
                button.setBackground(table.getSelectionBackground());
            } else {
                button.setForeground(table.getForeground());
                button.setBackground(table.getBackground());
            }
            label = (value == null) ? "" : value.toString();
            button.setText(label);
            x = Integer.parseInt(label.split("X: ")[1].split(";")[0].trim());
            y = Integer.parseInt(label.split("Y: ")[1].split(";")[0].trim());
            isPushed = true;
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            if (isPushed) {
                ViewPanel.setX(x);
                ViewPanel.setY(y);
            }
            isPushed = false;
            return label;
        }
    }
}
