package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeLogin {

    private JPanel panel1;
    private JLabel usernameJLabel;
    private JTextField usernameTextField;
    private JLabel passwordJLabel;
    private JPasswordField passwordPasswordField;
    private JButton loginButton;

    private final controller.Controller controller;


    // Costruttore di HomeLogin
    public HomeLogin() {

        controller = new Controller();


        // Cosa succede se l'utente clicca sul bottone "Login"
        loginButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                String username = usernameTextField.getText();
                String password = new String(passwordPasswordField.getPassword());
                String userRole = controller.HandleLogin(username, password);


                // Se l'utente logga come ADMIN
                switch (userRole) {
                    case "ADMIN" -> {
                        JOptionPane.showMessageDialog(panel1,
                                "Sei loggato con successo come admin.");

                        Window currentWindow = SwingUtilities.getWindowAncestor(panel1);
                        if (currentWindow instanceof JFrame) {
                            ((JFrame) currentWindow).dispose();
                        }


                        JFrame newFrame = new JFrame("Aeroporto di Napoli - Admin Home");
                        AdminDashboard adminDashboard = new AdminDashboard();
                        newFrame.setContentPane(adminDashboard.getPanel());
                        newFrame.setLocationRelativeTo(null);
                        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        newFrame.pack();
                        newFrame.setVisible(true);
                    }


                    // Se l'utente logga come USER
                    case "USER" -> {
                        JOptionPane.showMessageDialog(panel1,
                                "Sei loggato con successo come user.");

                        Window currentWindow = SwingUtilities.getWindowAncestor(panel1);
                        if (currentWindow instanceof JFrame) {
                            ((JFrame) currentWindow).dispose();
                        }


                        JFrame newFrame = new JFrame("Aeroporto di Napoli - User Home");
                        UserDashboard userDashboard = new UserDashboard();
                        newFrame.setContentPane(userDashboard.getPanel());
                        newFrame.setLocationRelativeTo(null);
                        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        newFrame.pack();
                        newFrame.setVisible(true);
                    }


                    // Se fallisce il login
                    case "FALLITO" -> {
                        JOptionPane.showMessageDialog(panel1,
                                "Login non valido. Inserisci di nuovo le credenziali.");
                        resetFields();
                    }
                }

            }
        });

    }


    // Funzione getPanel
    public JPanel getPanel () {
        return panel1;
    }

    // Funzione che resetta username e password
    public void resetFields () {
        usernameTextField.setText("");
        passwordPasswordField.setText("");
    }

}