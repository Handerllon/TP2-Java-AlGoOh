package Controlador.condicionesJuego;

import Controlador.estadosJuego.EstadoVerificador;

public class YaTomoCartaEnTurnoError implements EstadoVerificador
{
    @Override
    public boolean esFallido()
    {
        return true;
    }

    @Override
    public String getNombre()
    {
        return "Ya se tomo una carta en el turno actual";
    }
}
