package Controlador.condicionesJuego;

public class NoHaySuficientesSacrificiosError implements EstadoVerificador
{
    @Override
    public boolean esFallido()
    {
        return false;
    }

    @Override
    public String getNombre()
    {
        return "No hay suficientes sacrificios";
    }
}
