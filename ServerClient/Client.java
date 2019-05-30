package Lab.ServerClient;
import Lab.Live.Storyteller;
import Lab.Locations.CarService;
import Lab.ServerClient.Window.ConnectingWindow;
import Lab.ServerClient.Window.LoginWindow;
import Lab.Things.Car;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Client {

    private static Socket clientSocket;
    private static OutputStream out;
    private static CarService carService;
    private static String name;
    private static Storyteller storyteller;
    private static String login;
    private static String password;
    private static boolean wasConnected;
    private static long delayOnConnection = 20000;
    private static ConnectingWindow connectingWindow;

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
        byte[] bytes = null;
        try {
            bytes = new byte[65536];
            clientSocket.getInputStream().read(bytes);
        } catch (IOException e) {
            connectWithServer();
        }

        return new String(bytes, StandardCharsets.UTF_8);
    }

    public static void write(String message) {
        try {
            out.write(message.getBytes());
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
                out = clientSocket.getOutputStream();
                if(wasConnected){
                    write("Log " + login + " " + password);
                } else
                    wasConnected = true;
                if(connectingWindow != null)
                    connectingWindow.close();
                connectingWindow = null;
                break;
            } catch (IOException e) {
                if(connectingWindow == null)
                    connectingWindow = new ConnectingWindow();
                connectingWindow.setTime((time+delayOnConnection-System.currentTimeMillis())/1000);
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

    public static long getDelayOnConnection() {
        return delayOnConnection;
    }

    public static String getLogin() {
        return login;
    }

    public static ArrayList<Car> getCars(){
        return PostgreSQL.getCarsNonUser("");
    }
}
