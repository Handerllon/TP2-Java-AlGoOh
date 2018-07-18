package Controlador.estadosJuego;

import Modelo.Jugador;
import Vista.Vista;

public class FasePreparacion implements Fase
{
    private Vista vista;

    public FasePreparacion(Vista vista)
    {
        this.vista = vista;
    }

    public Fase cambiarFase()
    {
        return new FaseAtaque(this.vista);
    }

    @Override
    public void jugar(Jugador jugador)
    {
        // TODO: implementar la fase.
        System.out.println("----------------------");
        System.out.println(jugador.obtenerNombre() + " FasePreparacion.");
        System.out.println("----------------------");
        this.finalizarFase();
    }

    public void finalizarFase()
    {
        this.vista.actualizarEstado();
    }
}
