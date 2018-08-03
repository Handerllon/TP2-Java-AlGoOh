package Controlador.condicionesJuego;

import Controlador.estadosJuego.EstadoVerificador;
import Modelo.Jugador;

public class JugadorNoPermitidoParaJugarError implements EstadoVerificador
{
    Jugador responsable;

    public JugadorNoPermitidoParaJugarError(Jugador jugador)
    {
        this.responsable = jugador;
    }

    public Jugador getResponsable()
    {
        return this.responsable;
    }

    @Override
    public boolean esFallido()
    {
        return true;
    }

    @Override
    public String getNombre()
    {
        return "Jugador no permitido para jugar.";
    }
}