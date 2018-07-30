package Controlador.estadosJuego;

import Controlador.condicionesJuego.*;
import Modelo.Jugador;
import Modelo.Modelo;
import Modelo.carta.Carta;
import Modelo.carta.monstruo.CartaMonstruo;
import Modelo.excepciones.*;

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
        } else if (this.maquinaTurnos.yaMandoMonstruoARegionEnTurnoActual())
        {
            return new YaSeMandoMonstruoARegionEnTurnoActualError();
        } else if (!carta.esMonstruo())
        {
            return new NoEsCartaMonstruo();
        } else if (!this.modelo.haySuficientesCartasParaSacrificar((CartaMonstruo) carta))
        {
            return new NoHaySuficientesCartasParaSacrificarError();
        } else if (!solicitante.getRegionMonstruos().hayEspacioLibre())
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
        } else if (!carta.esMagica() && !carta.esTrampa())
        {
            return new NoEsCartaMyTError();
        } else if (!solicitante.getRegionMagicasYTrampas().hayEspacioLibre())
        {
            return new NoHayEspacioLibreEnRegionMyT();
        }

        return new EstadoVerificadorBueno();
    }

    public EstadoVerificador sePuedeUsarTrampa(Jugador solicitante, Carta carta)
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
        } else if (!carta.esTrampa())
        {
            return new NoEsCartaMyTError();
        }

        return new EstadoVerificadorBueno();
    }

    public EstadoVerificador sePuedeUsarMagica(Jugador solicitante, Carta carta)
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
        } else if (!carta.esMagica())
        {
            return new NoEsCartaMyTError();
        }
        return new EstadoVerificadorBueno();
    }

    public EstadoVerificador sePuedeUsarCampo(Jugador solicitante, Carta carta)
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
        } else if (!carta.esCampo())
        {
            return new NoEsCartaCampoError();
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
        }

        return new EstadoVerificadorBueno();
    }

    public EstadoVerificador sePuedeCambiarModoCarta(Jugador solicitante, Carta carta)
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
        } else if (!carta.esMonstruo())
        {
            return new NoEsCartaMonstruo();
        }

        return new EstadoVerificadorBueno();
    }

    // ----------------------------------------
    // Verificación de ataque de cartas.
    // ----------------------------------------
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
        } else if (!cartaAtacante.esMonstruo())
        {
            return new NoEsCartaMonstruo();
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