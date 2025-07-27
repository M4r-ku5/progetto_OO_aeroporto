import dao.util.ConnessioneDatabase;
import gui.MainFrame;

import javax.swing.*;
import java.sql.SQLException;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * Classe principale dell'applicazione Sistema di Gestione Aeroportuale.
 * Contiene il metodo main per l'avvio dell'applicazione.
 */
public class Main {
    /** Logger per la classe Main. */
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    /**
     * Metodo principale per l'avvio dell'applicazione.
     * @param args argomenti da linea di comando
     */
    public static void main(String[] args) {

        // Test della connessione al database
        try {
            ConnessioneDatabase.getInstance();
            logger.info("Test connessione database riuscito");
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Errore connessione database", e);
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainFrame frame = new MainFrame();
                frame.showPanel("LOGIN");
                frame.setVisible(true);
            }
        });
    }
}