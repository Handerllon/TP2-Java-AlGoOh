package areaDeJuego;

import AlGoOh.Jugador;
import carta.Carta;
import carta.CartaMagica;
import carta.CartaTrampa;

public class RegionMagicasYTrampas extends Region<Carta>
{
    private static int CAPACIDAD_REGION_MAGICAS = 5;

    public RegionMagicasYTrampas(Jugador jugador)
    {
        super(CAPACIDAD_REGION_MAGICAS, jugador);
    }

    public void colocarCarta(CartaMagica carta)
    {
        super.colocarCarta(carta);
        carta.efecto();
    }

    public void colocarCarta(CartaTrampa carta)
    {
        super.colocarCarta(carta);
        carta.efecto();
    }

    public CartaTrampa obtenerCartaTrampaAActivar()
    {

        CartaTrampa cartaTrampaBuscada = null;

        for (int i = 0; i < cartas.size(); i++)
        {

            if (cartas.get(i) instanceof CartaTrampa)
            {

                cartaTrampaBuscada = (CartaTrampa) cartas.get(i);
                return cartaTrampaBuscada;
            }
        }
        return cartaTrampaBuscada;
    }
}
