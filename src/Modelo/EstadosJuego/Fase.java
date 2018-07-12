package Modelo.EstadosJuego;

import Modelo.Jugador;

public interface Fase
{
    void jugar(Jugador jugador);

    Fase cambiarFase();

    void finalizarFase();
}
