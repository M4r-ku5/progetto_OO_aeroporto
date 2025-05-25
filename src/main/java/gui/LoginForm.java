package gui;

import controller.Controller;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm {
    private JPanel panel1;
    private JTextField usernameTextField;
    private JPasswordField userPasswordField;
    private JButton loginButton;

    private Controller controller;

    public LoginForm() {
        this.controller = new Controller();

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        });
    }


public JPanel getPanel() {
        return panel1;
    }

    private void handleLogin() {
        String username = usernameTextField.getText();
        String password = new String(userPasswordField.getPassword());

        String userRole = controller.authenticate(username, password);

        if (userRole != null) {
            JOptionPane.showMessageDialog(panel1, "Login successful! Role: " + userRole);
            SwingUtilities.getWindowAncestor(panel1).dispose();

            if (userRole.equals("admin")) {
                AdminDashboard adminDashboard = new AdminDashboard();
                JFrame adminFrame = new JFrame("Admin Dashboard - Aeroporto di Napoli");
                adminFrame.setContentPane(adminDashboard.getPanel());
                adminFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                adminFrame.pack();
                adminFrame.setLocationRelativeTo(null);
                adminFrame.setVisible(true);
                System.out.println("Admin dashboard opened.");

            } else if (userRole.equals("user")) {
                UserDashboard userDashboard = new UserDashboard();
                JFrame userFrame = new JFrame("User Dashboard - Aeroporto di Napoli");
                userFrame.setContentPane(userDashboard.getPanel());
                userFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                userFrame.pack();
                userFrame.setLocationRelativeTo(null);
                userFrame.setVisible(true);
                System.out.println("User dashboard opened.");
            }

        } else {
            JOptionPane.showMessageDialog(panel1, "Invalid username or password.", "Login Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}