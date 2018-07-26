package Controlador.excepciones;

import Controlador.condicionesJuego.EstadoVerificador;

public class NoSePuedeAtacarError extends RuntimeException
{
    EstadoVerificador estadoVerificador;

    public NoSePuedeAtacarError(EstadoVerificador estadoVerificador)
    {
        this.estadoVerificador = estadoVerificador;
    }

    public EstadoVerificador getEstadoVerificador()
    {
        return this.estadoVerificador;
    }
}
