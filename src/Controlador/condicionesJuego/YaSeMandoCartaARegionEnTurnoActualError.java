package Controlador.condicionesJuego;

import Controlador.estadosJuego.EstadoVerificador;

public class YaSeMandoCartaARegionEnTurnoActualError implements EstadoVerificador
{
    @Override
    public boolean esFallido()
    {
        return true;
    }

    @Override
    public String getNombre()
    {
        return "Se mandó la carta a la región en el turno actual";
    }
}
