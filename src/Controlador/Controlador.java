package Controlador;

import Controlador.estadosJuego.MaquinaTurnos;
import Controlador.excepciones.*;
import Modelo.Jugador;
import Modelo.Modelo;
import Modelo.carta.Carta;
import Modelo.carta.CartaNula;
import Modelo.carta.excepciones.ManoLlenaError;
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
        this.maquinaTurnos = MaquinaTurnos.obtenerInstancia(this.modelo.obtenerJugador(), this.modelo.obtenerOponente());
        // Vista va a mostrar la pantalla de bienvenida.
        this.vista.mostrar();
    }

    public void jugar()
    {
        // La vista pasa a la escena con el tablero principal.
        this.vista.mostrar();
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

    public String nombreJugadorActual()
    {
        return this.maquinaTurnos.obtenerJugadorActual().obtenerNombre();
    }

    public String nombreFaseActual()
    {
        return this.maquinaTurnos.faseActual().nombre();
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

    // ------------------------------------
    // Métodos de juego de cartas.
    // ------------------------------------
    // TODO: verificar la condición y fase en las cuales se pueden realizar estas operaciones.
    // TODO: verificar que el dueño de la carta y el jugador actual coincidan.

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
            try
            {
                this.modelo.tomarCarta(this.maquinaTurnos.obtenerJugadorActual());
            } catch (ManoLlenaError e)
            {
                throw e;
            }
        }
    }

    public void activarCartaMagica(Carta carta, Jugador solicitante) throws JugadorNoPermitidoParaJugar
    {
        if (jugadorPuedeJugar(solicitante) == false)
        {
            throw new JugadorNoPermitidoParaJugar(solicitante);
        }
        this.modelo.activarCartaMagica(solicitante, carta);
    }

    public void jugarCartaTrampa(Carta carta, Jugador solicitante) throws JugadorNoPermitidoParaJugar
    {
        if (jugadorPuedeJugar(solicitante) == false)
        {
            throw new JugadorNoPermitidoParaJugar(solicitante);
        }
        this.modelo.jugarCartaTrampa(solicitante, carta);
    }

    public void jugarCartaMagicaBocaArriba(Carta carta, Jugador solicitante) throws JugadorNoPermitidoParaJugar
    {
        if (jugadorPuedeJugar(solicitante) == false)
        {
            throw new JugadorNoPermitidoParaJugar(solicitante);
        }
        this.modelo.jugarCartaMagicaBocaArriba(solicitante, carta);
    }

    public void jugarCartaMagicaBocaAbajo(Carta carta, Jugador solicitante) throws JugadorNoPermitidoParaJugar
    {
        if (jugadorPuedeJugar(solicitante) == false)
        {
            throw new JugadorNoPermitidoParaJugar(solicitante);
        }
        this.modelo.jugarCartaMagicaBocaAbajo(solicitante, carta);
    }

    // ------------------------------------
    // Métodos de orientación de cartas.
    // ------------------------------------

    // TODO: verificar la condición y fase en las cuales se pueden realizar estas operaciones. Por ejemplo,
    // no se puede cambiar el modo del monstruo ni voltearla el mismo turno que es colocada en el campo.
    public void flipCartaMonstruo(CartaMonstruo carta, Jugador solicitante) throws JugadorNoPermitidoParaJugar,
            NoEsFasePreparacionError, CartaColocadaEnTurnoActualError
    {
        if (jugadorPuedeJugar(solicitante) == false)
        {
            throw new JugadorNoPermitidoParaJugar(solicitante);
        } else if (this.maquinaTurnos.faseActual().esFasePreparacion() == false)
        {
            throw new NoEsFasePreparacionError();
        } else if (this.maquinaTurnos.fueColocadaEnTurnoActual(carta) == true)
        {
            throw new CartaColocadaEnTurnoActualError();
        }
        {
            this.modelo.flipCartaMonstruo(carta);
        }
    }

    public void flipBocaAbajo(Carta carta, Jugador solicitante) throws JugadorNoPermitidoParaJugar
    {
        if (jugadorPuedeJugar(solicitante) == false)
        {
            throw new JugadorNoPermitidoParaJugar(solicitante);
        }
        this.modelo.flipBocaAbajo(carta);
    }

    public void flipBocaArriba(Carta carta, Jugador solicitante) throws JugadorNoPermitidoParaJugar
    {
        if (jugadorPuedeJugar(solicitante) == false)
        {
            throw new JugadorNoPermitidoParaJugar(solicitante);
        }
        this.modelo.flipBocaArriba(carta);
    }

    public void cambiarModoCartaMonstruo(CartaMonstruo carta, Jugador solicitante) throws JugadorNoPermitidoParaJugar
    {
        if (jugadorPuedeJugar(solicitante) == false)
        {
            throw new JugadorNoPermitidoParaJugar(solicitante);
        }
        this.modelo.cambiarModoCartaMonstruo(carta);
    }

    public void setModoAtaque(Carta carta, Jugador solicitante) throws JugadorNoPermitidoParaJugar
    {
        if (jugadorPuedeJugar(solicitante) == false)
        {
            throw new JugadorNoPermitidoParaJugar(solicitante);
        }
        this.modelo.setModoAtaque(carta);
    }

    public void setModoDefensa(Carta carta, Jugador solicitante) throws JugadorNoPermitidoParaJugar
    {
        if (jugadorPuedeJugar(solicitante) == false)
        {
            throw new JugadorNoPermitidoParaJugar(solicitante);
        }
        this.modelo.setModoDefensa(carta);
    }

    // ------------------------------------
    // Métodos de ataques.
    // ------------------------------------
    // TODO: verificar la condición y fase en las cuales se pueden realizar estas operaciones.
    public void atacar(CartaMonstruo cartaAtacante, CartaNula cartaNula, Jugador solicitante) throws NoEsUnaCartaParaAtacar, JugadorNoPermitidoParaJugar
    {
        if (jugadorPuedeJugar(solicitante) == false)
        {
            throw new JugadorNoPermitidoParaJugar(solicitante);
        }
        throw new NoEsUnaCartaParaAtacar();
    }

    public void atacar(CartaMonstruo cartaAtacante, CartaMonstruo cartaAtacada, Jugador solicitante) throws JugadorNoPermitidoParaJugar
    {
        if (jugadorPuedeJugar(solicitante) == false)
        {
            throw new JugadorNoPermitidoParaJugar(solicitante);
        }
        this.modelo.atacar(cartaAtacante, cartaAtacada);
    }

    public void atacar(CartaMonstruo cartaAtacante, Jugador solicitante) throws JugadorNoPermitidoParaJugar
    {
        if (jugadorPuedeJugar(solicitante) == false)
        {
            throw new JugadorNoPermitidoParaJugar(solicitante);
        }
        this.modelo.atacar(cartaAtacante);
    }
}
