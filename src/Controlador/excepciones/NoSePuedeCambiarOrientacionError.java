package Controlador.excepciones;

import Controlador.condicionesJuego.EstadoVerificador;

public class NoSePuedeCambiarOrientacionError extends RuntimeException
{
    EstadoVerificador estadoVerificador;

    public NoSePuedeCambiarOrientacionError(EstadoVerificador estadoVerificador)
    {
        this.estadoVerificador = estadoVerificador;
    }

    public EstadoVerificador getEstadoVerificador()
    {
        return this.estadoVerificador;
    }
}
