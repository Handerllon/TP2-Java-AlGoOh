package AlGoOh.EstadosJuego;

import AlGoOh.Jugador;

public interface Fase
{
    void jugar(Jugador jugador);

    Fase cambiarFase();

    void finalizarFase();
}
