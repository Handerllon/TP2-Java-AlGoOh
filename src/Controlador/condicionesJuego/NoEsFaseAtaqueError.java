package Controlador.condicionesJuego;

public class NoEsFaseAtaqueError implements EstadoVerificador
{
    @Override
    public boolean esFallido()
    {
        return false;
    }

    @Override
    public String getNombre()
    {
        return "No es fase de ataque";
    }
}
