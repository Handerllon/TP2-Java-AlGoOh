package areaDeJuego;

import carta.CartaMonstruo;

public interface Notificable
{
    void notificarColocacionDeCarta(CartaMonstruo carta);

    void notificarRemocionDeCarta(CartaMonstruo carta);
}
