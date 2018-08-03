package Controlador.condicionesJuego;

import Controlador.estadosJuego.EstadoVerificador;

public class NoEsFasePreparacionError implements EstadoVerificador
{
    @Override
    public boolean esFallido()
    {
        return true;
    }

    @Override
    public String getNombre()
    {
        return "No es la fase de preparación.";
    }
}
