package gui.admin.operazioni;

import controller.Controller;
import gui.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

// ******************************************************************************************
//  **************************** CLASSE INSERISCI VOLO PANEL ********************************
//   ******************************************************************************************

/**
 * Pannello per l'inserimento di un nuovo volo da parte dell'amministratore.
 */
public class InserisciVoloPanel {
    /** Pannello principale del form. */
    private JPanel rootPanel;
    /** Campo di testo per il codice del volo. */
    private JTextField codiceTextField;
    /** Campo di testo per la compagnia aerea. */
    private JTextField compagniaTextField;
    /** Campo di testo per la data del volo. */
    private JTextField dataTextField;
    /** Campo di testo per l'orario del volo. */
    private JTextField orarioTextField;
    /** ComboBox per selezionare il tipo di volo (partenza/arrivo). */
    private JComboBox<String> tipoVolo;
    /** Campo di testo per l'aeroporto di origine/destinazione. */
    private JTextField aeroportoTextField;
    /** Label dinamica per "Aeroporto di Destinazione" o "Aeroporto di Origine". */
    private JLabel aeroportoLabel;
    /** Pulsante per confermare l'inserimento del volo. */
    private JButton confermaButton;
    /** Pulsante per tornare indietro al pannello admin. */
    private JButton indietroButton;

    /** Riferimento al frame principale. */
    private MainFrame mainFrame;
    /** Riferimento al controller principale. */
    private Controller controller;

    /**
     * Costruttore del pannello InserisciVoloPanel.
     * @param mainFrame il frame principale
     * @param controller il controller dell'applicazione
     */
    public InserisciVoloPanel(MainFrame mainFrame, Controller controller) {
        this.mainFrame = mainFrame;
        this.controller = controller;

        tipoVolo.addActionListener(e -> aggiornaLabelAeroporto());
        aggiornaLabelAeroporto();

        confermaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inserisciVolo();
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
     * Aggiorna la label dell'aeroporto in base al tipo di volo selezionato.
     */
    private void aggiornaLabelAeroporto() {
        String tipoSelezionato = (String) tipoVolo.getSelectedItem();
        if ("Volo in Partenza".equals(tipoSelezionato)) {
            aeroportoLabel.setText("Aeroporto di Destinazione");
        } else {
            aeroportoLabel.setText("Aeroporto di Origine");
        }
    }

    /**
     * Gestisce l'inserimento di un nuovo volo, validando i dati e mostrando eventuali errori.
     */
    private void inserisciVolo() {
        String codice = codiceTextField.getText().trim();
        String compagnia = compagniaTextField.getText().trim();
        String dataStr = dataTextField.getText().trim();
        String orarioStr = orarioTextField.getText().trim();
        String aeroporto = aeroportoTextField.getText().trim();
        String tipo = (String) tipoVolo.getSelectedItem();

        try {
            LocalDate data = LocalDate.parse(dataStr);
            LocalTime orario = LocalTime.parse(orarioStr);

            boolean successo;
            if ("Volo in Partenza".equals(tipo)) {
                successo = controller.aggiungiVoloInPartenza(codice, compagnia, data, orario, aeroporto);
            } else {
                successo = controller.aggiungiVoloInArrivo(codice, compagnia, data, orario, aeroporto);
            }

            if (successo) {
                JOptionPane.showMessageDialog(rootPanel,
                    "Volo inserito con successo!",
                    "Successo",
                    JOptionPane.INFORMATION_MESSAGE);
                pulisciCampi();
            } else {
                JOptionPane.showMessageDialog(rootPanel,
                    "Errore durante l'inserimento del volo!",
                    "Errore",
                    JOptionPane.ERROR_MESSAGE);
            }

        } catch (DateTimeParseException ex) {
            String campo = ex.getParsedString().contains(":") ? "orario" : "data";
            String formato = campo.equals("data") ? "YYYY-MM-DD" : "HH:MM";

            JOptionPane.showMessageDialog(rootPanel,
                "Formato " + campo + " non valido!\n\n" +
                "Formato richiesto: " + formato,
                "Errore di Formato",
                JOptionPane.ERROR_MESSAGE);
        } catch (RuntimeException ex) {
            JOptionPane.showMessageDialog(rootPanel,
                "Errore durante l'inserimento del volo: " + ex.getMessage(),
                "Errore",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Pulisce tutti i campi del form dopo l'inserimento o in caso di reset.
     */
    private void pulisciCampi() {
        codiceTextField.setText("");
        compagniaTextField.setText("");
        dataTextField.setText("");
        orarioTextField.setText("");
        aeroportoTextField.setText("");
        tipoVolo.setSelectedIndex(0);
        aggiornaLabelAeroporto();
    }

    /**
     * Restituisce il root panel del pannello.
     * @return il root panel
     */
    public JPanel getRootPanel() {
        return rootPanel;
    }
}
