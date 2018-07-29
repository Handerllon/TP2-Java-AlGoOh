package Controlador.excepciones;

import Controlador.condicionesJuego.EstadoVerificador;

public class NoSePuedeEnviarARegionCampoError extends RuntimeException
{
    EstadoVerificador estadoVerificador;

    public NoSePuedeEnviarARegionCampoError(EstadoVerificador estadoVerificador)
    {
        this.estadoVerificador = estadoVerificador;
    }

    public EstadoVerificador getEstadoVerificador()
    {
        return this.estadoVerificador;
    }
}