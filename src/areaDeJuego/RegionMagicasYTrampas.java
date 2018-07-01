package areaDeJuego;

import AlGoOh.Jugador;
import carta.Carta;
import carta.CartaMagica;
import carta.CartaTrampa;

import java.util.ArrayList;

public class RegionMagicasYTrampas extends Region<Carta>
{
    private static int CAPACIDAD_REGION_MAGICAS = 5;
    protected ArrayList<CartaMagica> cartasMagicas = new ArrayList<>();
    protected ArrayList<CartaTrampa> cartasTrampa = new ArrayList<>();

    public RegionMagicasYTrampas(Jugador jugador)
    {
        super(CAPACIDAD_REGION_MAGICAS, jugador);
    }

    public void colocarCarta(CartaMagica carta)
    {
        super.colocarCarta(carta);
        this.cartasMagicas.add(carta);
        carta.efecto();
    }

    public void colocarCarta(CartaTrampa carta)
    {
        super.colocarCarta(carta);
        this.cartasTrampa.add(carta);
        // TODO: el efecto se activa en el turno del adversario.
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
