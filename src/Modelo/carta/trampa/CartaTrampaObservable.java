package Modelo.carta.trampa;

import Modelo.observadores.ObservadorDeCartaTrampa;

public interface CartaTrampaObservable
{
    void registrarObsevadorCartaTrampa(ObservadorDeCartaTrampa observador);

    void eliminarObservadorCartaTrampa(ObservadorDeCartaTrampa observador);

    void notificarEventoCartaTrampa();
}
