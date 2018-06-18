package areaDeJuego;

import carta.Carta;

import java.util.HashMap;

public class RegionCampo extends Region
{
    private static int CAPACIDAD_REGION_CAMPO = 40;

    public RegionCampo()
    {
        this.cartas = new HashMap<String, Carta>();
        this.capacidad = CAPACIDAD_REGION_CAMPO;
    }

    public void removerTodasLasCartas()
    {
        this.capacidad = CAPACIDAD_REGION_CAMPO;
        this.cartas.clear();
    }

}
