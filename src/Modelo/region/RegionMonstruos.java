package Modelo.region;

import Modelo.Jugador;
import Modelo.carta.monstruo.CartaMonstruo;

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
        CartaMonstruo cartaConAtaqueMinimo = null;

        int ataqueTope = Integer.MAX_VALUE;

        ArrayList<CartaMonstruo> cartas = this.getCartas();

        for (int i = 0; i < cartas.size(); i++)
        {
            if (cartas.get(i).getPuntosDeAtaque() < ataqueTope)
            {
                ataqueTope = cartas.get(i).getPuntosDeAtaque();
                cartaConAtaqueMinimo = cartas.get(i);
            }
        }

        return cartaConAtaqueMinimo;
    }
}

