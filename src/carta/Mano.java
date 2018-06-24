package carta;

import carta.excepciones.NoSePuedenAgregarMasCartasALaMano;

import java.util.LinkedList;

public class Mano
{
    private static int CANTIDAD_MAXIMA = 6;
    private LinkedList<Carta> cartas;

    public Mano()
    {
        this.cartas = new LinkedList<>();
    }

    public void agregarCarta(Carta carta)
    {
        if (this.cantidadDeCartas() <= CANTIDAD_MAXIMA)
        {
            cartas.add(carta);
        } else throw new NoSePuedenAgregarMasCartasALaMano();
    }

    public void quitarCarta(Carta carta)
    {
        this.cartas.remove(carta);
    }

    public Carta quitarUltimaCarta()
    {
        return this.cartas.removeLast();
    }

    public int cantidadDeCartas()
    {
        return cartas.size();
    }

    public boolean manoLlena()
    {
        return this.cantidadDeCartas() == this.CANTIDAD_MAXIMA;
    }
}
