package carta;

import AlGoOh.Jugador;
import carta.excepciones.NoHayCartasParaSacrificarError;

public abstract class CartaMonstruo extends Carta implements Efecto {
    protected int puntosAtaque;
    protected int puntosDefensa;
    protected int puntos;
    protected int estrellas;
    protected Modo modo;

    public CartaMonstruo(int puntosDefensa, int puntosAtaque, Jugador jugador, Jugador oponente) {
        super(jugador, oponente);

        this.puntosDefensa = puntosDefensa;
        this.puntosAtaque = puntosAtaque;

        this.modo = new ModoDefensa();
        this.puntos = this.puntosDefensa;
    }

    public void cambiarModo() {
        this.modo.cambiarModo(this);
        if (this.enAtaque()) {
            this.puntos = this.puntosAtaque;
        } else {
            this.puntos = this.puntosDefensa;
        }
    }

    protected void establecerModo(Modo modoNuevo) {
        this.modo = modoNuevo;
    }

    //TODO : Cambiar la forma que se obtienen los modificadores
    public int getPuntos() {
        int modificador = 0;
        if (this.enAtaque()) {
            modificador = this.jugador.obtenerModificadorDePuntosDeAtaque();
            return (this.puntos + modificador);
        } else {
            modificador = this.jugador.obtenerModificadorDePuntosDeDefensa();
            return (this.puntos + modificador);
        }
    }

    public boolean enAtaque() {
        return modo instanceof ModoAtaque;
    }

    public boolean enDefensa() {
        return modo instanceof ModoDefensa;
    }

    public int getEstrellas() {
        return this.estrellas;
    }

    public void efecto() {

    }

    // TODO: hay alguna forma de no preguntar el estado de la carta del oponente, utilizando solamente mensajes, y
    // que ella haga lo que tenga que hacer dependiendo del estado en que se encuentra? Además, capaz cada carta
    // tenga una estrategia de ataque diferente, como la carta come hombres.
    public void atacarCarta(CartaMonstruo cartaOponente) {

        cartaOponente.recibirAtaque(this);

    }

    public void recibirAtaque(CartaMonstruo cartaAtacante) {

        int diferenciaDePuntos = cartaAtacante.calcularDiferencia(this);


        if (this.enAtaque()) {

            if (diferenciaDePuntos > 0) {

                this.jugador.destruirMonstruo(this);
                this.jugador.disminuirPuntosVida(Math.abs(diferenciaDePuntos));
            } else if (diferenciaDePuntos < 0) {

                this.oponente.destruirMonstruo(cartaAtacante);
                this.oponente.disminuirPuntosVida(Math.abs(diferenciaDePuntos));
            } else {

                this.jugador.destruirMonstruo(this);
                this.oponente.destruirMonstruo(cartaAtacante);
            }
        } else {
            if (diferenciaDePuntos > 0) {

                this.jugador.destruirMonstruo(this);
            } else if (diferenciaDePuntos < 0) {

                this.oponente.disminuirPuntosVida(Math.abs(diferenciaDePuntos));
            } else {
                //No pasa nada
            }
        }
    }

    private int calcularDiferencia(CartaMonstruo cartaOponente) {

        return (this.getPuntos() - cartaOponente.getPuntos());
    }

    public int obtenerPuntosDeAtaque() {

        return this.puntosAtaque;
    }

    public void invocar(Sacrificio sacrificio) {

    }

    public void invocar() {
        throw new NoHayCartasParaSacrificarError();
    }

    public void atacarOponente() {
        this.efecto();
    }
}