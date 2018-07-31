package Modelo.finDeJuego;

import Modelo.Jugador;

public final class CausaPuntosDeVidaNulos extends CausaFinJuego
{
    private static String CAUSA = "Puntos De Vida Nulos";
    private static CausaPuntosDeVidaNulos instancia = null;

    private CausaPuntosDeVidaNulos(Jugador jugadorAsociadoAlFin)
    {
        super(CAUSA, jugadorAsociadoAlFin);
    }

    public static CausaPuntosDeVidaNulos getInstancia(Jugador jugadorAsociadoAlFin)
    {
        if (instancia == null)
        {
            instancia = new CausaPuntosDeVidaNulos(jugadorAsociadoAlFin);
        }
        return instancia;
    }

    public boolean debidoAPuntosDeVidaNulos()
    {
        return true;
    }
}
