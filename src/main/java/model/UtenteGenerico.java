package model;
import java.util.ArrayList;
import java.util.List;

public class UtenteGenerico extends Utente {

    private List<Prenotazione> listaPrenotazioni;

    public UtenteGenerico(String login, String password, Prenotazione prenotazione_1) {
        super(login, password);
        this.listaPrenotazioni = new ArrayList<>();
        listaPrenotazioni.add(prenotazione_1);
    }

    public List<Prenotazione> getPrenotazione() {
        return listaPrenotazioni;
    }

    public void aggiungiPrenotazione (Prenotazione p) {
        listaPrenotazioni.add(p);
    }

    public void effettuaPrenotazione () { };
    public void cercaPrenotazione () { };
    public void inserisciGate () { };
}