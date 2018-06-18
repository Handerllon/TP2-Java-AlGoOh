package carta;

import java.util.ArrayList;
import java.util.Stack;

public class Mazo
{
    private Stack<Carta> cartas;

    public Mazo(int cantidadCartas)
    {
        this.cartas = new Stack<Carta>();

        FabricaCartas fabricaCartas = new FabricaCartas();
        ArrayList<String> nombresMonstruosNormales = fabricaCartas.obtenerNombresMonstruosNormales();
        ArrayList<String> nombresMonstruosNoNormales = fabricaCartas.obtenerNombresMonstruosNoNormales();
        ArrayList<String> nombresMagicas = fabricaCartas.obtenerNombresMagicas();
        ArrayList<String> nombresCampo = fabricaCartas.obtenerNombresCampo();
        ArrayList<String> nombresTrampas = fabricaCartas.obtenerNombresTrampas();

        for (int i = 0; i < nombresMonstruosNoNormales.size() && cantidadCartas > 0; i++)
        {
            cartas.push(fabricaCartas.obtenerCarta(nombresMonstruosNoNormales.get(i)));
            cantidadCartas--;
        }
        for (int i = 0; i < nombresMagicas.size() && cantidadCartas > 0; i++)
        {
            cartas.push(fabricaCartas.obtenerCarta(nombresMagicas.get(i)));
            cantidadCartas--;
        }
        for (int i = 0; i < nombresCampo.size() && cantidadCartas > 0; i++)
        {
            cartas.push(fabricaCartas.obtenerCarta(nombresCampo.get(i)));
            cantidadCartas--;
        }
        for (int i = 0; i < nombresTrampas.size() && cantidadCartas > 0; i++)
        {
            cartas.push(fabricaCartas.obtenerCarta(nombresTrampas.get(i)));
            cantidadCartas--;
        }

        // Agrego todas las que se pueden repetir.
        for (int i = 0; cantidadCartas > 0; i++)
        {
            if (i == nombresMonstruosNormales.size())
                i = 0;
            cartas.push(fabricaCartas.obtenerCarta(nombresMonstruosNormales.get(i)));
            cantidadCartas--;
        }

        this.mezclar();
    }

    private void mezclar()
    {
        Stack<Carta> mazoTemporal = new Stack<Carta>();
        while (!this.cartas.isEmpty())
        {
            int loc = (int) (Math.random() * cartas.size());
            mazoTemporal.push(this.cartas.get(loc));
            this.cartas.remove(loc);
        }
        this.cartas = mazoTemporal;
    }

    public Carta agarrarCarta()
    {
        if (cartas.size() > 0)
        {
            Carta carta = cartas.pop();
            return carta;
        } else
            // Podría ser un evento que notifique que el jugador se quedó sin cartas en el mazo y perdió el juego.
            throw new MazoVacio();
    }
}
