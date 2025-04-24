package model;
import java.util.ArrayList;
import java.util.List;

public class Gate {

    private short numero;
    private List<VoloInPartenza> listaVoliInPartenza;


    public Gate (short numero, VoloInPartenza voloInPartenza_1) {

        this.numero = numero;
        this.listaVoliInPartenza = new ArrayList<>();
        listaVoliInPartenza.add(voloInPartenza_1);

    }


    public short getNumero () {
        return numero;
    }

    public void setNumero (short numero) {
        this.numero = numero;
    }


    public List<VoloInPartenza> getListaVoliInPartenza () {
        return listaVoliInPartenza;
    }

    public void setListaVoliInPartenza(VoloInPartenza voloInPartenza_1) {
        listaVoliInPartenza.add(voloInPartenza_1);
    }

}
