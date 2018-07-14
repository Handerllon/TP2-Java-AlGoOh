package Controlador.estadosJuego;

import Modelo.Jugador;

import java.util.concurrent.TimeUnit;

public class FaseAtaque implements Fase
{
    public FaseAtaque()
    {
    }

    public Fase cambiarFase()
    {
        return new FaseTrampas();
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
        try
        {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
