package Controlador.excepciones;

import Modelo.Jugador;

public class JugadorNoPermitidoParaJugar extends Exception {
    Jugador responsable;

    public JugadorNoPermitidoParaJugar(Jugador jugador) {
        this.responsable = jugador;
    }

    public Jugador obtenerResponsable() {
        return this.responsable;
    }
}