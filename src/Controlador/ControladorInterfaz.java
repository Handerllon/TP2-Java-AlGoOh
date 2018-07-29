package Controlador;

import Controlador.excepciones.*;
import Modelo.Jugador;
import Modelo.carta.Carta;
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

    void avanzarFase() throws SeTerminaronLasFasesError;

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

    void setCartaMonstruo(Carta carta, Jugador solicitante) throws NoSePuedeEnviarCartaMonstruoARegionError;

    void summonCartaMonstruo(Carta carta, Jugador solicitante) throws NoSePuedeEnviarCartaMonstruoARegionError;

    // Set.
    void setCartaTrampa(Carta carta, Jugador solicitante) throws NoSePuedeEnviarMyTARegionError;

    void setCartaMagica(Carta carta, Jugador solicitante) throws NoSePuedeEnviarMyTARegionError;

    // Activar.
    void activarCartaTrampa(Carta carta, Jugador solicitante) throws NoSePuedeUsarMyTError;

    void activarCartaMagica(Carta carta, Jugador solicitante) throws NoSePuedeUsarMyTError;

    void activarCartaCampo(Carta carta, Jugador solicitante) throws NoSePuedeEnviarARegionCampoError;

    // ------------------------------------
    // Métodos de orientación de cartas.
    // ------------------------------------
    void flipCarta(Carta carta, Jugador solicitante) throws NoSePuedeCambiarOrientacionError;

    void cambiarModoCartaMonstruo(Carta carta, Jugador solicitante) throws NoSePuedeCambiarOrientacionError;

    // TODO: me parece que estos no tienen sentido.
    void flipBocaAbajo(Carta carta, Jugador solicitante) throws NoSePuedeCambiarOrientacionError;

    void flipBocaArriba(Carta carta, Jugador solicitante) throws NoSePuedeCambiarOrientacionError;

    void setModoAtaque(Carta carta, Jugador solicitante) throws NoSePuedeCambiarOrientacionError;

    void setModoDefensa(Carta carta, Jugador solicitante) throws NoSePuedeCambiarOrientacionError;

    // ------------------------------------
    // Métodos de ataques.
    // ------------------------------------
    void atacar(Carta cartaAtacante, Jugador solicitante) throws NoSePuedeAtacarError;
}
