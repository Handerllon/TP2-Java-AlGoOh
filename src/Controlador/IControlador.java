package Controlador;

import Controlador.excepciones.*;
import Modelo.Jugador;
import Modelo.carta.Carta;
import Modelo.finDeJuego.CausaFinJuego;

public interface IControlador
{
    void jugar();

    void setNombreJugador(String text);

    void setNombreOponente(String text);

    CausaFinJuego getCausaFinDeJuego();

    // ------------------------------------
    // Métodos de fases y turnos.
    // ------------------------------------
    void terminarTurno();

    void avanzarProximaFase() throws SeTerminaronLasFasesError;

    String getNombreJugadorActual();

    String getNombreFaseActual();

    // ------------------------------------
    // Métodos de terminación.
    // ------------------------------------
    void cerrarPrograma();

    void confirmarSalirPrograma();

    // ------------------------------------
    // Métodos de juego de cartas.
    // ------------------------------------
    void tomarCarta(Jugador solicitante) throws NoSePuedeTomarCartaError;

    void setCartaMonstruo(Carta carta, Jugador solicitante) throws NoSePuedeMandarCartaARegionError;

    void summonCartaMonstruo(Carta carta, Jugador solicitante) throws NoSePuedeMandarCartaARegionError;

    void setCartaTrampa(Carta carta, Jugador solicitante) throws NoSePuedeEnviarARegionMyT;

    void setCartaMagica(Carta carta, Jugador solicitante) throws NoSePuedeEnviarARegionMyT;

    void activarCartaMagica(Carta carta, Jugador solicitante) throws NoSePuedeEnviarARegionMyT;

    // ------------------------------------
    // Métodos de orientación de cartas.
    // ------------------------------------
    void flipCarta(Carta carta, Jugador solicitante) throws NoSePuedeCambiarOrientacionError;

    void flipBocaAbajo(Carta carta, Jugador solicitante) throws NoSePuedeCambiarOrientacionError;

    void flipBocaArriba(Carta carta, Jugador solicitante) throws NoSePuedeCambiarOrientacionError;

    void setModoAtaque(Carta carta, Jugador solicitante) throws NoSePuedeCambiarOrientacionError;

    void setModoDefensa(Carta carta, Jugador solicitante) throws NoSePuedeCambiarOrientacionError;

    void cambiarModoCartaMonstruo(Carta carta, Jugador solicitante) throws NoSePuedeCambiarOrientacionError;

    // ------------------------------------
    // Métodos de ataques.
    // ------------------------------------
    void atacar(Carta cartaAtacante, Jugador solicitante) throws NoSePuedeAtacarError;
}
