package AlGoOh.EstadosJuego;

import AlGoOh.Jugador;

public class FaseTrampas implements Fase
{
    public FaseTrampas()
    {
    }

    public Fase cambiarFase()
    {
        return new FaseFinal();
    }

    @Override
    public void jugar(Jugador jugador)
    {
        // TODO: implementar la fase.
    }
}
