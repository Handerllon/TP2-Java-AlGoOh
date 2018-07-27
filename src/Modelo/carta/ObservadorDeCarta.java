package Modelo.carta;

import Modelo.carta.trampa.CartaTrampa;

public interface ObservadorDeCarta
{
    void notificarUsoDeCarta(CartaTrampa cartaTrampa);
}
