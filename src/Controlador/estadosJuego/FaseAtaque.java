package Controlador.estadosJuego;

import Modelo.Jugador;
import Modelo.carta.monstruo.FabricaCartasMonstruo;

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
        // DEBUG: Pruebo los fines de juego. Seleccionar el que se quiera.
        //
        //jugador.disminuirPuntosVida(jugador.getPuntosDeVida());
        //


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
