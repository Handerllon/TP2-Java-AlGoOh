package Modelo.carta;

import Modelo.carta.excepciones.NoSePuedenAgregarMasCartasALaMano;
import Vista.ObjectoObservador;

import java.util.ArrayList;
import java.util.LinkedList;

public class Mano
{
    private static int CANTIDAD_MAXIMA = 6;
    private LinkedList<Carta> cartas;
    private ArrayList<ObjectoObservador> observadores = new ArrayList<>();

    public Mano()
    {
        this.cartas = new LinkedList<>();
    }

    public void agregarCarta(Carta carta)
    {
        if (this.cantidadDeCartas() <= CANTIDAD_MAXIMA)
        {
            cartas.add(carta);
        } else throw new NoSePuedenAgregarMasCartasALaMano();
    }

    public void quitarCarta(Carta carta)
    {
        this.cartas.remove(carta);
    }

    public Carta quitarUltimaCarta()
    {
        return this.cartas.removeLast();
    }

    public int cantidadDeCartas()
    {
        return cartas.size();
    }

    public boolean manoLlena()
    {
        return this.cantidadDeCartas() == this.CANTIDAD_MAXIMA;
    }

    /*
    private ArrayList<Carta> obtenerCartasEnMano(){
    	
    	return this.cartas;
    }
*/
}
