package areaDeJuego;

import carta.CartaMonstruo;

import java.util.ArrayList;

public class RegionMonstruos extends Region<CartaMonstruo> {
    private int modificadorAtaque;
    private int modificadorDefensa;

    public RegionMonstruos(int capacidadMaxima) {
        super(capacidadMaxima);
        this.modificadorAtaque = 0;
        this.modificadorDefensa = 0;
    }

    public void modificarAtaqueMonstruosCon(int modificadorAtaque) {
        this.modificadorAtaque = this.modificadorAtaque + modificadorAtaque;
    }

    public void modificarDefensaMonstruosCon(int modificadorDefensa) {
        this.modificadorDefensa = this.modificadorDefensa + modificadorDefensa;
    }

    public int obtenerModificadorDePuntosDeAtaque() {
        return this.modificadorAtaque;
    }

    public int obtenerModificadorDePuntosDeDefensa() {
        return this.modificadorDefensa;
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
}

