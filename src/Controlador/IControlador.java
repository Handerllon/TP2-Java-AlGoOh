package Controlador;

import Controlador.excepciones.*;
import Modelo.Jugador;
import Modelo.carta.Carta;
import Modelo.carta.CartaNula;
import Modelo.carta.excepciones.ManoLlenaError;
import Modelo.carta.monstruo.CartaMonstruo;

public interface IControlador
{
    void terminarTurno();

    void avanzarProximaFase() throws SeTerminaronLasFasesError;

    String nombreJugadorActual();

    String nombreFaseActual();

    void tomarCarta(Jugador solicitante) throws JugadorNoPermitidoParaJugar, ManoLlenaError,
            NoEsFaseInicialError;

    void activarCartaMagica(Carta carta, Jugador solicitante) throws JugadorNoPermitidoParaJugar;

    void jugarCartaTrampa(Carta carta, Jugador solicitante) throws JugadorNoPermitidoParaJugar;

    void setCartaMagica(Carta carta, Jugador solicitante) throws JugadorNoPermitidoParaJugar;

    void flipCartaMonstruo(CartaMonstruo carta, Jugador solicitante) throws JugadorNoPermitidoParaJugar,
            NoEsFasePreparacionError, CartaColocadaEnTurnoActualError;

    void flipBocaAbajo(Carta carta, Jugador solicitante) throws JugadorNoPermitidoParaJugar,
            NoEsFasePreparacionError, CartaColocadaEnTurnoActualError;

    void flipBocaArriba(Carta carta, Jugador solicitante) throws JugadorNoPermitidoParaJugar,
            NoEsFasePreparacionError, CartaColocadaEnTurnoActualError;

    void cambiarModoCartaMonstruo(CartaMonstruo carta, Jugador solicitante) throws JugadorNoPermitidoParaJugar,
            NoEsFasePreparacionError, CartaColocadaEnTurnoActualError;

    void setModoAtaque(Carta carta, Jugador solicitante) throws JugadorNoPermitidoParaJugar,
            NoEsFasePreparacionError, CartaColocadaEnTurnoActualError;

    void setModoDefensa(Carta carta, Jugador solicitante) throws JugadorNoPermitidoParaJugar,
            NoEsFasePreparacionError, CartaColocadaEnTurnoActualError;

    void atacar(CartaMonstruo cartaAtacante, CartaNula cartaNula, Jugador solicitante) throws
            NoEsUnaCartaParaAtacar, JugadorNoPermitidoParaJugar;

    void atacar(CartaMonstruo cartaAtacante, CartaMonstruo cartaAtacada, Jugador solicitante) throws
            JugadorNoPermitidoParaJugar;

    void atacar(CartaMonstruo cartaAtacante, Jugador solicitante) throws JugadorNoPermitidoParaJugar;
}
