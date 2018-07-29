package Controlador.excepciones;

import Controlador.condicionesJuego.EstadoVerificador;

public class NoSePuedeEnviarCartaMonstruoARegionError extends RuntimeException
{
    EstadoVerificador estadoVerificador;

    public NoSePuedeEnviarCartaMonstruoARegionError(EstadoVerificador estadoVerificador)
    {
        this.estadoVerificador = estadoVerificador;
    }

    public EstadoVerificador getEstadoVerificador()
    {
        return this.estadoVerificador;
    }
}
