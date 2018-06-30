package carta;

import AlGoOh.Jugador;

public abstract class CartaMagica extends Carta
{
    public CartaMagica(Jugador jugador, Jugador oponente)
    {
        super(jugador, oponente);
    }

    public abstract void efecto();
}
