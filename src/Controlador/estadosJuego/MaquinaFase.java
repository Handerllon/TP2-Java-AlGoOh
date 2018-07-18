package Controlador.estadosJuego;

import Modelo.Jugador;
import Vista.Vista;

public class MaquinaFase
{
    private static final int cantidadFases = 5;
    protected Vista vista;
    private Jugador jugador;
    private Fase fase;

    // --------------------------------------------------------------------
    // Métodos de construcción e inicialización.
    // --------------------------------------------------------------------
    public MaquinaFase(Jugador jugador, Vista vista)
    {
        this.vista = vista;
        this.jugador = jugador;
        this.fase = new FaseInicial(vista);
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
