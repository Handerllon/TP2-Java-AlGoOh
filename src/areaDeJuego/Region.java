package areaDeJuego;

import areaDeJuego.excepciones.RegionSinEspacioLibre;
import carta.Carta;
import carta.CartaMonstruo;
import carta.excepciones.CartaNoExisteEnRegion;

import java.util.ArrayList;
import java.util.LinkedList;

public class Region<T extends Carta>
{
    protected LinkedList<T> cartas;
    protected int capacidadMaxima;

    public Region(int capacidadMaxima)
    {
        this.cartas = new LinkedList<>();
        this.capacidadMaxima = capacidadMaxima;
    }

    public void colocarCarta(T carta)
    {
        if (this.hayEspacioLibre())
        {
            cartas.add(carta);
        } else
            throw new RegionSinEspacioLibre(this);
    }

    public void removerCarta(T carta)
    {
        if (this.contieneCarta(carta))
        {
            this.cartas.remove(carta);
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
    }

    public boolean hayEspacioLibre()
    {
        return this.cartas.size() < this.capacidadMaxima;
    }
    
}