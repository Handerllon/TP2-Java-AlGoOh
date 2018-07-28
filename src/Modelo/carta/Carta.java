package Modelo.carta;

import Modelo.Jugador;
import Modelo.carta.orientacion.Orientacion;
import Modelo.observadores.ObservadorDeCarta;

import java.util.ArrayList;

public abstract class Carta implements Orientacion, CartaObservable
{
    protected String nombre;
    protected boolean orientacionArriba;
    protected String rutaImagen;
    private Jugador jugador, oponente;
    private ArrayList<ObservadorDeCarta> observadores = new ArrayList<>();

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

    // --------------------------------------------------------------------
    // Metodos por ser observable de Carta.
    // --------------------------------------------------------------------
    @Override
    public void agregarObsevador(ObservadorDeCarta observador)
    {
        observadores.add(observador);
    }

    @Override
    public void quitarObservador(ObservadorDeCarta observador)
    {
        observadores.remove(observador);
    }

    @Override
    public void notificarObservadores()
    {
        observadores.forEach(observador -> observador.notificar());
    }

    // ------------------------------------
    // Métodos de orientación de cartas.
    // ------------------------------------
    @Override
    public void cambiarOrientacion()
    {
        this.orientacionArriba = !this.orientacionArriba;
        notificarObservadores();
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
