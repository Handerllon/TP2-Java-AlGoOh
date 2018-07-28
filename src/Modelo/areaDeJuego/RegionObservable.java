package Modelo.areaDeJuego;

import Modelo.observadores.ObservadorRegion;

public interface RegionObservable
{
    void agregarObsevador(ObservadorRegion observador);

    void quitarObservador(ObservadorRegion observador);

    void notificarAgregacionCarta();

    void notificarRemocionCarta();
}
