package Controlador.condicionesJuego;

import Controlador.estadosJuego.EstadoVerificador;

public class YaSeMandoMonstruoARegionEnTurnoActualError implements EstadoVerificador
{
    @Override
    public boolean esFallido()
    {
        return true;
    }

    @Override
    public String getNombre()
    {
        return "Ya se mandó el monstruo a la región en el turno actual";
    }
}
