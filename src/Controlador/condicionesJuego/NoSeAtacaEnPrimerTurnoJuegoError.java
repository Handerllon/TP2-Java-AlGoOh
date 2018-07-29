package Controlador.condicionesJuego;

import Controlador.estadosJuego.EstadoVerificador;

public class NoSeAtacaEnPrimerTurnoJuegoError implements EstadoVerificador
{
    @Override
    public boolean esFallido()
    {
        return true;
    }

    @Override
    public String getNombre()
    {
        return "No se puede atacar en el primer turno dele juego";
    }
}
