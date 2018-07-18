package Controlador.estadosJuego;

import Modelo.Jugador;
import Vista.Vista;

public final class MaquinaFaseNula extends MaquinaFase
{
    private static MaquinaFaseNula instancia = null;

    // --------------------------------------------------------------------
    // Métodos de construcción e inicialización.
    // --------------------------------------------------------------------
    private MaquinaFaseNula(Jugador jugador, Vista vista)
    {
        super(jugador, vista);
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
