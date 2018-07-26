package Modelo.carta;

import Modelo.Jugador;
import Modelo.carta.orientacion.Orientacion;

public abstract class Carta implements Orientacion
{
    protected String nombre;
    protected boolean orientacionArriba;
    private Jugador jugador, oponente;
    protected String rutaImagen;

    public Carta(Jugador jugador, Jugador oponente, String rutaImagen)
    {
        this.orientacionArriba = false;
        this.jugador = jugador;
        this.oponente = oponente;
        this.rutaImagen = rutaImagen;
    }

    public String getLocacionDeImagen()
    {

        return this.rutaImagen;
    }

    public String getNombre()
    {
        return nombre;
    }

    public Jugador getPropietario()
    {
        return this.jugador;
    }

    public Jugador getOponente()
    {
        return this.oponente;
    }

    public void setOponente(Jugador oponente)
    {
        this.oponente = oponente;
    }

    // ------------------------------------
    // Métodos de orientación de cartas.
    // ------------------------------------
    @Override
    public void cambiarOrientacion()
    {
        this.orientacionArriba = !this.orientacionArriba;
    }

    @Override
    public boolean estaBocaArriba()
    {
        return this.orientacionArriba == true;
    }

    @Override
    public boolean estaBocaAbajo()
    {
        return !this.estaBocaArriba();
    }

    // ------------------------------------
    // Métodos de tipo de carta.
    // ------------------------------------
    public abstract boolean esCampo();

    public abstract boolean esMagica();

    public abstract boolean esMonstruo();

    public abstract boolean esTrampa();

    public boolean esParteExodia()
    {
        return false;
    }
}
