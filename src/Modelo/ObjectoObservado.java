package Modelo;

import Vista.ObjectoObservador;

public interface ObjectoObservado {


    void agregarObsevador(ObjectoObservador observer);

    void quitarObservador(ObjectoObservador observer);

    void notificarObservadores();

    Jugador obtenerJugador();

    Jugador obtenerOponente();
}
