package Modelo.excepciones;

import Controlador.estadosJuego.EstadoVerificador;

public class CartaEnDefensaNoPuedeAtacarError extends RuntimeException implements EstadoVerificador
{
    @Override
    public boolean esFallido()
    {
        return false;
    }

    @Override
    public String getNombre()
    {
        return "Carta en modo defensa no puede atacar";
    }
}
