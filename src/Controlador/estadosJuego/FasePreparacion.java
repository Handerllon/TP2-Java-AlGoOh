package Controlador.estadosJuego;

import Modelo.Jugador;
import Controlador.Controlador;

public class FasePreparacion implements Fase
{
    private Controlador controlador;

    public FasePreparacion(Controlador controlador)
    {
        this.controlador = controlador;
    }

    public Fase cambiarFase()
    {
        return new FaseAtaque(this.controlador);
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
        //this.controlador.actualizarEstado();
    }
}
