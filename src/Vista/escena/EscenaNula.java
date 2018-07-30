package Vista.escena;

import javafx.scene.layout.GridPane;

public final class EscenaNula implements Escena
{
    private static EscenaNula instancia = null;

    // --------------------------------------------------------------------
    // Métodos de construcción e inicialización.
    // --------------------------------------------------------------------
    private EscenaNula()
    {

    }

    public static EscenaNula getInstancia()
    {
        if (instancia == null)
        {
            instancia = new EscenaNula();
        }
        return instancia;
    }

    public EscenaNula clone() throws CloneNotSupportedException
    {
        throw new CloneNotSupportedException();
    }

    @Override
    public Escena cambiarEscena()
    {
        return null;
    }

    @Override
    public void mostrar()
    {

    }

    @Override
    public void actualizarEstado()
    {

    }

    @Override
    public boolean terminoElJuego()
    {
        return true;
    }

    @Override
    public void finDeJuego()
    {

    }

    @Override
    public void playMedia()
    {

    }

    @Override
    public void stopMedia()
    {

    }

    @Override
    public void cerrar()
    {

    }

    @Override
    public GridPane getGridPaneEscena()
    {
        return null;
    }

    @Override
    public void mostrarJugadorActual()
    {

    }

    @Override
    public void mostrarFaseActual()
    {

    }
}
