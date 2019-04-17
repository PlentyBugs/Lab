package Lab.ServerClient.Window;

import Lab.ServerClient.Client;

import javax.swing.*;
import java.awt.*;

public class RegisterPanel extends JPanel {

    private LoginWindow parent;

    public RegisterPanel(){
        setLayout(new GridBagLayout());
        JPanel loginPanel = new JPanel(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.insets = new Insets(2, 2, 2, 2);
        constraints.gridx = 0;
        constraints.gridy = 0;

        JLabel loginLabel = new JLabel("Логин");
        JTextArea login = new JTextArea();
        loginPanel.add(loginLabel, constraints);
        constraints.gridx ++;
        loginPanel.add(login, constraints);
        constraints.gridy ++;
        constraints.gridx = 0;

        JLabel passwordFieldLabel = new JLabel("Пароль");
        JPasswordField passwordField = new JPasswordField();
        loginPanel.add(passwordFieldLabel, constraints);
        constraints.gridx ++;
        loginPanel.add(passwordField, constraints);
        constraints.gridx = 0;
        constraints.gridy = 0;

        login.setMaximumSize(new Dimension(240, 20));
        login.setMinimumSize(new Dimension(240, 20));
        login.setPreferredSize(new Dimension(240, 20));

        passwordField.setMaximumSize(new Dimension(240, 20));
        passwordField.setMinimumSize(new Dimension(240, 20));
        passwordField.setPreferredSize(new Dimension(240, 20));

        JButton in = new JButton("Зарегистрироваться");
        in.addActionListener(e -> {
            String password = "";
            for(char c : passwordField.getPassword()){
                password += c;
            }
            String message = "Reg " + login.getText() + " " + password;

            Client.write(message);

            String wayIn = Client.read();
            if(!wayIn.contains("no connected")){
                Client.setLogin(login.getText());
                Client.setPassword(password);
                parent.close();
            }
        });

        in.setMaximumSize(new Dimension(240, 20));
        in.setMinimumSize(new Dimension(240, 20));
        in.setPreferredSize(new Dimension(240, 20));

        add(loginPanel, constraints);
        constraints.gridy ++;
        add(in, constraints);
    }

    public RegisterPanel setParent(LoginWindow parent) {
        this.parent = parent;
        return this;
    }
}
