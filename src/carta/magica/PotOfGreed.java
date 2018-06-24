package carta.magica;

import AlGoOh.Jugador;
import carta.CartaMagica;

public class PotOfGreed extends CartaMagica {
    public PotOfGreed(Jugador jugador, Jugador oponente) {
        super(jugador, oponente);
        this.nombre = "Pot of Greed";
    }

    public void efecto() {
        this.jugador.ollaDeLaCodicia();
    }
}
