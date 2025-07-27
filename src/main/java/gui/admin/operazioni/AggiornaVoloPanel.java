package gui.admin.operazioni;

import controller.Controller;
import gui.MainFrame;
import model.volo.StatoVolo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;



// ******************************************************************************************
//  ************************** CLASSE AGGIORNA VOLO PANEL ***********************************
//   ******************************************************************************************

/**
 * Pannello per l'aggiornamento delle informazioni di un volo da parte dell'amministratore.
 */
public class AggiornaVoloPanel {
    /** Pannello principale del form. */
    private JPanel rootPanel;
    /** Campo di testo per il codice del volo. */
    private JTextField codiceVoloTextField;
    /** Campo di testo per l'orario del volo. */
    private JTextField orarioTextField;
    /** Campo di testo per il ritardo del volo (in minuti). */
    private JTextField ritardoTextField;
    /** ComboBox per selezionare lo stato del volo. */
    private JComboBox<String> statoComboBox;
    /** Pulsante per confermare l'aggiornamento. */
    private JButton confermaButton;
    /** Pulsante per tornare indietro al pannello admin. */
    private JButton indietroButton;

    /** Riferimento al frame principale. */
    private MainFrame mainFrame;
    /** Riferimento al controller principale. */
    private Controller controller;

    /** Costante per gli errori. */
    private static final String ERRORE = "Errore";

    /**
     * Costruttore del pannello AggiornaVoloPanel.
     * @param mainFrame il frame principale
     * @param controller il controller dell'applicazione
     */
    public AggiornaVoloPanel(MainFrame mainFrame, Controller controller) {
        this.mainFrame = mainFrame;
        this.controller = controller;

        statoComboBox.setModel(new DefaultComboBoxModel<>(new String[]{
                "PROGRAMMATO","IN_RITARDO", "ATTERRATO", "CANCELLATO"
        }));


        confermaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aggiornaVolo();
            }
        });

        indietroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.showPanel("ADMIN");
            }
        });
    }

    /**
     * Gestisce l'aggiornamento dei dati del volo, validando i dati e mostrando eventuali errori.
     */
    private void aggiornaVolo() {
        String codiceVolo = codiceVoloTextField.getText();
        String orarioStr = orarioTextField.getText();
        String ritardoStr = ritardoTextField.getText();
        String statoStr = (String) statoComboBox.getSelectedItem();

        if (codiceVolo.isEmpty() || orarioStr.isEmpty() || ritardoStr.isEmpty() || statoStr == null) {
            JOptionPane.showMessageDialog(rootPanel, "Tutti i campi sono obbligatori.", ERRORE , JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            LocalTime nuovoOrario = LocalTime.parse(orarioStr); // Per il formato HH:mm
            Duration nuovoRitardo = Duration.ofMinutes(Long.parseLong(ritardoStr)); // Per il ritardo in minuti
            StatoVolo nuovoStato = StatoVolo.valueOf(statoStr.toUpperCase().replace(" ", "_"));

            boolean successo = controller.aggiornaVolo(codiceVolo, nuovoOrario, nuovoRitardo, nuovoStato);

            if (successo) {
                JOptionPane.showMessageDialog(rootPanel, "Volo aggiornato con successo!", "Successo", JOptionPane.INFORMATION_MESSAGE);
                pulisciCampi();
            } else {
                JOptionPane.showMessageDialog(rootPanel, "Errore: Volo non trovato o utente non autorizzato.", ERRORE, JOptionPane.ERROR_MESSAGE);
            }

        } catch (DateTimeParseException _) {
            JOptionPane.showMessageDialog(rootPanel, "Formato orario non valido. Usare HH:mm.", "Errore di Formato", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException _) {
            JOptionPane.showMessageDialog(rootPanel, "Formato ritardo non valido. Inserire i minuti.", "Errore di Formato", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException _) {
            JOptionPane.showMessageDialog(rootPanel, "Stato del volo non valido.", ERRORE, JOptionPane.ERROR_MESSAGE);
        } catch (RuntimeException ex) {
            JOptionPane.showMessageDialog(rootPanel,
                "Errore durante l'aggiornamento del volo: " + ex.getMessage(),
                ERRORE,
                JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Pulisce tutti i campi del form dopo l'aggiornamento o in caso di reset.
     */
    private void pulisciCampi() {
        codiceVoloTextField.setText("");
        orarioTextField.setText("");
        ritardoTextField.setText("");
        statoComboBox.setSelectedIndex(0);
    }

    /**
     * Restituisce il root panel del pannello.
     * @return il root panel
     */
    public JPanel getRootPanel() {
        return rootPanel;
    }
}
