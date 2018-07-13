package Observador;

import Modelo.areaDeJuego.Region;
import Modelo.carta.Carta;

public interface ObservadorRegion
{
    void actualizar();

    <T extends Carta> void actualizar(Region<T> region);
}
