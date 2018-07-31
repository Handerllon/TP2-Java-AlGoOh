package Modelo.carta.trampa;

import Modelo.carta.CartaNula;
import Modelo.carta.ataque.EstrategiaAtaque;
import Modelo.carta.ataque.EstrategiaAtaqueDefault;
import Modelo.carta.monstruo.CartaMonstruo;

public final class CartaTrampaNula extends CartaTrampa
{
    private static CartaTrampaNula instancia = null;
    private EstrategiaAtaque modificadorAtaque;

    // --------------------------------------------------------------------
    // Métodos de construcción e inicialización.
    // --------------------------------------------------------------------
    private CartaTrampaNula()
    {
        super(null, null, null);
        this.nombre = "";
        this.modificadorAtaque = EstrategiaAtaqueDefault.getInstancia();
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
