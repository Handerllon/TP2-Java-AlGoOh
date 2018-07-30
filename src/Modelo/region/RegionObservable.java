package Modelo.region;

import Modelo.observadores.ObservadorRegion;

public interface RegionObservable
{
    void registrarObsevador(ObservadorRegion observador);

    void eliminarObservador(ObservadorRegion observador);

    void notificarIngresoCarta();

    void notificarEgresoDeCarta();
}
