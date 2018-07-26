package Modelo.areaDeJuego;

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
        super.colocarCarta(carta);
        this.cartasMagicas.add(carta);
    }

    public void colocarCarta(CartaTrampa carta)
    {
        super.colocarCarta(carta);
        this.cartasTrampa.add(carta);
        // TODO: el efecto se activa en el turno del adversario. Se podría usar la máquina de turnos, y que esta
        // llame a un metodo "ActivarEfectosTrampa" de la region correspondiente.
        carta.efecto();
    }

    public boolean hayCartasTrampa()
    {
        return !this.cartasTrampa.isEmpty();
    }

    public CartaTrampa getCartaTrampaAUsar()
    {
        CartaTrampa cartaTrampa = CartaTrampaNula.getInstancia();
        if (hayCartasTrampa() && hayCartasTrampaBocaArriba())
            cartaTrampa = cartasTrampa.remove();
        return cartaTrampa;
    }

    public boolean hayCartasTrampaBocaArriba()
    {
        if (!hayCartasTrampa())
        {
            return false;
        }

        for (CartaTrampa cartaTrampa : cartasTrampa)
        {
            if (cartaTrampa.estaBocaArriba())
                return true;
        }

        return false;
    }
}
