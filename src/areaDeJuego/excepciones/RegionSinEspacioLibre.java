package areaDeJuego.excepciones;

import areaDeJuego.Region;

public class RegionSinEspacioLibre extends RuntimeException
{
    private Region region;

    public RegionSinEspacioLibre(Region region)
    {
        this.region = region;
    }
}
