package Controlador.estadosJuego;

import Controlador.condicionesJuego.*;
import Modelo.Jugador;
import Modelo.Modelo;
import Modelo.carta.Carta;
import Modelo.carta.excepciones.SacrificiosInsuficientesError;
import Modelo.carta.monstruo.CartaMonstruo;
import Modelo.excepciones.CartaBocaAbajoNoPuedeAtacarError;
import Modelo.excepciones.CartaEnDefensaNoPuedeAtacarError;

public final class VerificadorCondicionesJuego
{
    private static VerificadorCondicionesJuego instancia = null;
    MaquinaTurnos maquinaTurnos;
    Modelo modelo;

    private VerificadorCondicionesJuego(MaquinaTurnos maquinaTurnos, Modelo modelo)
    {
        this.maquinaTurnos = maquinaTurnos;
        this.modelo = modelo;
    }

    public static VerificadorCondicionesJuego getInstancia(MaquinaTurnos maquinaTurnos, Modelo modelo)
    {
        if (instancia == null)
        {
            instancia = new VerificadorCondicionesJuego(maquinaTurnos, modelo);
        }
        return instancia;
    }

    @Override
    public VerificadorCondicionesJuego clone() throws CloneNotSupportedException
    {
        throw new CloneNotSupportedException();
    }

    // ------------------------------------
    // Verificación de juego de jugador.
    // ------------------------------------
    private boolean jugadorPuedeJugar(Jugador jugador)
    {
        return this.maquinaTurnos.jugadorActualEsIgualA(jugador);
    }

    // ------------------------------------
    // Verificación de juego de cartas.
    // ------------------------------------
    public EstadoVerificador sePuedeTomarCarta(Jugador solicitante)
    {
        if (!jugadorPuedeJugar(solicitante))
        {
            return new JugadorNoPermitidoParaJugarError(solicitante);
        } else if (!this.maquinaTurnos.getFaseActual().esFaseInicial())
        {
            return new NoEsFaseInicialError();
        } else if (this.maquinaTurnos.yaTomoCartaEnTurnoActual())
        {
            return new YaTomoCartaEnTurnoError();
        }
        return new EstadoVerificadorBueno();
    }

    // -------------------
    // Cartas Monstruo.
    // -------------------
    public EstadoVerificador sePuedeEnviarARegionMonstruo(Jugador solicitante, Carta carta)
    {
        if (carta.getPropietario() != solicitante)
        {
            return new SolicitanteNoEsPropietarioDeCartaError();
        } else if (!jugadorPuedeJugar(solicitante))
        {
            return new JugadorNoPermitidoParaJugarError(solicitante);
        } else if (!this.maquinaTurnos.getFaseActual().esFasePreparacion())
        {
            return new NoEsFasePreparacionError();
        } else if (this.maquinaTurnos.seColocoCartaMonstruoEnRegionEnTurnoActual())
        {
            return new YaSeMandoCartaMonstruoARegionEnTurnoActualError();
        } else if (!this.modelo.seCumplenCondicionesDeSacrificiosRequeridos((CartaMonstruo) carta))
        {
            return new SacrificiosInsuficientesError();
        } else if (!solicitante.getRegionMonstruos().hayEspacioLibre() && !((CartaMonstruo) carta)
                .requiereSacrificios())
        {
            return new NoHayEspacioLibreEnRegionMonstruo();
        }

        return new EstadoVerificadorBueno();
    }

    // -------------------
    // Cartas MyT.
    // -------------------
    public EstadoVerificador sePuedeEnviarARegionMyT(Jugador solicitante, Carta carta)
    {
        if (carta.getPropietario() != solicitante)
        {
            return new SolicitanteNoEsPropietarioDeCartaError();
        } else if (!jugadorPuedeJugar(solicitante))
        {
            return new JugadorNoPermitidoParaJugarError(solicitante);
        } else if (!this.maquinaTurnos.getFaseActual().esFasePreparacion())
        {
            return new NoEsFasePreparacionError();
        } else if (!solicitante.getRegionMagicasYTrampas().hayEspacioLibre())
        {
            return new NoHayEspacioLibreEnRegionMyT();
        }

        return new EstadoVerificadorBueno();
    }

    public EstadoVerificador sePuedeActivarCartaDesdeMano(Jugador solicitante, Carta carta)
    {
        if (carta.getPropietario() != solicitante)
        {
            return new SolicitanteNoEsPropietarioDeCartaError();
        } else if (!jugadorPuedeJugar(solicitante))
        {
            return new JugadorNoPermitidoParaJugarError(solicitante);
        } else if (!this.maquinaTurnos.getFaseActual().esFasePreparacion())
        {
            return new NoEsFasePreparacionError();
        }

        return new EstadoVerificadorBueno();
    }

    public EstadoVerificador sePuedeActivarMagicaDesdeRegionMyT(Jugador solicitante, Carta carta)
    {
        if (carta.getPropietario() != solicitante)
        {
            return new SolicitanteNoEsPropietarioDeCartaError();
        } else if (!jugadorPuedeJugar(solicitante))
        {
            return new JugadorNoPermitidoParaJugarError(solicitante);
        } else if (!this.maquinaTurnos.getFaseActual().esFaseFinal())
        {
            return new NoEsFaseFinalError();
        }

        return new EstadoVerificadorBueno();
    }

    // ----------------------------------------
    // Verificación de orientación de cartas.
    // ----------------------------------------
    public EstadoVerificador sePuedeCambiarOrientacionCarta(Jugador solicitante, Carta carta)
    {

        if (!jugadorPuedeJugar(solicitante))
        {
            return new JugadorNoPermitidoParaJugarError(solicitante);
        } else if (!this.maquinaTurnos.getFaseActual().esFasePreparacion())
        {
            return new NoEsFasePreparacionError();
        } else if (this.maquinaTurnos.yaCambioOrientacionEnTurnoActual(carta))
        {
            return new CartaNoPuedeCambiarOrientacionEnTurnoActualError();
        } else if (this.maquinaTurnos.yaMandoCartaARegionEnTurnoActual(carta))
        {
            return new YaSeMandoCartaARegionEnTurnoActualError();
        }

        return new EstadoVerificadorBueno();
    }

    // ----------------------------------------
    // Verificación de ataque de cartas.
    // ----------------------------------------
    // Se supone que la carta ya está en el campo.
    public EstadoVerificador sePuedeAtacar(Carta cartaAtacante, Jugador solicitante)
    {
        if (!jugadorPuedeJugar(solicitante))
        {
            return new JugadorNoPermitidoParaJugarError(solicitante);
        } else if (!this.maquinaTurnos.getFaseActual().esFaseAtaque())
        {
            return new NoEsFaseAtaqueError();
        } else if (this.maquinaTurnos.esElPrimerTurnoDelJuego())
        {
            return new NoSeAtacaEnPrimerTurnoJuegoError();
        } else if (((CartaMonstruo) cartaAtacante).estaEnDefensa())
        {
            return new CartaEnDefensaNoPuedeAtacarError();
        } else if (cartaAtacante.estaBocaAbajo())
        {
            return new CartaBocaAbajoNoPuedeAtacarError();
        } else if (this.maquinaTurnos.cartaYaAtacoEnTurnoActual((CartaMonstruo) cartaAtacante))
        {
            return new CartaYaAtacoEnTurnoActualError();
        }
        return new EstadoVerificadorBueno();
    }
}