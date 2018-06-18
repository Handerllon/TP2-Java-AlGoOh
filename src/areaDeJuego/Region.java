package areaDeJuego;

import carta.Carta;
import carta.excepciones.CartaNoExisteEnRegion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Region
{
    protected HashMap<String, Carta> cartas;
    protected int capacidad;

    public void colocarCarta(Carta carta)
    {
        cartas.put(carta.obtenerNombre(), carta);
        this.capacidad--;
    }

    public Carta obtenerCarta(Carta carta)
    {
        return cartas.get(carta.obtenerNombre());
    }

    public boolean contieneCarta(Carta carta)
    {

        return cartas.containsKey(carta.obtenerNombre());
    }

    public ArrayList<Carta> obtenerCartas()
    {

        ArrayList<Carta> listaDeCartas = new ArrayList<Carta>();

        for (Map.Entry<String, Carta> entry : cartas.entrySet())
        {
            listaDeCartas.add(entry.getValue());
        }

        return listaDeCartas;
    }

    public boolean hayEspacioLibre()
    {
        return this.capacidad != 0;
    }

    public void removerCarta(Carta carta)
    {
        if (this.contieneCarta(carta))
            this.cartas.remove(carta.obtenerNombre());
        else
            throw new CartaNoExisteEnRegion(carta);
    }
}
