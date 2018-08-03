package Modelo.finDeJuego;

import Modelo.Jugador;

public final class CausaCincoPartesExodiaReunidas extends CausaFinJuego
{
    private static String CAUSA = "Cinco partes de Exodia reunidas.";
    private static CausaCincoPartesExodiaReunidas instancia = null;

    private CausaCincoPartesExodiaReunidas(Jugador jugadorAsociadoAlFin)
    {
        super(CAUSA, jugadorAsociadoAlFin);
    }

    public static CausaCincoPartesExodiaReunidas getInstancia(Jugador jugadorAsociadoAlFin)
    {
        if (instancia == null)
        {
            instancia = new CausaCincoPartesExodiaReunidas(jugadorAsociadoAlFin);
        }
        return instancia;
    }

    public boolean debidoACincoPartesDeExodiaReunidas()
    {
        return true;
    }
}