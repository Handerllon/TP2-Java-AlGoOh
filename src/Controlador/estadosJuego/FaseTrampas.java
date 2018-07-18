package Controlador.estadosJuego;

import Modelo.Jugador;
import Vista.Vista;

public class FaseTrampas implements Fase
{
    private Vista vista;

    public FaseTrampas(Vista vista)
    {
        this.vista = vista;
    }

    public Fase cambiarFase()
    {
        return new FaseFinal(this.vista);
    }

    @Override
    public void jugar(Jugador jugador)
    {
        // TODO: implementar la fase.
        System.out.println("----------------------");
        System.out.println(jugador.obtenerNombre() + " FaseTrampas.");
        System.out.println("----------------------");
        this.finalizarFase();
    }

    public void finalizarFase()
    {
        this.vista.actualizarEstado();
    }
}
