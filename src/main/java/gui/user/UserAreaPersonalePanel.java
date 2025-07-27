package gui.user;

import controller.Controller;
import gui.MainFrame;
import model.prenotazione.Prenotazione;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

// ******************************************************************************************
//  ************************* CLASSE USER AREA PERSONALE PANEL *******************************
//   ******************************************************************************************

/**
 * Pannello dell'area personale dell'utente.
 * Permette la visualizzazione e ricerca delle proprie prenotazioni.
 */
public class UserAreaPersonalePanel {
    /** Pannello principale del form. */
    private JPanel rootPanel;
    /** Tabella per la visualizzazione delle prenotazioni dell'utente. */
    private JTable prenotazioniTable;
    /** Pulsante per tornare indietro al pannello utente principale. */
    private JButton indietroButton;
    /** Pulsante per effettuare il logout. */
    private JButton logoutButton;
    /** Campo di testo per il criterio di ricerca delle prenotazioni. */
    private JTextField criterioTextField;
    /** Pulsante per cercare prenotazioni specifiche. */
    private JButton cercaButton;
    /** Riferimento al frame principale. */
    private MainFrame mainFrame;
    /** Riferimento al controller principale. */
    private Controller controller;

    /**
     * Instantiates a new User area personale panel.
     *
     * @param mainFrame  the main frame
     * @param controller the controller
     */
    public UserAreaPersonalePanel(MainFrame mainFrame, Controller controller) {
        this.mainFrame = mainFrame;
        this.controller = controller;

        cercaButton.addActionListener(e -> aggiornaTabellaPrenotazioni(criterioTextField.getText()));

        indietroButton.addActionListener(e -> mainFrame.showPanel("USER"));

        logoutButton.addActionListener(e -> {
            controller.logout();
            mainFrame.showPanel("LOGIN");
        });
    }

    /**
     * Aggiorna tabella prenotazioni.
     * @param criterio Il criterio per la ricerca.
     */
    public void aggiornaTabellaPrenotazioni(String criterio) {
        String[] columnNames = {"Nome Passeggero", "Numero Biglietto", "Posto", "Codice Volo", "Stato"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        try {
            List<Prenotazione> prenotazioni = controller.cercaPrenotazioniUtente(criterio);
            for (Prenotazione p : prenotazioni) {
                String codiceVolo = "";
                if (p.getVoloInPartenza() != null) {
                    codiceVolo = p.getVoloInPartenza().getCodiceVolo();
                }

                Object[] row = new Object[]{
                        p.getNomePasseggero(),
                        p.getNumeroBiglietto(),
                        p.getPostoAssegnato(),
                        codiceVolo,
                        p.getStatoDellaPrenotazione()
                };
                model.addRow(row);
            }
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(rootPanel,
                "Errore durante il caricamento delle prenotazioni: " + e.getMessage(),
                "Errore",
                JOptionPane.ERROR_MESSAGE);
        }

        prenotazioniTable.setModel(model);
    }

    /**
     * Restituisce il pannello principale.
     * @return il root panel
     */
    public JPanel getRootPanel() {
        return rootPanel;
    }
}