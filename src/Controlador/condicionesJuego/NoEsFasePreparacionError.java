package Controlador.condicionesJuego;

public class NoEsFasePreparacionError implements EstadoVerificador
{
    @Override
    public boolean esFallido()
    {
        return false;
    }

    @Override
    public String getNombre()
    {
        return "No es la fase de preparación";
    }
}
