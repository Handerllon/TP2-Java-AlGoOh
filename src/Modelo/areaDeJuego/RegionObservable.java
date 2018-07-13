package Modelo.areaDeJuego;

public interface RegionObservable
{
    void agregarObsevador(ObservadorRegion observador);

    void quitarObservador(ObservadorRegion observador);

    void notificarAgregacionCarta();
    void notificarRemocionCarta();
}
