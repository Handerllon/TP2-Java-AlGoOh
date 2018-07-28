package Modelo.carta;

import Modelo.observadores.ObservadorDeMano;

public interface ManoObservable
{
    void agregarObsevador(ObservadorDeMano observador);

    void quitarObservador(ObservadorDeMano observador);

    void notificarObservadores();
}
