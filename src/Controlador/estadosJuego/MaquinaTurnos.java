package Controlador.estadosJuego;

import Modelo.Jugador;

import java.util.concurrent.ThreadLocalRandom;

public final class MaquinaTurnos
{
    private static MaquinaTurnos instancia = null;
    private Fase faseActual;
    private Jugador jugador1, jugador2, jugadorActual;

    // --------------------------------------------------------------------
    // Métodos de construcción e inicialización.
    // --------------------------------------------------------------------
    private MaquinaTurnos(Jugador jugador1, Jugador jugador2)
    {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.faseActual = FaseInicial.obtenerInstancia(this);

        this.sortearJugadorInicial();
    }

    public static MaquinaTurnos obtenerInstancia(Jugador jugador1, Jugador jugador2)
    {
        if (instancia == null)
        {
            instancia = new MaquinaTurnos(jugador1, jugador2);
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
            this.jugadorActual = this.jugador1;
        } else
        {
            this.jugadorActual = this.jugador2;
        }
    }

    // --------------------------------------------------------------------
    // Métodos de fases y turnos.
    // --------------------------------------------------------------------
    public void avanzarProximaFase()
    {
        this.faseActual = this.faseActual.avanzar();
    }

    public void terminarTurno()
    {
        this.swapJugadorActual();
        this.reiniciarFases();
    }

    private void swapJugadorActual()
    {
        if (this.obtenerJugadorActual() == this.jugador1)
        {
            this.jugadorActual = this.jugador2;
        } else
        {
            this.jugadorActual = this.jugador1;
        }
    }

    private void reiniciarFases()
    {
        this.faseActual = FaseInicial.obtenerInstancia(this);
    }

    // --------------------------------------------------------------------
    // Métodos de consultas.
    // --------------------------------------------------------------------
    public Jugador obtenerJugadorActual()
    {
        return this.jugadorActual;
    }

    public Fase faseActual()
    {
        return this.faseActual;
    }
}

