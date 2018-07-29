package Controlador.condicionesJuego;

import Controlador.estadosJuego.EstadoVerificador;

public class NoHaySuficientesCartasParaSacrificarError implements EstadoVerificador
{
    @Override
    public boolean esFallido()
    {
        return true;
    }

    @Override
    public String getNombre()
    {
        return "No hay suficientes sacrificios";
    }
}
