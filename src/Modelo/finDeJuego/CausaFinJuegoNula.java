package Modelo.finDeJuego;

public class CausaFinJuegoNula extends CausaFinJuego
{
    private static String CAUSA = "No hay causa de fin de juego";

    public CausaFinJuegoNula()
    {
        super(CAUSA, null);
    }
}
