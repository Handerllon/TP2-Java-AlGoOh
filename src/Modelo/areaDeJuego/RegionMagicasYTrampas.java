package Modelo.areaDeJuego;

import Modelo.Jugador;
import Modelo.carta.Carta;
import Modelo.carta.magica.CartaMagica;
import Modelo.carta.trampa.CartaTrampa;

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
    }

    public void colocarCarta(CartaTrampa carta)
    {
        super.colocarCarta(carta);
        this.cartasTrampa.add(carta);
        // TODO: el efecto se activa en el turno del adversario. Se podría usar la máquina de turnos, y que esta
        // llame a un metodo "ActivarEfectosTrampa" de la region correspondiente.
        carta.efecto();
    }

    public CartaTrampa getCartaTrampaAActivar()
    {

        CartaTrampa cartaTrampaBuscada = null;

        for (int i = 0; i < cartas.size(); i++)
        {

            if (cartas.get(i) instanceof CartaTrampa)
            {

                // TODO: evitar casteo porque rompe el principio de sustitución de Liskov.
                cartaTrampaBuscada = (CartaTrampa) cartas.get(i);
                return cartaTrampaBuscada;
            }
        }
        return cartaTrampaBuscada;
    }
}
