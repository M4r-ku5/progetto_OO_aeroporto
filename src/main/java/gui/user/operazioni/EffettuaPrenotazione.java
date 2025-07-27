package gui.user.operazioni;

import controller.Controller;
import gui.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



// ******************************************************************************************
//  ************************** CLASSE EFFETTUA PRENOTAZIONE *********************************
//   ******************************************************************************************

/**
 * Pannello per effettuare una nuova prenotazione da parte dell'utente.
 * Permette l'inserimento dei dati per creare una prenotazione.
 */
public class EffettuaPrenotazione {
    /** Pannello principale del form. */
    private JPanel rootPanel;
    /** Pulsante per tornare indietro al pannello utente. */
    private JButton indietroButton;
    /** Pulsante per confermare la prenotazione. */
    private JButton confermaButton;
    /** Campo di testo per il nome del passeggero. */
    private JTextField nomePasseggeroTextField;
    /** Campo di testo per il numero del biglietto. */
    private JTextField numeroBigliettoTextField;
    /** Campo di testo per il posto assegnato. */
    private JTextField postoTextField;
    /** Campo di testo per il codice del volo. */
    private JTextField codiceVoloTextField;

    /** Riferimento al frame principale. */
    private MainFrame mainFrame;
    /** Riferimento al controller principale. */
    private Controller controller;

    /**
     * Costruttore di EffettuaPrenotazione.
     * @param mainFrame Il frame principale.
     * @param controller Il controller.
     */
    public EffettuaPrenotazione(MainFrame mainFrame, Controller controller) {
        this.mainFrame = mainFrame;
        this.controller = controller;

        confermaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                effettuaPrenotazione();
            }
        });
        indietroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.showPanel("USER");
            }
        });
    }

    private void effettuaPrenotazione() {
        String nomePasseggero = nomePasseggeroTextField.getText().trim();
        String numeroBiglietto = numeroBigliettoTextField.getText().trim();
        String posto = postoTextField.getText().trim();
        String codiceVolo = codiceVoloTextField.getText().trim();

        try {
            boolean successo = controller.effettuaPrenotazione(codiceVolo, nomePasseggero, numeroBiglietto, posto);

            if (successo) {
                JOptionPane.showMessageDialog(rootPanel,
                    "Prenotazione effettuata con successo!",
                    "Successo",
                    JOptionPane.INFORMATION_MESSAGE);
                pulisciCampi();
            } else {
                JOptionPane.showMessageDialog(rootPanel,
                    "Errore durante la prenotazione!",
                    "Errore",
                    JOptionPane.ERROR_MESSAGE);
            }
        } catch (RuntimeException e) {
            // Gestisce l'eccezione quando il volo non esiste
            if (e.getMessage().contains("VOLO_NON_ESISTENTE")) {
                JOptionPane.showMessageDialog(rootPanel,
                    "Il volo con codice '" + codiceVolo + "' non esiste. Verifica il codice e riprova.",
                    "Volo non trovato",
                    JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(rootPanel,
                    "Errore imprevisto: " + e.getMessage(),
                    "Errore",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void pulisciCampi() {
        nomePasseggeroTextField.setText("");
        numeroBigliettoTextField.setText("");
        postoTextField.setText("");
        codiceVoloTextField.setText("");
    }

    /**
     * Restituisce il pannello principale.
     * @return il root panel
     */
    public JPanel getRootPanel() {
        return rootPanel;
    }
}
