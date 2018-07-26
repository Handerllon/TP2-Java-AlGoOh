package Modelo.carta.ataque;

import Modelo.carta.monstruo.CartaMonstruo;

public class AtaqueDefault implements Ataque
{
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


