package carta.excepciones;

import carta.Carta;

public class CartaNoExisteEnRegion extends RuntimeException {
    Carta carta;

    public CartaNoExisteEnRegion(Carta carta) {
        this.carta = carta;
    }
}
