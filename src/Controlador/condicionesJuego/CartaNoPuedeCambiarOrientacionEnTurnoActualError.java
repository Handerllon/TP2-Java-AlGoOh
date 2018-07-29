package Controlador.condicionesJuego;

import Controlador.estadosJuego.EstadoVerificador;

public class CartaNoPuedeCambiarOrientacionEnTurnoActualError implements EstadoVerificador
{
    @Override
    public boolean esFallido()
    {
        return true;
    }

    @Override
    public String getNombre()
    {
        return "Carta no puede cambiar la orientación en el turno actual";
    }
}
