package gui;

import controller.Controller;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm {
    private JPanel rootPanel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    private MainFrame mainFrame;
    private Controller controller;

    public LoginForm(MainFrame mainFrame, Controller controller) {
        this.mainFrame = mainFrame;
        this.controller = controller;

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (controller.login(username, password)) {
                    // Pulisci i campi dopo il login
                    usernameField.setText("");
                    passwordField.setText("");

                    if (controller.isAmministratore()) {
                        mainFrame.showPanel("ADMIN");
                    } else {
                        // mainFrame.showPanel("USER"); // Logica per l'utente generico
                        JOptionPane.showMessageDialog(rootPanel, "Login Utente Riuscito! (Pannello non ancora implementato)");
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPanel,
                            "Login fallito. Credenziali non valide.",
                            "Errore",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }
}