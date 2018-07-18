package Controlador.estadosJuego;

import Modelo.Jugador;
import Vista.Vista;

import java.util.concurrent.TimeUnit;

public class FaseFinal implements Fase
{
    private Vista vista;

    public FaseFinal(Vista vista)
    {
        this.vista = vista;
    }

    public Fase cambiarFase()
    {
        return new FaseInicial(this.vista);
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
