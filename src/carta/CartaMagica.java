package carta;

import AlGoOh.Jugador;

public abstract class CartaMagica extends Carta implements Efecto {
    public CartaMagica(Jugador jugador, Jugador oponente) {
        super(jugador, oponente);
    }
}
