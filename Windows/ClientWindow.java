package Lab.Windows;

import Lab.Locations.CarService;
import Lab.ServerClient.Client;
import Lab.Things.Car;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.Socket;

public class ClientWindow extends JFrame {

    private static int width = 480;
    private static int height = 720;
    private static Socket socket;
    private Lab.Windows.Console console;

    public ClientWindow(Socket clientSocket){
        super("История историй об исторической истории Историка");
        socket = clientSocket;
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

        JPanel carPanel = new JPanel();

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setPreferredSize(new Dimension(width, height));
        panel.setMaximumSize(new Dimension(width, height));
        panel.setMinimumSize(new Dimension(width, height));
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.anchor = GridBagConstraints.NORTH;
        constraints.insets = new Insets(0, 0, 0, 0);
        constraints.gridx = 0;
        constraints.gridy = 0;

        console = new Lab.Windows.Console();
        console.setSizeArea(width, height/2);
        panel.add(console, constraints);

        constraints.gridy ++;
        JPanel messagePanel = new JPanel(new GridBagLayout());
        GridBagConstraints messageConstraints = new GridBagConstraints();

        messageConstraints.anchor = GridBagConstraints.NORTH;
        messageConstraints.insets = new Insets(0, 0, 0, 0);
        messageConstraints.gridx = 0;
        messageConstraints.gridy = 0;

        for(String buttonCommand : new String[]{"info", "insert", "remove_all", "remove", "remove_greater", "import", "show", "load", "save"}){
            MenuButton button = new MenuButton();
            switch (buttonCommand){
                case "info": button.setText("Информация"); break;
                case "insert": button.setText("Вставить"); break;
                case "remove_all": button.setText("Удалить равные"); break;
                case "remove": button.setText("Удалить по ключу"); break;
                case "remove_greater": button.setText("Удалить превышающие"); break;
                case "import": button.setText("Импортировать"); break;
                case "show": button.setText("Показать"); break;
                case "load": button.setText("Загрузить"); break;
                case "save": button.setText("Сохранить"); break;
            }
            button.setName(button.getText());
            button.setCommand(buttonCommand);
            button.setPreferredSize(new Dimension(width/3, height/6));
            button.setMinimumSize(new Dimension(width/3, height/6));
            button.setMaximumSize(new Dimension(width/3, height/6));

            button.addActionListener(e -> {
                try {
                    if(button.getCarWindow() != null){
                        if(!button.getCarWindow().getVisible()){
                            button.getCarWindow().setIsVisible(true);
                        } else {
                            button.getCarWindow().setIsVisible(false);
                        }
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

            messagePanel.add(button, messageConstraints);
            messageConstraints.gridx ++;
            if(messageConstraints.gridx == 3){
                messageConstraints.gridx = 0;
                messageConstraints.gridy ++;
            }
        }

        panel.add(messagePanel, constraints);

        getContentPane().add(panel, BorderLayout.EAST);
        getContentPane().add(carPanel, BorderLayout.WEST);
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
}
