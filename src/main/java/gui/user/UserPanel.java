package gui.user;

import controller.Controller;
import gui.MainFrame;
import model.volo.Volo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;



// ******************************************************************************************
//  ********************************* CLASSE USER PANEL **************************************
//   ******************************************************************************************

/**
 * Pannello principale per gli utenti registrati.
 * Fornisce accesso alle funzionalità di prenotazione e gestione voli.
 */
public class UserPanel {
    /** Pannello principale del form. */
    private JPanel rootPanel;
    /** Tabella per la visualizzazione di tutti i voli disponibili. */
    private JTable allVoliTable;
    /** Pulsante per effettuare il logout. */
    private JButton logoutButton;
    /** Pulsante per accedere all'area personale. */
    private JButton areaPersonaleButton;
    /** Pulsante per effettuare una nuova prenotazione. */
    private JButton effettuaPrenotazioneButton;
    /** Pulsante per modificare una prenotazione esistente. */
    private JButton modificaPrenotazioneButton;
    /** Riferimento al frame principale. */
    private MainFrame mainFrame;
    /** Riferimento al controller principale. */
    private Controller controller;

    /**
     * Costruttore di UserPanel.
     * @param mainFrame Il frame principale
     * @param controller Il controller
     */
    public UserPanel(MainFrame mainFrame, Controller controller) {
        this.mainFrame = mainFrame;
        this.controller = controller;

        areaPersonaleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.showPanel("USER_AREA_PERSONALE");
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.logout();
                mainFrame.showPanel("LOGIN");
            }
        });
        effettuaPrenotazioneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.showPanel("EFFETTUA_PRENOTAZIONE");
            }
        });
        modificaPrenotazioneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.showPanel("MODIFICA_PRENOTAZIONE");
            }
        });
    }

    /**
     * Aggiorna tabella voli.
     */
    public void aggiornaTabellaVoli() {
        String[] columnNames = {"Codice Volo", "Compagnia", "Data", "Orario", "Stato", "Tipo"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        List<Volo> voli = controller.getVoli();
        for (Volo volo : voli) {
            Object[] row = new Object[]{
                    volo.getCodiceVolo(),
                    volo.getCompagniaAerea(),
                    volo.getDataVolo(),
                    volo.getOrarioVolo(),
                    volo.getStatoDelVolo(),
                    volo.getTipoVolo()
            };
            model.addRow(row);
        }
        allVoliTable.setModel(model);
    }

    /**
     * Restituisce il pannello principale.
     * @return il root panel
     */
    public JPanel getRootPanel() {
        return rootPanel;
    }
}