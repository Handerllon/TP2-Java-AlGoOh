package Controlador.estadosJuego;

import Controlador.Controlador;
import Modelo.Jugador;

public class FaseAtaque implements Fase
{
    private Controlador controlador;

    public FaseAtaque(Controlador controlador)
    {
        this.controlador = controlador;
    }

    public Fase cambiarFase()
    {
        return new FaseTrampas(this.controlador);
    }

    @Override
    public String nombre()
    {
        return "Ataque";
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
//        this.vista.actualizarEstado();
//    }
}
