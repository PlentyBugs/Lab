package Lab.ServerClient.Window;

import Lab.Locations.CarService;
import Lab.ServerClient.Client;
import Lab.Things.Car;
import Lab.Windows.CarTable;
import Lab.Windows.CarWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.Socket;
import java.util.Locale;
import java.util.ResourceBundle;

import Lab.Windows.Console;
import Lab.Windows.ViewPanel;

public class ClientWindow extends JFrame {

    private static int width = 870;
    private static int height = 480;
    private static Socket socket;
    private Console console;
    private JPanel content;
    private static Locale locale = Locale.US;
    private CarWindow carWindow;
    private ViewPanel viewPanel;
    private final ResourceBundle[] bundle;
    private int index = 0;

    public ClientWindow(Socket clientSocket){
        super("Автосервис");
        socket = clientSocket;
        new CarTable();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {}

            @Override
            public void componentMoved(ComponentEvent e) {}

            @Override
            public void componentShown(ComponentEvent e){}

            @Override
            public void componentHidden(ComponentEvent e) {
                try {
                    clientSocket.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                close();
            }
        });
        setResizable(false);
        bundle = new ResourceBundle[1];
        bundle[0] = ResourceBundle.getBundle("resource.lab4",
                locale);

        console = new Console();
        console.setSizeArea(width-160, height-20);
        content = new JPanel();

        content.setPreferredSize(new Dimension(width-150, height));
        content.setMinimumSize(new Dimension(width-150, height));
        content.setMaximumSize(new Dimension(width-150, height));

