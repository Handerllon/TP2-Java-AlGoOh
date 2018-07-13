package Modelo.carta;

import Modelo.Jugador;

public abstract class CartaMagica extends Carta
{
    public CartaMagica(Jugador jugador, Jugador oponente, String locacionDeImagen)
    {
        super(jugador, oponente, locacionDeImagen);
    }

    public abstract void efecto();
}
