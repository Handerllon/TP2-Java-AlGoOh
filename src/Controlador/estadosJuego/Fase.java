package Controlador.estadosJuego;

import Modelo.Jugador;

public interface Fase
{
    void jugar(Jugador jugador);

    Fase cambiarFase();

    String nombre();
}
