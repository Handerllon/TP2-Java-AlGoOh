package Modelo.region;

import Modelo.Jugador;
import Modelo.carta.monstruo.CartaMonstruo;
import Modelo.carta.monstruo.CartaMonstruoNula;

import java.util.ArrayList;

public class RegionMonstruos extends Region<CartaMonstruo>
{
    private static int CAPACIDAD_REGION_MONSTRUOS = 5;

    public RegionMonstruos(Jugador jugador)
    {
        super(CAPACIDAD_REGION_MONSTRUOS, jugador);
    }

    public CartaMonstruo getMonstruoConMenorAtaque()
    {
        CartaMonstruo cartaConAtaqueMinimo = CartaMonstruoNula.getInstancia();

        if (!this.estaVacia())
        {
            ArrayList<CartaMonstruo> cartas = this.getCartas();
            CartaMonstruo cartaMonstruo;
            int puntosDeAtaque, ataqueTope = Integer.MAX_VALUE;

            for (int i = 0; i < cartas.size(); i++)
            {
                cartaMonstruo = cartas.get(i);
                puntosDeAtaque = cartaMonstruo.getPuntosDeAtaque();

                if (puntosDeAtaque < ataqueTope)
                {
                    ataqueTope = puntosDeAtaque;
                    cartaConAtaqueMinimo = cartaMonstruo;
                }
            }
        }

        return cartaConAtaqueMinimo;
    }
}

