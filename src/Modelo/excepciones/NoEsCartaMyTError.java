package Modelo.excepciones;

import Controlador.estadosJuego.EstadoVerificador;

public class NoEsCartaMyTError extends RuntimeException implements EstadoVerificador
{
    @Override
    public boolean esFallido()
    {
        return false;
    }

    @Override
    public String getNombre()
    {
        return "No es carta mágica o trampa.";
    }
}
