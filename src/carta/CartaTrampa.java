package carta;

import AlGoOh.Jugador;

public abstract class CartaTrampa extends Carta implements Efecto
{
    public CartaTrampa(Jugador jugador, Jugador oponente){
        super(jugador,oponente);
    }
}
