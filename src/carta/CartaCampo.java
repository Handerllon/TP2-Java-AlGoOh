package carta;

import AlGoOh.Jugador;

public abstract class CartaCampo extends Carta
{
    protected static int modificadorAtaque;
    protected static int modificadorDefensa;

    public CartaCampo(Jugador jugador, Jugador oponente)
    {
        super(jugador, oponente);
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

//    public void notificarColocacionCarta(CartaMonstruo carta)
//    {
//        this.efecto(carta);
//        this.regionCampoOponente.notificarColocacionDeCarta(carta);
//    }
//
//    public void notificarRemocionCarta(CartaMonstruo carta)
//    {
//        this.deshacerEfecto(carta);
//        this.regionCampoOponente.notificarRemocionDeCarta(carta);
//    }
}
