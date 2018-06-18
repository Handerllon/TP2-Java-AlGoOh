package carta;

import carta.monstruo.CartaMonstruo;

public class ModoAtaque extends Modo
{

    public ModoAtaque(int puntosAtaque)
    {
        this.puntos = puntosAtaque;
    }

    public void cambiarModo(CartaMonstruo carta)
    {
        carta.establecerModo(new ModoDefensa(carta.getPuntosDefensa()));
    }
}
