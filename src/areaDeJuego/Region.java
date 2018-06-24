package areaDeJuego;

import areaDeJuego.excepciones.RegionSinEspacioLibre;
import carta.Carta;
import carta.excepciones.CartaNoExisteEnRegion;

import java.util.ArrayList;
import java.util.HashMap;

public class Region<T extends Carta> {
    // TODO: está mal la estructura de datos porque pueden existir varias cartas con el mismo Key, y put() las pisaría.
    // Utilizas HashMap<String,Stack<T>>.
    protected HashMap<String, T> cartas;
    protected int capacidadMaxima, cantidadCartasActuales;

    public Region(int capacidadMaxima) {
        this.cartas = new HashMap<>();
        this.capacidadMaxima = capacidadMaxima;
        this.cantidadCartasActuales = 0;
    }

    public void colocarCarta(T carta) {
        if (this.hayEspacioLibre()) {
            cartas.put(carta.obtenerNombre(), carta);
            this.cantidadCartasActuales++;
        } else
            throw new RegionSinEspacioLibre(this);
    }

    public void removerCarta(T carta) {
        if (this.contieneCarta(carta)) {
            this.cartas.remove(carta.obtenerNombre());
            this.cantidadCartasActuales--;
        } else
            throw new CartaNoExisteEnRegion(carta);
    }

    public boolean contieneCarta(T carta) {
        return cartas.containsKey(carta.obtenerNombre());
    }

    public ArrayList<T> obtenerCartas() {
        ArrayList<T> listaDeCartas = new ArrayList<T>();

        this.cartas.forEach((key, value) -> listaDeCartas.add(value));

        return listaDeCartas;
    }

    public void removerTodasLasCartas() {
        this.cartas.clear();
        this.cantidadCartasActuales = 0;
    }

    public boolean hayEspacioLibre() {
        return this.cantidadCartasActuales < this.capacidadMaxima;
    }
}
