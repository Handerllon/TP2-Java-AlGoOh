package Modelo.carta.trampa;

import Modelo.carta.CartaNula;
import Modelo.carta.ObservadorDeCarta;
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

    // ------------------------------------
    // Metodos de observador de modelo.
    // ------------------------------------
    @Override
    public void agregarObsevador(ObservadorDeCarta observer)
    {

    }

    @Override
    public void quitarObservador(ObservadorDeCarta observer)
    {

    }

    @Override
    public void notificarObservadores()
    {

    }

    // ------------------------------------
    // Metodos de efecto de carta trampa.
    // ------------------------------------
    @Override
    public void efecto(CartaMonstruo cartaAtacante, CartaMonstruo cartaAtacada)
    {

    }

    @Override
    public void deshacerEfecto()
    {

    }
}
