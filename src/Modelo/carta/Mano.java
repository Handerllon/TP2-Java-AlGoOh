package Modelo.carta;

import Modelo.Jugador;
import Modelo.carta.excepciones.ManoLlenaError;
import Modelo.finDeJuego.CausaCincoPartesExodiaReunidas;
import Modelo.finDeJuego.CausaFinJuego;
import Modelo.finDeJuego.FinJuegoObservable;
import Modelo.finDeJuego.ObservadorFinJuego;

import java.util.ArrayList;
import java.util.LinkedList;

public class Mano implements FinJuegoObservable
{
    private static int CANTIDAD_MAXIMA = 6;
    private static int contadorPartesExodia;
    private LinkedList<Carta> cartas;
    private Jugador jugadorAsociado;
    private ArrayList<ObservadorFinJuego> observadoresFinJuegos = new ArrayList<>();

    public Mano(Jugador jugador)
    {
        this.cartas = new LinkedList<>();
        this.contadorPartesExodia = 0;
        this.jugadorAsociado = jugador;
    }

    public void agregarCarta(Carta carta)
    {
        if (this.manoLlena() == false)
        {
            this.cartas.add(carta);
            this.verificarAgregacionParteExodia(carta);
            this.verificarExodiaCompleto(carta);
        } else
        {
            throw new ManoLlenaError();
        }
    }

    public void quitarCarta(Carta carta)
    {
        if (this.cartas.isEmpty() == false)
        {
            this.cartas.remove(carta);
            this.verificarRemocionParteExodia(carta);
        }
    }

    public int cantidadDeCartas()
    {
        return cartas.size();
    }

    public boolean manoLlena()
    {
        return this.cantidadDeCartas() == this.CANTIDAD_MAXIMA;
    }

    // --------------------------------------------------------------------
    // Metodos de verificación de fin de juego.
    // --------------------------------------------------------------------
    private void verificarExodiaCompleto(Carta carta)
    {

        if (this.contadorPartesExodia == 5)
        {
            this.notificarFinDeJuego(new CausaCincoPartesExodiaReunidas(this.jugadorAsociado));
        }
    }

    private void verificarAgregacionParteExodia(Carta carta)
    {
        if (carta.esParteExodia())
        {
            this.contadorPartesExodia++;
        }
    }

    private void verificarRemocionParteExodia(Carta carta)
    {
        if (carta.esParteExodia())
        {
            this.contadorPartesExodia--;
        }
    }

    // --------------------------------------------------------------------
    // Metodos de observadores de fin de juego.
    // --------------------------------------------------------------------
    @Override
    public void agregarObsevadorFinDeJuego(ObservadorFinJuego observador)
    {
        this.observadoresFinJuegos.add(observador);
    }

    @Override
    public void quitarObservadorFinDeJuego(ObservadorFinJuego observador)
    {
        if (this.observadoresFinJuegos.isEmpty() == false)
        {
            this.observadoresFinJuegos.remove(observador);
        }
    }

    @Override
    public void notificarFinDeJuego(CausaFinJuego causaFinJuego)
    {
        this.observadoresFinJuegos.forEach(item -> item.finDeJuego(causaFinJuego));
    }
}
