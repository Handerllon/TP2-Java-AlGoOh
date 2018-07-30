package Modelo;

import Modelo.observadores.ObservadorDeJugador;

public interface JugadorObservable
{
    void registrarObsevador(ObservadorDeJugador observador);

    void eliminarObservador(ObservadorDeJugador observador);

    void notificarCambioEnPuntosDeVida();
}
