package Lab.ServerClient.Window;

import Lab.ServerClient.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public class ConnectingWindow extends JFrame {

    private JProgressBar progressBar;
    private JLabel label2;

    public ConnectingWindow(){
        setLayout(new BorderLayout());
        JLabel label = new JLabel("Попытка подключения к серверу");
        JLabel label1 = new JLabel("Осталось: ");
        progressBar = new JProgressBar();
        progressBar.setMinimum(0);
        progressBar.setMaximum((int) Client.getDelayOnConnection()/1000);
        progressBar.setValue(progressBar.getMaximum());
        label2 = new JLabel(progressBar.getValue() + "/" + progressBar.getMaximum() + " с");

        getContentPane().add(label, BorderLayout.NORTH);
        getContentPane().add(label1, BorderLayout.WEST);
        getContentPane().add(progressBar, BorderLayout.EAST);
        getContentPane().add(label2, BorderLayout.SOUTH);
        pack();
        setVisible(true);
    }

    public void setTime(long time) {
        progressBar.setValue((int) time);
        label2.setText(progressBar.getValue() + "/" + progressBar.getMaximum() + " с");
    }

    public void close(){
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }
}
