package Modelo.areaDeJuego;

import Modelo.Jugador;
import Modelo.areaDeJuego.excepciones.RegionSinEspacioLibre;
import Modelo.carta.Carta;
import Modelo.carta.excepciones.CartaNoExisteEnRegionError;

import java.util.ArrayList;

public abstract class Region<T extends Carta> implements RegionObservable
{
    protected ArrayList<T> cartas = new ArrayList<>();
    protected int capacidadMaxima;
    protected Jugador jugador, oponente;
    protected T ultimaCartaEnEntrar, ultimaCartaEnSalir;
    protected ArrayList<ObservadorRegion> observadoresRegion = new ArrayList<>();

    public Region(int capacidadMaxima, Jugador jugador)
    {
        this.capacidadMaxima = capacidadMaxima;
        this.jugador = jugador;
    }

    public void setOponente(Jugador oponente)
    {
        this.oponente = oponente;
    }

    public void colocarCarta(T carta)
    {
        if (this.hayEspacioLibre())
        {
            this.cartas.add(carta);
            this.ultimaCartaEnEntrar = carta;
            this.notificarAgregacionCarta();
        } else
            throw new RegionSinEspacioLibre(this);
    }

    public void removerCarta(T carta)
    {
        if (this.contieneCarta(carta))
        {
            this.ultimaCartaEnSalir = carta;
            this.cartas.remove(carta);
            this.notificarRemocionCarta();
        } else
            throw new CartaNoExisteEnRegionError(carta);
    }

    public boolean contieneCarta(T carta)
    {
        return cartas.contains(carta);
    }

    public ArrayList<T> getCartas()
    {
        return new ArrayList<>(this.cartas);
    }

    public void removerTodasLasCartas()
    {
        ArrayList<T> cartasARemover = this.getCartas();
        cartasARemover.forEach(item -> this.removerCarta(item));
    }

    public boolean hayEspacioLibre()
    {
        return this.cartas.size() < this.capacidadMaxima;
    }

    public boolean estaVacia()
    {
        return this.cartas.isEmpty();
    }

    public T getUltimaCartaEnEntrar()
    {
        return this.ultimaCartaEnEntrar;
    }

    public T getUltimaCartaEnSalir()
    {
        return this.ultimaCartaEnSalir;
    }

    // Metodos de región observable.
    @Override
    public void agregarObsevador(ObservadorRegion observador)
    {
        this.observadoresRegion.add(observador);
    }

    @Override
    public void quitarObservador(ObservadorRegion observador)
    {
        if (this.observadoresRegion.isEmpty() == false)
        {
            this.observadoresRegion.remove(observador);
        }
    }

    @Override
    public void notificarAgregacionCarta()
    {
        this.observadoresRegion.forEach(item -> item.agregacionCarta(this));
    }

    @Override
    public void notificarRemocionCarta()
    {
        this.observadoresRegion.forEach(item -> item.remocionCarta(this));
    }

    public int cantidadCartas()
    {
        return this.cartas.size();
    }
}