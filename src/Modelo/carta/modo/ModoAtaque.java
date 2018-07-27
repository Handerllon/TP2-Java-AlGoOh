package Modelo.carta.modo;

import Modelo.carta.monstruo.CartaMonstruo;

public final class ModoAtaque implements Modo
{
    private static ModoAtaque instancia = null;

    // --------------------------------------------------------------------
    // Métodos de construcción e inicialización.
    // --------------------------------------------------------------------
    private ModoAtaque()
    {

    }

    public static ModoAtaque getInstancia()
    {
        if (instancia == null)
        {
            instancia = new ModoAtaque();
        }
        return instancia;
    }

    @Override
    public ModoAtaque clone() throws CloneNotSupportedException
    {
        throw new CloneNotSupportedException();
    }

    // ------------------------------------
    // Métodos de interfaz Modo.
    // ------------------------------------
    @Override
    public void cambiarModo(CartaMonstruo carta)
    {
        carta.setModo(ModoDefensa.getInstancia());
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
