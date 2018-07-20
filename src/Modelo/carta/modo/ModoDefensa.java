package Modelo.carta.modo;

import Modelo.carta.monstruo.CartaMonstruo;

public class ModoDefensa implements Modo
{
    public void cambiarModo(CartaMonstruo carta)
    {
        carta.establecerModo(new ModoAtaque());
    }

    @Override
    public boolean esAtaque()
    {
        return false;
    }

    @Override
    public boolean esDefensa()
    {
        return true;
    }
}
