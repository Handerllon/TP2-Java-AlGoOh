package areaDeJuego;

import carta.Carta;

import java.util.HashMap;

public class RegionMonstruos extends Region
{
    private static int CAPACIDAD_REGION_MONSTRUOS = 5;

    public RegionMonstruos()
    {
        this.cartas = new HashMap<String, Carta>();
        this.capacidad = CAPACIDAD_REGION_MONSTRUOS;
    }

    public void removerTodasLasCartas()
    {
        this.capacidad = CAPACIDAD_REGION_MONSTRUOS;
        this.cartas.clear();
    }

    public boolean estaVacia()
    {
        return this.capacidad == CAPACIDAD_REGION_MONSTRUOS;
    }

}
