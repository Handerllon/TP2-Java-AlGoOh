package Modelo.areaDeJuego;

public interface ObservadorRegion<R extends Region>
{
    void agregacionCarta(R region);
    void remocionCarta(R region);
}
