package Controlador.estadosJuego;

import Controlador.Controlador;
import Modelo.Jugador;

public class MaquinaFase
{
    private static final int cantidadFases = 5;
    private Controlador controlador;
    private Jugador jugador;
    private Fase fase;

    // --------------------------------------------------------------------
    // Métodos de construcción e inicialización.
    // --------------------------------------------------------------------
    public MaquinaFase(Jugador jugador, Controlador controlador)
    {
        this.controlador = controlador;
        this.jugador = jugador;
        this.fase = new FaseInicial(controlador);
    }

    public void jugar()
    {
        this.fase.jugar(this.jugador);
    }

    public void avanzarProximaFase()
    {
        this.fase = this.fase.cambiarFase();
    }

    public Jugador obtenerJugador()
    {
        return this.jugador;
    }

    public String nombreFaseActual()
    {
        return this.fase.nombre();
    }
}
