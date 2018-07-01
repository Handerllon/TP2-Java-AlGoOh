package AlGoOh.EstadosJuego;

import AlGoOh.Jugador;

import java.util.Scanner;

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
        this.finalizarFase();
    }

    public void finalizarFase()
    {
        Scanner s = new Scanner(System.in);

        System.out.println("Presionar ENTER para terminar la fase.");

        s.nextLine();
    }
}
