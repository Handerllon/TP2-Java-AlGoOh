package Modelo.carta.trampa;

import Modelo.carta.CartaNula;
import Modelo.carta.monstruo.CartaMonstruo;

public final class CartaTrampaNula extends CartaTrampa
{
    private static CartaTrampaNula instancia = null;

    // --------------------------------------------------------------------
    // Métodos de construcción e inicialización.
    // --------------------------------------------------------------------
    private CartaTrampaNula()
    {
        super(null, null, null);
    }

    public static CartaTrampaNula getInstancia()
    {
        if (instancia == null)
        {
            instancia = new CartaTrampaNula();
        }
        return instancia;
    }

    public CartaNula clone() throws CloneNotSupportedException
    {
        throw new CloneNotSupportedException();
    }

    @Override
    public void efecto(CartaMonstruo cartaAtacante)
    {

    }

    @Override
    public void efecto(CartaMonstruo cartaAtacante, CartaMonstruo cartaAtacada)
    {

    }

    @Override
    public void deshacerEfecto()
    {

    }

    public boolean trampaCancelaAtaqueAMonstruo()
    {
        return false;
    }
}