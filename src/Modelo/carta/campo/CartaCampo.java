package Modelo.carta.campo;

import Modelo.Jugador;
import Modelo.carta.Carta;
import Modelo.carta.monstruo.CartaMonstruo;

public abstract class CartaCampo extends Carta
{
    protected static int modificadorAtaque;
    protected static int modificadorDefensa;

    public CartaCampo(Jugador jugador, Jugador oponente, String locacionDeImagen)
    {
        super(jugador, oponente, locacionDeImagen);
    }

    public abstract void efecto();

    public abstract void efecto(CartaMonstruo carta);

    public abstract void deshacerEfecto();

    public abstract void deshacerEfecto(CartaMonstruo carta);

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
        cartaMonstruo.restarPuntosAtaque(this.modificadorAtaque);
    }

    protected void restaurarPuntosDefensa(CartaMonstruo cartaMonstruo)
    {
        cartaMonstruo.restarPuntosDefensa(this.modificadorDefensa);
    }

    public boolean esCampo()
    {
        return true;
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
        return false;
    }
}
