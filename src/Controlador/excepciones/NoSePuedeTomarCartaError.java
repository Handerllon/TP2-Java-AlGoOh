package Controlador.excepciones;

import Controlador.condicionesJuego.EstadoVerificador;

public class NoSePuedeTomarCartaError extends RuntimeException
{
    EstadoVerificador estadoVerificador;

    public NoSePuedeTomarCartaError(EstadoVerificador estadoVerificador)
    {
        this.estadoVerificador = estadoVerificador;
    }

    public EstadoVerificador getEstadoVerificador()
    {
        return this.estadoVerificador;
    }
}
