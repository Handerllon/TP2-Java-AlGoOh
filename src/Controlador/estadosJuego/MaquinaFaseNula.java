package Controlador.estadosJuego;

import Controlador.Controlador;
import Modelo.Jugador;

public final class MaquinaFaseNula extends MaquinaFase
{
    private static MaquinaFaseNula instancia = null;

    // --------------------------------------------------------------------
    // Métodos de construcción e inicialización.
    // --------------------------------------------------------------------
    private MaquinaFaseNula(Jugador jugador, Controlador controlador)
    {
        super(jugador, controlador);
    }

    public static MaquinaFaseNula obtenerInstancia()
    {
        if (instancia == null)
        {
            instancia = new MaquinaFaseNula(null, null);
        }
        return instancia;
    }

    public MaquinaFaseNula clone() throws CloneNotSupportedException
    {
        throw new CloneNotSupportedException();
    }

    public void jugar()
    {
    }
}
