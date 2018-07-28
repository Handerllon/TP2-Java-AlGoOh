package Modelo.areaDeJuego;

import Modelo.Jugador;
import Modelo.areaDeJuego.excepciones.RegionSinEspacioLibre;
import Modelo.carta.Carta;
import Modelo.carta.excepciones.CartaNoExisteEnRegionError;
import Modelo.observadores.ObservadorRegion;

import java.util.ArrayList;

public abstract class Region<T extends Carta> implements RegionObservable
{
    protected ArrayList<T> cartas = new ArrayList<>();
    protected int capacidadMaxima;
    protected Jugador jugador, oponente;
    protected T ultimaCartaEnEntrar, ultimaCartaEnSalir;
    protected ArrayList<ObservadorRegion> observadoresRegion = new ArrayList<>();

    // --------------------------------------------------------------------
    // Métodos de construcción e inicialización.
    // --------------------------------------------------------------------
    public Region(int capacidadMaxima, Jugador jugador)
    {
        this.capacidadMaxima = capacidadMaxima;
        this.jugador = jugador;
    }

    public void setOponente(Jugador oponente)
    {
        this.oponente = oponente;
    }

    public void colocarCarta(T carta) throws RegionSinEspacioLibre
    {
        if (this.hayEspacioLibre())
        {
            this.cartas.add(carta);
            this.ultimaCartaEnEntrar = carta;
            this.notificarIngresoCarta();
        } else
            throw new RegionSinEspacioLibre(this);
    }

    public void removerCarta(T carta) throws CartaNoExisteEnRegionError
    {
        if (this.contieneCarta(carta))
        {
            this.ultimaCartaEnSalir = carta;
            this.cartas.remove(carta);
            this.notificarSalidaCarta();
        } else
            throw new CartaNoExisteEnRegionError(carta);
    }

    public void removerTodasLasCartas()
    {
        ArrayList<T> cartasARemover = this.getCartas();
        cartasARemover.forEach(item -> this.removerCarta(item));
        // No puedo avisarle a los observadores con notificarSalidaCarta() porque eso es
        // para una sola carta.
        this.notificarCambios();
    }

    // ------------------------------------
    // Métodos de consultas.
    // ------------------------------------
    public boolean contieneCarta(T carta)
    {
        return cartas.contains(carta);
    }

    public boolean hayEspacioLibre()
    {
        return this.cartas.size() < this.capacidadMaxima;
    }

    public boolean estaVacia()
    {
        return this.cartas.isEmpty();
    }

    public ArrayList<T> getCartas()
    {
        return new ArrayList<>(this.cartas);
    }

    public int getCantidadCartas()
    {
        return this.cartas.size();
    }

    public T getUltimaCartaEnEntrar()
    {
        return this.ultimaCartaEnEntrar;
    }

    public T getUltimaCartaEnSalir()
    {
        return this.ultimaCartaEnSalir;
    }

    // --------------------------------------------------------------------
    // Metodos por ser un observable de Región.
    // --------------------------------------------------------------------
    @Override
    public void registrarObsevador(ObservadorRegion observador)
    {
        this.observadoresRegion.add(observador);
    }

    @Override
    public void eliminarObservador(ObservadorRegion observador)
    {
        this.observadoresRegion.remove(observador);
    }

    @Override
    public void notificarIngresoCarta()
    {
        this.observadoresRegion.forEach(observador ->
        {
            observador.ingresoCarta(this);
            observador.huboCambios();
        });
    }

    @Override
    public void notificarSalidaCarta()
    {
        this.observadoresRegion.forEach(observador ->
        {
            observador.egresoCarta(this);
            observador.huboCambios();
        });
    }

    @Override
    public void notificarCambios()
    {
        this.observadoresRegion.forEach(observador -> observador.huboCambios());
    }
}