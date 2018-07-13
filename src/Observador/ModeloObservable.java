package Observador;

import java.util.ArrayList;

import Modelo.carta.Carta;
import Modelo.carta.CartaCampo;
import Modelo.carta.CartaMonstruo;

public interface ModeloObservable
{
    void agregarObsevador(ObjetoObservador observador);

    void quitarObservador(ObjetoObservador observador);

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
