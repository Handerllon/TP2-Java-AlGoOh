package Modelo.carta.mano;

import Modelo.Jugador;
import Modelo.carta.Carta;
import Modelo.finDeJuego.CausaCincoPartesExodiaReunidas;
import Modelo.finDeJuego.CausaFinJuego;
import Modelo.finDeJuego.FinDeJuegoObservable;
import Modelo.observadores.ObservadorDeFinJuego;
import Modelo.observadores.ObservadorDeMano;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Mano implements FinDeJuegoObservable, ManoObservable
{
    private static int CANTIDAD_MAXIMA = 6;
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

    public void agregarCarta(Carta carta)
    {
        if (this.manoLlena())
        {
            // Se quita una carta al azar.
            // nextInt produce un conjunto abierto, por lo que no hay que sumarle 1 ya que el array de cartas arranca
            // en cero.
            int randomNum = ThreadLocalRandom.current().nextInt(0, cantidadDeCartas());
            quitarCarta(getCartas().get(randomNum));
        }
        this.cartas.add(carta);
        this.verificarAgregacionParteExodia(carta);
        this.verificarExodiaCompleto(carta);
        this.notificarIngresoDeCartaAMano();
    }

    public void quitarCarta(Carta carta)
    {
        if (!this.cartas.isEmpty())
        {
            this.cartas.remove(carta);
            this.verificarRemocionParteExodia(carta);
            this.notificarEgresoDeCartaAMano();
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
            this.notificarFinDeJuego(CausaCincoPartesExodiaReunidas.getInstancia(this.jugadorAsociado));
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
    public void notificarIngresoDeCartaAMano()
    {
        observadoresMano.forEach(observador -> observador.ingresoCartaAMano());
    }

    @Override
    public void notificarEgresoDeCartaAMano()
    {
        observadoresMano.forEach(observador -> observador.egresoCartaAMano());
    }
}
