package Controlador.condicionesJuego;

public class NoEsFaseFinalError implements EstadoVerificador
{
    @Override
    public boolean esFallido()
    {
        return false;
    }

    @Override
    public String getNombre()
    {
        return "No es fase final";
    }
}
