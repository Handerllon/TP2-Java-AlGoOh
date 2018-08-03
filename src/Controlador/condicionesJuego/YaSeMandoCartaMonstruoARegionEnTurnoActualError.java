package Controlador.condicionesJuego;

import Controlador.estadosJuego.EstadoVerificador;

public class YaSeMandoCartaMonstruoARegionEnTurnoActualError implements EstadoVerificador
{
    @Override
    public boolean esFallido()
    {
        return true;
    }

    @Override
    public String getNombre()
    {
        return "Ya se mandó la carta monstruo a la región en el turno actual.";
    }
}

