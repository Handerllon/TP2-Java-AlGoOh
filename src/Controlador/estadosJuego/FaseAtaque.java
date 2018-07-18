package Controlador.estadosJuego;

import Modelo.Jugador;
import Vista.Vista;

public class FaseAtaque implements Fase
{
    private Vista vista;

    public FaseAtaque(Vista vista)
    {
        this.vista = vista;
    }

    public Fase cambiarFase()
    {
        return new FaseTrampas(this.vista);
    }

    @Override
    public void jugar(Jugador jugador)
    {
        // TODO: implementar la fase.
        System.out.println("----------------------");
        System.out.println(jugador.obtenerNombre() + " FaseAtaque.");
        System.out.println("----------------------");
        this.finalizarFase();
    }

    public void finalizarFase()
    {
        this.vista.actualizarEstado();
    }
}