        draw();
    }

    public void draw(){

        carWindow = new CarWindow();
        viewPanel = new ViewPanel();

        JPanel menu = new JPanel(new GridBagLayout());

        GridBagConstraints menuConstraints = new GridBagConstraints();

        menuConstraints.anchor = GridBagConstraints.NORTH;
        menuConstraints.insets = new Insets(2, 0, 2, 5);
        menuConstraints.gridx = 0;
        menuConstraints.gridy = 0;

        JPanel panel = new JPanel(new BorderLayout());
        JLabel userLabel = new JLabel(bundle[0].getString("user"));
        JLabel userName = new JLabel(Client.getLogin());
        panel.add(userLabel, BorderLayout.NORTH);
        panel.add(userName, BorderLayout.SOUTH);

        menu.add(panel, menuConstraints);
        menuConstraints.gridy ++;

        for(String buttonCommand : new String[]{"view", "story", "info", "insert", "import", "load", "save"}){
            JButton button = new JButton();
            button.setText(bundle[0].getString(buttonCommand));
            button.setName(button.getText());
            button.setPreferredSize(new Dimension(150, height/9));
            button.setMinimumSize(new Dimension(150, height/9));
            button.setMaximumSize(new Dimension(150, height/9));

            button.addActionListener(e -> {
                try {
                    if(buttonCommand.equals("info")) {
                        content.removeAll();
                        content.add(CarTable.getCarTable());
                        CarTable.setContent(content);
                        getContentPane().removeAll();

                        getContentPane().add(menu, BorderLayout.WEST);
                        getContentPane().add(content, BorderLayout.EAST);
                        repaint();
                        revalidate();
                    } else if(buttonCommand.equals("view")){
                        content.removeAll();
                        content.add(viewPanel.getPanel());
                        getContentPane().removeAll();

                        getContentPane().add(menu, BorderLayout.WEST);
                        getContentPane().add(content, BorderLayout.EAST);
                        repaint();
                        revalidate();
                    } else if(buttonCommand.equals("story")){
                        content.removeAll();
                        content.add(console);
                        getContentPane().removeAll();

                        getContentPane().add(menu, BorderLayout.WEST);
                        getContentPane().add(content, BorderLayout.EAST);
                        repaint();
                        revalidate();
                    } else if(buttonCommand.equals("insert")){
                        content.removeAll();
                        content.add(carWindow);
                        getContentPane().removeAll();

                        getContentPane().add(menu, BorderLayout.WEST);
                        getContentPane().add(content, BorderLayout.EAST);
                        repaint();
                        revalidate();
                    } else if(buttonCommand.contains("show")){

                    } else {
                        if(buttonCommand.equals("import")){
                            final JFileChooser fc = new JFileChooser();

                            int returnVal = fc.showOpenDialog(this);
                            if(returnVal == JFileChooser.APPROVE_OPTION){
                                File file = fc.getSelectedFile();
                                if(getFileExtension(file.getName()).equals("csv") || getFileExtension(file.getName()).equals("txt")){
                                    String importJsonCars = "";
                                    for(Car car : new CarService().readFromCsvFile(file).getCars().values()){
                                        importJsonCars += car.toJson() + "\n";
                                    }
                                    Client.write(buttonCommand + importJsonCars.getBytes().length);
                                    Client.write(importJsonCars);
                                }
                            }
                        } else if(buttonCommand.equals("save")){
                            Client.write(buttonCommand);
                            Thread thread = new Thread(() -> {
                                String wayIn = null;
                                wayIn = Client.read();

                                final JFileChooser fc = new JFileChooser();

                                int returnVal = fc.showSaveDialog(this);
                                if(returnVal == JFileChooser.APPROVE_OPTION){
                                    File file = fc.getSelectedFile();
                                    if(getFileExtension(file.getName()).equals("csv") || getFileExtension(file.getName()).equals("txt")){
                                        try(FileOutputStream out = new FileOutputStream(file);
                                            BufferedOutputStream bos = new BufferedOutputStream(out)){
                                            byte[] buffer = wayIn.getBytes();
                                            bos.write(buffer, 0, buffer.length);
                                            bos.flush();
                                            System.out.println("Коллекция успешно сохранена в файл " + file.getName());
                                        } catch (IOException ex) {
                                            Client.connectWithServer();
                                        }
                                    }
                                }
                            });
                            thread.start();
                        } else {
                            Client.write(buttonCommand);
                            Thread thread = new Thread(() -> {
                                String wayIn = null;
                                wayIn = Client.read();
                                System.out.println(wayIn);
                            });
                            thread.start();
                        }
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            });

            menu.add(button, menuConstraints);
            menuConstraints.gridy ++;
        }

        JComboBox<Locale> localeJList = new JComboBox<>();
        localeJList.addItem(new Locale("ru", "RU"));
        localeJList.addItem(new Locale("hr", "HR"));
        localeJList.addItem(new Locale("mk", "MK"));
        localeJList.addItem(new Locale("en", "NZ"));
        localeJList.addItem(Locale.US);
        localeJList.addItem(Locale.GERMANY);
        localeJList.setSelectedIndex(index);
        localeJList.addActionListener(e -> {
            locale = localeJList.getItemAt(localeJList.getSelectedIndex());
            bundle[0] = ResourceBundle.getBundle("resource.lab4",
                    locale);
            getContentPane().removeAll();

            index = localeJList.getSelectedIndex();
            draw();
        });
        localeJList.setPreferredSize(new Dimension(150, 20));
        localeJList.setMinimumSize(new Dimension(150, 20));
        localeJList.setMaximumSize(new Dimension(150, 20));
        menu.add(localeJList, menuConstraints);
        menuConstraints.gridy ++;

        getContentPane().add(menu, BorderLayout.WEST);
        getContentPane().add(content, BorderLayout.EAST);
        pack();
        setVisible(true);
    }

    public void close(){
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

    public Console getConsole() {
        return console;
    }

    public String getFileExtension(String fullName) {
        if(fullName != null){
            String fileName = new File(fullName).getName();
            int dotIndex = fileName.lastIndexOf('.');
            return (dotIndex == -1) ? "" : fileName.substring(dotIndex + 1);
        }
        return "NULL";
    }

    public static Locale getLoc() {
        return locale;
    }
}
