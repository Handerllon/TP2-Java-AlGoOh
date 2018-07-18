package Controlador.estadosJuego;

import Modelo.Jugador;
import Vista.Vista;

import java.util.concurrent.ThreadLocalRandom;

public final class MaquinaTurnos
{
    private static MaquinaTurnos instancia = null;
    private MaquinaFase maquinaFaseJugador1, maquinaFaseJugador2;
    private MaquinaFase maquinaFaseActiva, maquinaFaseEnEspera;
    private Vista vista;

    // --------------------------------------------------------------------
    // Métodos de construcción e inicialización.
    // --------------------------------------------------------------------
    private MaquinaTurnos(Jugador jugador1, Jugador jugador2, Vista vista)
    {
        this.vista = vista;
        this.maquinaFaseJugador1 = new MaquinaFase(jugador1, vista);
        this.maquinaFaseJugador2 = new MaquinaFase(jugador2, vista);

        this.establecerMaquinaFasesInicial();
    }

    public static MaquinaTurnos obtenerInstancia(Jugador jugador1, Jugador jugador2, Vista vista)
    {
        if (instancia == null)
        {
            instancia = new MaquinaTurnos(jugador1, jugador2, vista);
        }
        return instancia;
    }

    public MaquinaTurnos clone() throws CloneNotSupportedException
    {
        throw new CloneNotSupportedException();
    }

    public void establecerMaquinaFasesInicial()
    {
        // nextInt produce un conjunto abierto, asique se le suma 1 para incluir el límite superior.
        int randomNum = ThreadLocalRandom.current().nextInt(0, 1 + 1);
        if (randomNum == 0)
        {
            this.maquinaFaseActiva = this.maquinaFaseJugador1;
            this.maquinaFaseEnEspera = this.maquinaFaseJugador2;
        } else
        {
            this.maquinaFaseActiva = this.maquinaFaseJugador2;
            this.maquinaFaseEnEspera = this.maquinaFaseJugador1;
        }
    }

    public void jugar()
    {
        this.maquinaFaseActiva.jugar();
        this.cambiarTurno();
    }

    private void cambiarTurno()
    {
        MaquinaFase maquiniaFaseSwap = this.maquinaFaseActiva;

        this.maquinaFaseActiva = this.maquinaFaseEnEspera;
        this.maquinaFaseEnEspera = maquiniaFaseSwap;
    }

    public void parar()
    {
        this.maquinaFaseActiva = MaquinaFaseNula.obtenerInstancia();
    }

	public void finDeTurno() {
		// TODO Hacer
		
	}
}

