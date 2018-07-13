package Modelo.areaDeJuego;

import Modelo.Jugador;
import Modelo.carta.CartaMonstruo;

import java.util.ArrayList;

public class RegionMonstruos extends Region<CartaMonstruo>
{
    private static int CAPACIDAD_REGION_MONSTRUOS = 5;

    public RegionMonstruos(Jugador jugador)
    {
        super(CAPACIDAD_REGION_MONSTRUOS, jugador);
    }

    public CartaMonstruo obtenerMonstruoConMenorAtaque()
    {
        CartaMonstruo cartaConAtaqueMinimo = null;

        int ataqueTope = Integer.MAX_VALUE;

        ArrayList<CartaMonstruo> cartas = this.obtenerCartas();

        for (int i = 0; i < cartas.size(); i++)
        {
            if (cartas.get(i).obtenerPuntosDeAtaque() < ataqueTope)
            {
                ataqueTope = cartas.get(i).obtenerPuntosDeAtaque();
                cartaConAtaqueMinimo = cartas.get(i);
            }
        }

        return cartaConAtaqueMinimo;
    }

    public void notificarObservadores()
    {
        this.observadoresRegion.forEach(item -> item.actualizar(this));
    }
}

