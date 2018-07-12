package Modelo.EstadosJuego;

import Modelo.Jugador;

import java.util.concurrent.TimeUnit;

public class FaseFinal implements Fase
{
    public FaseFinal()
    {
    }

    public Fase cambiarFase()
    {
        return new FaseInicial();
    }

    @Override
    public void jugar(Jugador jugador)
    {
        // TODO: implementar la fase.
        System.out.println("----------------------");
        System.out.println(jugador.obtenerNombre() + " FaseFinal.");
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
