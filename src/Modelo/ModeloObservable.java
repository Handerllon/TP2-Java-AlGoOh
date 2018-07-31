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

    ArrayList<CartaMonstruo> getCartasEnRegionMonstruosJugador();

    ArrayList<CartaMonstruo> getCartasEnRegionMonstruosOponente();

    ArrayList<Carta> getCartasEnRegionMagicasYTrampasDe(Jugador jugador);

    ArrayList<Carta> getCartasEnRegionMagicasYTrampasJugador();

    ArrayList<Carta> getCartasEnRegionMagicasYTrampasOponente();

    ArrayList<CartaCampo> getCartasEnRegionCampoDe(Jugador jugador);

    ArrayList<CartaCampo> getCartasEnRegionCampoJugador();

    ArrayList<CartaCampo> getCartasEnRegionCampoOponente();

    int getCantidadCartasRestantesMazoDe(Jugador jugador);

    int getCantidadCartasRestantesMazoJugador();

    int getCantidadCartasRestantesMazoOponente();

    ArrayList<Carta> getCartasEnManoDe(Jugador jugador);

    Jugador getJugador();

    Jugador getOponente();
}
