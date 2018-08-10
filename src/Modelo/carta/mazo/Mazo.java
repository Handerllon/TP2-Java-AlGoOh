package Modelo.carta.mazo;

import Modelo.Jugador;
import Modelo.carta.Carta;
import Modelo.carta.CartaNula;
import Modelo.carta.FabricaCartas;
import Modelo.carta.monstruo.CartaMonstruo;
import Modelo.carta.trampa.CartaTrampa;
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
    private ArrayList<CartaMonstruo> cartasMonstruo = new ArrayList<>();
    private ArrayList<CartaTrampa> cartasTrampa = new ArrayList<>();
    private ArrayList<ObservadorDeFinJuego> observadoresFinJuegos = new ArrayList<>();
    private ArrayList<ObservadorDeMazo> observadoresMazo = new ArrayList<>();

    public Mazo(Jugador jugador, Jugador oponente)
    {
        this.jugador = jugador;
        this.oponente = oponente;

        int cantidadCartasRestantes = CANTIDAD_CARTAS_INICIALES;

        FabricaCartas fabricaCartas = new FabricaCartas(jugador, oponente);

        CartaMonstruo cartaMonstruoAAgregar;
        CartaTrampa cartaTrampaAAgregar;

        ArrayList<String> nombresMonstruosNoNormales = fabricaCartas.getNombresCartasMonstruosNoNormales();
        for (int i = 0; i < nombresMonstruosNoNormales.size() && cantidadCartasRestantes > 0; i++)
        {
            cartaMonstruoAAgregar = fabricaCartas.crearCartaMonstruo(nombresMonstruosNoNormales.get(i));
            cartas.push(cartaMonstruoAAgregar);
            cartasMonstruo.add(cartaMonstruoAAgregar);
            cantidadCartasRestantes--;
        }

        ArrayList<String> nombresMagicas = fabricaCartas.getNombresCartasMagicas();
        for (int i = 0; i < nombresMagicas.size() && cantidadCartasRestantes > 0; i++)
        {
            cartas.push(fabricaCartas.crearCartaMagica(nombresMagicas.get(i)));
            cantidadCartasRestantes--;
        }

        ArrayList<String> nombresCampo = fabricaCartas.getNombresCartasCampo();
        for (int i = 0; i < nombresCampo.size() && cantidadCartasRestantes > 0; i++)
        {
            cartas.push(fabricaCartas.crearCartaCampo(nombresCampo.get(i)));
            cantidadCartasRestantes--;
        }

        ArrayList<String> nombresTrampas = fabricaCartas.getNombresCartasTrampa();
        for (int i = 0; i < nombresTrampas.size() && cantidadCartasRestantes > 0; i++)
        {
            cartaTrampaAAgregar = fabricaCartas.crearCartaTrampa(nombresTrampas.get(i));
            cartas.push(cartaTrampaAAgregar);
            cartasTrampa.add(cartaTrampaAAgregar);
            cantidadCartasRestantes--;
        }

        // Agrego todas las que se pueden repetir.
        ArrayList<String> nombresMonstruosNormales = fabricaCartas.getNombresCartasMonstruosNormales();
        for (int i = 0; cantidadCartasRestantes > 0; i++)
        {
            // Se reinicia la iteración.
            if (i == nombresMonstruosNormales.size())
            {
                i = 0;
            }

            cartaMonstruoAAgregar = fabricaCartas.crearCartaMonstruo(nombresMonstruosNormales.get(i));
            cartas.push(cartaMonstruoAAgregar);
            cartasMonstruo.add(cartaMonstruoAAgregar);

            cantidadCartasRestantes--;
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
        Carta carta;
        if (!this.cartas.isEmpty())
        {
            carta = this.cartas.pop();
            // Si la carta no existe en la lista, no sucede nada según la documentación de ArrayList.
            this.cartasMonstruo.remove(carta);
            this.cartasTrampa.remove(carta);
            this.notificarTomaDeCartaDeMazo();
        } else
        {
            carta = CartaNula.getInstancia();
            this.notificarFinDeJuego(CausaSinCartasEnMazo.getInstancia(this.jugador));
        }

        return carta;
    }

    public Stack<Carta> getCartas()
    {
        return this.cartas;
    }

    public ArrayList<CartaMonstruo> getCartasMonstruo()
    {
        return this.cartasMonstruo;
    }

    public ArrayList<CartaTrampa> getCartasTrampa()
    {
        return this.cartasTrampa;
    }

    public int cantidadCartas()
    {
        return cartas.size();
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
    public void notificarTomaDeCartaDeMazo()
    {
        observadoresMazo.forEach(observador -> observador.seTomoCartaDeMazo());
    }
}
