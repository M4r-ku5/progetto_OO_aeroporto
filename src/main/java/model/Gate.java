package model;


// ******************************************************************************************
//  ************************************ CLASSE GATE *****************************************
//   ******************************************************************************************

public class Gate {

    /** Numero identificativo del Gate. */
    private short numeroGate;




    /**
     * Costruttore della classe Gate.
     * @param numeroGate Numero identificativo del Gate.
     */
    public Gate(short numeroGate) {
        this.numeroGate = numeroGate;
    }




// *****************************************************************************************
//  ****************************** METODI GETTER E SETTER ***********************************
//   *****************************************************************************************

    /**
     * Getter di numeroGate.
     * @return numeroGate.
     */
    public short getNumeroGate() {
        return numeroGate;
    }

    /**
     * Setter di numeroGate.
     * @param numeroGate Numero identificativo del Gate.
     */
    public void setNumeroGate(short numeroGate) {
        this.numeroGate = numeroGate;
    }

}