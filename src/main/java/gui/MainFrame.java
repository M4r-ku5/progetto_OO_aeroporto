package gui;

import controller.Controller;
import gui.admin.AdminPanel;
import gui.admin.VisualizzaVoliPanel;
import gui.admin.operazioni.AggiornaVoloPanel;
import gui.admin.operazioni.AssegnaGatePanel;
import gui.admin.operazioni.CercaPasseggeroPanel;
import gui.admin.operazioni.InserisciVoloPanel;
import gui.guest.GuestPanel;
import gui.user.UserPanel;
import gui.user.operazioni.EffettuaPrenotazione;
import gui.user.operazioni.ModificaPrenotazionePanel;
import gui.user.UserAreaPersonalePanel;

import javax.swing.*;
import java.awt.*;

// ******************************************************************************************
//  ********************************* CLASSE MAIN FRAME *************************************
//   ******************************************************************************************

/**
 * Finestra principale dell'applicazione.
 * Gestisce il CardLayout e la visualizzazione dei vari pannelli dell'interfaccia grafica.
 */
public class MainFrame extends JFrame {
    /** Gestore del layout a schede per la visualizzazione dei pannelli. */
    private transient CardLayout cardLayout;
    /** Pannello contenitore per tutti i pannelli dell'applicazione. */
    private transient JPanel cardPanel;
    /** Controller principale dell'applicazione. */
    private transient Controller controller;

    // Pannelli dell'applicazione
    /** Form di login dell'applicazione. */
    private transient LoginForm loginForm;
    /** Pannello per gli utenti guest. */
    private transient GuestPanel guestPanel;
    /** Pannello amministratore principale. */
    private transient AdminPanel adminPanel;
    /** Pannello per l'inserimento di nuovi voli. */
    private transient InserisciVoloPanel inserisciVoloPanel;
    /** Pannello per l'aggiornamento dei voli esistenti. */
    private transient AggiornaVoloPanel aggiornaVoloPanel;
    /** Pannello per l'assegnazione dei gate ai voli. */
    private transient AssegnaGatePanel assegnaGatePanel;
    /** Pannello utente principale. */
    private transient UserPanel userPanel;
    /** Pannello area personale utente. */
    private transient UserAreaPersonalePanel userAreaPersonalePanel;
    /** Pannello per effettuare prenotazioni. */
    private transient EffettuaPrenotazione effettuaPrenotazionePanel;
    /** Pannello per modificare prenotazioni esistenti. */
    private transient ModificaPrenotazionePanel modificaPrenotazionePanel;
    /** Pannello per la ricerca passeggeri. */
    private transient CercaPasseggeroPanel cercaPasseggeroPanel;
    /** Pannello per la visualizzazione di tutti i voli. */
    private transient VisualizzaVoliPanel visualizzaVoliPanel;

    /**
     * Costruttore della finestra principale MainFrame.
     */
    public MainFrame() {
        controller = new Controller();
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Inizializzazione dei pannelli
        loginForm = new LoginForm(this, controller);
        guestPanel = new GuestPanel(this, controller);
        adminPanel = new AdminPanel(this, controller);
        inserisciVoloPanel = new InserisciVoloPanel(this, controller);
        aggiornaVoloPanel = new AggiornaVoloPanel(this, controller);
        assegnaGatePanel = new AssegnaGatePanel(this, controller);
        userPanel = new UserPanel(this, controller);
        userAreaPersonalePanel = new UserAreaPersonalePanel(this, controller);
        effettuaPrenotazionePanel = new EffettuaPrenotazione(this, controller);
        modificaPrenotazionePanel = new ModificaPrenotazionePanel(this, controller);
        cercaPasseggeroPanel = new CercaPasseggeroPanel(this, controller);
        visualizzaVoliPanel = new VisualizzaVoliPanel(this, controller);

        // Aggiunta dei pannelli al CardLayout
        cardPanel.add(loginForm.getRootPanel(), "LOGIN");
        cardPanel.add(guestPanel.getRootPanel(), "GUEST");
        cardPanel.add(adminPanel.getRootPanel(), "ADMIN");
        cardPanel.add(inserisciVoloPanel.getRootPanel(), "INSERISCI_VOLO");
        cardPanel.add(aggiornaVoloPanel.getRootPanel(), "AGGIORNA_VOLO");
        cardPanel.add(assegnaGatePanel.getRootPanel(), "ASSEGNA_GATE");
        cardPanel.add(userPanel.getRootPanel(), "USER");
        cardPanel.add(userAreaPersonalePanel.getRootPanel(), "USER_AREA_PERSONALE");
        cardPanel.add(effettuaPrenotazionePanel.getRootPanel(), "EFFETTUA_PRENOTAZIONE");
        cardPanel.add(modificaPrenotazionePanel.getRootPanel(), "MODIFICA_PRENOTAZIONE");
        cardPanel.add(cercaPasseggeroPanel.getRootPanel(), "CERCA_PASSEGERO");
        cardPanel.add(visualizzaVoliPanel.getRootPanel(), "VISUALIZZA_VOLI");

        this.setContentPane(cardPanel);
        this.setTitle("Sistema di Gestione Aeroportuale");
        this.setSize(900, 700);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.pack();
    }

    /**
     * Mostra il pannello specificato dal nome.
     * Aggiorna i dati dei pannelli prima della visualizzazione.
     * @param panelName il nome del pannello da mostrare
     */
    public void showPanel(String panelName) {
        if ("USER".equals(panelName)) {
            userPanel.aggiornaTabellaVoli();
        } else if ("USER_AREA_PERSONALE".equals(panelName)) {
            userAreaPersonalePanel.aggiornaTabellaPrenotazioni("");
        } else if ("GUEST".equals(panelName)) {
            guestPanel.aggiornaTabellaVoli();
        } else if ("CERCA_PASSEGERO".equals(panelName)) {
            cercaPasseggeroPanel.mostraPasseggeri();
        } else if ("VISUALIZZA_VOLI".equals(panelName)) {
            visualizzaVoliPanel.aggiornaTabellaVoli();
        }
        cardLayout.show(cardPanel, panelName);
    }
}