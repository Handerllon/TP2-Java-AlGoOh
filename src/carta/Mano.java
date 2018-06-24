package carta;

import carta.excepciones.NoSePuedenAgregarMasCartasALaMano;

import java.util.HashMap;

public class Mano
{
    private static int CANTIDAD_MAXIMA = 6;
    private HashMap<String, Carta> cartas;
    private int cantidadEnMano = 0;

    public Mano()
    {
        this.cantidadEnMano = 0;
        this.cartas = new HashMap<String, Carta>();
    }

    public void agregarCarta(Carta carta)
    {
        if (cantidadEnMano <= CANTIDAD_MAXIMA)
        {
            cartas.put(carta.obtenerNombre(), carta);
            this.cantidadEnMano++;
        } else throw new NoSePuedenAgregarMasCartasALaMano();
    }

    public void quitarCarta(String unNombreDeCarta)
    {
        this.cartas.remove(unNombreDeCarta);
        this.cantidadEnMano--;
    }

    public int cantidadDeCartas()
    {
        return cantidadEnMano;
    }
}
