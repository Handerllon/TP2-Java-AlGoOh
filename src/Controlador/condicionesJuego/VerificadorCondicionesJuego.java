package Controlador.condicionesJuego;

import Controlador.estadosJuego.MaquinaTurnos;
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
            return new JugadorNoPermitidoParaJugar(solicitante);
        } else if (!this.maquinaTurnos.getFaseActual().esFaseInicial())
        {
            return new NoEsFaseInicialError();
        } else if (this.maquinaTurnos.yaTomoCartaEnTurno())
        {
            return new YaTomoCartaEnTurnoError();
        }
        return new EstadoVerificadorBueno();
    }

    // -------------------
    // Cartas Monstruo.
    // -------------------
    public EstadoVerificador sePuedeEnviarARegionMonstruo(Carta carta, Jugador solicitante)
    {

        if (carta.getPropietario() != solicitante)
        {
            return new SolicitanteNoEsPropietarioDeCartaError();
        } else if (!jugadorPuedeJugar(solicitante))
        {
            return new JugadorNoPermitidoParaJugar(solicitante);
        } else if (!this.maquinaTurnos.getFaseActual().esFasePreparacion())
        {
            return new NoEsFasePreparacionError();
        } else if (this.maquinaTurnos.yaMandoMonstruoARegionEnTurno())
        {
            return new YaSeMandoMonstruoARegionEnTurnoActual();
        } else if (!carta.esMonstruo())
        {
            return new NoEsCartaMonstruo();
        } else
        {
            if (!this.modelo.haySuficientesSacrificios((CartaMonstruo) carta))
            {
                return new NoHaySuficientesSacrificiosError();
            }
        }

        return new EstadoVerificadorBueno();
    }

    // -------------------
    // Cartas MyT.
    // -------------------
    public EstadoVerificador sePuedeEnviarARegionMyT(Carta carta, Jugador solicitante)
    {
        if (carta.getPropietario() != solicitante)
        {
            return new SolicitanteNoEsPropietarioDeCartaError();
        } else if (!jugadorPuedeJugar(solicitante))
        {
            return new JugadorNoPermitidoParaJugar(solicitante);
        } else if (!this.maquinaTurnos.getFaseActual().esFasePreparacion())
        {
            return new NoEsFasePreparacionError();
        } else if (!carta.esMagica() && !carta.esTrampa())
        {
            return new NoEsCartaMyTError();
        }

        return new EstadoVerificadorBueno();
    }

    public EstadoVerificador sePuedeUsarTrampa(Carta carta, Jugador solicitante)
    {
        if (carta.getPropietario() != solicitante)
        {
            return new SolicitanteNoEsPropietarioDeCartaError();
        } else if (!jugadorPuedeJugar(solicitante))
        {
            return new JugadorNoPermitidoParaJugar(solicitante);
        } else if (!this.maquinaTurnos.getFaseActual().esFasePreparacion())
        {
            return new NoEsFasePreparacionError();
        } else if (!carta.esTrampa())
        {
            return new NoEsCartaMyTError();
        }

        return new EstadoVerificadorBueno();
    }

    public EstadoVerificador sePuedeUsarMagica(Carta carta, Jugador solicitante)
    {
        if (carta.getPropietario() != solicitante)
        {
            return new SolicitanteNoEsPropietarioDeCartaError();
        } else if (!jugadorPuedeJugar(solicitante))
        {
            return new JugadorNoPermitidoParaJugar(solicitante);
        } else if (!this.maquinaTurnos.getFaseActual().esFaseFinal())
        {
            return new NoEsFaseFinalError();
        } else if (!carta.esMagica())
        {
            return new NoEsCartaMyTError();
        }
        return new EstadoVerificadorBueno();
    }

    public EstadoVerificador sePuedeUsarCampo(Carta carta, Jugador solicitante)
    {
        if (carta.getPropietario() != solicitante)
        {
            return new SolicitanteNoEsPropietarioDeCartaError();
        } else if (!jugadorPuedeJugar(solicitante))
        {
            return new JugadorNoPermitidoParaJugar(solicitante);
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
    public EstadoVerificador sePuedeCambiarOrientacionCarta(Carta carta, Jugador solicitante)
    {

        if (!jugadorPuedeJugar(solicitante))
        {
            return new JugadorNoPermitidoParaJugar(solicitante);
        } else if (!this.maquinaTurnos.getFaseActual().esFasePreparacion())
        {
            return new NoEsFasePreparacionError();
        } else if (this.maquinaTurnos.yaColocoEnTurno(carta))
        {
            return new CartaNoPuedeCambiarOrientacionEnTurnoActualError();
        }

        return new EstadoVerificadorBueno();
    }

    public EstadoVerificador sePuedeCambiarModoCarta(Carta carta, Jugador solicitante)
    {

        if (!jugadorPuedeJugar(solicitante))
        {
            return new JugadorNoPermitidoParaJugar(solicitante);
        } else if (!this.maquinaTurnos.getFaseActual().esFasePreparacion())
        {
            return new NoEsFasePreparacionError();
        } else if (this.maquinaTurnos.yaColocoEnTurno(carta))
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
            return new JugadorNoPermitidoParaJugar(solicitante);
        } else if (!this.maquinaTurnos.getFaseActual().esFaseAtaque())
        {
            return new NoEsFaseAtaqueError();
        } else if (this.maquinaTurnos.esPrimerTurnoJuego())
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
        } else if (this.maquinaTurnos.yaAtacoEnTurno((CartaMonstruo) cartaAtacante))
        {
            return new CartaYaAtacoEnTurnoActualError();
        }
        return new EstadoVerificadorBueno();
    }
}