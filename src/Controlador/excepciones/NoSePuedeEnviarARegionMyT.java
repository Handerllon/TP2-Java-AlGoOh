package Controlador.excepciones;

import Controlador.condicionesJuego.EstadoVerificador;

public class NoSePuedeEnviarARegionMyT extends RuntimeException
{
    EstadoVerificador estadoVerificador;

    public NoSePuedeEnviarARegionMyT(EstadoVerificador estadoVerificador)
    {
        this.estadoVerificador = estadoVerificador;
    }

    public EstadoVerificador getEstadoVerificador()
    {
        return this.estadoVerificador;
    }
}
