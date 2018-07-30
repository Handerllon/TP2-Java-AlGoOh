package Controlador.condicionesJuego;

import Controlador.estadosJuego.EstadoVerificador;

public class NoHayEspacioLibreEnRegionMonstruo implements EstadoVerificador
{
    @Override
    public boolean esFallido()
    {
        return true;
    }

    @Override
    public String getNombre()
    {
        return "No hay espacio libre en región monstruo.";
    }
}
