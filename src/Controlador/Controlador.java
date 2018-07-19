package Controlador;

import Controlador.estadosJuego.MaquinaTurnos;
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
        this.vista.mostrar();
    }

    public void jugar()
    {
        this.vista.mostrar();
        this.maquinaTurnos.jugar();
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
        this.terminar();
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
    private void terminar()
    {
        this.maquinaTurnos.parar();
    }

    public void confirmarSalirPrograma()
    {
        this.vista.confirmarSalirPrograma();
    }

    public void cerrarPrograma()
    {
        this.vista.cerrar();
    }

    public void finDeTurno()
    {

        this.maquinaTurnos.finDeTurno();
    }

    public void finDeFase()
    {

    }

    public void tomarCarta()
    {
        // TODO Auto-generated method stub

    }

    public void usarCartaMagica(Carta carta)
    {
        // TODO Auto-generated method stub

    }

    public void jugarCartaTrampa(Carta carta)
    {
        // TODO Auto-generated method stub

    }

    public void jugarCartaMagicaBocaArriba(Carta carta)
    {
        // TODO Auto-generated method stub

    }

    public void jugarCartaMagicaBocaAbajo(Carta carta)
    {
        // TODO Auto-generated method stub

    }

    public void orientarCartaBocaAbajo(Carta carta)
    {
        // TODO Auto-generated method stub

    }

    public void orientarCartaBocaArriba(Carta carta)
    {
        // TODO Auto-generated method stub

    }

    public void ponerCartaEnModoAtaque(Carta carta)
    {
        // TODO Auto-generated method stub

    }

    public void ponerCartaEnModoDefensa(Carta carta)
    {
        // TODO Auto-generated method stub

    }

    public void darVueltaCartaMonstruo(CartaMonstruo carta)
    {
        // TODO Auto-generated method stub

    }

    public void cambiarOrientacionCartaMonstruo(CartaMonstruo carta)
    {
        // TODO Auto-generated method stub

    }

    public void atacar(CartaMonstruo cartaAtacante, CartaNula cartaNula)
    {
        // TODO Auto-generated method stub

    }

    public void atacar(CartaMonstruo cartaAtacante, CartaMonstruo cartaAtacada)
    {
        // TODO Auto-generated method stub

    }

    public void atacar(CartaMonstruo carta)
    {
        // TODO Auto-generated method stub

    }

    public String nombreJugadorInicialSorteado()
    {
        return this.maquinaTurnos.obtenerJugadorInicialSorteado().obtenerNombre();
    }
}
