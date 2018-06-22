package carta;

import AlGoOh.Jugador;
import carta.campo.FabricaCartasCampo;
import carta.magica.FabricaCartasMagicas;
import carta.monstruo.FabricaCartasMonstruo;
import carta.trampa.FabricaCartasTrampa;

import java.util.ArrayList;
import java.util.Stack;

public class Mazo
{
    private Stack<Carta> cartas;

    public Mazo(int cantidadCartas, Jugador jugador, Jugador oponente)
    {
        this.cartas = new Stack<Carta>();

        FabricaCartasMonstruo fabricaCartasMonstruo = new FabricaCartasMonstruo(jugador,oponente);
        ArrayList<String> nombresMonstruosNormales = fabricaCartasMonstruo.obtenerNombresMonstruosNormales();
        ArrayList<String> nombresMonstruosNoNormales = fabricaCartasMonstruo.obtenerNombresMonstruosNoNormales();

        FabricaCartasMagicas fabricaCartasMagicas = new FabricaCartasMagicas(jugador,oponente);
        ArrayList<String> nombresMagicas = fabricaCartasMagicas.obtenerNombres();

        FabricaCartasCampo fabricaCartasCampo = new FabricaCartasCampo(jugador,oponente);
        ArrayList<String> nombresCampo = fabricaCartasCampo.obtenerNombres();

        FabricaCartasTrampa fabricaCartasTrampa = new FabricaCartasTrampa(jugador,oponente);
        ArrayList<String> nombresTrampas = fabricaCartasTrampa.obtenerNombres();

        for (int i = 0; i < nombresMonstruosNoNormales.size() && cantidadCartas > 0; i++)
        {
            cartas.push(fabricaCartasMonstruo.obtenerCarta(nombresMonstruosNoNormales.get(i)));
            cantidadCartas--;
        }
        for (int i = 0; i < nombresMagicas.size() && cantidadCartas > 0; i++)
        {
            cartas.push(fabricaCartasMagicas.obtenerCarta(nombresMagicas.get(i)));
            cantidadCartas--;
        }
        for (int i = 0; i < nombresCampo.size() && cantidadCartas > 0; i++)
        {
            cartas.push(fabricaCartasCampo.obtenerCarta(nombresCampo.get(i)));
            cantidadCartas--;
        }
        for (int i = 0; i < nombresTrampas.size() && cantidadCartas > 0; i++)
        {
            cartas.push(fabricaCartasTrampa.obtenerCarta(nombresTrampas.get(i)));
            cantidadCartas--;
        }

        // Agrego todas las que se pueden repetir.
        for (int i = 0; cantidadCartas > 0; i++)
        {
            if (i == nombresMonstruosNormales.size())
                i = 0;
            cartas.push(fabricaCartasMonstruo.obtenerCarta(nombresMonstruosNormales.get(i)));
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
