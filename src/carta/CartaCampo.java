package carta;

import AlGoOh.Jugador;

public abstract class CartaCampo extends Carta implements Efecto {
    public CartaCampo(Jugador jugador, Jugador oponente) {
        super(jugador, oponente);
    }
    
    public abstract void efecto(CartaMonstruo cartaMonstruo);
    
    public abstract void efecto();
    
    public abstract void quitarEfecto(CartaMonstruo cartaMonstruo);
}
