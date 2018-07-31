package Modelo;

import Modelo.carta.Carta;
import Modelo.carta.campo.CartaCampo;
import Modelo.carta.monstruo.CartaMonstruo;
import Modelo.observadores.ObservadorDeModelo;

import java.util.ArrayList;

public interface ModeloObservable
{
    void registrarObsevador(ObservadorDeModelo observador);

    void eliminarObservador(ObservadorDeModelo observador);

    void notificarTomaDeCartaDeMazo();

    void notificarIngresoCartaAMano();

    void notificarEgresoCartaAMano();

    void notificarIngresoCartaARegion();

    void notificarEgresoCartaARegion();

    void notificarCambioEnPuntosDeVida();

    void notificarCambioDeOrientacionCarta();

    void notificarCambioDeModoCarta();

    // ------------------------------------
    // Métodos de consultas.
    // ------------------------------------
    ArrayList<CartaMonstruo> getCartasEnRegionMonstruosDe(Jugador jugador);

    ArrayList<Carta> getCartasEnRegionMagicasYTrampasDe(Jugador jugador);

    ArrayList<CartaCampo> getCartasEnRegionCampoDe(Jugador jugador);

    int getCantidadCartasRestantesMazoDe(Jugador jugador);

    Jugador getJugador();

    Jugador getOponente();
}
