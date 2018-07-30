package Modelo.observadores;

import Modelo.region.Region;

public interface ObservadorRegion<R extends Region>
{
    void ingresoCarta(R region);

    void egresoCarta(R region);
}
