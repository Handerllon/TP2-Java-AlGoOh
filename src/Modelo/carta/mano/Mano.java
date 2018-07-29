package Modelo.carta.mano;

import Modelo.Jugador;
import Modelo.carta.Carta;
import Modelo.carta.excepciones.ManoLlenaError;
import Modelo.finDeJuego.CausaCincoPartesExodiaReunidas;
import Modelo.finDeJuego.CausaFinJuego;
import Modelo.finDeJuego.FinDeJuegoObservable;
import Modelo.observadores.ObservadorDeFinJuego;
import Modelo.observadores.ObservadorDeMano;

import java.util.ArrayList;

public class Mano implements FinDeJuegoObservable, ManoObservable
{
    private static int CANTIDAD_MAXIMA = 7;
    private static int contadorPartesExodia;
    private ArrayList<Carta> cartas;
    private Jugador jugadorAsociado;
    private ArrayList<ObservadorDeFinJuego> observadoresFinJuegos = new ArrayList<>();
    private ArrayList<ObservadorDeMano> observadoresMano = new ArrayList<>();

    public Mano(Jugador jugador)
    {
        this.cartas = new ArrayList<>();
        this.contadorPartesExodia = 0;
        this.jugadorAsociado = jugador;
    }

    public void agregarCarta(Carta carta) throws ManoLlenaError
    {
        if (!this.manoLlena())
        {
            this.cartas.add(carta);
            this.notificarEvento();
            this.verificarAgregacionParteExodia(carta);
            this.verificarExodiaCompleto(carta);
        } else
        {
            // TODO: Recordar que si la mano está llena, la vista debe pedirle al usuario que descarte 1 de ellas.
            // También podría hacerse que se descarte alguna al azar automáticamente.
            quitarCarta(getCartas().iterator().next());
            throw new ManoLlenaError();
        }
    }

    public void quitarCarta(Carta carta)
    {
        if (!this.cartas.isEmpty())
        {
            this.cartas.remove(carta);
            this.notificarEvento();
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

    public ArrayList<Carta> getCartas()
    {
        return this.cartas;
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
    // Metodos por ser un observable de Fin De Juego.
    // --------------------------------------------------------------------
    @Override
    public void agregarObsevadorFinDeJuego(ObservadorDeFinJuego observador)
    {
        this.observadoresFinJuegos.add(observador);
    }

    @Override
    public void quitarObservadorFinDeJuego(ObservadorDeFinJuego observador)
    {
        this.observadoresFinJuegos.remove(observador);
    }

    @Override
    public void notificarFinDeJuego(CausaFinJuego causaFinJuego)
    {
        this.observadoresFinJuegos.forEach(item -> item.seLlegoAFinDeJuego(causaFinJuego));
    }

    // --------------------------------------------------------------------
    // Metodos por ser observable de Mano.
    // --------------------------------------------------------------------
    @Override
    public void registrarObsevador(ObservadorDeMano observador)
    {
        observadoresMano.add(observador);
    }

    @Override
    public void eliminarObservador(ObservadorDeMano observador)
    {
        observadoresMano.remove(observador);
    }

    @Override
    public void notificarEvento()
    {
        observadoresMano.forEach(observador -> observador.huboCambios());
    }
}
