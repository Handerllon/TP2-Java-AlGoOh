package AlGoOh.EstadosJuego;

import AlGoOh.Jugador;

import java.util.Scanner;

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
        this.finalizarFase();
    }

    public void finalizarFase()
    {
        Scanner s = new Scanner(System.in);

        System.out.println("Presionar ENTER para terminar la fase.");

        s.nextLine();
    }
}
