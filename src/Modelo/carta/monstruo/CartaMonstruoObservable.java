package Modelo.carta.monstruo;

import Modelo.observadores.ObservadorDeCartaMonstruo;

public interface CartaMonstruoObservable
{
    void registrarObsevador(ObservadorDeCartaMonstruo observador);

    void eliminarObservador(ObservadorDeCartaMonstruo observador);

    void notificarCambioDeModoDeCarta();
}
