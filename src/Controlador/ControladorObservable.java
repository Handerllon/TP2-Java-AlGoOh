package Controlador;

import Controlador.observadores.ObservadorDeControlador;

public interface ControladorObservable
{
    void registrarObsevador(ObservadorDeControlador observador);

    void eliminarObservador(ObservadorDeControlador observador);

    void notificarFinDeTurno();

    void notificarFinDeFase();
}
