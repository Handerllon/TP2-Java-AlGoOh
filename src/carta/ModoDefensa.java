package carta;

import carta.monstruo.CartaMonstruo;

public class ModoDefensa extends Modo
{

    public ModoDefensa(int puntosDefensa)
    {
        this.puntos = puntosDefensa;
    }

    public void cambiarModo(CartaMonstruo carta)
    {
        carta.establecerModo(new ModoAtaque(carta.getPuntosAtaque()));
    }

}
