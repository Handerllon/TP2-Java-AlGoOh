package Modelo.region;

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

    // --------------------------------------------------------------------
    // Metodos por ser un observable de Region.
    // --------------------------------------------------------------------
    @Override
    public void ingresoCarta(RegionMonstruos region)
    {
        CartaMonstruo carta = region.getUltimaCartaEnEntrar();
        if (!this.estaVacia())
        {
            this.cartas.iterator().next().efecto(carta);
        }
    }

    @Override
    public void egresoCarta(RegionMonstruos region)
    {
        CartaMonstruo carta = region.getUltimaCartaEnSalir();
        if (!this.estaVacia())
        {
            this.cartas.iterator().next().deshacerEfecto(carta);
        }
    }
}
