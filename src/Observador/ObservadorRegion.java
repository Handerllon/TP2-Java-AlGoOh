package Observador;

import Modelo.areaDeJuego.Region;

public interface ObservadorRegion
{
    void actualizar();

    void actualizar(Region tRegion);
}
