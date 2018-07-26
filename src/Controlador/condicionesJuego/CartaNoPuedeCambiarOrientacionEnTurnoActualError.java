package Controlador.condicionesJuego;

public class CartaNoPuedeCambiarOrientacionEnTurnoActualError implements EstadoVerificador
{
    @Override
    public boolean esFallido()
    {
        return false;
    }

    @Override
    public String getNombre()
    {
        return "Carta no puede cambiar la orientación en el turno actual";
    }
}
