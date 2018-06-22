package AlGoOh;

import AlGoOh.excepciones.JugadorSinVida;
import areaDeJuego.AreaDeCartas;
import carta.*;

public class Jugador
{

    private String nombre;
    private Mazo mazo;
    private Mano cartasEnMano;
    private int puntosDeVida;
    private AreaDeCartas areaDeCartas;
	private Jugador oponente;

    public Jugador(String unNombre)
    {

        this.nombre = unNombre;

        int cantidadCartasInicialesMazo = 40;
        this.mazo = new Mazo(cantidadCartasInicialesMazo);

        int cartasATomarInicialmente = 5;
        this.cartasEnMano = new Mano(cartasATomarInicialmente);

        int puntosDeVidaIniciales = 8000;
        this.puntosDeVida = puntosDeVidaIniciales;

        this.areaDeCartas = new AreaDeCartas(this);


        // TODO: Terminar de implementar bien la generación del mazo.
//        for (int i = 0; i < cartasATomarInicialmente; i++)
//        {
//            this.cartasEnMano.agregarCarta(mazo.agarrarCarta());
//        }

    }
    public int getPuntosDeVida()
    {
        return this.puntosDeVida;
    }

    public void disminuirPuntosVida(int puntosARestar)
    {
        this.puntosDeVida -= puntosARestar;
        if (this.puntosDeVida <= 0)
            // TODO: Esto puede ser un trigger para terminar el juego.
            throw new JugadorSinVida();
    }

    public AreaDeCartas getAreaDeCartas()
    {
        return this.areaDeCartas;
    }

    public void agregarCarta(CartaMonstruo carta) {

        this.areaDeCartas.agregarCarta(carta);
    }

    public void agregarCarta(CartaMonstruo carta, Sacrificio sacrificio) {
    	
    	this.areaDeCartas.agregarCarta(carta, sacrificio);
    }
    
    public void agregarCarta(CartaMagica carta) {
    	
    	this.areaDeCartas.agregarCarta(carta);
    	carta.efecto( this, this.oponente);
    }
    
    public void agregarCarta(CartaCampo cartaDeCampo) {
    	
    	this.areaDeCartas.agregarCarta(cartaDeCampo);
    	cartaDeCampo.efecto(this, this.oponente);
    }

    public void atacar(CartaMonstruo cartaAtacante, CartaMonstruo cartaOponente)
    {
        //tablero.atacarOponente(cartaAtacante, cartaOponente);
        
        cartaAtacante.atacarA(this, this.oponente, cartaOponente);
    }
    
    public void establecerOponente(Jugador oponente){
    	
    	this.oponente = oponente;
    }
    
    public boolean cartaEstaEnCementerio(CartaMonstruo carta)
    {
        return areaDeCartas.cartaEstaEnCementerio(carta);
    }

    public boolean cartaEstaEnRegionMonstruos(CartaMonstruo carta)
    {
        return areaDeCartas.cartaEstaEnRegionMonstruos(carta);
    }

	public void destruirMonstruo(CartaMonstruo carta) {
		
		this.areaDeCartas.removerCarta(carta);
		this.areaDeCartas.enviarAlCementerio(carta);
	}
	
	public void destruirMonstruos(){
		
		this.areaDeCartas.enviarMonstruosAlCementerio();
	}
}

