package Controlador.condicionesJuego;

public class CartaYaAtacoEnTurnoActualError implements EstadoVerificador
{
    @Override
    public boolean esFallido()
    {
        return false;
    }

    @Override
    public String getNombre()
    {
        return "Carta ya atacó en el turno actual";
    }
}
