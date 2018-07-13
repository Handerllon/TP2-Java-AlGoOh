package Modelo.areaDeJuego;

import Modelo.Jugador;
import Modelo.carta.CartaCampo;
import Modelo.carta.CartaMonstruo;

public class RegionCampo extends Region<CartaCampo>
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

    public void notificarObservadores()
    {
        this.observadoresRegion.forEach(item -> item.actualizar(this));
    }

    @Override
    public void actualizar(Region tRegion)
    {
        CartaMonstruo carta = (CartaMonstruo) tRegion.obtenerUltimaCartaEnEntrar();
        if (this.estaVacia() == false)
        {
            this.cartas.get(0).efecto(carta);
        }
    }
}
