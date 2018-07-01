package AlGoOh.EstadosJuego;

import AlGoOh.Jugador;

import java.util.Scanner;

public class FasePreparacion implements Fase
{
    public FasePreparacion()
    {
    }

    public Fase cambiarFase()
    {
        return new FaseAtaque();
    }

    @Override
    public void jugar(Jugador jugador)
    {
        // TODO: implementar la fase.
        this.finalizarFase();
    }

    public void finalizarFase()
    {
        Scanner s = new Scanner(System.in);

        System.out.println("Presionar ENTER para terminar la fase.");

        s.nextLine();
    }
}
