package Controlador.condicionesJuego;

public class NoSeAtacaEnPrimerTurnoJuegoError implements EstadoVerificador
{
    @Override
    public boolean esFallido()
    {
        return false;
    }

    @Override
    public String getNombre()
    {
        return "No se puede atacar en el primer turno dele juego";
    }
}
