package Controlador.estadosJuego;

import Modelo.Jugador;

public interface Fase
{
    void jugar(Jugador jugador);

    Fase cambiarFase();

    // TODO: esto lo llama el controlador cuando en la vista se aprieta el botón de finalización de fase.
    void finalizarFase();
}
