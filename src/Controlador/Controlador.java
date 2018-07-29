package Controlador;

import Controlador.estadosJuego.*;
import Controlador.excepciones.*;
import Modelo.Jugador;
import Modelo.Modelo;
import Modelo.carta.Carta;
import Modelo.carta.Sacrificio;
import Modelo.carta.campo.CartaCampo;
import Modelo.carta.excepciones.ManoLlena;
import Modelo.carta.magica.CartaMagica;
import Modelo.carta.monstruo.CartaMonstruo;
import Modelo.carta.trampa.CartaTrampa;
import Modelo.finDeJuego.CausaFinJuego;
import Modelo.finDeJuego.CausaFinJuegoNula;
import Modelo.observadores.ObservadorDeCartaTrampa;
import Modelo.observadores.ObservadorDeFinJuego;
import Vista.Vista;

import java.util.ArrayList;

public final class Controlador implements ObservadorDeFinJuego, ControladorInterfaz, ObservadorDeCartaTrampa
{
    private static final int cantidadCartasTomarInicialmente = 5;
    private static Controlador instancia = null;
    private Modelo modelo;
    private Vista vista;
    private CausaFinJuego causaFinDeJuego = CausaFinJuegoNula.getInstancia();
    private VerificadorCondicionesJuego verificadorCondicionesJuego;
    private MaquinaTurnos maquinaTurnos;
    private EstadoVerificador estadoVerificador;
    private ArrayList<CartaTrampa> cartasTrampaUtilizadas = new ArrayList<>();

    // --------------------------------------------------------------------
    // Métodos de construcción e inicialización.
    // --------------------------------------------------------------------
    private Controlador(Modelo modelo)
    {
        this.modelo = modelo;
        this.modelo.agregarObsevadorFinDeJuego(this);
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
        // TODO: ver si esto funciona bien.

        for (int i = 0; i < cantidadCartasTomarInicialmente; i++)
        {
            this.modelo.tomarCarta(this.modelo.getJugador());
            this.modelo.tomarCarta(this.modelo.getOponente());
        }

        try
        {
            tomarCarta(getJugadorActual());
        } catch (ManoLlena manoLlena)
        {
            this.vista.avisoManoLlena(manoLlena);
        }
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
        this.modelo.terminar();
    }

    public CausaFinJuego getCausaFinDeJuego()
    {
        return this.causaFinDeJuego;
    }

    // --------------------------------------------------------------------
    // Métodos de observador de cartas.
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

