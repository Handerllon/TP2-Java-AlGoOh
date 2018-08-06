package Modelo.carta;

import Controlador.visitor.VisitadorCarta;
import Modelo.Jugador;
import Modelo.carta.orientacion.Orientacion;
import Modelo.observadores.ObservadorDeCarta;

import java.util.ArrayList;

public abstract class Carta implements Orientacion, CartaObservable
{
    protected String nombre;
    protected boolean orientacionArriba;
    protected String rutaImagen;
    protected Jugador jugador, oponente;
    private ArrayList<ObservadorDeCarta> observadores = new ArrayList<>();

    public Carta(Jugador jugador, Jugador oponente, String rutaImagen)
    {
        this.orientacionArriba = false;
        this.jugador = jugador;
        this.oponente = oponente;
        this.rutaImagen = rutaImagen;
    }

    public Carta()
    {

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

    // --------------------------------------------------------------------
    // Metodos para patrón visitador.
    // --------------------------------------------------------------------
    public abstract void aceptar(VisitadorCarta visitadorCarta);

    // --------------------------------------------------------------------
    // Metodos por ser observable de Carta.
    // --------------------------------------------------------------------
    @Override
    public void registrarObsevador(ObservadorDeCarta observador)
    {
        observadores.add(observador);
    }

    @Override
    public void eliminarObservador(ObservadorDeCarta observador)
    {
        observadores.remove(observador);
    }

    @Override
    public void notificarCambioDeOrientacionDeCarta()
    {
        observadores.forEach(observador -> observador.cartaCambioDeOrientacion());
    }

    // ------------------------------------
    // Métodos de orientación de cartas.
    // ------------------------------------
    @Override
    public void cambiarOrientacion()
    {
        this.orientacionArriba = !this.orientacionArriba;
        notificarCambioDeOrientacionDeCarta();
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

    public boolean tieneQuickEffect()
    {
        return false;
    }

    public void efecto()
    {

    }

    // ------------------------------------
    // Métodos de tipo de carta.
    // ------------------------------------
    public abstract boolean esMagica();

    public abstract boolean esMonstruo();

    public abstract boolean esTrampa();

    public boolean esParteExodia()
    {
        return false;
    }
}
