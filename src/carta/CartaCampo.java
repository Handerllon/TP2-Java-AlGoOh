package carta;

import AlGoOh.Jugador;

public abstract class CartaCampo extends Carta implements Efecto
{
    public CartaCampo(Jugador jugador, Jugador oponente){
        super(jugador,oponente);
    }
}
