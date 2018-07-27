package Modelo.carta.modo;

import Modelo.carta.monstruo.CartaMonstruo;

public final class ModoDefensa implements Modo
{
    private static ModoDefensa instancia = null;

    // --------------------------------------------------------------------
    // Métodos de construcción e inicialización.
    // --------------------------------------------------------------------
    private ModoDefensa()
    {

    }

    public static ModoDefensa getInstancia()
    {
        if (instancia == null)
        {
            instancia = new ModoDefensa();
        }
        return instancia;
    }

    @Override
    public ModoDefensa clone() throws CloneNotSupportedException
    {
        throw new CloneNotSupportedException();
    }

    // ------------------------------------
    // Métodos de interfaz Modo.
    // ------------------------------------
    @Override
    public void cambiarModo(CartaMonstruo carta)
    {
        carta.setModo(ModoAtaque.getInstancia());
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
