package Modelo.finDeJuego;

import Modelo.Jugador;

public class CausaSinCartasEnMazo extends CausaFinJuego
{
    private static String CAUSA = "Sin cartas en el mazo";

    public CausaSinCartasEnMazo(Jugador jugadorAsociadoAlFin)
    {
        super(CAUSA, jugadorAsociadoAlFin);
    }
}