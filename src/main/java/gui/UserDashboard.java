package gui;

import javax.swing.*;

public class UserDashboard {
    private JPanel mainPanel;


    public UserDashboard() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JLabel welcomeLabel = new JLabel("Welcome to the User Dashboard");
        mainPanel.add(welcomeLabel);
    }

    public JPanel getPanel() {
        return mainPanel;
    }
}
