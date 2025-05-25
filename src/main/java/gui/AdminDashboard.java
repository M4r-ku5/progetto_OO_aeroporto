package gui;

import controller.Controller;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminDashboard {
    private JPanel mainPanel;
    private JPanel panel1;
    private JTextField codiceVoloTextField;
    private JTextField compagniaAereaTextField;
    private JTextField aeroportoOTextField;
    private JTextField aeroportoDTextField;
    private JTextField dataVoloTextField;
    private JTextField orarioPrevistoTextField;
    private JButton aggiungiVoloButton;
    private JButton aggiornaVoloButton;
    private JTable tabellaVoli;

    public Controller controller;

    public AdminDashboard() {
        this.controller = new Controller();

        loadFlightsTable();

        aggiungiVoloButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleAddFlight();
            }
        });

        aggiornaVoloButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleUpdateFlight();
            }
        });

        tabellaVoli.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // Controlla che l'evento non sia di "aggiustamento" e che una riga sia effettivamente selezionata
                if (!e.getValueIsAdjusting() && tabellaVoli.getSelectedRow() != -1) {
                    populateFieldsFromSelectedRow();
                }
            }
        });
    }

    public JPanel getPanel() {
        return mainPanel;
    }

    private void loadFlightsTable() {
        String[] columnNames = {"Codice Volo", "Compagnia Aerea", "Aeroporto di Partenza",
                                "Aeroporto di Arrivo", "Data Volo", "Orario Previsto"};

        Object[][] data = controller.getAllFlights();

        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames) {
        @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
    };
        tabellaVoli.setModel(tableModel);
    }

    private void populateFieldsFromSelectedRow() {
        int selectedRow = tabellaVoli.getSelectedRow();
        if (selectedRow >= 0) { // Assicurati che una riga sia selezionata
            DefaultTableModel model = (DefaultTableModel) tabellaVoli.getModel();
            codiceVoloTextField.setText(model.getValueAt(selectedRow, 0).toString());
            compagniaAereaTextField.setText(model.getValueAt(selectedRow, 1).toString());
            aeroportoOTextField.setText(model.getValueAt(selectedRow, 2).toString());
            aeroportoDTextField.setText(model.getValueAt(selectedRow, 3).toString());
            dataVoloTextField.setText(model.getValueAt(selectedRow, 4).toString());
            orarioPrevistoTextField.setText(model.getValueAt(selectedRow, 5).toString());

        }
    }

    private void handleAddFlight() {
        String codiceVolo = codiceVoloTextField.getText();
        String compagniaAerea = compagniaAereaTextField.getText();
        String aeroportoO = aeroportoOTextField.getText();
        String aeroportoD = aeroportoDTextField.getText();
        String dataVolo = dataVoloTextField.getText();
        String orarioPrevisto = orarioPrevistoTextField.getText();

        if (codiceVolo.isEmpty() || compagniaAerea.isEmpty() || dataVolo.isEmpty() || orarioPrevisto.isEmpty()) {
            JOptionPane.showMessageDialog(mainPanel, "Tutti i campi sono obbligatori.", "Errore", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean success = controller.addFlight(codiceVolo, compagniaAerea, aeroportoO, aeroportoD, dataVolo, orarioPrevisto);

        if (success) {
            JOptionPane.showMessageDialog(mainPanel, "Volo aggiunto con successo.", "Successo", JOptionPane.INFORMATION_MESSAGE);
            loadFlightsTable();
            clearFlightInputFields();
        } else {
            JOptionPane.showMessageDialog(mainPanel, "Errore nell'aggiunta del volo. Controlla i dati inseriti.", "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleUpdateFlight() {
        JOptionPane.showMessageDialog(panel1, "Funzionalità di aggiornamento del volo non ancora implementata.", "Info", JOptionPane.INFORMATION_MESSAGE);
        System.out.println("TODO: Implementaree la funzionalità di aggiornamento del volo.");
    }

    private void clearFlightInputFields() {
        codiceVoloTextField.setText("");
        compagniaAereaTextField.setText("");
        aeroportoOTextField.setText("");
        aeroportoDTextField.setText("");
        dataVoloTextField.setText("");
        orarioPrevistoTextField.setText("");
    }
}

