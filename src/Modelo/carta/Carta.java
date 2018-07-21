package Modelo.carta;

import Modelo.Jugador;
import Modelo.carta.orientacion.Orientacion;
import Modelo.carta.orientacion.OrientacionAbajo;
import Modelo.carta.orientacion.OrientacionArriba;

public abstract class Carta
{
    protected String nombre;
    protected Orientacion orientacion;
    protected Jugador jugador, oponente;
    protected String rutaImagen;

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

    public void establecerOrientacion(Orientacion orientacionNueva)
    {
        this.orientacion = orientacionNueva;
    }

    public boolean orientacionArriba()
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

    public abstract boolean esCampo();

    public abstract boolean esMagica();

    public abstract boolean esMonstruo();

    public abstract boolean esTrampa();
}
