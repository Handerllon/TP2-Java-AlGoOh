package Modelo.region;

import Modelo.Jugador;
import Modelo.carta.Carta;
import Modelo.carta.magica.CartaMagica;
import Modelo.carta.trampa.CartaTrampa;
import Modelo.carta.trampa.CartaTrampaNula;

import java.util.ArrayList;

public class RegionMagicasYTrampas extends Region<Carta>
{
    private static int CAPACIDAD_REGION_MAGICAS = 5;
    ArrayList<CartaMagica> cartasMagicas = new ArrayList<>();
    ArrayList<CartaTrampa> cartasTrampa = new ArrayList<>();

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

    public void removerCarta(CartaTrampa carta){
        this.cartasTrampa.remove(carta);
        super.removerCarta(carta);
    }

    public void removerCarta(CartaMagica carta){
        this.cartasMagicas.remove(carta);
        super.removerCarta(carta);
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
            cartaTrampa = cartasTrampa.iterator().next();
        }
        return cartaTrampa;
    }
}
