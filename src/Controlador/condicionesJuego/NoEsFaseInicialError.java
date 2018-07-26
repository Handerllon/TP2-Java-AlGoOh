package Controlador.condicionesJuego;

public class NoEsFaseInicialError implements EstadoVerificador
{
    @Override
    public boolean esFallido()
    {
        return false;
    }

    @Override
    public String getNombre()
    {
        return "No es la fase inicial";
    }
}
