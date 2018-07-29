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

    void avanzarFase() throws SeTerminaronLasFases;

    String getNombreJugadorActual();
    
    String getNombreFaseActual();

    Jugador getJugadorActual();

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

    // Set.
    void setCartaTrampa(Jugador solicitante, Carta carta) throws NoSePuedeEnviarMyTARegionError;

    void setCartaMagica(Jugador solicitante, Carta carta) throws NoSePuedeEnviarMyTARegionError;

    // Activar.
    void activarCartaTrampa(Jugador solicitante, Carta carta) throws NoSePuedeUsarMyTError;

    void activarCartaMagica(Jugador solicitante, Carta carta) throws NoSePuedeUsarMyTError;

    void activarCartaCampo(Jugador solicitante, Carta carta) throws NoSePuedeEnviarARegionCampoError;

    // ------------------------------------
    // Métodos de orientación de cartas.
    // ------------------------------------
    void flipCarta(Jugador solicitante, Carta carta) throws NoSePuedeCambiarOrientacionError;

    void cambiarModoCartaMonstruo(Jugador solicitante, Carta carta) throws NoSePuedeCambiarOrientacionError;

    // TODO: me parece que estos no tienen sentido.
    void flipBocaAbajo(Jugador solicitante, Carta carta) throws NoSePuedeCambiarOrientacionError;

    void flipBocaArriba(Jugador solicitante, Carta carta) throws NoSePuedeCambiarOrientacionError;

    void setModoAtaque(Jugador solicitante, Carta carta) throws NoSePuedeCambiarOrientacionError;

    void setModoDefensa(Jugador solicitante, Carta carta) throws NoSePuedeCambiarOrientacionError;

    // ------------------------------------
    // Métodos de ataques.
    // ------------------------------------
    void atacar(Jugador solicitante, Carta cartaAtacante) throws NoSePuedeAtacarError;

}
