package Controlador.excepciones.especificas;

import Modelo.Jugador;

public class JugadorNoPermitidoParaJugar extends RuntimeException
{
    Jugador responsable;

    public JugadorNoPermitidoParaJugar(Jugador jugador)
    {
        this.responsable = jugador;
    }

    public Jugador getResponsable()
    {
        return this.responsable;
    }
}