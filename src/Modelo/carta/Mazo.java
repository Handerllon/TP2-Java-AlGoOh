package Modelo.carta;

import Modelo.Jugador;
import Modelo.finDeJuego.CausaFinJuego;
import Modelo.finDeJuego.CausaSinCartasEnMazo;
import Modelo.finDeJuego.FinJuegoObservable;
import Modelo.finDeJuego.ObservadorFinJuego;

import java.util.ArrayList;
import java.util.Stack;

public class Mazo implements FinJuegoObservable
{
    private static int CANTIDAD_CARTAS_INICIALES = 40;
    private Jugador jugador, oponente;
    private Stack<Carta> cartas = new Stack<>();
    private ArrayList<ObservadorFinJuego> observadoresFinJuegos = new ArrayList<>();

    public Mazo(Jugador jugador, Jugador oponente)
    {
        this.jugador = jugador;
        this.oponente = oponente;

        int cantidadCartas = CANTIDAD_CARTAS_INICIALES;

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
        if (this.cartas.isEmpty() == false)
        {
            return this.cartas.pop();
        } else
        {
            this.notificarFinDeJuego(new CausaSinCartasEnMazo(this.jugador));
            return new CartaNula();
        }
    }

    // --------------------------------------------------------------------
    // Metodos de observadores de fin de juego.
    // --------------------------------------------------------------------
    @Override
    public void agregarObsevadorFinDeJuego(ObservadorFinJuego observador)
    {
        this.observadoresFinJuegos.add(observador);
    }

    @Override
    public void quitarObservadorFinDeJuego(ObservadorFinJuego observador)
    {
        if (this.observadoresFinJuegos.isEmpty() == false)
        {
            this.observadoresFinJuegos.remove(observador);
        }
    }

    @Override
    public void notificarFinDeJuego(CausaFinJuego causaFinJuego)
    {
        this.observadoresFinJuegos.forEach(item -> item.finDeJuego(causaFinJuego));
    }
}
