package Controlador;

import Controlador.excepciones.*;
import Modelo.Jugador;
import Modelo.carta.Carta;
import Modelo.carta.excepciones.ManoLlenaError;
import Modelo.carta.excepciones.NoEsUnaCartaMonstruo;
import Modelo.carta.monstruo.CartaMonstruo;
import Modelo.finDeJuego.CausaFinJuego;

public interface IControlador
{
    void terminarTurno();

    void avanzarProximaFase() throws SeTerminaronLasFasesError;

    String nombreJugadorActual();

    String nombreFaseActual();

    void cerrarPrograma();

    CausaFinJuego obtenerCausaFinDeJuego();

    void confirmarSalirPrograma();

    void jugar();

    void establecerNombreJugador(String text);

    void establecerNombreOponente(String text);

    // ------------------------------------
    // Métodos de juego de cartas.
    // ------------------------------------
    void tomarCarta(Jugador solicitante) throws JugadorNoPermitidoParaJugar, ManoLlenaError,
            NoEsFaseInicialError;

    void activarCartaMagica(Carta carta, Jugador solicitante) throws JugadorNoPermitidoParaJugar,
            SolicitanteNoEsPropietarioDeCartaError, NoEsFaseFinalError;

    void setCartaTrampa(Carta carta, Jugador solicitante) throws JugadorNoPermitidoParaJugar,
            SolicitanteNoEsPropietarioDeCartaError, NoEsFasePreparacionError;

    void setCartaMagica(Carta carta, Jugador solicitante) throws JugadorNoPermitidoParaJugar,
            SolicitanteNoEsPropietarioDeCartaError, NoEsFasePreparacionError;

    void setCartaMonstruo(Carta carta, Jugador solicitante) throws JugadorNoPermitidoParaJugar,
            SolicitanteNoEsPropietarioDeCartaError, NoEsFasePreparacionError, NoHaySuficientesSacrificiosError,
            NoEsUnaCartaMonstruo, YaSeMandoMonstruoARegionEnTurno;

    void summonCartaMonstruo(Carta carta, Jugador solicitante) throws JugadorNoPermitidoParaJugar,
            SolicitanteNoEsPropietarioDeCartaError, NoEsFasePreparacionError, NoHaySuficientesSacrificiosError,
            NoEsUnaCartaMonstruo, YaSeMandoMonstruoARegionEnTurno, CartaColocadaEnTurnoActualError;

    // ------------------------------------
    // Métodos de orientación de cartas.
    // ------------------------------------
    void flipCartaMonstruo(CartaMonstruo carta, Jugador solicitante) throws JugadorNoPermitidoParaJugar,
            NoEsFasePreparacionError, CartaColocadaEnTurnoActualError;

    void flipBocaAbajo(Carta carta, Jugador solicitante) throws JugadorNoPermitidoParaJugar,
            NoEsFasePreparacionError, CartaColocadaEnTurnoActualError;

    void flipBocaArriba(Carta carta, Jugador solicitante) throws JugadorNoPermitidoParaJugar,
            NoEsFasePreparacionError, CartaColocadaEnTurnoActualError;

    void setModoAtaque(Carta carta, Jugador solicitante) throws JugadorNoPermitidoParaJugar,
            NoEsFasePreparacionError, CartaColocadaEnTurnoActualError;

    void setModoDefensa(Carta carta, Jugador solicitante) throws JugadorNoPermitidoParaJugar,
            NoEsFasePreparacionError, CartaColocadaEnTurnoActualError;

    void cambiarModoCartaMonstruo(CartaMonstruo carta, Jugador solicitante) throws JugadorNoPermitidoParaJugar,
            NoEsFasePreparacionError, CartaColocadaEnTurnoActualError;

    // ------------------------------------
    // Métodos de ataques.
    // ------------------------------------
    void atacar(CartaMonstruo cartaAtacante, CartaMonstruo cartaAtacada, Jugador solicitante) throws
            JugadorNoPermitidoParaJugar, NoEsFaseAtaqueError, NoSeAtacaEnPrimerTurnoJuegoError, CartaYaAtacoError;

    void atacar(CartaMonstruo cartaAtacante, Jugador solicitante) throws JugadorNoPermitidoParaJugar,
            NoEsFaseAtaqueError, NoSeAtacaEnPrimerTurnoJuegoError, CartaYaAtacoError;
}
