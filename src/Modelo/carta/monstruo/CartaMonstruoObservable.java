package Modelo.carta.monstruo;

import Modelo.observadores.ObservadorDeCartaMonstruo;

public interface CartaMonstruoObservable
{
    void registrarObsevadorCartaMonstruo(ObservadorDeCartaMonstruo observador);

    void eliminarObservadorCartaMonstruo(ObservadorDeCartaMonstruo observador);

    void notificarCambioDeModoDeCarta();
}
