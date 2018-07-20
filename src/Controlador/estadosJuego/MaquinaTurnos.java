package Controlador.estadosJuego;

import Controlador.Controlador;
import Modelo.Jugador;

import java.util.concurrent.ThreadLocalRandom;

public final class MaquinaTurnos
{
    private static MaquinaTurnos instancia = null;
    private MaquinaFase maquinaFaseJugador1, maquinaFaseJugador2;
    private MaquinaFase maquinaFaseActiva, maquinaFaseEnEspera;
    private Controlador controlador;
    private Jugador jugador1, jugador2, jugadorInicialSorteado;

    // --------------------------------------------------------------------
    // Métodos de construcción e inicialización.
    // --------------------------------------------------------------------
    private MaquinaTurnos(Jugador jugador1, Jugador jugador2, Controlador controlador)
    {
        this.controlador = controlador;
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.maquinaFaseJugador1 = new MaquinaFase(jugador1, controlador);
        this.maquinaFaseJugador2 = new MaquinaFase(jugador2, controlador);

        this.sortearJugadorInicial();
    }

    public static MaquinaTurnos obtenerInstancia(Jugador jugador1, Jugador jugador2, Controlador controlador)
    {
        if (instancia == null)
        {
            instancia = new MaquinaTurnos(jugador1, jugador2, controlador);
        }
        return instancia;
    }

    public MaquinaTurnos clone() throws CloneNotSupportedException
    {
        throw new CloneNotSupportedException();
    }

    public void sortearJugadorInicial()
    {
        // nextInt produce un conjunto abierto, asique se le suma 1 para incluir el límite superior.
        int randomNum = ThreadLocalRandom.current().nextInt(0, 1 + 1);
        if (randomNum == 0)
        {
            this.maquinaFaseActiva = this.maquinaFaseJugador1;
            this.maquinaFaseEnEspera = this.maquinaFaseJugador2;
            this.jugadorInicialSorteado = this.jugador1;
        } else
        {
            this.maquinaFaseActiva = this.maquinaFaseJugador2;
            this.maquinaFaseEnEspera = this.maquinaFaseJugador1;
            this.jugadorInicialSorteado = this.jugador2;
        }
    }

    public void jugar()
    {
        this.maquinaFaseActiva.jugar();
    }

    // --------------------------------------------------------------------
    // Métodos de fases y turnos.
    // --------------------------------------------------------------------
    public void avanzarProximaFase()
    {
        this.maquinaFaseActiva.avanzarProximaFase();
    }

    public void terminarTurno()
    {
        MaquinaFase maquiniaFaseSwap = this.maquinaFaseActiva;

        this.maquinaFaseActiva = this.maquinaFaseEnEspera;
        this.maquinaFaseEnEspera = maquiniaFaseSwap;
    }

    // --------------------------------------------------------------------
    // Métodos de consultas.
    // --------------------------------------------------------------------
    public Jugador obtenerJugadorActual()
    {
        return this.maquinaFaseActiva.obtenerJugador();
    }

    public String nombreFaseActual()
    {
        return this.maquinaFaseActiva.nombreFaseActual();
    }
}

