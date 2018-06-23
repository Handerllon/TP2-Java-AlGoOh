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
        this.mazo = new Mazo(this, this.oponente);

        int cartasATomarInicialmente = 5;
        this.cartasEnMano = new Mano();

        int puntosDeVidaIniciales = 8000;
        this.puntosDeVida = puntosDeVidaIniciales;

        this.areaDeCartas = new AreaDeCartas(this);
        
        this.popularMano(cartasATomarInicialmente);
        
        
    }

    // --------------------------------------------------------------------
    // Métodos de inicialización.
    // --------------------------------------------------------------------

    public void establecerOponente(Jugador oponente)
    {

        this.oponente = oponente;
    }

    private void popularMano(int cartasATomarInicialmente){
    	
       for (int i = 0; i < cartasATomarInicialmente; i++)
       {
           this.cartasEnMano.agregarCarta(mazo.tomarCarta());
       }
       
    }

    // --------------------------------------------------------------------
    // Métodos de agregado de cartas.
    // --------------------------------------------------------------------

    public void agregarCarta(CartaMonstruo cartaMonstruo)
    {

        this.areaDeCartas.agregarCarta(cartaMonstruo);
        this.cartasEnMano.jugarCarta(cartaMonstruo.obtenerNombre());
        cartaMonstruo.efecto();
    }

    public void agregarCarta(CartaMonstruo cartaMonstruo, Sacrificio sacrificio)
    {

        this.areaDeCartas.agregarCarta(cartaMonstruo, sacrificio);
        this.cartasEnMano.jugarCarta(cartaMonstruo.obtenerNombre());
        cartaMonstruo.efecto();
    }

    public void agregarCarta(CartaMagica cartaMagica)
    {

        this.areaDeCartas.agregarCarta(cartaMagica);
        this.cartasEnMano.jugarCarta(cartaMagica.obtenerNombre());
        cartaMagica.efecto();
    }

    public void agregarCarta(CartaCampo cartaCampo)
    {

        this.areaDeCartas.agregarCarta(cartaCampo);
        this.cartasEnMano.jugarCarta(cartaCampo.obtenerNombre());
        cartaCampo.efecto();
    }

    // --------------------------------------------------------------------
    // Métodos de acciones.
    // --------------------------------------------------------------------

    public void atacar(CartaMonstruo cartaAtacante, CartaMonstruo cartaOponente)
    {
        cartaAtacante.atacarA(cartaOponente);
    }

    public void destruirMonstruo(CartaMonstruo carta)
    {


        this.areaDeCartas.removerCarta(carta);
        this.areaDeCartas.enviarAlCementerio(carta);

    }

    public void destruirMonstruos()
    {

        this.areaDeCartas.enviarMonstruosAlCementerio();
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
    
    public void wasteland(Jugador jugador, int modificadorAtaque, int modificadorDefensa) {
    	
    	if (this == jugador){
    		this.areaDeCartas.modificarAtaqueMonstruosCon(modificadorAtaque);
    	}
    	else{
    		this.areaDeCartas.modificarDefensaMonstruosCon(modificadorDefensa);
    	}
    	
    }
    public void sogen(Jugador jugador, int modificadorAtaque, int modificadorDefensa) {
    	
    	if (this == jugador){
    		this.areaDeCartas.modificarDefensaMonstruosCon(modificadorDefensa);
    	}
    	else{
    		this.areaDeCartas.modificarAtaqueMonstruosCon(modificadorAtaque);
    	}
    }
    
    
    public void ollaDeLaCodicia() {
    	
    	this.cartasEnMano.agregarCarta(mazo.tomarCarta());
    	this.cartasEnMano.agregarCarta(mazo.tomarCarta());
    }
    
    // --------------------------------------------------------------------
    // Métodos de consultas.
    // --------------------------------------------------------------------

    public boolean cartaEstaEnCementerio(CartaMonstruo carta)
    {
        return areaDeCartas.cartaEstaEnCementerio(carta);
    }

    public boolean cartaEstaEnRegionMonstruos(CartaMonstruo carta)
    {
        return areaDeCartas.cartaEstaEnRegionMonstruos(carta);
    }
    
    public int cantidadDeCartasEnMano() {
    	
    	return this.cartasEnMano.cantidadDeCartas();
    }
    

	public int obtenerModificadorDePuntosDeAtaque() {
		
		return this.areaDeCartas.obtenerModificadorDePuntosDeAtaque();
	}
	
	public int obtenerModificadorDePuntosDeDefensa() {
		
		return this.areaDeCartas.obtenerModificadorDePuntosDeDefensa();
	}

}

