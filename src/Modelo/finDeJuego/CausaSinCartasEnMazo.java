package Modelo.finDeJuego;

import Modelo.Jugador;

public final class CausaSinCartasEnMazo extends CausaFinJuego
{
    private static String CAUSA = "Sin cartas en el mazo";
    private static CausaSinCartasEnMazo instancia = null;

    private CausaSinCartasEnMazo(Jugador jugadorAsociadoAlFin)
    {
        super(CAUSA, jugadorAsociadoAlFin);
    }

    public static CausaSinCartasEnMazo getInstancia(Jugador jugadorAsociadoAlFin)
    {
        if (instancia == null)
        {
            instancia = new CausaSinCartasEnMazo(jugadorAsociadoAlFin);
        }
        return instancia;
    }

    public boolean debidoASinCartasEnMazo()
    {
        return true;
    }
}