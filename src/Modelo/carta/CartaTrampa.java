package Modelo.carta;

import Modelo.Jugador;

public abstract class CartaTrampa extends Carta
{
    protected boolean trampaCancelaAtaqueAMonstruo;

    public CartaTrampa(Jugador jugador, Jugador oponente, String locacionDeImagen)
    {
        super(jugador, oponente, locacionDeImagen);
        trampaCancelaAtaqueAMonstruo = false;
    }

    public boolean trampaCancelaAtaqueAMonstruo()
    {

        return trampaCancelaAtaqueAMonstruo;
    }

    public abstract void efecto(CartaMonstruo cartaMonstruoJugador, CartaMonstruo cartaMonstruoOponente);

    public abstract void efecto();
}
