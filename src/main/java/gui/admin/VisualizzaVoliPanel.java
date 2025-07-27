package gui.admin;

import controller.Controller;
import gui.MainFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



// ******************************************************************************************
//  *************************** CLASSE VISUALIZZA VOLI PANEL ********************************
//   ******************************************************************************************

/**
 * Pannello per la visualizzazione di tutti i voli presenti nel sistema.
 */
public class VisualizzaVoliPanel {
    /** Pannello principale del form. */
    private JPanel rootPanel;
    /** Tabella per la visualizzazione della lista dei voli. */
    private JTable listaVoliTable;
    /** Pulsante per tornare indietro al pannello admin. */
    private JButton indietroButton;
    /** Riferimento al frame principale. */
    private MainFrame mainFrame;
    /** Riferimento al controller principale. */
    private Controller controller;

    /**
     * Costruttore del pannello VisualizzaVoliPanel.
     * @param mainFrame il frame principale
     * @param controller il controller dell'applicazione
     */
    public VisualizzaVoliPanel(MainFrame mainFrame, Controller controller) {
        this.mainFrame = mainFrame;
        this.controller = controller;

        indietroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.showPanel("ADMIN");
            }
        });
    }

    /**
     * Aggiorna la tabella dei voli con i dati dal database.
     */
    public void aggiornaTabellaVoli() {
        String[] columnNames = controller.getVoliColumnNames();
        Object[][] data = controller.getVoliDataForTable();

        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        listaVoliTable.setModel(model);
    }

    /**
     * Restituisce il pannello principale.
     * @return il root panel
     */
    public JPanel getRootPanel() {
        return rootPanel;
    }
}
