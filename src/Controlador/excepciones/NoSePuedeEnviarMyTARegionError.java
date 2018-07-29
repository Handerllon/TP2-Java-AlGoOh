package Controlador.excepciones;

import Controlador.condicionesJuego.EstadoVerificador;

public class NoSePuedeEnviarMyTARegionError extends RuntimeException
{
    EstadoVerificador estadoVerificador;

    public NoSePuedeEnviarMyTARegionError(EstadoVerificador estadoVerificador)
    {
        this.estadoVerificador = estadoVerificador;
    }

    public EstadoVerificador getEstadoVerificador()
    {
        return this.estadoVerificador;
    }
}