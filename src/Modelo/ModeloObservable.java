package Modelo;

import Modelo.carta.Carta;
import Modelo.carta.campo.CartaCampo;
import Modelo.carta.monstruo.CartaMonstruo;

import java.util.ArrayList;

public interface ModeloObservable
{
    void agregarObsevador(ObservadorDeModelo observador);

    void quitarObservador(ObservadorDeModelo observador);

    void notificarObservadores();

    ArrayList<CartaMonstruo> obtenerCartasEnRegionMonstruosJugador();

    ArrayList<CartaMonstruo> obtenerCartasEnRegionMonstruosOponente();

    ArrayList<Carta> obtenerCartasEnRegionMagicasYTrampasJugador();

    ArrayList<Carta> obtenerCartasEnRegionMagicasYTrampasOponente();

    CartaCampo obtenerCartasEnRegionCampoJugador();

    CartaCampo obtenerCartasEnRegionCampoOponente();

    int obtenerNumeroDeCartasRestantesEnMazoJugador();

    int obtenerNumeroDeCartasRestantesEnMazoOponente();

    ArrayList<Carta> obtenerCartasEnLaManoDelJugador();

    ArrayList<Carta> obtenerCartasEnLaManoDelOponente();
}
