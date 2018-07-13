package Modelo.carta;

import Modelo.carta.excepciones.NoSePuedenAgregarMasCartasALaMano;
import Modelo.finDeJuego.CausaFinJuego;
import Modelo.finDeJuego.FinJuegoObservable;
import Modelo.finDeJuego.ObservadorFinJuego;

import java.util.LinkedList;

public class Mano implements FinJuegoObservable
{
    private static int CANTIDAD_MAXIMA = 6;
    private LinkedList<Carta> cartas;

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

    @Override
    public void agregarObsevador(ObservadorFinJuego observador)
    {

    }

    @Override
    public void quitarObservador(ObservadorFinJuego observador)
    {

    }

    @Override
    public void notificarFinDeJuego(CausaFinJuego causaFinJuego)
    {

    }
}
