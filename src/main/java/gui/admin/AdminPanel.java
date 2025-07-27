package gui.admin;

import controller.Controller;
import gui.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



// ******************************************************************************************
//  ******************************* CLASSE ADMIN PANEL **************************************
//   ******************************************************************************************

/**
 * Pannello principale dell'amministratore.
 */
public class AdminPanel {
    /** Pannello principale del form. */
    private JPanel rootPanel;
    /** Pulsante per accedere all'inserimento di un nuovo volo. */
    private JButton inserisciVoloButton;
    /** Pulsante per accedere all'aggiornamento di un volo esistente. */
    private JButton aggiornaVoloButton;
    /** Pulsante per accedere all'assegnazione di un gate a un volo. */
    private JButton assegnaGateButton;
    /** Pulsante per effettuare il logout. */
    private JButton logoutButton;
    /** Pulsante per accedere alla ricerca passeggeri. */
    private JButton cercaPasseggeroButton;
    /** Pulsante per visualizzare tutti i voli. */
    private JButton visualizzaVoliButton;
    /** Riferimento al frame principale. */
    private MainFrame mainFrame;
    /** Riferimento al controller principale. */
    private Controller controller;

    /**
     * Costruttore del pannello AdminPanel.
     * @param mainFrame il frame principale
     * @param controller il controller dell'applicazione
     */
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
        cercaPasseggeroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.showPanel("CERCA_PASSEGERO");
            }
        });
        visualizzaVoliButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.showPanel("VISUALIZZA_VOLI");
            }
        });
    }

    /**
     * Restituisce il root panel del pannello.
     * @return il root panel
     */
    public JPanel getRootPanel() {
        return rootPanel;
    }
}