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

    private void inicializarJuego(String nombreJugador, String nombreOponente)
    {

        this.jugador1 = new Jugador(nombreJugador);
        this.jugador2 = new Jugador(nombreOponente);

        this.jugador1.establecerOponente(this.jugador2);
        this.jugador2.establecerOponente(this.jugador1);

        this.jugador1.crearMazo();
        this.jugador2.crearMazo();

        this.maquinaTurnos = new MaquinaTurnos(this.jugador1, this.jugador2);
    }

    public void comenzarJuego()
    {
        int i = 1;
        while (i != 20)
        {
            this.maquinaTurnos.jugar();
            i++;
        }
    }
}