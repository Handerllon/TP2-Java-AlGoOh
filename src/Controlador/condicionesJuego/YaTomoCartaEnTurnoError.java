package Controlador.condicionesJuego;

public class YaTomoCartaEnTurnoError implements EstadoVerificador
{
    @Override
    public boolean esFallido()
    {
        return false;
    }

    @Override
    public String getNombre()
    {
        return "Ya se tomo una carta en el turno actual";
    }
}
