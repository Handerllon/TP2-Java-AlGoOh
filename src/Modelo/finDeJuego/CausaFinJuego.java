package Modelo.finDeJuego;

import Modelo.Jugador;

public abstract class CausaFinJuego
{
    private String nombreCausa;
    private Jugador jugadorAsociadoAlFin;

    private CausaFinJuego()
    {

    }

    public CausaFinJuego(String causa, Jugador jugadorAsociadoAlFin)
    {
        this.nombreCausa = causa;
        this.jugadorAsociadoAlFin = jugadorAsociadoAlFin;
    }

    public String getNombreCausa()
    {
        return this.nombreCausa;
    }

    public String getNombreJugador()
    {
        return this.jugadorAsociadoAlFin.getNombre();
    }

    public boolean debidoASinCartasEnMazo()
    {
        return false;
    }

    public boolean debidoAPuntosDeVidaNulos()
    {
        return false;
    }

    public boolean debidoACincoPartesDeExodiaReunidas()
    {
        return false;
    }
}
