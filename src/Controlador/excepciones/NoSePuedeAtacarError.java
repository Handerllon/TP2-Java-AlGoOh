package Controlador.excepciones;

import Controlador.estadosJuego.EstadoVerificador;
import Modelo.Jugador;

public class NoSePuedeAtacarError extends RuntimeException
{
    EstadoVerificador estadoVerificador;
    Jugador jugadorResponsable;

    public NoSePuedeAtacarError(Jugador jugadorResponsable, EstadoVerificador estadoVerificador)
    {
        this.estadoVerificador = estadoVerificador;
        this.jugadorResponsable = jugadorResponsable;
    }

    public EstadoVerificador getEstadoVerificador()
    {
        return this.estadoVerificador;
    }

    public String getNombreResponsable()
    {
        return this.jugadorResponsable.getNombre();
    }
}
