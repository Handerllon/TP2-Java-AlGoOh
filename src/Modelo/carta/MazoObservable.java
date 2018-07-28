package Modelo.carta;

import Modelo.observadores.ObservadorDeMazo;

public interface MazoObservable
{
    void registrarObsevador(ObservadorDeMazo observador);

    void eliminarObservador(ObservadorDeMazo observador);

    void notificarEvento();
}
