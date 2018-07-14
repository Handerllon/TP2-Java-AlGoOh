package Modelo.finDeJuego;

import Modelo.Jugador;

public class CausaCincoPartesExodiaReunidas extends CausaFinJuego
{
    private static String CAUSA = "Cinco partes de Exodia reunidas";

    public CausaCincoPartesExodiaReunidas(Jugador jugadorAsociadoAlFin)
    {
        super(CAUSA, jugadorAsociadoAlFin);
    }
}