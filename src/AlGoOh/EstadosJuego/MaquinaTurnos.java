package AlGoOh.EstadosJuego;

import AlGoOh.Jugador;

import java.util.concurrent.ThreadLocalRandom;

public class MaquinaTurnos
{
    private Jugador jugador1;
    private Jugador jugador2;
    private Jugador jugadorEnTurno;
    private Jugador jugadorEnEspera;
    private Fase fase;

    public MaquinaTurnos(Jugador jugador1, Jugador jugador2)
    {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;

        this.establecerJugadorInicial();

        this.fase = new FaseInicial();
    }

    public void establecerJugadorInicial()
    {
        // nextInt is normally exclusive of the top value, so add 1 to make it inclusive.
        int randomNum = ThreadLocalRandom.current().nextInt(0, 1 + 1);
        if (randomNum == 0)
        {
            this.jugadorEnTurno = this.jugador1;
            this.jugadorEnEspera = this.jugador2;
        } else
        {
            this.jugadorEnTurno = this.jugador2;
            this.jugadorEnEspera = this.jugador1;
        }
    }

    public void jugarTurno()
    {
        this.fase.jugar(this.jugadorEnTurno);
        this.fase = this.fase.cambiarFase();
        this.cambiarTurno();
    }

    private void cambiarTurno()
    {
        Jugador jugadorSwap = this.jugadorEnTurno;

        this.jugadorEnTurno = this.jugadorEnEspera;
        this.jugadorEnEspera = jugadorSwap;
    }
}

