package Controlador.estadosJuego;

import Modelo.Jugador;

import java.util.concurrent.TimeUnit;

public class FasePreparacion implements Fase
{
    public FasePreparacion()
    {
    }

    public Fase cambiarFase()
    {
        return new FaseAtaque();
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
        try
        {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
