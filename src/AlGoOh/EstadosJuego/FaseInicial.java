package AlGoOh.EstadosJuego;

import AlGoOh.Jugador;

import java.util.concurrent.TimeUnit;

public class FaseInicial implements Fase
{
    public FaseInicial()
    {
    }

    public Fase cambiarFase()
    {
        return new FasePreparacion();
    }

    @Override
    public void jugar(Jugador jugador)
    {
        // TODO: implementar la fase.
        System.out.println("----------------------");
        System.out.println(jugador.obtenerNombre() + " FaseInicial.");
        System.out.println("----------------------");
        jugador.tomarCarta();


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
