package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminPanel {
    private JPanel rootPanel;
    private JButton inserisciVoloButton;
    private JButton aggiornaVoloButton;
    private JButton assegnaGateButton;
    private JButton logoutButton;
    private MainFrame mainFrame;
    private Controller controller;

    public AdminPanel(MainFrame mainFrame, Controller controller) {
        this.mainFrame = mainFrame;
        this.controller = controller;

        inserisciVoloButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.showPanel("INSERISCI_VOLO");
            }
        });

        aggiornaVoloButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.showPanel("AGGIORNA_VOLO");
            }
        });

        assegnaGateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.showPanel("ASSEGNA_GATE");
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.logout();
                mainFrame.showPanel("LOGIN");
            }
        });
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }
}