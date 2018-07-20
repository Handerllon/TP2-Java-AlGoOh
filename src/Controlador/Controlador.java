package Controlador;

import Controlador.estadosJuego.MaquinaTurnos;
import Controlador.excepciones.NoEsUnaCartaParaAtacar;
import Modelo.Modelo;
import Modelo.carta.Carta;
import Modelo.carta.CartaNula;
import Modelo.carta.monstruo.CartaMonstruo;
import Modelo.finDeJuego.CausaFinJuego;
import Modelo.finDeJuego.CausaFinJuegoNula;
import Modelo.finDeJuego.ObservadorDeFinJuego;
import Vista.Vista;

public final class Controlador implements ObservadorDeFinJuego
{
    private static Modelo modelo;
    private static Vista vista;
    private static Controlador instancia = null;
    private static CausaFinJuego causaFinDeJuego = CausaFinJuegoNula.obtenerInstancia();
    private MaquinaTurnos maquinaTurnos;

    // --------------------------------------------------------------------
    // Métodos de construcción e inicialización.
    // --------------------------------------------------------------------
    private Controlador(Modelo modelo)
    {
        this.modelo = modelo;
        this.modelo.agregarObsevadorFinDeJuego(this);
    }

    public static Controlador obtenerInstancia(Modelo modelo)
    {
        if (instancia == null)
        {
            instancia = new Controlador(modelo);
        }
        return instancia;
    }

    public Controlador clone() throws CloneNotSupportedException
    {
        throw new CloneNotSupportedException();
    }

    public void establecerVista(Vista vista)
    {
        this.vista = vista;
    }

    // --------------------------------------------------------------------
    // Ejecucion.
    // --------------------------------------------------------------------
    public void iniciar()
    {
        this.maquinaTurnos = MaquinaTurnos.obtenerInstancia(this.modelo.obtenerJugador(), this.modelo.obtenerOponente(), this);
        // Vista va a mostrar la pantalla de bienvenida.
        this.vista.mostrar();
    }

    public void jugar()
    {
        // La vista pasa a la escena con el tablero principal.
        this.vista.mostrar();
    }

    // --------------------------------------------------------------------
    // Interfaz con el modelo.
    // --------------------------------------------------------------------
    public void establecerNombreJugador(String text)
    {
        this.modelo.establecerNombreJugador(text);
    }

    public void establecerNombreOponente(String text)
    {
        this.modelo.establecerNombreOponente(text);
    }

    // --------------------------------------------------------------------
    // Métodos de observador de modelo.
    // --------------------------------------------------------------------
    @Override
    public void seLlegoAFinDeJuego(CausaFinJuego causaFinJuego)
    {
        this.causaFinDeJuego = causaFinJuego;
        this.vista.finDeJuego();
        this.modelo.terminar();
    }

    public CausaFinJuego obtenerCausaFinDeJuego()
    {
        return this.causaFinDeJuego;
    }

    // --------------------------------------------------------------------
    // Métodos de terminación.
    // --------------------------------------------------------------------
    public void confirmarSalirPrograma()
    {
        this.vista.confirmarSalirPrograma();
    }

    public void cerrarPrograma()
    {
        this.vista.cerrar();
    }

    // --------------------------------------------------------------------
    // Métodos de fases y turnos.
    // --------------------------------------------------------------------
    public void terminarTurno()
    {

        this.maquinaTurnos.terminarTurno();
    }

    public void avanzarProximaFase()
    {
        this.maquinaTurnos.avanzarProximaFase();
    }

    public String obtenerNombreJugadorActual()
    {
        return this.maquinaTurnos.obtenerJugadorActual().obtenerNombre();
    }

    public String nombreFaseActual()
    {
        return this.maquinaTurnos.nombreFaseActual();
    }

    // ------------------------------------
    // Métodos de juego de cartas.
    // ------------------------------------
    // TODO: verificar la condición y fase en las cuales se pueden realizar estas operaciones.
    // TODO: verificar que el dueño de la carta y el jugador actual coincidan.
    public void tomarCarta()
    {
        this.modelo.tomarCarta(this.maquinaTurnos.obtenerJugadorActual());
    }

    public void activarCartaMagica(Carta carta)
    {
        this.modelo.activarCartaMagica(this.maquinaTurnos.obtenerJugadorActual(),carta);
    }

    public void jugarCartaTrampa(Carta carta)
    {
        this.modelo.jugarCartaTrampa(this.maquinaTurnos.obtenerJugadorActual(),carta);

    }

    public void jugarCartaMagicaBocaArriba(Carta carta)
    {
        this.modelo.jugarCartaMagicaBocaArriba(this.maquinaTurnos.obtenerJugadorActual(),carta);
    }

    public void jugarCartaMagicaBocaAbajo(Carta carta)
    {
        this.modelo.jugarCartaMagicaBocaAbajo(this.maquinaTurnos.obtenerJugadorActual(),carta);
    }

    // ------------------------------------
    // Métodos de orientación de cartas.
    // ------------------------------------

    // TODO: verificar la condición y fase en las cuales se pueden realizar estas operaciones. Por ejemplo,
    // no se puede cambiar la posición del monstruo ni voltearla el mismo turno que es colocada en el campo.
    public void voltearBocaAbajo(Carta carta)
    {
        this.modelo.voltearBocaAbajo(carta);
    }

    public void voltearBocaArriba(Carta carta)
    {
        this.modelo.voltearBocaArriba(carta);
    }

    public void ponerEnModoAtaque(Carta carta)
    {
        this.modelo.ponerEnModoAtaque(carta);
    }

    public void ponerEnModoDefensa(Carta carta)
    {
        this.modelo.ponerEnModoDefensa(carta);
    }

    public void voltearCartaMonstruo(CartaMonstruo carta)
    {
        this.modelo.voltearCartaMonstruo(carta);
    }

    public void cambiarModoCartaMonstruo(CartaMonstruo carta)
    {
        this.modelo.cambiarModoCartaMonstruo(carta);
    }

    // ------------------------------------
    // Métodos de ataques.
    // ------------------------------------
    // TODO: verificar la condición y fase en las cuales se pueden realizar estas operaciones.
    public void atacar(CartaMonstruo cartaAtacante, CartaNula cartaNula) throws NoEsUnaCartaParaAtacar
    {
        throw new NoEsUnaCartaParaAtacar();
    }

    public void atacar(CartaMonstruo cartaAtacante, CartaMonstruo cartaAtacada)
    {
        this.modelo.atacar(cartaAtacante, cartaAtacada);
    }

    public void atacar(CartaMonstruo cartaAtacante)
    {
        this.modelo.atacar(cartaAtacante);
    }
}
