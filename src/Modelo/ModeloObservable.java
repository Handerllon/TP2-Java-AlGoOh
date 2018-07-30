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

    void notificarEvento();

    void notificarTomaDeCartaDeMazo();

    void notificarIngresoCartaAMano();

    void notificarEgresoCartaAMano();

    void notificarCambioEnPuntosDeVida();

    // ------------------------------------
    // Métodos de consultas.
    // ------------------------------------
    ArrayList<CartaMonstruo> getCartasEnRegionMonstruosJugador();

    ArrayList<CartaMonstruo> getCartasEnRegionMonstruosOponente();

    ArrayList<Carta> getCartasEnRegionMagicasYTrampasJugador();

    ArrayList<Carta> getCartasEnRegionMagicasYTrampasOponente();

    ArrayList<CartaCampo> getCartasEnRegionCampoJugador();

    ArrayList<CartaCampo> getCartasEnRegionCampoOponente();

    int getCantidadCartasRestantesMazoJugador();

    int getCantidadCartasRestantesMazoOponente();

    ArrayList<Carta> getCartasManoJugador();

    ArrayList<Carta> getCartasManoOponente();

    Jugador getJugador();

    Jugador getOponente();
}
