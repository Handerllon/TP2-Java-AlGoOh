package Controlador.condicionesJuego;

import Controlador.estadosJuego.EstadoVerificador;

public class SolicitanteNoEsPropietarioDeCartaError implements EstadoVerificador
{
    @Override
    public boolean esFallido()
    {
        return true;
    }

    @Override
    public String getNombre()
    {
        return "El solicitante no es propietario de la carta.";
    }
}
