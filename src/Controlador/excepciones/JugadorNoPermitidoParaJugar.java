package Controlador.excepciones;

import Modelo.Jugador;

public class JugadorNoPermitidoParaJugar extends Exception implements Razon
{
    Jugador jugadorResponsable;

    public JugadorNoPermitidoParaJugar()
    {

    }

    public JugadorNoPermitidoParaJugar(Jugador jugador)
    {
        this.jugadorResponsable = jugador;
    }

    @Override
    public String nombre()
    {
        return "Jugador No Permitido Para Jugar";
    }
}