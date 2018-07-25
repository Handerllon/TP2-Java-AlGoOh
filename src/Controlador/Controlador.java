package Controlador;

import Controlador.estadosJuego.MaquinaTurnos;
import Controlador.excepciones.*;
import Modelo.Jugador;
import Modelo.Modelo;
import Modelo.carta.Carta;
import Modelo.carta.Sacrificio;
import Modelo.carta.excepciones.ManoLlenaError;
import Modelo.carta.excepciones.NoEsUnaCartaMonstruo;
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

    public static Controlador obtenerInstancia(Modelo modelo)
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

    public void establecerVista(Vista vista)
    {
        this.vista = vista;
    }

    // ------------------------------------
    // Ejecucion.
    // ------------------------------------
    public void iniciar()
    {
        this.maquinaTurnos = MaquinaTurnos.obtenerInstancia(this.modelo.obtenerJugador(), this.modelo.obtenerOponente());
        // Vista va a mostrar la pantalla de bienvenida.
        this.vista.mostrar();
    }

    public void jugar()
    {
        // La vista pasa a la escena con el tablero principal.
        this.vista.mostrar();
    }

    // ------------------------------------
    // Métodos de observador de fin de juego.
    // ------------------------------------
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
    // Interfaz con el modelo.
    // ------------------------------------
    public void establecerNombreJugador(String text)
    {
        this.modelo.establecerNombreJugador(text);
    }

    public void establecerNombreOponente(String text)
    {
        this.modelo.establecerNombreOponente(text);
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
        } else
        {
            throw new SeTerminaronLasFasesError();
        }
    }

    private boolean jugadorPuedeJugar(Jugador jugador)
    {
        return this.maquinaTurnos.obtenerJugadorActual() == jugador;
    }

    private boolean cartaPuedeCambiarOrientacion(Carta carta)
    {
        if (this.maquinaTurnos.fueColocadaEnTurnoActual(carta) == true)
        {
            return false;
        } else
        {
            return true;
        }
    }

    private boolean cartaPuedeAtacar(CartaMonstruo carta)
    {
        if (this.maquinaTurnos.usoAtaqueEnTurnoActual(carta) == true)
        {
            return false;
        } else
        {
            return true;
        }
    }

    @Override
    public String nombreJugadorActual()
    {
        return this.maquinaTurnos.obtenerJugadorActual().obtenerNombre();
    }

    @Override
    public String nombreFaseActual()
    {
        return this.maquinaTurnos.faseActual().nombre();
    }

    // ------------------------------------
    // Métodos de juego de cartas.
    // ------------------------------------
    @Override
    public void tomarCarta(Jugador solicitante) throws JugadorNoPermitidoParaJugar, ManoLlenaError, NoEsFaseInicialError
    {
        if (jugadorPuedeJugar(solicitante) == false)
        {
            throw new JugadorNoPermitidoParaJugar(solicitante);
        } else if (this.maquinaTurnos.faseActual().esFaseInicial() == false)
        {
            throw new NoEsFaseInicialError();
        } else
        {
            this.modelo.tomarCarta(this.maquinaTurnos.obtenerJugadorActual());
        }
    }

    @Override
    public void setCartaMonstruo(Carta carta, Jugador solicitante) throws JugadorNoPermitidoParaJugar,
            SolicitanteNoEsPropietarioDeCartaError, NoEsFasePreparacionError, NoHaySuficientesSacrificiosError,
            NoEsUnaCartaMonstruo, YaSeMandoMonstruoARegionEnTurno
    {
        if (carta.obtenerPropietario() != solicitante)
        {
            throw new SolicitanteNoEsPropietarioDeCartaError();
        } else if (jugadorPuedeJugar(solicitante) == false)
        {
            throw new JugadorNoPermitidoParaJugar(solicitante);
        } else if (this.maquinaTurnos.faseActual().esFasePreparacion() == false)
        {
            throw new NoEsFasePreparacionError();
        } else
        {
            if (this.modelo.requiereSacrificios(carta) == false)
            {
                if (this.maquinaTurnos.yaSeMandoMonstruoARegionEnTurno() == true)
                {
                    throw new YaSeMandoMonstruoARegionEnTurno();
                }
                this.modelo.setCartaMonstruo(carta);
                this.maquinaTurnos.seColocoCartaEnRegion(carta);
            } else if (this.modelo.haySuficientesSacrificios(carta) == false)
            {
                throw new NoHaySuficientesSacrificiosError();
            } else
            {
                Sacrificio sacrificios = this.vista.pedirSacrificios();

                this.modelo.setCartaMonstruo(carta, sacrificios);
                this.maquinaTurnos.seColocoCartaEnRegion(carta);
            }
        }
    }

    @Override
    public void summonCartaMonstruo(Carta carta, Jugador solicitante) throws JugadorNoPermitidoParaJugar,
            SolicitanteNoEsPropietarioDeCartaError, NoEsFasePreparacionError, NoHaySuficientesSacrificiosError,
            NoEsUnaCartaMonstruo, YaSeMandoMonstruoARegionEnTurno, CartaColocadaEnTurnoActualError
    {
        if (carta.obtenerPropietario() != solicitante)
        {
            throw new SolicitanteNoEsPropietarioDeCartaError();
        } else if (jugadorPuedeJugar(solicitante) == false)
        {
            throw new JugadorNoPermitidoParaJugar(solicitante);
        } else if (this.maquinaTurnos.faseActual().esFasePreparacion() == false)
        {
            throw new NoEsFasePreparacionError();
        } else
        {
            if (this.modelo.requiereSacrificios(carta) == false)
            {
                if (this.maquinaTurnos.yaSeMandoMonstruoARegionEnTurno() == true)
                {
                    throw new YaSeMandoMonstruoARegionEnTurno();
                }
                this.flipBocaArriba(carta, solicitante);
                this.setModoAtaque(carta, solicitante);
                this.modelo.setCartaMonstruo(carta);
                this.maquinaTurnos.seColocoCartaEnRegion(carta);
            } else if (this.modelo.haySuficientesSacrificios(carta) == false)
            {
                throw new NoHaySuficientesSacrificiosError();
            } else
            {
                Sacrificio sacrificios = this.vista.pedirSacrificios();
                this.flipBocaArriba(carta, solicitante);
                this.setModoAtaque(carta, solicitante);
                this.modelo.setCartaMonstruo(carta, sacrificios);
                this.maquinaTurnos.seColocoCartaEnRegion(carta);
            }
        }
    }

    @Override
    public void activarCartaMagica(Carta carta, Jugador solicitante) throws JugadorNoPermitidoParaJugar,
            SolicitanteNoEsPropietarioDeCartaError, NoEsFaseFinalError
    {
        if (carta.obtenerPropietario() != solicitante)
        {
            throw new SolicitanteNoEsPropietarioDeCartaError();
        } else if (jugadorPuedeJugar(solicitante) == false)
        {
            throw new JugadorNoPermitidoParaJugar(solicitante);
        } else if (this.maquinaTurnos.faseActual().esFaseFinal() == false)
        {
            throw new NoEsFaseFinalError();
        } else
        {
            this.modelo.activarCartaMagica(solicitante, carta);
            this.maquinaTurnos.seCambioOrientacionCarta(carta);
        }
    }

    @Override
    public void setCartaTrampa(Carta carta, Jugador solicitante) throws JugadorNoPermitidoParaJugar,
            SolicitanteNoEsPropietarioDeCartaError, NoEsFasePreparacionError
    {
        if (carta.obtenerPropietario() != solicitante)
        {
            throw new SolicitanteNoEsPropietarioDeCartaError();
        } else if (jugadorPuedeJugar(solicitante) == false)
        {
            throw new JugadorNoPermitidoParaJugar(solicitante);
        } else if (this.maquinaTurnos.faseActual().esFasePreparacion() == false)
        {
            throw new NoEsFasePreparacionError();
        } else
        {
            this.modelo.setCartaTrampa(solicitante, carta);
        }
    }

    @Override
    public void setCartaMagica(Carta carta, Jugador solicitante) throws JugadorNoPermitidoParaJugar,
            SolicitanteNoEsPropietarioDeCartaError, NoEsFasePreparacionError
    {
        if (carta.obtenerPropietario() != solicitante)
        {
            throw new SolicitanteNoEsPropietarioDeCartaError();
        } else if (jugadorPuedeJugar(solicitante) == false)
        {
            throw new JugadorNoPermitidoParaJugar(solicitante);
        } else if (this.maquinaTurnos.faseActual().esFasePreparacion() == false)
        {
            throw new NoEsFasePreparacionError();
        }
        {
            this.modelo.setCartaMagica(solicitante, carta);
        }
    }

    // ------------------------------------
    // Métodos de orientación de cartas.
    // ------------------------------------
    @Override
    public void flipCartaMonstruo(CartaMonstruo carta, Jugador solicitante) throws JugadorNoPermitidoParaJugar,
            NoEsFasePreparacionError, CartaColocadaEnTurnoActualError
    {
        if (jugadorPuedeJugar(solicitante) == false)
        {
            throw new JugadorNoPermitidoParaJugar(solicitante);
        } else if (this.maquinaTurnos.faseActual().esFasePreparacion() == false)
        {
            throw new NoEsFasePreparacionError();
        } else if (cartaPuedeCambiarOrientacion(carta) == false)
        {
            throw new CartaColocadaEnTurnoActualError();
        } else
        {
            this.modelo.flipCartaMonstruo(carta);
            this.maquinaTurnos.seCambioOrientacionCarta(carta);
        }
    }

    @Override
    public void flipBocaAbajo(Carta carta, Jugador solicitante) throws JugadorNoPermitidoParaJugar,
            NoEsFasePreparacionError, CartaColocadaEnTurnoActualError
    {
        if (jugadorPuedeJugar(solicitante) == false)
        {
            throw new JugadorNoPermitidoParaJugar(solicitante);
        } else if (this.maquinaTurnos.faseActual().esFasePreparacion() == false)
        {
            throw new NoEsFasePreparacionError();
        } else if (cartaPuedeCambiarOrientacion(carta) == false)
        {
            throw new CartaColocadaEnTurnoActualError();
        } else
        {

            this.modelo.flipBocaAbajo(carta);
            this.maquinaTurnos.seCambioOrientacionCarta(carta);
        }
    }

    @Override
    public void flipBocaArriba(Carta carta, Jugador solicitante) throws JugadorNoPermitidoParaJugar,
            NoEsFasePreparacionError, CartaColocadaEnTurnoActualError
    {
        if (jugadorPuedeJugar(solicitante) == false)
        {
            throw new JugadorNoPermitidoParaJugar(solicitante);
        } else if (this.maquinaTurnos.faseActual().esFasePreparacion() == false)
        {
            throw new NoEsFasePreparacionError();
        } else if (cartaPuedeCambiarOrientacion(carta) == false)
        {
            throw new CartaColocadaEnTurnoActualError();
        } else
        {
            this.modelo.flipBocaArriba(carta);
            this.maquinaTurnos.seCambioOrientacionCarta(carta);
        }
    }

    @Override
    public void cambiarModoCartaMonstruo(CartaMonstruo carta, Jugador solicitante) throws JugadorNoPermitidoParaJugar,
            NoEsFasePreparacionError, CartaColocadaEnTurnoActualError
    {
        if (jugadorPuedeJugar(solicitante) == false)
        {
            throw new JugadorNoPermitidoParaJugar(solicitante);
        } else if (this.maquinaTurnos.faseActual().esFasePreparacion() == false)
        {
            throw new NoEsFasePreparacionError();
        } else if (cartaPuedeCambiarOrientacion(carta) == false)
        {
            throw new CartaColocadaEnTurnoActualError();
        } else
        {
            this.modelo.cambiarModoCartaMonstruo(carta);
            this.maquinaTurnos.seCambioOrientacionCarta(carta);
        }
    }

    @Override
    public void setModoAtaque(Carta carta, Jugador solicitante) throws JugadorNoPermitidoParaJugar,
            NoEsFasePreparacionError, CartaColocadaEnTurnoActualError
    {
        if (jugadorPuedeJugar(solicitante) == false)
        {
            throw new JugadorNoPermitidoParaJugar(solicitante);
        } else if (this.maquinaTurnos.faseActual().esFasePreparacion() == false)
        {
            throw new NoEsFasePreparacionError();
        } else if (cartaPuedeCambiarOrientacion(carta) == false)
        {
            throw new CartaColocadaEnTurnoActualError();
        } else
        {
            this.modelo.setModoAtaque(carta);
            this.maquinaTurnos.seCambioOrientacionCarta(carta);
        }
    }

    @Override
    public void setModoDefensa(Carta carta, Jugador solicitante) throws JugadorNoPermitidoParaJugar,
            NoEsFasePreparacionError, CartaColocadaEnTurnoActualError
    {
        if (jugadorPuedeJugar(solicitante) == false)
        {
            throw new JugadorNoPermitidoParaJugar(solicitante);
        } else if (this.maquinaTurnos.faseActual().esFasePreparacion() == false)
        {
            throw new NoEsFasePreparacionError();
        } else if (cartaPuedeCambiarOrientacion(carta) == false)
        {
            throw new CartaColocadaEnTurnoActualError();
        } else
        {
            this.modelo.setModoDefensa(carta);
            this.maquinaTurnos.seCambioOrientacionCarta(carta);
        }
    }

    // ------------------------------------
    // Métodos de ataques.
    // ------------------------------------
    @Override
    public void atacar(CartaMonstruo cartaAtacante, CartaMonstruo cartaAtacada, Jugador solicitante) throws
            JugadorNoPermitidoParaJugar, NoEsFaseAtaqueError, NoSeAtacaEnPrimerTurnoJuegoError, CartaYaAtacoError
    {
        if (jugadorPuedeJugar(solicitante) == false)
        {
            throw new JugadorNoPermitidoParaJugar(solicitante);
        } else if (this.maquinaTurnos.faseActual().esFaseAtaque() == false)
        {
            throw new NoEsFaseAtaqueError();
        } else if (this.maquinaTurnos.esPrimerTurnoJuego() == true)
        {
            throw new NoSeAtacaEnPrimerTurnoJuegoError();
        } else if (this.cartaPuedeAtacar(cartaAtacante) == false)
        {
            throw new CartaYaAtacoError();
        } else
        {
            this.modelo.atacar(cartaAtacante, cartaAtacada);
            this.maquinaTurnos.cartaAtaco(cartaAtacante);
        }
    }

    @Override
    public void atacar(CartaMonstruo cartaAtacante, Jugador solicitante) throws JugadorNoPermitidoParaJugar,
            NoEsFaseAtaqueError, NoSeAtacaEnPrimerTurnoJuegoError, CartaYaAtacoError
    {
        if (jugadorPuedeJugar(solicitante) == false)
        {
            throw new JugadorNoPermitidoParaJugar(solicitante);
        } else if (this.maquinaTurnos.faseActual().esFaseAtaque() == false)
        {
            throw new NoEsFaseAtaqueError();
        } else if (this.maquinaTurnos.esPrimerTurnoJuego() == true)
        {
            throw new NoSeAtacaEnPrimerTurnoJuegoError();
        } else if (this.cartaPuedeAtacar(cartaAtacante) == false)
        {
            throw new CartaYaAtacoError();
        } else
        {
            this.modelo.atacar(cartaAtacante);
            this.maquinaTurnos.cartaAtaco(cartaAtacante);
        }
    }
}
