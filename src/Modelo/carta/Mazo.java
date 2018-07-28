package Modelo.carta;

import Modelo.Jugador;
import Modelo.finDeJuego.CausaFinJuego;
import Modelo.finDeJuego.CausaSinCartasEnMazo;
import Modelo.finDeJuego.FinDeJuegoObservable;
import Modelo.observadores.ObservadorDeFinJuego;
import Modelo.observadores.ObservadorDeMazo;

import java.util.ArrayList;
import java.util.Stack;

public class Mazo implements FinDeJuegoObservable, MazoObservable
{
    private static int CANTIDAD_CARTAS_INICIALES = 40;
    private Jugador jugador, oponente;
    private Stack<Carta> cartas = new Stack<>();
    private ArrayList<ObservadorDeFinJuego> observadoresFinJuegos = new ArrayList<>();
    private ArrayList<ObservadorDeMazo> observadoresMazo = new ArrayList<>();

    public Mazo(Jugador jugador, Jugador oponente)
    {
        this.jugador = jugador;
        this.oponente = oponente;

        int cantidadCartas = CANTIDAD_CARTAS_INICIALES;

        FabricaCartas fabricaCartas = new FabricaCartas(jugador, oponente);

        ArrayList<String> nombresMonstruosNoNormales = fabricaCartas.getNombresCartasMonstruosNoNormales();
        for (int i = 0; i < nombresMonstruosNoNormales.size() && cantidadCartas > 0; i++)
        {
            cartas.push(fabricaCartas.crearCartaMonstruo(nombresMonstruosNoNormales.get(i)));
            cantidadCartas--;
        }

        ArrayList<String> nombresMagicas = fabricaCartas.getNombresCartasMagicas();
        for (int i = 0; i < nombresMagicas.size() && cantidadCartas > 0; i++)
        {
            cartas.push(fabricaCartas.crearCartaMagica(nombresMagicas.get(i)));
            cantidadCartas--;
        }

        ArrayList<String> nombresCampo = fabricaCartas.getNombresCartasCampo();
        for (int i = 0; i < nombresCampo.size() && cantidadCartas > 0; i++)
        {
            cartas.push(fabricaCartas.crearCartaCampo(nombresCampo.get(i)));
            cantidadCartas--;
        }

        ArrayList<String> nombresTrampas = fabricaCartas.getNombresCartasTrampa();
        for (int i = 0; i < nombresTrampas.size() && cantidadCartas > 0; i++)
        {
            cartas.push(fabricaCartas.crearCartaTrampa(nombresTrampas.get(i)));
            cantidadCartas--;
        }

        // Agrego todas las que se pueden repetir.
        ArrayList<String> nombresMonstruosNormales = fabricaCartas.getNombresCartasMonstruosNormales();
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
        if (!this.cartas.isEmpty())
        {
            this.notificarEvento();
            return this.cartas.pop();
        } else
        {
            this.notificarFinDeJuego(new CausaSinCartasEnMazo(this.jugador));
            return CartaNula.getInstancia();
        }
    }

    public Stack<Carta> getCartas()
    {
        return this.cartas;
    }

    // --------------------------------------------------------------------
    // Metodos por ser un observable de Fin De Juego.
    // --------------------------------------------------------------------
    @Override
    public void agregarObsevadorFinDeJuego(ObservadorDeFinJuego observador)
    {
        this.observadoresFinJuegos.add(observador);
    }

    @Override
    public void quitarObservadorFinDeJuego(ObservadorDeFinJuego observador)
    {
        this.observadoresFinJuegos.remove(observador);
    }

    @Override
    public void notificarFinDeJuego(CausaFinJuego causaFinJuego)
    {
        this.observadoresFinJuegos.forEach(item -> item.seLlegoAFinDeJuego(causaFinJuego));
    }

    public int cantidadCartas()
    {
        return cartas.size();
    }

    // --------------------------------------------------------------------
    // Metodos por ser observable de Mano.
    // --------------------------------------------------------------------
    @Override
    public void registrarObsevador(ObservadorDeMazo observador)
    {
        observadoresMazo.add(observador);
    }

    @Override
    public void eliminarObservador(ObservadorDeMazo observador)
    {
        observadoresMazo.remove(observador);
    }

    @Override
    public void notificarEvento()
    {
        observadoresMazo.forEach(observador -> observador.huboCambios());
    }
}
