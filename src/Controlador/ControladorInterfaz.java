package Controlador;

import Controlador.excepciones.*;
import Controlador.observadores.ObservadorDeControlador;
import Modelo.Jugador;
import Modelo.carta.Carta;
import Modelo.carta.monstruo.CartaMonstruo;
import Modelo.finDeJuego.CausaFinJuego;

public interface ControladorInterfaz
{
    void jugar();

    void setNombreJugador(String text);

    void setNombreOponente(String text);

    CausaFinJuego getCausaFinDeJuego();

    // ------------------------------------
    // Métodos de fases y turnos.
    // ------------------------------------
    void terminarTurno();

    void avanzarFase() throws SeTerminaronLasFases;

    String getNombreJugadorActual();

    String getNombreFaseActual();

    Jugador getJugadorActual();

    void registrarObsevador(ObservadorDeControlador observadorDeControlador);

    // ------------------------------------
    // Métodos de terminación.
    // ------------------------------------
    void cerrarPrograma();

    void confirmarSalirPrograma();

    // ------------------------------------
    // Métodos de juego de cartas.
    // ------------------------------------
    void tomarCarta(Jugador solicitante) throws NoSePuedeTomarCartaError;

    void setCartaMonstruo(Jugador solicitante, Carta carta) throws NoSePuedeEnviarCartaMonstruoARegionError;

    void summonCartaMonstruo(Jugador solicitante, Carta carta) throws NoSePuedeEnviarCartaMonstruoARegionError;

    // -------------
    // Set.
    // -------------
    void setCartaTrampa(Jugador solicitante, Carta carta) throws NoSePuedeEnviarMyTARegionError;

    void setCartaMagica(Jugador solicitante, Carta carta) throws NoSePuedeEnviarMyTARegionError;

    // -------------
    // Activar.
    // -------------
    void activarCartaTrampa(Jugador solicitante, Carta carta) throws NoSePuedeUsarMyTError;

    void activarCartaMagicaDesdeMano(Jugador solicitante, Carta carta) throws NoSePuedeUsarMyTError;

    void activarCartaMagicaDesdeRegionMyT(Jugador solicitante, Carta carta) throws NoSePuedeUsarMyTError;

    void activarCartaCampoDesdeMano(Jugador solicitante, Carta carta) throws NoSePuedeEnviarARegionCampoError;

    // ------------------------------------
    // Métodos de orientación de cartas.
    // ------------------------------------
    void flipCarta(Jugador solicitante, Carta carta) throws NoSePuedeCambiarOrientacionError;

    void cambiarModoCartaMonstruo(Jugador solicitante, Carta carta) throws NoSePuedeCambiarOrientacionError;

    // ------------------------------------
    // Métodos de ataques.
    // ------------------------------------
    void atacar(Jugador solicitante, Carta cartaAtacante) throws NoSePuedeAtacarError;

    void atacarCarta(Jugador solicitante, CartaMonstruo cartaAtacante, CartaMonstruo cartaSolicitada);
}
