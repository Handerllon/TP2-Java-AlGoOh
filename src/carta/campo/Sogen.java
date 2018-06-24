package carta.campo;

import AlGoOh.Jugador;
import carta.CartaCampo;

public class Sogen extends CartaCampo {
    private static int modificadorAtaque;
    private static int modificadorDefensa;
    private static Jugador jugador;
    private static Jugador oponente;

    public Sogen(Jugador jugador, Jugador oponente) {
        super(jugador, oponente);
        this.nombre = "Sogen";

        //En region de jugador que juega la carta
        this.modificadorDefensa = 500;
        //En region del oponente
        this.modificadorAtaque = 200;

        this.jugador = jugador;
        this.oponente = oponente;
    }

    public void efecto() {
        this.jugador.sogen(this.jugador, this.modificadorAtaque, this.modificadorDefensa);
        this.oponente.sogen(this.jugador, this.modificadorAtaque, this.modificadorDefensa);
    }
}
