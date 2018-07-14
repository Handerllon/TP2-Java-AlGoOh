package Modelo.finDeJuego;

import Modelo.Jugador;

public class CausaPuntosDeVidaNulos extends CausaFinJuego
{
    private static String CAUSA = "Puntos De Vida Nulos";

    public CausaPuntosDeVidaNulos(Jugador jugadorAsociadoAlFin){
        super(CAUSA,jugadorAsociadoAlFin);
    }
}
