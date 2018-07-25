package Controlador;

import Controlador.estadosJuego.MaquinaTurnos;
import Controlador.excepciones.*;
import Controlador.excepciones.especificas.SolicitanteNoEsPropietarioDeCartaError;
import Controlador.excepciones.especificas.YaSeMandoMonstruoARegionEnTurnoActual;
import Modelo.Jugador;
import Modelo.Modelo;
import Modelo.carta.Carta;
import Modelo.carta.Sacrificio;
import Modelo.carta.monstruo.CartaMonstruo;
import Modelo.finDeJuego.CausaFinJuego;
import Modelo.finDeJuego.CausaFinJuegoNula;
import Modelo.finDeJuego.ObservadorDeFinJuego;
import Vista.Vista;

public final class Controlador implements ObservadorDeFinJuego, IControlador
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
    }

    @Override
    public void avanzarProximaFase() throws SeTerminaronLasFasesError
    {
        if (this.maquinaTurnos.faseActual().esFaseFinal() == false)
        {
            this.maquinaTurnos.avanzarProximaFase();
        }
        throw new SeTerminaronLasFasesError();
    }

    private boolean jugadorPuedeJugar(Jugador jugador)
    {
        return this.maquinaTurnos.getJugadorActual() == jugador;
    }

    @Override
    public String nombreJugadorActual()
    {
        return this.maquinaTurnos.getJugadorActual().obtenerNombre();
    }

    @Override
    public String nombreFaseActual()
    {
        return this.maquinaTurnos.faseActual().nombre();
    }

    // ------------------------------------
    // Métodos de juego de cartas.
    // ------------------------------------
    // TODO: extraer las verificaciones en métodos.
    // TODO: se puede hacer un notificador desde las cartas a la maquina de turnos cuando pasa algun evento
    // interesante (ataque, cambio de orientacion, cambio de modo...)
    @Override
    public void tomarCarta(Jugador solicitante) throws NoSePuedeTomarCartaError
    {
        if (!sePuedeTomarCarta(solicitante))
        {
            throw new NoSePuedeTomarCartaError();
        } else
        {
            this.modelo.tomarCarta(this.maquinaTurnos.getJugadorActual());
            this.maquinaTurnos.seTomoCartaEnTurno();
        }
    }

    private boolean sePuedeTomarCarta(Jugador solicitante)
    {
        if (!jugadorPuedeJugar(solicitante))
        {
            return false;
        } else if (!this.maquinaTurnos.faseActual().esFaseInicial())
        {
            return false;
        } else if (this.maquinaTurnos.yaTomoCartaEnTurno())
        {
            return false;
        }
        return true;
    }

    @Override
    public void setCartaMonstruo(Carta carta, Jugador solicitante) throws NoSePuedeMandarCartaARegionError
    {
        if (!sePuedeMandarARegion(carta, solicitante))
        {
            throw new NoSePuedeMandarCartaARegionError();
        } else
        {
            if (!this.modelo.requiereSacrificios(carta))
            {

                this.modelo.setCartaMonstruo(carta);
                this.maquinaTurnos.seColocoCartaEnRegion(carta);
            } else
            {
                Sacrificio sacrificios = this.vista.pedirSacrificios();
                this.modelo.setCartaMonstruo(carta, sacrificios);
                this.maquinaTurnos.seColocoCartaEnRegion(carta);
            }
        }
    }

    @Override
    public void summonCartaMonstruo(Carta carta, Jugador solicitante) throws NoSePuedeMandarCartaARegionError
    {
        if (!sePuedeMandarARegion(carta, solicitante))
        {
            throw new NoSePuedeMandarCartaARegionError();
        } else
        {
            if (!this.modelo.requiereSacrificios(carta))
            {
                // TODO: verificar si esto funciona bien durante el juego.
                this.flipBocaArriba(carta, solicitante);
                this.setModoAtaque(carta, solicitante);
                this.modelo.setCartaMonstruo(carta);
                this.maquinaTurnos.seColocoCartaEnRegion(carta);
            } else
            {
                Sacrificio sacrificios = this.vista.pedirSacrificios();
                // TODO: verificar si esto funciona bien durante el juego.
                this.flipBocaArriba(carta, solicitante);
                this.setModoAtaque(carta, solicitante);
                this.modelo.setCartaMonstruo(carta, sacrificios);
                this.maquinaTurnos.seColocoCartaEnRegion(carta);
            }
        }
    }

    private boolean sePuedeMandarARegion(Carta carta, Jugador solicitante)
    {

        if (carta.getPropietario() != solicitante)
        {
            return false;
            //throw new SolicitanteNoEsPropietarioDeCartaError();
        } else if (!jugadorPuedeJugar(solicitante))
        {
            return false;
            //throw new JugadorNoPermitidoParaJugar(solicitante);
        } else if (!this.maquinaTurnos.faseActual().esFasePreparacion())
        {
            return false;
            //throw new NoEsFasePreparacionError();
        } else if (this.maquinaTurnos.yaMandoMonstruoARegionEnTurno())
        {
            return false;
            //throw new YaSeMandoMonstruoARegionEnTurnoActual();
        } else
        {
            if (!this.modelo.haySuficientesSacrificios(carta))
            {
                return false;
                //throw new NoHaySuficientesSacrificiosError();
            }
        }

        return true;
    }

    @Override
    public void setCartaTrampa(Carta carta, Jugador solicitante) throws NoSePuedeEnviarARegionMyT
    {
        if (!sePuedeEnviarARegionMyT(carta, solicitante))
        {
            throw new NoSePuedeEnviarARegionMyT();
        } else
        {
            this.modelo.setCartaTrampa(solicitante, carta);
        }
    }

    @Override
    public void setCartaMagica(Carta carta, Jugador solicitante) throws NoSePuedeEnviarARegionMyT
    {
        if (!sePuedeEnviarARegionMyT(carta, solicitante))
        {
            throw new NoSePuedeEnviarARegionMyT();
        } else
        {
            this.modelo.setCartaMagica(solicitante, carta);
        }
    }

    private boolean sePuedeEnviarARegionMyT(Carta carta, Jugador solicitante)
    {
        if (carta.getPropietario() != solicitante)
        {
            return false;
            //throw new SolicitanteNoEsPropietarioDeCartaError();
        } else if (!jugadorPuedeJugar(solicitante))
        {
            return false;
            //throw new JugadorNoPermitidoParaJugar(solicitante);
        } else if (!this.maquinaTurnos.faseActual().esFasePreparacion())
        {
            return false;
            //throw new NoEsFaseFinalError();
        }
        return true;
    }

    @Override
    public void activarCartaMagica(Carta carta, Jugador solicitante) throws NoSePuedeEnviarARegionMyT
    {
        if (!sePuedeEnviarMagicaARegionMyT(carta, solicitante))
        {
            throw new NoSePuedeEnviarARegionMyT();
        } else
        {
            this.modelo.activarCartaMagica(solicitante, carta);
            this.maquinaTurnos.seCambioOrientacionCarta(carta);
        }
    }

    private boolean sePuedeEnviarMagicaARegionMyT(Carta carta, Jugador solicitante)
    {
        if (carta.getPropietario() != solicitante)
        {
            return false;
            //throw new SolicitanteNoEsPropietarioDeCartaError();
        } else if (!jugadorPuedeJugar(solicitante))
        {
            return false;
            //throw new JugadorNoPermitidoParaJugar(solicitante);
        } else if (!this.maquinaTurnos.faseActual().esFaseFinal())
        {
            return false;
            //throw new NoEsFaseFinalError();
        }
        return true;
    }

    // ------------------------------------
    // Métodos de orientación de cartas.
    // ------------------------------------
    @Override
    public void flipCartaMonstruo(CartaMonstruo carta, Jugador solicitante) throws NoSePuedeCambiarOrientacionError
    {
        if (!sePuedeCambiarOrientacionCarta(carta, solicitante))
        {
            throw new NoSePuedeCambiarOrientacionError();
        } else
        {
            this.modelo.flipCartaMonstruo(carta);
            this.maquinaTurnos.seCambioOrientacionCarta(carta);
        }
    }

    @Override
    public void flipBocaAbajo(Carta carta, Jugador solicitante) throws NoSePuedeCambiarOrientacionError
    {
        if (!sePuedeCambiarOrientacionCarta(carta, solicitante))
        {
            throw new NoSePuedeCambiarOrientacionError();
        } else
        {
            this.modelo.flipBocaAbajo(carta);
            this.maquinaTurnos.seCambioOrientacionCarta(carta);
        }
    }

    @Override
    public void flipBocaArriba(Carta carta, Jugador solicitante) throws NoSePuedeCambiarOrientacionError
    {
        if (!sePuedeCambiarOrientacionCarta(carta, solicitante))
        {
            throw new NoSePuedeCambiarOrientacionError();
        } else
        {
            this.modelo.flipBocaArriba(carta);
            this.maquinaTurnos.seCambioOrientacionCarta(carta);
        }
    }

    @Override
    public void cambiarModoCartaMonstruo(CartaMonstruo carta, Jugador solicitante) throws NoSePuedeCambiarOrientacionError
    {
        if (!sePuedeCambiarOrientacionCarta(carta, solicitante))
        {
            throw new NoSePuedeCambiarOrientacionError();
        } else
        {
            this.modelo.cambiarModoCartaMonstruo(carta);
            this.maquinaTurnos.seCambioOrientacionCarta(carta);
        }
    }

    @Override
    public void setModoAtaque(Carta carta, Jugador solicitante) throws NoSePuedeCambiarOrientacionError
    {
        if (!sePuedeCambiarOrientacionCarta(carta, solicitante))
        {
            throw new NoSePuedeCambiarOrientacionError();
        } else
        {
            this.modelo.setModoAtaque(carta);
            this.maquinaTurnos.seCambioOrientacionCarta(carta);
        }
    }

    @Override
    public void setModoDefensa(Carta carta, Jugador solicitante) throws NoSePuedeCambiarOrientacionError
    {
        if (!sePuedeCambiarOrientacionCarta(carta, solicitante))
        {
            throw new NoSePuedeCambiarOrientacionError();
        } else
        {
            this.modelo.setModoDefensa(carta);
            this.maquinaTurnos.seCambioOrientacionCarta(carta);
        }
    }

    private boolean sePuedeCambiarOrientacionCarta(Carta carta, Jugador solicitante)
    {

        if (!jugadorPuedeJugar(solicitante))
        {
            return false;
            //throw new JugadorNoPermitidoParaJugar(solicitante);
        } else if (!this.maquinaTurnos.faseActual().esFasePreparacion())
        {
            return false;
            //throw new NoEsFasePreparacionError();
        } else if (this.maquinaTurnos.yaColocoEnTurno(carta))
        {
            return false;
            //throw new CartaNoPuedeCambiarOrientacionEnTurnoActualError();
        }

        return true;
    }

    // ------------------------------------
    // Métodos de ataques.
    // ------------------------------------
    @Override
    public void atacar(CartaMonstruo cartaAtacante, CartaMonstruo cartaAtacada, Jugador solicitante) throws NoSePuedeAtacarError
    {
        if (!sePuedeAtacar(cartaAtacante, solicitante))
        {
            throw new NoSePuedeAtacarError();
        } else
        {
            this.modelo.atacar(cartaAtacante, cartaAtacada);
            this.maquinaTurnos.cartaAtaco(cartaAtacante);
        }
    }

    @Override
    public void atacar(CartaMonstruo cartaAtacante, Jugador solicitante) throws NoSePuedeAtacarError
    {
        if (!sePuedeAtacar(cartaAtacante, solicitante))
        {
            throw new NoSePuedeAtacarError();
        } else
        {
            this.modelo.atacar(cartaAtacante);
            this.maquinaTurnos.cartaAtaco(cartaAtacante);
        }
    }

    private boolean sePuedeAtacar(CartaMonstruo cartaAtacante, Jugador solicitante)
    {
        if (!jugadorPuedeJugar(solicitante))
        {
            return false;
            //throw new JugadorNoPermitidoParaJugar(solicitante);
        } else if (!this.maquinaTurnos.faseActual().esFaseAtaque())
        {
            return false;
            //throw new NoEsFaseAtaqueError();
        } else if (this.maquinaTurnos.esPrimerTurnoJuego())
        {
            return false;
            //throw new NoSeAtacaEnPrimerTurnoJuegoError();
        } else if (this.maquinaTurnos.yaUsoAtaqueEnTurno(cartaAtacante))
        {
            return false;
            //throw new CartaYaAtacoEnTurnoActualError();
        }
        return true;
    }
}
