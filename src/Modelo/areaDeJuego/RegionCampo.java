package Modelo.areaDeJuego;

import Modelo.Jugador;
import Modelo.carta.campo.CartaCampo;
import Modelo.carta.monstruo.CartaMonstruo;
import Modelo.observadores.ObservadorRegion;

public class RegionCampo extends Region<CartaCampo> implements ObservadorRegion<RegionMonstruos>
{
    private static int CAPACIDAD_REGION_CAMPO = 1;

    public RegionCampo(Jugador jugador)
    {
        super(CAPACIDAD_REGION_CAMPO, jugador);
    }

    public void colocarCarta(CartaCampo carta)
    {
        super.colocarCarta(carta);
        carta.efecto();
    }

    public void removerCarta(CartaCampo carta)
    {
        super.removerCarta(carta);
        carta.deshacerEfecto();
    }

    @Override
    public void agregacionCarta(RegionMonstruos region)
    {
        CartaMonstruo carta = region.getUltimaCartaEnEntrar();
        if (this.estaVacia() == false)
        {
            this.cartas.get(0).efecto(carta);
        }
    }

    @Override
    public void remocionCarta(RegionMonstruos region)
    {
        CartaMonstruo carta = region.getUltimaCartaEnSalir();
        if (this.estaVacia() == false)
        {
            this.cartas.get(0).deshacerEfecto(carta);
        }
    }
}
