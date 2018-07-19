package Controlador.estadosJuego;

import Controlador.Controlador;
import Modelo.Jugador;
import Modelo.carta.FabricaCartas;
import Modelo.carta.Mano;
import Modelo.carta.monstruo.CartaMonstruo;

public class FaseInicial implements Fase
{
    private Controlador controlador;
    private boolean seComandoFinDeFase = false;

    public FaseInicial(Controlador controlador)
    {
        this.controlador = controlador;
    }

    public Fase cambiarFase()
    {
        return new FasePreparacion(this.controlador);
    }

    @Override
    public void jugar(Jugador jugador)
    {
        // TODO: implementar la fase.
        System.out.println("----------------------");
        System.out.println(jugador.obtenerNombre() + " FaseInicial.");
        System.out.println("----------------------");

        // TODO: simulo game over o victoria.
        //int randomNum = ThreadLocalRandom.current().nextInt(0, 5 + 1);
        int randomNum = 2;
        if (randomNum == 0)
        {
            for (int k = 0; k <= 40; k++)
                jugador.obtenerMazo().tomarCarta();
        }
        if (randomNum == 1)
        {
            Mano manoJugador = jugador.obtenerMano();

            FabricaCartas fabricaCartasJugador1 = new FabricaCartas(null, null);
            CartaMonstruo exodiaParte1 = fabricaCartasJugador1.crearCartaMonstruo("Exodia The Forbidden One");
            CartaMonstruo exodiaParte2 = fabricaCartasJugador1.crearCartaMonstruo("Left Arm Of The Forbidden One");
            CartaMonstruo exodiaParte3 = fabricaCartasJugador1.crearCartaMonstruo("Left Leg Of The Forbidden One");
            CartaMonstruo exodiaParte4 = fabricaCartasJugador1.crearCartaMonstruo("Right Arm Of The Forbidden One");
            CartaMonstruo exodiaParte5 = fabricaCartasJugador1.crearCartaMonstruo("Right Leg Of The Forbidden One");

            manoJugador.agregarCarta(exodiaParte1);
            manoJugador.agregarCarta(exodiaParte2);
            manoJugador.agregarCarta(exodiaParte3);
            manoJugador.agregarCarta(exodiaParte4);
            manoJugador.agregarCarta(exodiaParte5);
        }
    }

    public void finalizarFase()
    {
        //this.controlador.actualizarEstado();
    }
}
