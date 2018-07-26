package Controlador.condicionesJuego;

import Modelo.Jugador;

public class JugadorNoPermitidoParaJugar implements EstadoVerificador
{
    Jugador responsable;

    public JugadorNoPermitidoParaJugar(Jugador jugador)
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
        return "Jugador no permitido para jugar";
    }
}