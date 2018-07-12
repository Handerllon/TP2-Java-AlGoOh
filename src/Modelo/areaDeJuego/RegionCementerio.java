package Modelo.areaDeJuego;

import Modelo.Jugador;
import Modelo.carta.Carta;

public class RegionCementerio extends Region<Carta>
{
    private static int CAPACIDAD_REGION_CEMENTERIO = 40;

    public RegionCementerio(Jugador jugador)
    {
        super(CAPACIDAD_REGION_CEMENTERIO, jugador);
    }
}
