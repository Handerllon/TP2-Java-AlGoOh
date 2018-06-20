package areaDeJuego;

import carta.Carta;
import carta.excepciones.CartaNoExisteEnRegion;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Region
{
    protected HashMap<String, Carta> cartas;
    protected int capacidad, cantidadCartas;

    public Region(int capacidad){
        this.cartas = new HashMap<String, Carta>();
        this.capacidad = capacidad;
        this.cantidadCartas = 0;
    }

    public void colocarCarta(Carta carta)
    {
        cartas.put(carta.obtenerNombre(), carta);
        this.cantidadCartas--;
    }

    public void removerCarta(Carta carta)
    {
        if (this.contieneCarta(carta))
        {
            this.cartas.remove(carta.obtenerNombre());
            this.cantidadCartas--;
        } else
            throw new CartaNoExisteEnRegion(carta);
    }

    public boolean contieneCarta(Carta carta)
    {
        return cartas.containsKey(carta.obtenerNombre());
    }

    public ArrayList<Carta> obtenerCartas()
    {
        ArrayList<Carta> listaDeCartas = new ArrayList<Carta>();

        this.cartas.forEach((key, value) -> listaDeCartas.add(value));

        return listaDeCartas;
    }

    public void removerTodasLasCartas()
    {
        this.cantidadCartas = 0;
        this.cartas.clear();
    }

    public boolean hayEspacioLibre()
    {
        return this.cantidadCartas < this.capacidad;
    }

    public boolean estaVacia()
    {
        return this.cantidadCartas == 0;
    }
    
    public void insertarCarta(Carta unaCarta){
    	
    	cartas.put(unaCarta.obtenerNombre(), unaCarta);
    }
}
