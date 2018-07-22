package Modelo.carta.excepciones;

import Controlador.excepciones.Razon;

public class ManoLlenaError extends RuntimeException implements Razon
{
    @Override
    public String nombre()
    {
        return "Mano llena";
    }
}
