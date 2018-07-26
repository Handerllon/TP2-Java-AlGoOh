package Modelo.finDeJuego;

import Modelo.Jugador;

public abstract class CausaFinJuego
{
    private String tipoCausa;
    private Jugador jugadorAsociadoAlFin;

    private CausaFinJuego()
    {

    }

    public CausaFinJuego(String causa, Jugador jugadorAsociadoAlFin)
    {
        this.tipoCausa = causa;
        this.jugadorAsociadoAlFin = jugadorAsociadoAlFin;
    }

    public String getCausa()
    {
        return this.tipoCausa;
    }

    public String getNombreJugador()
    {
        return this.jugadorAsociadoAlFin.getNombre();
    }
}
