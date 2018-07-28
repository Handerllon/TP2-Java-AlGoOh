package Modelo.carta;

import Modelo.observadores.ObservadorDeCarta;

public interface CartaObservable
{
    void agregarObsevador(ObservadorDeCarta observador);

    void quitarObservador(ObservadorDeCarta observador);

    void notificarObservadores();
}
