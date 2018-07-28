package Modelo.observadores;

import Modelo.areaDeJuego.Region;

public interface ObservadorRegion<R extends Region>
{
    void agregacionCarta(R region);

    void remocionCarta(R region);
}
