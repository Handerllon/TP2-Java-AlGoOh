package Modelo.areaDeJuego;

import Modelo.Jugador;
import Modelo.areaDeJuego.excepciones.RegionSinEspacioLibre;
import Modelo.carta.Carta;
import Modelo.carta.excepciones.CartaNoExisteEnRegion;
import Observador.RegionObservable;
import Observador.ObservadorRegion;

import java.util.ArrayList;

public abstract class Region<T extends Carta> implements RegionObservable, ObservadorRegion
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

    public void establecerOponente(Jugador oponente)
    {
        this.oponente = oponente;
    }

    public void colocarCarta(T carta)
    {
        if (this.hayEspacioLibre())
        {
            this.cartas.add(carta);
            this.ultimaCartaEnEntrar = carta;
            this.notificarObservadores();
        } else
            throw new RegionSinEspacioLibre(this);
    }

    public void removerCarta(T carta)
    {
        if (this.contieneCarta(carta))
        {
            this.ultimaCartaEnSalir = carta;
            this.cartas.remove(carta);
            this.notificarObservadores();
        } else
            throw new CartaNoExisteEnRegion(carta);
    }

    public boolean contieneCarta(T carta)
    {
        return cartas.contains(carta);
    }

    public ArrayList<T> obtenerCartas()
    {
        return new ArrayList<>(this.cartas);
    }

    public void removerTodasLasCartas()
    {
        this.cartas.clear();
        this.notificarObservadores();
    }

    public boolean hayEspacioLibre()
    {
        return this.cartas.size() < this.capacidadMaxima;
    }

    public boolean estaVacia()
    {
        return this.cartas.isEmpty();
    }

    public ArrayList<T> obtenerCartasEnRegion()
    {
        return this.cartas;
    }

    public T obtenerUltimaCartaEnEntrar()
    {
        return this.ultimaCartaEnEntrar;
    }

    // Metodos de observadores de region.
    @Override
    public void agregarObsevador(ObservadorRegion observador)
    {
        this.observadoresRegion.add(observador);
    }

    @Override
    public void quitarObservador(ObservadorRegion observador)
    {
        this.observadoresRegion.remove(observador);
    }

    @Override
    public void notificarObservadores()
    {
        this.observadoresRegion.forEach(item -> item.actualizar(this));
    }

    @Override
    public void actualizar()
    {

    }

    @Override
    public <T extends Carta> void actualizar(Region<T> region)
    {

    }
}