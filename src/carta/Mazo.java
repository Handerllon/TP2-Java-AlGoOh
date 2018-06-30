package carta;

import AlGoOh.Jugador;
import carta.excepciones.MazoVacio;

import java.util.ArrayList;
import java.util.Stack;

public class Mazo
{
    private Stack<Carta> cartas;

    public Mazo(Jugador jugador, Jugador oponente)
    {
        this.cartas = new Stack<Carta>();
        //En el mazo hay un maximo de 40 cartas, con las cuales se comienza el juego
        int cantidadCartas = 40;

        FabricaCartas fabricaCartas = new FabricaCartas(jugador, oponente);

        ArrayList<String> nombresMonstruosNoNormales = fabricaCartas.obtenerNombresCartasMonstruosNoNormales();
        for (int i = 0; i < nombresMonstruosNoNormales.size() && cantidadCartas > 0; i++)
        {
            cartas.push(fabricaCartas.crearCartaMonstruo(nombresMonstruosNoNormales.get(i)));
            cantidadCartas--;
        }

        ArrayList<String> nombresMagicas = fabricaCartas.obtenerNombresCartasMagicas();
        for (int i = 0; i < nombresMagicas.size() && cantidadCartas > 0; i++)
        {
            cartas.push(fabricaCartas.crearCartaMagica(nombresMagicas.get(i)));
            cantidadCartas--;
        }

        ArrayList<String> nombresCampo = fabricaCartas.obtenerNombresCartasCampo();
        for (int i = 0; i < nombresCampo.size() && cantidadCartas > 0; i++)
        {
            cartas.push(fabricaCartas.crearCartaCampo(nombresCampo.get(i)));
            cantidadCartas--;
        }

        ArrayList<String> nombresTrampas = fabricaCartas.obtenerNombresCartasTrampa();
        for (int i = 0; i < nombresTrampas.size() && cantidadCartas > 0; i++)
        {
            cartas.push(fabricaCartas.crearCartaTrampa(nombresTrampas.get(i)));
            cantidadCartas--;
        }

        // Agrego todas las que se pueden repetir.
        ArrayList<String> nombresMonstruosNormales = fabricaCartas.obtenerNombresCartasMonstruosNormales();
        for (int i = 0; cantidadCartas > 0; i++)
        {
            if (i == nombresMonstruosNormales.size())
                i = 0;
            cartas.push(fabricaCartas.crearCartaMonstruo(nombresMonstruosNormales.get(i)));
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

    public Carta tomarCarta()
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
