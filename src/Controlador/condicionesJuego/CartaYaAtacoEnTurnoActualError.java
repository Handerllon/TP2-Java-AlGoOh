package Controlador.condicionesJuego;

import Controlador.estadosJuego.EstadoVerificador;

public class CartaYaAtacoEnTurnoActualError implements EstadoVerificador
{
    @Override
    public boolean esFallido()
    {
        return true;
    }

    @Override
    public String getNombre()
    {
        return "Carta ya atacó en el turno actual.";
    }
}
