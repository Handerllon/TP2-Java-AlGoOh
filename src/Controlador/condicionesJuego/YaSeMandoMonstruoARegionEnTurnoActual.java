package Controlador.condicionesJuego;

public class YaSeMandoMonstruoARegionEnTurnoActual implements EstadoVerificador
{
    @Override
    public boolean esFallido()
    {
        return false;
    }

    @Override
    public String getNombre()
    {
        return "Ya se mandó el monstruo a la región en el turno actual";
    }
}
