package Lab.ServerClient.Window;

import javax.swing.*;
import java.awt.event.WindowEvent;

public class DialogWindow extends JFrame {

    public DialogWindow(String message){
        super(message);
        JLabel label = new JLabel(message);

        getContentPane().add(label);
        pack();
        setVisible(true);
        Thread checker = new Thread(() -> {
            try {
                wait(5000);
            } catch (InterruptedException e) {}
            close();
        });
        checker.start();
    }

    public void close(){
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }
}
