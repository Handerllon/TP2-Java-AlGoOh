package Modelo.carta.excepciones;

import Modelo.Jugador;

public class ManoLlena extends RuntimeException
{
    Jugador jugadorResponsable;

    public ManoLlena(Jugador jugadorResponsable)
    {
        this.jugadorResponsable = jugadorResponsable;
    }

    public String getNombreResponsable()
    {
        return this.jugadorResponsable.getNombre();
    }
}
