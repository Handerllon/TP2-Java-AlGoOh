package Controlador.excepciones;

import Modelo.Jugador;

public class NoSePuedeTomarCartaError extends Exception
{
    Jugador jugadorResponsable;
    Razon razon;

    public NoSePuedeTomarCartaError(Jugador jugador, Razon razon)
    {
        this.jugadorResponsable = jugador;
        this.razon = razon;
    }
}
