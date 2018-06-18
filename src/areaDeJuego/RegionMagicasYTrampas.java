package areaDeJuego;

import carta.Carta;

import java.util.HashMap;

public class RegionMagicasYTrampas extends Region
{
    private static int CAPACIDAD_REGION_MAGICAS = 5;

    public RegionMagicasYTrampas()
    {
        this.cartas = new HashMap<String, Carta>();
        this.capacidad = CAPACIDAD_REGION_MAGICAS;
    }

    public void removerTodasLasCartas()
    {
        this.capacidad = CAPACIDAD_REGION_MAGICAS;
        this.cartas.clear();
    }
}
