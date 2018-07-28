package Modelo.carta.ataque;

import Modelo.carta.monstruo.CartaMonstruo;

public final class EstrategiaAtaqueDefault implements EstrategiaAtaque
{
    private static EstrategiaAtaqueDefault instancia = null;

    // --------------------------------------------------------------------
    // Métodos de construcción e inicialización.
    // --------------------------------------------------------------------
    private EstrategiaAtaqueDefault()
    {

    }

    public static EstrategiaAtaqueDefault getInstancia()
    {
        if (instancia == null)
        {
            instancia = new EstrategiaAtaqueDefault();
        }
        return instancia;
    }

    @Override
    public EstrategiaAtaqueDefault clone() throws CloneNotSupportedException
    {
        throw new CloneNotSupportedException();
    }

    // ------------------------------------
    // Métodos de interfaz Ataque.
    // ------------------------------------
    @Override
    public void ejecutar(CartaMonstruo atacante, CartaMonstruo atacada)
    {
        atacada.recibirAtaque(atacante);

        if (atacada.estaBocaAbajo())
        {
            atacada.cambiarOrientacion();
        }
    }

    @Override
    public void ejecutar(CartaMonstruo atacante)
    {
        atacante.getOponente().disminuirPuntosVida(atacante.getPuntosDeAtaque());
    }
}


