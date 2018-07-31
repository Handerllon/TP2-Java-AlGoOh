package Modelo.carta.excepciones;

import Controlador.estadosJuego.EstadoVerificador;

public class SacrificiosInsuficientesError extends RuntimeException implements EstadoVerificador
{
    @Override
    public boolean esFallido()
    {
        return true;
    }

    @Override
    public String getNombre()
    {
        return "No hay suficientes sacrificios.";
    }
}