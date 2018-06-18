package carta;

import carta.excepciones.NoSePuedenAgregarMasCartasALaMano;

import java.util.ArrayList;
import java.util.Collection;

public class Mano
{
    private static int CANTIDAD_MAXIMA = 6;
    private Collection<Carta> cartas;
    private int cantidadEnMano = 0;

    public Mano(int cantidadInicial)
    {
        this.cantidadEnMano = cantidadInicial;
        this.cartas = new ArrayList<Carta>();
    }

    public void agregarCarta(Carta carta)
    {
        if (cantidadEnMano <= CANTIDAD_MAXIMA)
        {
            cartas.add(carta);
        }
        else throw new NoSePuedenAgregarMasCartasALaMano();
    }
}
