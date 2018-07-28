package Modelo.region.excepciones;

import Modelo.region.Region;

public class RegionSinEspacioLibre extends RuntimeException
{
    private Region region;

    public RegionSinEspacioLibre(Region region)
    {
        this.region = region;
    }
}
