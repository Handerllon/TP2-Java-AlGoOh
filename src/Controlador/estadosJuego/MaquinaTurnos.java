package Controlador.estadosJuego;

import Modelo.Jugador;

import java.util.concurrent.ThreadLocalRandom;

public class MaquinaTurnos
{
    private MaquinaFases maquinaFasesJugador1, maquinaFasesJugador2;
    private MaquinaFases maquinaFaseActiva, maquinaFaseEnEspera;

    public MaquinaTurnos(Jugador jugador1, Jugador jugador2)
    {
        this.maquinaFasesJugador1 = new MaquinaFases(jugador1);
        this.maquinaFasesJugador2 = new MaquinaFases(jugador2);

        this.establecerMaquinaFasesInicial();
    }

    public void establecerMaquinaFasesInicial()
    {
        // nextInt is normally exclusive of the top value, so add 1 to make it inclusive.
        int randomNum = ThreadLocalRandom.current().nextInt(0, 1 + 1);
        if (randomNum == 0)
        {
            this.maquinaFaseActiva = this.maquinaFasesJugador1;
            this.maquinaFaseEnEspera = this.maquinaFasesJugador2;
        } else
        {
            this.maquinaFaseActiva = this.maquinaFasesJugador2;
            this.maquinaFaseEnEspera = this.maquinaFasesJugador1;
        }
    }

    public void jugar()
    {
        this.maquinaFaseActiva.jugar();
        this.cambiarTurno();
    }

    private void cambiarTurno()
    {
        MaquinaFases maquiniaFaseSwap = this.maquinaFaseActiva;

        this.maquinaFaseActiva = this.maquinaFaseEnEspera;
        this.maquinaFaseEnEspera = maquiniaFaseSwap;
    }
}

