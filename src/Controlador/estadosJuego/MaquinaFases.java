package Controlador.estadosJuego;

import Modelo.Jugador;

public class MaquinaFases
{
    private static int cantidadFases = 5;
    private Jugador jugador;
    private Fase fase;

    public MaquinaFases(Jugador jugador)
    {
        this.jugador = jugador;
        this.fase = new FaseInicial();
    }

    public void jugar()
    {
        for (int numeroFase = 1; numeroFase <= this.cantidadFases; numeroFase++)
        {
            this.fase.jugar(this.jugador);
            this.fase = this.fase.cambiarFase();
        }
    }
}
