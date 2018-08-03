package Controlador.condicionesJuego;

import Controlador.estadosJuego.EstadoVerificador;

public class NoEsFaseAtaqueError implements EstadoVerificador
{
    @Override
    public boolean esFallido()
    {
        return true;
    }

    @Override
    public String getNombre()
    {
        return "No es fase de ataque.";
    }
}
