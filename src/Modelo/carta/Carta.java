package Modelo.carta;

import Modelo.Jugador;

public abstract class Carta
{
    protected String nombre;
    protected Orientacion orientacion;
    protected Jugador jugador, oponente;
    private String rutaImagen;

    public Carta(Jugador jugador, Jugador oponente, String rutaImagen)
    {
        this.orientacion = new OrientacionAbajo();
        this.jugador = jugador;
        this.oponente = oponente;
        this.rutaImagen = rutaImagen;
    }

    public String obtenerLocacionDeImagen()
    {

        return this.rutaImagen;
    }

    public String obtenerNombre()
    {
        return nombre;
    }

    public void cambiarOrientacion()
    {
        this.orientacion.cambiarOrientacion(this);
    }

    protected void establecerOrientacion(Orientacion orientacionNueva)
    {
        this.orientacion = orientacionNueva;
    }

    protected boolean orientacionArriba()
    {
        return this.orientacion instanceof OrientacionArriba;
    }

    public Jugador obtenerPropietario()
    {
        return this.jugador;
    }

    public boolean esParteExodia()
    {
        return false;
    }
}
