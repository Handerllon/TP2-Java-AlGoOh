package Modelo.carta.trampa;

import Modelo.Jugador;
import Modelo.carta.Carta;
import Modelo.carta.monstruo.CartaMonstruo;

public abstract class CartaTrampa extends Carta
{
    protected boolean trampaCancelaAtaqueAMonstruo;

    public CartaTrampa(Jugador jugador, Jugador oponente, String locacionDeImagen)
    {
        super(jugador, oponente, locacionDeImagen);
        this.trampaCancelaAtaqueAMonstruo = false;
    }

    public boolean trampaCancelaAtaqueAMonstruo()
    {
        return this.trampaCancelaAtaqueAMonstruo;
    }

    public abstract void efecto(CartaMonstruo cartaAtacante);

    public abstract void efecto(CartaMonstruo cartaAtacante, CartaMonstruo cartaAtacada);

    public abstract void deshacerEfecto();

    public boolean esCampo()
    {
        return false;
    }

    public boolean esMagica()
    {
        return false;
    }

    public boolean esMonstruo()
    {
        return false;
    }

    public boolean esTrampa()
    {
        return true;
    }
}
