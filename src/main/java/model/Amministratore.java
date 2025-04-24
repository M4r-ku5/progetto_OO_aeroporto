package model;
import java.util.ArrayList;
import java.util.List;

public class Amministratore extends Utente {

    private List<Volo> listaVoli;

    public Amministratore (String login, String password, Volo volo_1) {
        super (login, password);
        this.listaVoli = new ArrayList<>();
        listaVoli.add(volo_1);
    }


    public List<Volo> getListaVoli() {
        return listaVoli;
    }

    public void setListaVoli(List<Volo> listaVoli) {
        this.listaVoli = listaVoli;
    }


    public void inserisciVolo () { };
    public void aggiornaVolo () { };
    public void assegnaGate () { };
}
