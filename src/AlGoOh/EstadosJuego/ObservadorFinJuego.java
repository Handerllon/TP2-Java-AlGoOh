package AlGoOh.EstadosJuego;

public class ObservadorFinJuego
{
    private boolean eventoFinalizacionJuego;

    public ObservadorFinJuego()
    {
        this.eventoFinalizacionJuego = false;
    }

    public void mazoVacio()
    {
        this.eventoFinalizacionJuego = true;
    }

    public boolean eventoFinalizacionJuego()
    {
        return this.eventoFinalizacionJuego;
    }
}
