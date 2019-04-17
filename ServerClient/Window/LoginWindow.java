package Lab.ServerClient.Window;

import Lab.Exceptions.IsNakedException;
import Lab.ServerClient.Client;
import Lab.Windows.ClientWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public class LoginWindow extends JFrame {
    private boolean panel; // true == login, false == register
    private int width;
    private int height;

    public LoginWindow(){
        super("Вход/Регистрация");
        width = 360;
        height = 180;
        setResizable(false);

        setMaximumSize(new Dimension(width, height));
        setMinimumSize(new Dimension(width, height));
        setPreferredSize(new Dimension(width, height));
        panel = true;
        drawWindow();
    }

    private void drawWindow(){
        getContentPane().removeAll();

        if(panel)
            getContentPane().add(new LoginPanel().setParent(this), BorderLayout.NORTH);
        else
            getContentPane().add(new RegisterPanel().setParent(this), BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.insets = new Insets(2, 2, 2, 2);
        constraints.gridx = 0;
        constraints.gridy = 0;

        JButton login = setSize(new JButton("Login"), width/2, 40);
        login.addActionListener(e -> {
            this.panel = true;
            drawWindow();
        });
        JButton register = setSize(new JButton("Registration"), width/2, 40);
        register.addActionListener(e -> {
            this.panel = false;
            drawWindow();
        });

        panel.add(login, constraints);
        constraints.gridx ++;
        panel.add(register, constraints);

        getContentPane().add(panel);
        pack();
        setVisible(true);
    }

    private JButton setSize(JButton button, int width, int height){
        button.setMaximumSize(new Dimension(width, height));
        button.setMinimumSize(new Dimension(width, height));
        button.setPreferredSize(new Dimension(width, height));
        return button;
    }

    public void close(){
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));

        ClientWindow clientWindow = new ClientWindow(Client.getClientSocket());

        Client.getStoryteller().setConsole(clientWindow.getConsole());

        try {
            Client.getStoryteller().tellStory();
        } catch (IsNakedException e) {
            e.printStackTrace();
        }
    }
}
