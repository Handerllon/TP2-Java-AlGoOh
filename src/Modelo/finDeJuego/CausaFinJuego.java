package Modelo.finDeJuego;

import Modelo.Jugador;

public abstract class CausaFinJuego
{
    private String tipoCausa;
    private Jugador jugadorAsociadoAlFin;

    public CausaFinJuego(String causa, Jugador jugadorAsociadoAlFin)
    {
        this.tipoCausa = causa;
        this.jugadorAsociadoAlFin = jugadorAsociadoAlFin;
    }

    public String obtenerCausa()
    {
        return this.tipoCausa;
    }

    public String obtenerNombreJugador()
    {
        return this.jugadorAsociadoAlFin.obtenerNombre();
    }
}
