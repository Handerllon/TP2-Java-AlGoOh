package Modelo.areaDeJuego;

import Modelo.Jugador;
import Modelo.carta.Carta;
import Modelo.carta.CartaCampo;

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

    @Override
    public <T extends Carta> void actualizar(Region<T> tRegion)
    {
        T carta = tRegion.obtenerUltimaCartaEnEntrar();
        if (this.estaVacia() == false)
        {
            this.cartas.get(0).efecto(carta);
        }
    }
}