    // ------------------------------------
    // Métodos de fases y turnos.
    // ------------------------------------
    @Override
    public void terminarTurno()
    {
        this.maquinaTurnos.terminarTurno();
        this.cartasTrampaUtilizadas.forEach(cartaTrampa -> cartaTrampa.deshacerEfecto());
        this.vista.mostrarJugadorActual();
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
            avanzarFase();
            this.vista.mostrarFaseActual();
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
            if (!this.modelo.requiereSacrificios((CartaMonstruo) carta))
            {

                this.modelo.setCartaMonstruo(solicitante, (CartaMonstruo) carta);
                this.maquinaTurnos.seColocaCartaMonstruoEnRegionEnTurnoActual((CartaMonstruo) carta);
            } else
            {
                Sacrificio sacrificios = this.vista.pedirSacrificios();
                this.modelo.setCartaMonstruo(solicitante, (CartaMonstruo) carta, sacrificios);
                this.maquinaTurnos.seColocaCartaMonstruoEnRegionEnTurnoActual((CartaMonstruo) carta);
            }
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
            if (!this.modelo.requiereSacrificios((CartaMonstruo) carta))
            {
                this.modelo.summonCartaMonstruo(solicitante, (CartaMonstruo) carta);
                this.maquinaTurnos.seColocaCartaMonstruoEnRegionEnTurnoActual((CartaMonstruo) carta);
            } else
            {
                Sacrificio sacrificios = this.vista.pedirSacrificios();
                this.modelo.summonCartaMonstruo(solicitante, (CartaMonstruo) carta, sacrificios);
                this.maquinaTurnos.seColocaCartaMonstruoEnRegionEnTurnoActual((CartaMonstruo) carta);
            }
        }
    }

    // Set.
    @Override
    public void setCartaTrampa(Jugador solicitante, Carta carta) throws NoSePuedeEnviarMyTARegionError
    {
        estadoVerificador = verificadorCondicionesJuego.sePuedeEnviarARegionMyT(solicitante, carta);
        if (estadoVerificador.esFallido())
        {
            throw new NoSePuedeUsarMyTError(solicitante, estadoVerificador);
        } else
        {
            this.modelo.setCartaTrampa(solicitante, (CartaTrampa) carta);
        }
    }

    @Override
    public void setCartaMagica(Jugador solicitante, Carta carta) throws NoSePuedeEnviarMyTARegionError
    {
        estadoVerificador = verificadorCondicionesJuego.sePuedeEnviarARegionMyT(solicitante, carta);
        if (estadoVerificador.esFallido())
        {
            throw new NoSePuedeUsarMyTError(solicitante, estadoVerificador);
        } else
        {
            this.modelo.setCartaMagica(solicitante, (CartaMagica) carta);
        }
    }

    // Activar.
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

    @Override
    public void activarCartaMagica(Jugador solicitante, Carta carta) throws NoSePuedeUsarMyTError
    {
        estadoVerificador = verificadorCondicionesJuego.sePuedeUsarMagica(solicitante, carta);
        if (estadoVerificador.esFallido())
        {
            throw new NoSePuedeUsarMyTError(solicitante, estadoVerificador);
        } else
        {
            this.modelo.activarCartaMagica((CartaMagica) carta);
        }
    }

    @Override
    public void activarCartaCampo(Jugador solicitante, Carta carta) throws NoSePuedeEnviarARegionCampoError
    {
        estadoVerificador = verificadorCondicionesJuego.sePuedeUsarCampo(solicitante, carta);
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
    public void flipBocaAbajo(Jugador solicitante, Carta carta) throws NoSePuedeCambiarOrientacionError
    {
        estadoVerificador = verificadorCondicionesJuego.sePuedeCambiarOrientacionCarta(solicitante, carta);
        if (estadoVerificador.esFallido())
        {
            throw new NoSePuedeCambiarOrientacionError(solicitante, estadoVerificador);
        } else
        {
            this.modelo.flipBocaAbajo(carta);
            this.maquinaTurnos.seCambiaOrientacionCartaEnTurnoActual(carta);
        }
    }

    @Override
    public void flipBocaArriba(Jugador solicitante, Carta carta) throws NoSePuedeCambiarOrientacionError
    {
        estadoVerificador = verificadorCondicionesJuego.sePuedeCambiarOrientacionCarta(solicitante, carta);
        if (estadoVerificador.esFallido())
        {
            throw new NoSePuedeCambiarOrientacionError(solicitante, estadoVerificador);
        } else
        {
            this.modelo.flipBocaArriba(carta);
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

    @Override
    public void setModoAtaque(Jugador solicitante, Carta carta) throws NoSePuedeCambiarOrientacionError
    {
        estadoVerificador = verificadorCondicionesJuego.sePuedeCambiarModoCarta(solicitante, carta);
        if (estadoVerificador.esFallido())
        {
            throw new NoSePuedeCambiarOrientacionError(solicitante, estadoVerificador);
        } else
        {
            this.modelo.setModoAtaque((CartaMonstruo) carta);
            this.maquinaTurnos.seCambiaOrientacionCartaEnTurnoActual(carta);
        }
    }

    @Override
    public void setModoDefensa(Jugador solicitante, Carta carta) throws NoSePuedeCambiarOrientacionError
    {
        estadoVerificador = verificadorCondicionesJuego.sePuedeCambiarModoCarta(solicitante, carta);
        if (estadoVerificador.esFallido())
        {
            throw new NoSePuedeCambiarOrientacionError(solicitante, estadoVerificador);
        } else
        {
            this.modelo.setModoDefensa((CartaMonstruo) carta);
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
        } else
        {
            if (((CartaMonstruo) cartaAtacante).getOponente().getRegionMonstruos().estaVacia())
            {
                avanzarAFase(FaseTrampa.getInstancia(this.maquinaTurnos));

                this.modelo.atacar((CartaMonstruo) cartaAtacante);
            } else
            {
                CartaMonstruo cartaAAtacar = this.vista.solicitarCartaAAtacar();

                avanzarAFase(FaseTrampa.getInstancia(this.maquinaTurnos));

                this.modelo.atacar((CartaMonstruo) cartaAtacante, cartaAAtacar);
            }
            this.maquinaTurnos.cartaAtacaEnTurnoActual((CartaMonstruo) cartaAtacante);
            retrocederFase();
        }
    }

    private void avanzarAFase(Fase fase)
    {
        this.maquinaTurnos.setFaseActual(fase);
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
    }

}
