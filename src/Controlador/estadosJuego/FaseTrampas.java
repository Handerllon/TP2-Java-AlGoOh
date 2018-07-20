package Controlador.estadosJuego;

import Controlador.Controlador;
import Modelo.Jugador;

public class FaseTrampas implements Fase
{
    private Controlador controlador;

    public FaseTrampas(Controlador controlador)
    {
        this.controlador = controlador;
    }

    public Fase cambiarFase()
    {
        return new FaseFinal(this.controlador);
    }

    @Override
    public String nombre()
    {
        return "Trampas";
    }

    @Override
    public void jugar(Jugador jugador)
    {
        // TODO: implementar la fase.
        System.out.println("----------------------");
        System.out.println(jugador.obtenerNombre() + " Fase " + this.nombre());
        System.out.println("----------------------");
    }

//    public void finalizarFase()
//    {
//        //this.controlador.actualizarEstado();
//    }
}
