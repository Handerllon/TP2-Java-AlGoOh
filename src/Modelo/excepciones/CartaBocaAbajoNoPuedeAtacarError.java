package Modelo.excepciones;

import Controlador.condicionesJuego.EstadoVerificador;

public class CartaBocaAbajoNoPuedeAtacarError extends RuntimeException implements EstadoVerificador
{
    @Override
    public boolean esFallido()
    {
        return false;
    }

    @Override
    public String getNombre()
    {
        return "Carta boca abajo no puede atacar";
    }
}