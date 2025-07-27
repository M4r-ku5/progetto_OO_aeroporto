package model.prenotazione;



// ******************************************************************************************
//  ******************************* ENUM STATO PRENOTAZIONE ********************************
//   ******************************************************************************************

/**
 * Enumerazione che rappresenta i possibili stati di una prenotazione nel sistema.
 */
public enum StatoPrenotazione {
    /** La prenotazione è stata confermata. */
    CONFERMATA,

    /** La prenotazione è in attesa di conferma o elaborazione. */
    IN_ATTESA,

    /** La prenotazione è stata cancellata. */
    CANCELLATA
}
