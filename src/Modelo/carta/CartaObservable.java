package Modelo.carta;

import Modelo.observadores.ObservadorDeCarta;

public interface CartaObservable
{
    void registrarObsevador(ObservadorDeCarta observador);

    void eliminarObservador(ObservadorDeCarta observador);

    void notificarCambioDeOrientacionDeCarta();
}
