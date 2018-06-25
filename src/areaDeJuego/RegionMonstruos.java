package areaDeJuego;

import carta.CartaMonstruo;

import java.util.ArrayList;

import AlGoOh.Jugador;

public class RegionMonstruos extends Region<CartaMonstruo> {

    public RegionMonstruos(int capacidadMaxima) {
        super(capacidadMaxima);
    }
    
    public CartaMonstruo obtenerMonstruoConMenorAtaque() {
        CartaMonstruo cartaConAtaqueMinimo = null;

        int ataqueTope = Integer.MAX_VALUE;

        ArrayList<CartaMonstruo> cartas = this.obtenerCartas();

        for (int i = 0; i < cartas.size(); i++) {
            if (cartas.get(i).obtenerPuntosDeAtaque() < ataqueTope) {
                ataqueTope = cartas.get(i).obtenerPuntosDeAtaque();
                cartaConAtaqueMinimo = cartas.get(i);
            }
        }

        return cartaConAtaqueMinimo;
    }
    
    public void wasteland(int modificadorAtaque, int modificadorDefensa, Jugador jugador){
    	
    	for (int i=0 ; i<this.cartas.size(); i++){
    		this.cartas.get(i).wasteland(modificadorAtaque, modificadorDefensa, jugador);
    	}
    }
    
    public void sogen(int modificadorAtaque, int modificadorDefensa, Jugador jugador){
    	
    	for (int i=0 ; i<this.cartas.size(); i++){
    		this.cartas.get(i).sogen(modificadorAtaque, modificadorDefensa, jugador);
    	}
    }
}

