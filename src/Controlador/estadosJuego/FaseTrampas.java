package Controlador.estadosJuego;

import Modelo.Jugador;

import java.util.concurrent.TimeUnit;

public class FaseTrampas implements Fase
{
    public FaseTrampas()
    {
    }

    public Fase cambiarFase()
    {
        return new FaseFinal();
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
        try
        {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
