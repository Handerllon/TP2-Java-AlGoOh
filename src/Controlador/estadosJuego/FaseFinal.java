package Controlador.estadosJuego;

import Controlador.Controlador;
import Modelo.Jugador;

public class FaseFinal implements Fase
{
    private Controlador controlador;

    public FaseFinal(Controlador controlador)
    {
        this.controlador = controlador;
    }

    public Fase cambiarFase()
    {
        return new FaseInicial(this.controlador);
    }

    @Override
    public String nombre()
    {
        return "Final";
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
//        try
//        {
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException e)
//        {
//            e.printStackTrace();
//        }
//    }
}
