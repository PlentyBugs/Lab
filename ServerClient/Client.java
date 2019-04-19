package Lab.ServerClient;
import Lab.Live.Storyteller;
import Lab.Locations.CarService;
import Lab.ServerClient.Window.LoginWindow;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Client {

    private static Socket clientSocket;
    private static ObjectOutputStream out;
    private static ObjectInputStream in;
    private static CarService carService;
    private static String name;
    private static Storyteller storyteller;
    private static String login;
    private static String password;
    private static boolean wasConnected;
    private static long delayOnConnection = 20000;

    public static void main(String[] args) {
        wasConnected = false;

        storyteller = new Storyteller();

        if (args.length > 0){
            storyteller.setFileName(args[0]);
        }

        connectWithServer();

        carService = new CarService();
        storyteller.setCarService(carService);

        new LoginWindow();
    }

    public static String read() {
        String str = null;
        try {
            str = (String) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static void write(String message) {
        try {
            out.writeObject(message);
            out.flush();
        } catch (IOException e) {
            connectWithServer();
        }
    }

    public static CarService getCarService() {
        return carService;
    }

    public static Socket getClientSocket() {
        return clientSocket;
    }

    public static void setCarService(CarService carService) {
        Client.carService = carService;
    }

    public static Storyteller getStoryteller() {
        return storyteller;
    }

    public static void setStoryteller(Storyteller storyteller) {
        Client.storyteller = storyteller;
    }

    public static void connectWithServer(){

        long time = System.currentTimeMillis();

        while (System.currentTimeMillis() < delayOnConnection + time){
            try {
                clientSocket = new Socket(InetAddress.getByName("localhost"), 4004);
                out = new ObjectOutputStream(clientSocket.getOutputStream());
                in = new ObjectInputStream(clientSocket.getInputStream());
                if(wasConnected){
                    write("Log " + login + " " + password);
                } else
                    wasConnected = true;
                break;
            } catch (IOException e) {
                System.out.println("Попытка соединения с сервером, осталось " + Long.toString((time+delayOnConnection-System.currentTimeMillis())/1000) + " секунд");
            }
        }
    }

    public static void setLogin(String login) {
        Client.login = login;
    }

    public static void setPassword(String password) {
        Client.password = password;
    }

    public static OutputStream getOut() {
        return out;
    }
}
