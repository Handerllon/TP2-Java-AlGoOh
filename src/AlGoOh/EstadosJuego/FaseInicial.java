package AlGoOh.EstadosJuego;

import AlGoOh.Jugador;

public class FaseInicial implements Fase
{
    public FaseInicial()
    {
    }

    public Fase cambiarFase()
    {
        return new FasePreparacion();
    }

    @Override
    public void jugar(Jugador jugador)
    {
        // TODO: implementar la fase.
        jugador.tomarCarta();
    }
}
