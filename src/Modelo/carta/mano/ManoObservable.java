package Modelo.carta.mano;

import Modelo.observadores.ObservadorDeMano;

public interface ManoObservable
{
    void registrarObsevador(ObservadorDeMano observador);

    void eliminarObservador(ObservadorDeMano observador);

    void notificarEgresoDeCartaAMano();

    void notificarIngresoDeCartaAMano();
}
