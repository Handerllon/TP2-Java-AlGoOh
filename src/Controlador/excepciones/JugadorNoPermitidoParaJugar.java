package Controlador.excepciones;

import Modelo.Jugador;

public class JugadorNoPermitidoParaJugar extends Exception
{
    Jugador jugador;

    public JugadorNoPermitidoParaJugar(Jugador jugador)
    {
        this.jugador = jugador;
    }

    public String obtenerNombreJugador()
    {
        return this.jugador.obtenerNombre();
    }
}
