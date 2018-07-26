package Controlador.excepciones;

import Controlador.condicionesJuego.EstadoVerificador;

public class NoSePuedeMandarCartaARegionError extends RuntimeException
{
    EstadoVerificador estadoVerificador;

    public NoSePuedeMandarCartaARegionError(EstadoVerificador estadoVerificador)
    {
        this.estadoVerificador = estadoVerificador;
    }

    public EstadoVerificador getEstadoVerificador()
    {
        return this.estadoVerificador;
    }
}
