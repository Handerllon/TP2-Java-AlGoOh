package Modelo.carta.modo;

import Modelo.carta.monstruo.CartaMonstruo;

public class ModoAtaque implements Modo
{
    public void cambiarModo(CartaMonstruo carta)
    {
        carta.establecerModo(new ModoDefensa());
    }

    @Override
    public boolean esAtaque()
    {
        return true;
    }

    @Override
    public boolean esDefensa()
    {
        return false;
    }
}
