package Modelo.carta.ataque;

import Modelo.carta.monstruo.CartaMonstruo;

public final class AtaqueDefault implements Ataque
{
    private static AtaqueDefault instancia = null;

    // --------------------------------------------------------------------
    // Métodos de construcción e inicialización.
    // --------------------------------------------------------------------
    private AtaqueDefault()
    {

    }

    public static AtaqueDefault getInstancia()
    {
        if (instancia == null)
        {
            instancia = new AtaqueDefault();
        }
        return instancia;
    }

    @Override
    public AtaqueDefault clone() throws CloneNotSupportedException
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


