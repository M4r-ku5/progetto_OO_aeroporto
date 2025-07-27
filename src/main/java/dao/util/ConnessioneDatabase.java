package dao.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe di utilità per gestire la connessione al database PostgreSQL.
 * Implementa il pattern Singleton per garantire una sola connessione al database.
 */
public class ConnessioneDatabase {
    /** Logger per la classe. */
    private static final Logger logger = Logger.getLogger(ConnessioneDatabase.class.getName());

    /** Istanza singleton della classe. */
    private static ConnessioneDatabase instance;

    /** Connessione al database. */
    private Connection connection;

    /** Parametri di connessione al database. */
    private static final String URL = "jdbc:postgresql://localhost:5432/Aeroporto";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "admin";

    /**
     * Costruttore privato per implementare il pattern Singleton.
     * Inizializza la connessione al database PostgreSQL.
     */
    private ConnessioneDatabase() throws SQLException {
        try {
            // Carica il driver PostgreSQL
            Class.forName("org.postgresql.Driver");

            // Stabilisce la connessione
            this.connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            logger.log(Level.INFO, "Connessione al database stabilita con successo");
        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, "Driver PostgreSQL non trovato", e);
            throw new SQLException("Driver PostgreSQL non disponibile", e);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Errore durante la connessione al database", e);
            throw e;
        }
    }

    /**
     * Restituisce l'istanza singleton della classe.
     * @return l'istanza unica di ConnessioneDatabase
     * @throws SQLException se si verifica un errore durante la creazione della connessione
     */
    public static synchronized ConnessioneDatabase getInstance() throws SQLException {
        if (instance == null) {
            instance = new ConnessioneDatabase();
        }
        return instance;
    }

    /**
     * Restituisce la connessione al database.
     * @return la connessione al database
     * @throws SQLException se la connessione non è valida
     */
    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            // Riconnessione se la connessione è stata chiusa
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            logger.log(Level.INFO, "Connessione al database ristabilita");
        }
        return connection;
    }

    /**
     * Chiude la connessione al database.
     */
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                logger.log(Level.INFO, "Connessione al database chiusa");
            }
        } catch (SQLException e) {
            logger.log(Level.WARNING, "Errore durante la chiusura della connessione", e);
        }
    }

    /**
     * Verifica se la connessione è attiva.
     * @return true se la connessione è attiva, false altrimenti
     */
    public boolean isConnectionActive() {
        try {
            return connection != null && !connection.isClosed() && connection.isValid(5);
        } catch (SQLException e) {
            logger.log(Level.WARNING, "Errore durante la verifica della connessione", e);
            return false;
        }
    }
}
