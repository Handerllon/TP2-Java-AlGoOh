package AlGoOh;

public class AlGoOh
{

    private Jugador jugador;
    private Jugador oponente;

    public AlGoOh()
    {

    }

    public void inicializarJuego(String nombreJugador, String nombreOponente)
    {

        this.jugador = new Jugador(nombreJugador);
        this.oponente = new Jugador(nombreOponente);

    }


    public Jugador obtenerJugador()
    {
        return this.jugador;
    }
}