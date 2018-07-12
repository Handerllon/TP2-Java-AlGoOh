package areaDeJuego;

import AlGoOh.Jugador;
import carta.CartaCampo;
import carta.CartaMonstruo;
import vistaJuego.Observador;

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

    public void notificarColocacionDeCarta(CartaMonstruo carta)
    {
        if (this.obtenerCartas().size() != 0)
        {
            this.cartas.get(0).efecto(carta);
        }
    }

    public void notificarRemocionDeCarta(CartaMonstruo carta)
    {
        if (this.obtenerCartas().size() != 0)
        {
            this.cartas.get(0).deshacerEfecto(carta);
        }
    }

}
