package areaDeJuego;

import carta.Carta;
import carta.CartaCampo;
import carta.CartaMagica;
import carta.CartaMonstruo;

import java.util.ArrayList;

public class AreaDeCartas {
    private static int CAPACIDAD_REGION_MONSTRUOS = 5;
    private static int CAPACIDAD_REGION_MAGICAS = 5;
    private static int CAPACIDAD_REGION_CAMPO = 1;
    private static int CAPACIDAD_REGION_CEMENTERIO = 40;
    private RegionMonstruos regionMonstruos;
    private Region regionMagicasYTrampas;
    private Region regionCampo;
    private Region cementerio;

    public AreaDeCartas() {
        this.regionMonstruos = new RegionMonstruos(CAPACIDAD_REGION_MONSTRUOS);
        this.regionMagicasYTrampas = new Region<>(CAPACIDAD_REGION_MAGICAS);
        this.regionCampo = new Region<CartaCampo>(CAPACIDAD_REGION_CAMPO);
        this.cementerio = new Region<>(CAPACIDAD_REGION_CEMENTERIO);
    }

    // --------------------------------------------------------------------
    // Métodos de agregación de cartas.
    // --------------------------------------------------------------------

    public void colocarCarta(CartaMonstruo cartaMonstruo) {
        this.regionMonstruos.colocarCarta(cartaMonstruo);
    }

    public void colocarCarta(CartaMagica cartaMagica) {
        this.regionMagicasYTrampas.colocarCarta(cartaMagica);
    }

    public void colocarCarta(CartaCampo cartaCampo) {
        this.regionCampo.colocarCarta(cartaCampo);
    }

    public void removerCarta(CartaMonstruo cartaMonstruo) {
        this.regionMonstruos.removerCarta(cartaMonstruo);
    }

    // --------------------------------------------------------------------
    // Métodos de movimiento de cartas.
    // --------------------------------------------------------------------

    public void enviarAlCementerio(Carta carta) {
        this.cementerio.colocarCarta(carta);
    }

    public void enviarMonstruosAlCementerio() {
        ArrayList<CartaMonstruo> cartas = this.regionMonstruos.obtenerCartas();

        cartas.forEach(item -> this.enviarAlCementerio(item));

        this.regionMonstruos.removerTodasLasCartas();
    }

    // --------------------------------------------------------------------
    // Métodos de consulta.
    // --------------------------------------------------------------------

    public boolean cartaEstaEnCementerio(Carta carta) {
        return this.cementerio.contieneCarta(carta);
    }

    public boolean cartaEstaEnRegionMonstruos(CartaMonstruo carta) {
        return this.regionMonstruos.contieneCarta(carta);
    }

    public void modificarAtaqueMonstruosCon(int modificadorAtaque) {

        this.regionMonstruos.modificarAtaqueMonstruosCon(modificadorAtaque);
    }

    public void modificarDefensaMonstruosCon(int modificadorDefensa) {

        this.regionMonstruos.modificarDefensaMonstruosCon(modificadorDefensa);
    }

    public int obtenerModificadorDePuntosDeAtaque() {

        return this.regionMonstruos.obtenerModificadorDePuntosDeAtaque();
    }

    public int obtenerModificadorDePuntosDeDefensa() {

        return this.regionMonstruos.obtenerModificadorDePuntosDeDefensa();
    }

    public CartaMonstruo obtenerMonstruoConMenorAtaque() {

        return this.regionMonstruos.obtenerMonstruoConMenorAtaque();
    }
}
