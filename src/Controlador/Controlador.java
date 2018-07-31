package Controlador;

import Controlador.estadosJuego.*;
import Controlador.excepciones.*;
import Controlador.observadores.ObservadorDeControlador;
import Modelo.Jugador;
import Modelo.Modelo;
import Modelo.carta.Carta;
import Modelo.carta.campo.CartaCampo;
import Modelo.carta.magica.CartaMagica;
import Modelo.carta.monstruo.CartaMonstruo;
import Modelo.carta.trampa.CartaTrampa;
import Modelo.finDeJuego.CausaFinJuego;
import Modelo.finDeJuego.CausaFinJuegoNula;
import Modelo.observadores.ObservadorDeCartaTrampa;
import Modelo.observadores.ObservadorDeFinJuego;
import Vista.Vista;

import java.util.ArrayList;

public final class Controlador implements ControladorObservable, ObservadorDeFinJuego, ControladorInterfaz,
        ObservadorDeCartaTrampa
{
    private static int cantidadCartasTomarInicialmente = 5;
    private static Controlador instancia = null;
    private Modelo modelo;
    private Vista vista;
    private CausaFinJuego causaFinDeJuego = CausaFinJuegoNula.getInstancia();
    private VerificadorCondicionesJuego verificadorCondicionesJuego;
    private MaquinaTurnos maquinaTurnos;
    private EstadoVerificador estadoVerificador;
    private ArrayList<CartaTrampa> cartasTrampaUtilizadas = new ArrayList<>();
    private ArrayList<ObservadorDeControlador> observadoresDeControlador = new ArrayList<>();

    // --------------------------------------------------------------------
    // Métodos de construcción e inicialización.
    // --------------------------------------------------------------------
    private Controlador(Modelo modelo)
    {
        this.modelo = modelo;
        this.modelo.agregarObsevadorFinDeJuego(this);
        this.modelo.agregarObservadorCartasTrampa(this);

        this.maquinaTurnos = MaquinaTurnos.getInstancia(modelo.getJugador(), modelo.getOponente());
        this.verificadorCondicionesJuego = VerificadorCondicionesJuego.getInstancia(this.maquinaTurnos, modelo);
    }

    public static Controlador getInstancia(Modelo modelo)
    {
        if (instancia == null)
        {
            instancia = new Controlador(modelo);
        }
        return instancia;
    }

    @Override
    public Controlador clone() throws CloneNotSupportedException
    {
        throw new CloneNotSupportedException();
    }

    public void setVista(Vista vista)
    {
        this.vista = vista;
    }

    // ------------------------------------
    // Ejecucion.
    // ------------------------------------
    public void iniciar()
    {
        // Vista va a mostrar la pantalla de bienvenida.
        this.vista.mostrar();
    }

    public void jugar()
    {
        // La vista pasa a la escena con el tablero principal.
        this.vista.mostrar();

        for (int i = 0; i < cantidadCartasTomarInicialmente; i++)
        {
            this.modelo.tomarCarta(getJugadorActual());
            this.modelo.tomarCarta(getJugadorActual().getOponente());
        }

        this.accionarFaseInicialAutomatica();
    }

    // --------------------------------------------------------------------
    // Interfaz con el modelo.
    // --------------------------------------------------------------------
    public void setNombreJugador(String text)
    {
        this.modelo.setNombreJugador(text);
    }

    public void setNombreOponente(String text)
    {
        this.modelo.setNombreOponente(text);
    }

    // --------------------------------------------------------------------
    // Métodos de observador de fin de juego.
    // --------------------------------------------------------------------
    @Override
    public void seLlegoAFinDeJuego(CausaFinJuego causaFinJuego)
    {
        this.causaFinDeJuego = causaFinJuego;
        this.vista.finDeJuego();
    }

    public CausaFinJuego getCausaFinDeJuego()
    {
        return this.causaFinDeJuego;
    }

    // --------------------------------------------------------------------
    // Métodos de observador de cartas trampa.
    // --------------------------------------------------------------------
    @Override
    public void seUsoCartaTrampa(CartaTrampa cartaTrampa)
    {
        this.cartasTrampaUtilizadas.add(cartaTrampa);
    }

    // ------------------------------------
    // Métodos de terminación.
    // ------------------------------------
    public void confirmarSalirPrograma()
    {
        this.vista.confirmarSalirPrograma();
    }

    public void cerrarPrograma()
    {
        this.vista.cerrar();
    }

    // --------------------------------------------------------------------
    // Metodos por ser un observable de Controlador.
    // --------------------------------------------------------------------
    @Override
    public void registrarObsevador(ObservadorDeControlador observador)
    {
        this.observadoresDeControlador.add(observador);
    }

    @Override
    public void eliminarObservador(ObservadorDeControlador observador)
    {
        this.observadoresDeControlador.remove(observador);
    }

    @Override
    public void notificarFinDeTurno()
    {
        observadoresDeControlador.forEach(observador -> observador.seTerminoElTurno());
    }

    @Override
    public void notificarFinDeFase()
    {
        observadoresDeControlador.forEach(observador -> observador.seTerminoLaFase());
    }

    // ------------------------------------
    // Métodos de fases y turnos.
    // ------------------------------------
    @Override
    public void terminarTurno()
    {
        this.maquinaTurnos.terminarTurno();
        this.cartasTrampaUtilizadas.forEach(cartaTrampa -> cartaTrampa.deshacerEfecto());
        this.cartasTrampaUtilizadas.clear();
        this.notificarFinDeTurno();

        this.accionarFaseInicialAutomatica();
    }

    @Override
    public void avanzarFase() throws SeTerminaronLasFases
    {
        if (this.maquinaTurnos.getFaseActual().esFaseFinal())
        {
            throw new SeTerminaronLasFases();
        } else
        {
            this.maquinaTurnos.avanzarFase();
        }

        this.notificarFinDeFase();
    }

    private void cambiarAFase(Fase fase)
    {
        this.maquinaTurnos.setFaseActual(fase);
        this.notificarFinDeFase();
    }

    private void retrocederFase() throws SeTerminaronLasFases
    {
        if (this.maquinaTurnos.getFaseActual().esFaseInicial())
        {
            throw new SeTerminaronLasFases();
        } else
        {
            this.maquinaTurnos.retrocederFase();
        }

        this.notificarFinDeFase();
    }

    private void accionarFaseInicialAutomatica()
    {
        tomarCarta(getJugadorActual());

        try
        {
            this.avanzarFase();
        } catch (SeTerminaronLasFases seTerminaronLasFases)
        {
            this.vista.getControlador().terminarTurno();
        }
    }

    @Override
    public String getNombreJugadorActual()
    {
        return getJugadorActual().getNombre();
    }

    @Override
    public String getNombreFaseActual()
    {
        return this.maquinaTurnos.getFaseActual().getNombre();
    }

    @Override
    public Jugador getJugadorActual()
    {
        return this.maquinaTurnos.getJugadorActual();
    }

    // ------------------------------------
    // Métodos de juego de cartas.
    // ------------------------------------
    @Override
    public void tomarCarta(Jugador solicitante) throws NoSePuedeTomarCartaError
    {
        estadoVerificador = verificadorCondicionesJuego.sePuedeTomarCarta(solicitante);
        if (estadoVerificador.esFallido())
        {
            throw new NoSePuedeTomarCartaError(solicitante, estadoVerificador);
        } else
        {
            this.modelo.tomarCarta(getJugadorActual());
            this.maquinaTurnos.seTomaCartaEnTurnoActual();
        }
    }

    @Override
    public void setCartaMonstruo(Jugador solicitante, Carta carta) throws NoSePuedeEnviarCartaMonstruoARegionError
    {
        estadoVerificador = verificadorCondicionesJuego.sePuedeEnviarARegionMonstruo(solicitante, carta);
        if (estadoVerificador.esFallido())
        {
            throw new NoSePuedeEnviarCartaMonstruoARegionError(solicitante, estadoVerificador);
        } else
        {
            this.modelo.setCartaMonstruo(solicitante, (CartaMonstruo) carta);
            this.maquinaTurnos.seColocaCartaMonstruoEnRegionEnTurnoActual();
            this.maquinaTurnos.seColocaCartaEnRegionEnTurnoActual(carta);
        }
    }

    @Override
    public void summonCartaMonstruo(Jugador solicitante, Carta carta) throws NoSePuedeEnviarCartaMonstruoARegionError
    {
        estadoVerificador = verificadorCondicionesJuego.sePuedeEnviarARegionMonstruo(solicitante, carta);
        if (estadoVerificador.esFallido())
        {
            throw new NoSePuedeEnviarCartaMonstruoARegionError(solicitante, estadoVerificador);
        } else
        {
            this.modelo.summonCartaMonstruo(solicitante, (CartaMonstruo) carta);
            this.maquinaTurnos.seColocaCartaMonstruoEnRegionEnTurnoActual();
            this.maquinaTurnos.seColocaCartaEnRegionEnTurnoActual(carta);
        }
    }

    // -------------
    // Set.
    // -------------
    // Se usa desde la mano: Envía la carta trampa a la región mágicas y trampa con orientación boca abajo.
    @Override
    public void setCartaTrampa(Jugador solicitante, Carta carta) throws NoSePuedeEnviarMyTARegionError
    {
        estadoVerificador = verificadorCondicionesJuego.sePuedeEnviarARegionMyT(solicitante, carta);
        if (estadoVerificador.esFallido())
        {
            throw new NoSePuedeEnviarMyTARegionError(solicitante, estadoVerificador);
        } else
        {
            this.modelo.setCartaTrampa(solicitante, (CartaTrampa) carta);
        }
    }

    // Se usa desde la mano: Envía la carta mágica a la región mágicas y trampa con orientación boca abajo.
    @Override
    public void setCartaMagica(Jugador solicitante, Carta carta) throws NoSePuedeEnviarMyTARegionError
    {
        estadoVerificador = verificadorCondicionesJuego.sePuedeEnviarARegionMyT(solicitante, carta);
        if (estadoVerificador.esFallido())
        {
            throw new NoSePuedeEnviarMyTARegionError(solicitante, estadoVerificador);
        } else
        {
            this.modelo.setCartaMagica(solicitante, (CartaMagica) carta);
        }
    }

    // -------------
    // Activar.
    // -------------
    // Se usa desde la región MyT: activa la carta trampa, poniéndola boca arriba.
    @Override
    public void activarCartaTrampa(Jugador solicitante, Carta carta) throws NoSePuedeUsarMyTError
    {
        estadoVerificador = verificadorCondicionesJuego.sePuedeUsarTrampa(solicitante, carta);
        if (estadoVerificador.esFallido())
        {
            throw new NoSePuedeUsarMyTError(solicitante, estadoVerificador);
        } else
        {
            this.modelo.activarCartaTrampa(solicitante, (CartaTrampa) carta);
        }
    }

    // Se usa desde la mano: activa la carta mágica, poniéndola boca arriba.
    @Override
    public void activarCartaMagicaDesdeMano(Jugador solicitante, Carta carta) throws NoSePuedeUsarMyTError
    {
        estadoVerificador = verificadorCondicionesJuego.sePuedeActivarMagicaDesdeMano(solicitante, carta);
        if (estadoVerificador.esFallido())
        {
            throw new NoSePuedeUsarMyTError(solicitante, estadoVerificador);
        } else
        {
            this.modelo.activarCartaMagicaDesdeMano(solicitante, (CartaMagica) carta);
        }
    }

    // Se usa desde la región MyT: activa la carta mágica, poniéndola boca arriba.
    @Override
    public void activarCartaMagicaDesdeRegionMyT(Jugador solicitante, Carta carta) throws NoSePuedeUsarMyTError
    {
        estadoVerificador = verificadorCondicionesJuego.sePuedeActivarMagicaDesdeRegionMyT(solicitante, carta);
        if (estadoVerificador.esFallido())
        {
            throw new NoSePuedeUsarMyTError(solicitante, estadoVerificador);
        } else
        {
            this.modelo.activarCartaMagicaDesdeRegionMyT(solicitante, (CartaMagica) carta);
        }
    }

    @Override
    public void activarCartaCampoDesdeMano(Jugador solicitante, Carta carta) throws NoSePuedeEnviarARegionCampoError
    {
        estadoVerificador = verificadorCondicionesJuego.sePuedeActivarCampoDesdeMano(solicitante, carta);
        if (estadoVerificador.esFallido())
        {
            throw new NoSePuedeEnviarARegionCampoError(solicitante, estadoVerificador);
        } else
        {
            this.modelo.activarCartaCampo(solicitante, (CartaCampo) carta);
        }
    }

    // ------------------------------------
    // Métodos de orientación de cartas.
    // ------------------------------------
    @Override
    public void flipCarta(Jugador solicitante, Carta carta) throws NoSePuedeCambiarOrientacionError
    {
        estadoVerificador = verificadorCondicionesJuego.sePuedeCambiarOrientacionCarta(solicitante, carta);
        if (estadoVerificador.esFallido())
        {
            throw new NoSePuedeCambiarOrientacionError(solicitante, estadoVerificador);
        } else
        {
            this.modelo.flipCarta(carta);
            this.maquinaTurnos.seCambiaOrientacionCartaEnTurnoActual(carta);
        }
    }

    @Override
    public void cambiarModoCartaMonstruo(Jugador solicitante, Carta carta) throws NoSePuedeCambiarOrientacionError
    {
        estadoVerificador = verificadorCondicionesJuego.sePuedeCambiarModoCarta(solicitante, carta);
        if (estadoVerificador.esFallido())
        {
            throw new NoSePuedeCambiarOrientacionError(solicitante, estadoVerificador);
        } else
        {
            this.modelo.cambiarModoCartaMonstruo((CartaMonstruo) carta);
            this.maquinaTurnos.seCambiaOrientacionCartaEnTurnoActual(carta);
        }
    }

    // ------------------------------------
    // Métodos de ataques.
    // ------------------------------------
    @Override
    public void atacar(Jugador solicitante, Carta cartaAtacante) throws NoSePuedeAtacarError
    {
        estadoVerificador = verificadorCondicionesJuego.sePuedeAtacar(cartaAtacante, solicitante);
        if (estadoVerificador.esFallido())
        {
            throw new NoSePuedeAtacarError(solicitante, estadoVerificador);
        } else if (((CartaMonstruo) cartaAtacante).tieneQuickEffect())
        {
            ((CartaMonstruo) cartaAtacante).efecto();
            // En realidad no se sabe si atacó, solamente que se uso, pero a los efectos es lo mismo.
            this.maquinaTurnos.cartaAtacaEnTurnoActual((CartaMonstruo) cartaAtacante);
        } else
        {
            if (((CartaMonstruo) cartaAtacante).getOponente().getRegionMonstruos().estaVacia())
            {
                cambiarAFase(FaseTrampa.getInstancia(this.maquinaTurnos));

                this.modelo.atacar((CartaMonstruo) cartaAtacante);
                this.maquinaTurnos.cartaAtacaEnTurnoActual((CartaMonstruo) cartaAtacante);
                retrocederFase();
            } else
            {
                this.vista.solicitarCartaAAtacar((CartaMonstruo) cartaAtacante);
            }
        }
    }

    // Se supone que previamente a este método se utilizó el método atacar(), con lo cual ya está realizada la
    // verificación si el jugador puede atacar.
    @Override
    public void atacarCarta(Jugador solicitante, CartaMonstruo cartaAtacante, CartaMonstruo cartaSolicitada)
    {
        cambiarAFase(FaseTrampa.getInstancia(this.maquinaTurnos));

        this.modelo.atacar((CartaMonstruo) cartaAtacante, cartaSolicitada);
        this.maquinaTurnos.cartaAtacaEnTurnoActual((CartaMonstruo) cartaAtacante);
        retrocederFase();
    }
}
