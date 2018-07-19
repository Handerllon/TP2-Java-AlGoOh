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
        // TODO: hacer como con la vista final.
        for (int numeroFase = 1; numeroFase <= this.cantidadFases; numeroFase++)
        {
            this.fase.jugar(this.jugador);
            this.fase = this.fase.cambiarFase();
        }
    }
}
