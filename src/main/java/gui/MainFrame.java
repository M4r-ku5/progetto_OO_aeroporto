package gui;

import controller.Controller;
import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private CardLayout cardLayout;
    private JPanel cardPanel;
    private Controller controller;

    private LoginForm loginForm;
    private AdminPanel adminPanel;
    private InserisciVoloPanel inserisciVoloPanel;
    private AggiornaVoloPanel aggiornaVoloPanel;

    public MainFrame() {
        controller = new Controller();
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        loginForm = new LoginForm(this, controller);
        adminPanel = new AdminPanel(this, controller);
        inserisciVoloPanel = new InserisciVoloPanel(this, controller);
        aggiornaVoloPanel = new AggiornaVoloPanel(this, controller);

        cardPanel.add(loginForm.getRootPanel(), "LOGIN");
        cardPanel.add(adminPanel.getRootPanel(), "ADMIN");
        cardPanel.add(inserisciVoloPanel.getRootPanel(), "INSERISCI_VOLO");
        cardPanel.add(aggiornaVoloPanel.getRootPanel(), "AGGIORNA_VOLO");

        this.setContentPane(cardPanel);
        this.setTitle("Sistema di Gestione Aeroportuale");
        this.setSize(900, 700);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void showPanel(String panelName) {
        cardLayout.show(cardPanel, panelName);
    }
}