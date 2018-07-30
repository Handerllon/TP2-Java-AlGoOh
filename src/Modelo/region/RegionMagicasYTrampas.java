package Modelo.region;

import Modelo.Jugador;
import Modelo.carta.Carta;
import Modelo.carta.magica.CartaMagica;
import Modelo.carta.trampa.CartaTrampa;
import Modelo.carta.trampa.CartaTrampaNula;

import java.util.LinkedList;
import java.util.Queue;

public class RegionMagicasYTrampas extends Region<Carta>
{
    private static int CAPACIDAD_REGION_MAGICAS = 5;
    Queue<CartaMagica> cartasMagicas = new LinkedList<>();
    Queue<CartaTrampa> cartasTrampa = new LinkedList<>();

    public RegionMagicasYTrampas(Jugador jugador)
    {
        super(CAPACIDAD_REGION_MAGICAS, jugador);
    }

    public void colocarCarta(CartaMagica carta)
    {
        this.cartasMagicas.add(carta);
        super.colocarCarta(carta);
    }

    public void colocarCarta(CartaTrampa carta)
    {
        this.cartasTrampa.add(carta);
        super.colocarCarta(carta);
    }

    public boolean hayCartasTrampa()
    {
        return !this.cartasTrampa.isEmpty();
    }

    public CartaTrampa getCartaTrampaAUsar()
    {
        CartaTrampa cartaTrampa = CartaTrampaNula.getInstancia();
        if (hayCartasTrampa())
        {
            cartaTrampa = cartasTrampa.remove();
            super.removerCarta(cartaTrampa);
        }
        return cartaTrampa;
    }
}
