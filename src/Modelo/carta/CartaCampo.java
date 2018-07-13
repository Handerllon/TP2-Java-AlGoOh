package Modelo.carta;

import Modelo.Jugador;

public abstract class CartaCampo<T extends Carta> extends Carta
{
    protected static int modificadorAtaque;
    protected static int modificadorDefensa;

    public CartaCampo(Jugador jugador, Jugador oponente, String locacionDeImagen)
    {
        super(jugador, oponente, locacionDeImagen);
    }

    public abstract void efecto();

    public abstract void efecto(Carta carta);

    public abstract void deshacerEfecto();

    public abstract void deshacerEfecto(Carta carta);

    protected void modificarPuntosAtaque(CartaMonstruo cartaMonstruo)
    {
        cartaMonstruo.sumarPuntosAtaque(this.modificadorAtaque);
    }

    protected void modificarPuntosDefensa(CartaMonstruo cartaMonstruo)
    {
        cartaMonstruo.sumarPuntosDefensa(this.modificadorDefensa);
    }

    protected void restaurarPuntosAtaque(CartaMonstruo cartaMonstruo)
    {
        cartaMonstruo.sumarPuntosAtaque(-this.modificadorAtaque);
    }

    protected void restaurarPuntosDefensa(CartaMonstruo cartaMonstruo)
    {
        cartaMonstruo.sumarPuntosDefensa(-this.modificadorDefensa);
    }
}
