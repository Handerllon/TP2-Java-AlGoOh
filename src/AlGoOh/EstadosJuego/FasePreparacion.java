package AlGoOh.EstadosJuego;

import AlGoOh.Jugador;

public class FasePreparacion implements Fase
{
    public FasePreparacion()
    {
    }

    public Fase cambiarFase()
    {
        return new FaseTrampas();
    }

    @Override
    public void jugar(Jugador jugador)
    {
        // TODO: implementar la fase.
    }
}
