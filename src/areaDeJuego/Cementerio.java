package areaDeJuego;

import carta.Carta;

import java.util.HashMap;

public class Cementerio extends Region
{

    private static int CAPACIDAD_REGION_CEMENTERIO = 40;

    public Cementerio()
    {
        this.cartas = new HashMap<String, Carta>();
        this.capacidad = CAPACIDAD_REGION_CEMENTERIO;
    }

    public void removerTodasLasCartas()
    {
        this.capacidad = CAPACIDAD_REGION_CEMENTERIO;
        this.cartas.clear();
    }
}
