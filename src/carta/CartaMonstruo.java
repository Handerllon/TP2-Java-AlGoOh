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
        if (this.enAtaque()) {
            return (this.puntos);
        } else {
            return (this.puntos);
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
    
    public void aplicarEfectosDeCampo() {
    	
    	this.jugador.aplicarEfectosDeCampo(this);
    	
    }
    
    public void quitarEfectosDeCampo(){
    	
    	this.jugador.quitarEfectosDeCampo(this);
    }

    // TODO: hay alguna forma de no preguntar el estado de la carta del oponente, utilizando solamente mensajes, y
    // que ella haga lo que tenga que hacer dependiendo del estado en que se encuentra? Además, capaz cada carta
    // tenga una estrategia de ataque diferente, como la carta come hombres.
    // TODO: Ver de nuevo funcionamiento de cartas trampa
    public void atacarCarta(CartaMonstruo cartaOponente) {
    	
    	CartaTrampa cartaTrampa = this.oponente.obtenerCartaTrampaAActivar();
    	
    	if (cartaTrampa == null){
    		
    		cartaOponente.recibirAtaque(this);
    	}
    	
    	else if (!cartaTrampa.trampaCancelaAtaqueAMonstruo()){
    		cartaTrampa.efecto(this, cartaOponente);
    		cartaOponente.recibirAtaque(this);
    	}
    	else{
    		cartaTrampa.efecto(this, cartaOponente);
    	}
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

    public void reinforcements() {
    	
    	this.puntosAtaque = this.puntosAtaque + 500;
    	if (this.enAtaque()){
    		this.puntos = this.puntosAtaque;
    	}
    	
    }
    
    public void atacarOponente() {
    	
    	CartaTrampa cartaTrampa = this.oponente.obtenerCartaTrampaAActivar();
    	
    	if (cartaTrampa == null){
    		
    		this.oponente.disminuirPuntosVida(this.obtenerPuntosDeAtaque());
    	}
    	else{
    		cartaTrampa.efecto(this, null);
    	}
    }
    
    public void wasteland(int modificadorAtaque, int modificadorDefensa, Jugador jugador) {
    	
    	if (this.jugador == jugador){
    		this.puntosAtaque = this.puntosAtaque + modificadorAtaque;
    		if(this.enAtaque())
    			this.puntos = puntosAtaque;
    	}
    	else{
    		this.puntosDefensa = this.puntosDefensa + modificadorDefensa;
    		if(this.enDefensa())
    			this.puntos = puntosDefensa;
    	}
    }
    
    public void quitarWasteland(int modificadorAtaque, int modificadorDefensa, Jugador jugador) {
    	
    	if (this.jugador == jugador){
    		this.puntosAtaque = this.puntosAtaque - modificadorAtaque;
    		if(this.enAtaque())
    			this.puntos = puntosAtaque;
    	}
    	else{
    		this.puntosDefensa = this.puntosDefensa - modificadorDefensa;
    		if(this.enDefensa())
    			this.puntos = puntosDefensa;
    	}
    	
    }
    
    

    public void sogen(int modificadorAtaque, int modificadorDefensa, Jugador jugador) {
    	
    	if (this.jugador == jugador){
    		this.puntosDefensa = this.puntosDefensa + modificadorDefensa;
    		if(this.enDefensa())
    			this.puntos = puntosDefensa;
    	}
    	else{
    		this.puntosAtaque = this.puntosAtaque + modificadorAtaque;
    		if(this.enAtaque())
    			this.puntos = puntosAtaque;
    	}
    }
    
    public void quitarSogen(int modificadorAtaque, int modificadorDefensa, Jugador jugador) {
    	
    	if (this.jugador == jugador){
    		this.puntosDefensa = this.puntosDefensa - modificadorDefensa;
    		if(this.enDefensa())
    			this.puntos = puntosDefensa;
    	}
    	else{
    		this.puntosAtaque = this.puntosAtaque - modificadorAtaque;
    		if(this.enAtaque())
    			this.puntos = puntosAtaque;
    	}
    }

}