package Modelo.carta.trampa;

import Modelo.observadores.ObservadorDeCartaTrampa;

public interface CartaTrampaObservable
{
    void agregarObsevadorCartaTrampa(ObservadorDeCartaTrampa observador);

    void quitarObservadorCartaTrampa(ObservadorDeCartaTrampa observador);

    void notificarObservadoresCartaTrampa();

    // ------------------------------------
    // Métodos de consultas.
    // ------------------------------------
}
