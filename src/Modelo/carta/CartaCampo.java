package Modelo.carta;

import Modelo.Jugador;

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
        cartaMonstruo.sumarPuntosAtaque(-this.modificadorAtaque);
    }

    protected void restaurarPuntosDefensa(CartaMonstruo cartaMonstruo)
    {
        cartaMonstruo.sumarPuntosDefensa(-this.modificadorDefensa);
    }

//    public void notificarColocacionCarta(CartaMonstruo Modelo.carta)
//    {
//        this.efecto(Modelo.carta);
//        this.regionCampoOponente.notificarColocacionDeCarta(Modelo.carta);
//    }
//
//    public void notificarRemocionCarta(CartaMonstruo Modelo.carta)
//    {
//        this.deshacerEfecto(Modelo.carta);
//        this.regionCampoOponente.notificarRemocionDeCarta(Modelo.carta);
//    }
}
