package Controlador.estadosJuego;

import Controlador.Controlador;
import Modelo.Jugador;

public class FaseInicial implements Fase
{
    private Controlador controlador;

    public FaseInicial(Controlador controlador)
    {
        this.controlador = controlador;
    }

    public Fase cambiarFase()
    {
        return new FasePreparacion(this.controlador);
    }

    @Override
    public String nombre()
    {
        return "Inicial";
    }

    @Override
    public void jugar(Jugador jugador)
    {
        // TODO: implementar la fase.
        System.out.println("----------------------");
        System.out.println(jugador.obtenerNombre() + " Fase " + this.nombre());
        System.out.println("----------------------");
        jugador.tomarCarta();
        this.controlador.avanzarProximaFase();
    }

//    public void finalizarFase()
//    {
//        this.controlador.actualizarEstado();
//    }
}
