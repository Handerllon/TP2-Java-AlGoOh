package AlGoOh.EstadosJuego;

import AlGoOh.Jugador;

public class FaseFinal implements Fase
{
    public FaseFinal()
    {
    }

    public Fase cambiarFase()
    {
        return new FaseInicial();
    }

    @Override
    public void jugar(Jugador jugador)
    {
        // TODO: implementar la fase.
    }
}
