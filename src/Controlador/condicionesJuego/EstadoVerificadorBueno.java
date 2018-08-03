package Controlador.condicionesJuego;

import Controlador.estadosJuego.EstadoVerificador;

public class EstadoVerificadorBueno implements EstadoVerificador
{
    @Override
    public boolean esFallido()
    {
        return false;
    }

    @Override
    public String getNombre()
    {
        return "Estado bueno.";
    }
}
