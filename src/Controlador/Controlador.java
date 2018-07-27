package Controlador;

import Controlador.condicionesJuego.EstadoVerificador;
import Controlador.condicionesJuego.VerificadorCondicionesJuego;
import Controlador.estadosJuego.Fase;
import Controlador.estadosJuego.FaseTrampa;
import Controlador.estadosJuego.MaquinaTurnos;
import Controlador.excepciones.*;
import Modelo.Jugador;
import Modelo.Modelo;
import Modelo.carta.Carta;
import Modelo.carta.ObservadorDeCarta;
import Modelo.carta.Sacrificio;
import Modelo.carta.magica.CartaMagica;
import Modelo.carta.monstruo.CartaMonstruo;
import Modelo.carta.trampa.CartaTrampa;
import Modelo.finDeJuego.CausaFinJuego;
import Modelo.finDeJuego.CausaFinJuegoNula;
import Modelo.finDeJuego.ObservadorDeFinJuego;
import Vista.Vista;

import java.util.ArrayList;

public final class Controlador implements ObservadorDeFinJuego, IControlador, ObservadorDeCarta
{
    private static Modelo modelo;
    private static Vista vista;
    private static Controlador instancia = null;
    private static CausaFinJuego causaFinDeJuego = CausaFinJuegoNula.getInstancia();
    private static VerificadorCondicionesJuego verificadorCondicionesJuego;
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
        this.maquinaTurnos = MaquinaTurnos.getInstancia(this.modelo.getJugador(), this.modelo.getOponente());
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
    public void notificarUsoDeCarta(CartaTrampa cartaTrampa)
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
    }

    @Override
    public void avanzarFase() throws SeTerminaronLasFasesError
    {
        if (this.maquinaTurnos.getFaseActual().esFaseFinal())
        {
            throw new SeTerminaronLasFasesError();
        } else
        {
            this.maquinaTurnos.avanzarFase();
        }
    }

    @Override
    public String getNombreJugadorActual()
    {
        return this.maquinaTurnos.getJugadorActual().getNombre();
    }

    @Override
    public String getNombreFaseActual()
    {
        return this.maquinaTurnos.getFaseActual().getNombre();
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
            throw new NoSePuedeTomarCartaError(estadoVerificador);
        } else
        {
            this.modelo.tomarCarta(this.maquinaTurnos.getJugadorActual());
            this.maquinaTurnos.seTomoCartaEnTurno();
        }
    }

    @Override
    public void setCartaMonstruo(Carta carta, Jugador solicitante) throws NoSePuedeMandarCartaARegionError
    {
        estadoVerificador = verificadorCondicionesJuego.sePuedeMandarARegionMonstruo(carta, solicitante);
        if (estadoVerificador.esFallido())
        {
            throw new NoSePuedeMandarCartaARegionError(estadoVerificador);
        } else
        {
            if (!this.modelo.requiereSacrificios((CartaMonstruo) carta))
            {

                this.modelo.setCartaMonstruo((CartaMonstruo) carta);
                this.maquinaTurnos.seColocoCartaEnRegion(carta);
            } else
            {
                Sacrificio sacrificios = this.vista.pedirSacrificios();
                this.modelo.setCartaMonstruo((CartaMonstruo) carta, sacrificios);
                this.maquinaTurnos.seColocoCartaEnRegion(carta);
            }
        }
    }

    @Override
    public void summonCartaMonstruo(Carta carta, Jugador solicitante) throws NoSePuedeMandarCartaARegionError
    {
        estadoVerificador = verificadorCondicionesJuego.sePuedeMandarARegionMonstruo(carta, solicitante);
        if (estadoVerificador.esFallido())
        {
            throw new NoSePuedeMandarCartaARegionError(estadoVerificador);
        } else
        {
            if (!this.modelo.requiereSacrificios((CartaMonstruo) carta))
            {
                this.modelo.summonCartaMonstruo((CartaMonstruo) carta);
                this.maquinaTurnos.seColocoCartaEnRegion(carta);
            } else
            {
                Sacrificio sacrificios = this.vista.pedirSacrificios();
                this.modelo.summonCartaMonstruo((CartaMonstruo) carta, sacrificios);
                this.maquinaTurnos.seColocoCartaEnRegion(carta);
            }
        }
    }

    @Override
    public void setCartaTrampa(Carta carta, Jugador solicitante) throws NoSePuedeEnviarARegionMyT
    {
        estadoVerificador = verificadorCondicionesJuego.sePuedeEnviarARegionMyT(carta, solicitante);
        if (estadoVerificador.esFallido())
        {
            throw new NoSePuedeEnviarARegionMyT(estadoVerificador);
        } else
        {
            this.modelo.setCartaTrampa(solicitante, (CartaTrampa) carta);
        }
    }

    @Override
    public void setCartaMagica(Carta carta, Jugador solicitante) throws NoSePuedeEnviarARegionMyT
    {
        estadoVerificador = verificadorCondicionesJuego.sePuedeEnviarARegionMyT(carta, solicitante);
        if (estadoVerificador.esFallido())
        {
            throw new NoSePuedeEnviarARegionMyT(estadoVerificador);
        } else
        {
            this.modelo.setCartaMagica(solicitante, (CartaMagica) carta);
        }
    }

    @Override
    public void activarCartaMagica(Carta carta, Jugador solicitante) throws NoSePuedeEnviarARegionMyT
    {
        estadoVerificador = verificadorCondicionesJuego.sePuedeEnviarMagicaARegionMyT(carta, solicitante);
        if (estadoVerificador.esFallido())
        {
            throw new NoSePuedeEnviarARegionMyT(estadoVerificador);
        } else
        {
            this.modelo.activarCartaMagica((CartaMagica) carta);
        }
    }

    // ------------------------------------
    // Métodos de orientación de cartas.
    // ------------------------------------
    @Override
    public void flipCarta(Carta carta, Jugador solicitante) throws NoSePuedeCambiarOrientacionError
    {
        estadoVerificador = verificadorCondicionesJuego.sePuedeCambiarOrientacionCarta(carta, solicitante);
        if (estadoVerificador.esFallido())
        {
            throw new NoSePuedeCambiarOrientacionError(estadoVerificador);
        } else
        {
            this.modelo.flipCarta(carta);
            this.maquinaTurnos.seCambioOrientacionCarta(carta);
        }
    }

    @Override
    public void flipBocaAbajo(Carta carta, Jugador solicitante) throws NoSePuedeCambiarOrientacionError
    {
        estadoVerificador = verificadorCondicionesJuego.sePuedeCambiarOrientacionCarta(carta, solicitante);
        if (estadoVerificador.esFallido())
        {
            throw new NoSePuedeCambiarOrientacionError(estadoVerificador);
        } else
        {
            this.modelo.flipBocaAbajo(carta);
            this.maquinaTurnos.seCambioOrientacionCarta(carta);
        }
    }

    @Override
    public void flipBocaArriba(Carta carta, Jugador solicitante) throws NoSePuedeCambiarOrientacionError
    {
        estadoVerificador = verificadorCondicionesJuego.sePuedeCambiarOrientacionCarta(carta, solicitante);
        if (estadoVerificador.esFallido())
        {
            throw new NoSePuedeCambiarOrientacionError(estadoVerificador);
        } else
        {
            this.modelo.flipBocaArriba(carta);
            this.maquinaTurnos.seCambioOrientacionCarta(carta);
        }
    }

    @Override
    public void cambiarModoCartaMonstruo(Carta carta, Jugador solicitante) throws NoSePuedeCambiarOrientacionError
    {
        estadoVerificador = verificadorCondicionesJuego.sePuedeCambiarModoCarta(carta, solicitante);
        if (estadoVerificador.esFallido())
        {
            throw new NoSePuedeCambiarOrientacionError(estadoVerificador);
        } else
        {
            this.modelo.cambiarModoCartaMonstruo((CartaMonstruo) carta);
            this.maquinaTurnos.seCambioOrientacionCarta(carta);
        }
    }

    @Override
    public void setModoAtaque(Carta carta, Jugador solicitante) throws NoSePuedeCambiarOrientacionError
    {
        estadoVerificador = verificadorCondicionesJuego.sePuedeCambiarModoCarta(carta, solicitante);
        if (estadoVerificador.esFallido())
        {
            throw new NoSePuedeCambiarOrientacionError(estadoVerificador);
        } else
        {
            this.modelo.setModoAtaque((CartaMonstruo) carta);
            this.maquinaTurnos.seCambioOrientacionCarta(carta);
        }
    }

    @Override
    public void setModoDefensa(Carta carta, Jugador solicitante) throws NoSePuedeCambiarOrientacionError
    {
        estadoVerificador = verificadorCondicionesJuego.sePuedeCambiarModoCarta(carta, solicitante);
        if (estadoVerificador.esFallido())
        {
            throw new NoSePuedeCambiarOrientacionError(estadoVerificador);
        } else
        {
            this.modelo.setModoDefensa((CartaMonstruo) carta);
            this.maquinaTurnos.seCambioOrientacionCarta(carta);
        }
    }

    // ------------------------------------
    // Métodos de ataques.
    // ------------------------------------
    @Override
    public void atacar(Carta cartaAtacante, Jugador solicitante) throws NoSePuedeAtacarError
    {
        estadoVerificador = verificadorCondicionesJuego.sePuedeAtacar(cartaAtacante, solicitante);
        if (estadoVerificador.esFallido())
        {
            throw new NoSePuedeAtacarError(estadoVerificador);
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
            this.maquinaTurnos.cartaAtaco((CartaMonstruo) cartaAtacante);
            retrocederFase();
        }
    }

    private void avanzarAFase(Fase fase)
    {
        this.maquinaTurnos.setFaseActual(fase);
    }

    private void retrocederFase() throws SeTerminaronLasFasesError
    {
        if (this.maquinaTurnos.getFaseActual().esFaseInicial())
        {
            throw new SeTerminaronLasFasesError();
        } else
        {
            this.maquinaTurnos.retrocederFase();
        }
    }
}
