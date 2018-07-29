package Modelo.excepciones;

import Controlador.condicionesJuego.EstadoVerificador;

public class NoEsCartaCampoError extends RuntimeException implements EstadoVerificador
{
    @Override
    public boolean esFallido()
    {
        return false;
    }

    @Override
    public String getNombre()
    {
        return "No es carta campo";
    }
}