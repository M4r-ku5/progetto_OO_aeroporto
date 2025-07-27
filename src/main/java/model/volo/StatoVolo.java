package model.volo;



// ******************************************************************************************
//  *********************************** ENUM STATO VOLO ************************************
//   ******************************************************************************************

/**
 * Enumerazione che rappresenta i possibili stati di un volo nel sistema.
 */
public enum StatoVolo {
    /** Il volo è programmato. */
    PROGRAMMATO,

    /** Il volo ha accumulato ritardo rispetto all'orario programmato. */
    IN_RITARDO,

    /** Il volo è atterrato. */
    ATTERRATO,

    /** Il volo è stato cancellato e non verrà effettuato. */
    CANCELLATO
}
