package AlGoOh;

import AlGoOh.EstadosJuego.MaquinaTurnos;

public class AlGoOh
{
    private Jugador jugador1;
    private Jugador jugador2;
    private MaquinaTurnos maquinaTurnos;

    public AlGoOh()
    {
        this.inicializarJuego("J1", "J2");
    }

    public void inicializarJuego(String nombreJugador, String nombreOponente)
    {

        this.jugador1 = new Jugador(nombreJugador);
        this.jugador2 = new Jugador(nombreOponente);

        this.jugador1.establecerOponente(this.jugador2);
        this.jugador2.establecerOponente(this.jugador1);

        this.jugador1.crearMazo();
        this.jugador2.crearMazo();

        this.maquinaTurnos = new MaquinaTurnos(this.jugador1, this.jugador2);
        this.comenzarJuego();
    }

    public void comenzarJuego(){
        while(true)
            this.maquinaTurnos.jugarTurno();
    }
}