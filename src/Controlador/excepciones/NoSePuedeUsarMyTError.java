package Controlador.excepciones;

import Controlador.estadosJuego.EstadoVerificador;
import Modelo.Jugador;

public class NoSePuedeUsarMyTError extends RuntimeException
{
    EstadoVerificador estadoVerificador;
    Jugador jugadorResponsable;

    public NoSePuedeUsarMyTError(Jugador jugadorResponsable, EstadoVerificador estadoVerificador)
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
