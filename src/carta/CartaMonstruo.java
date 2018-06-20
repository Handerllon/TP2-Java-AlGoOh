package carta;

import AlGoOh.Jugador;

public abstract class CartaMonstruo extends Carta
{
    protected int puntosAtaque;
    protected int puntosDefensa;
    protected int puntos;
    protected int estrellas;

    protected Modo modo;

    public CartaMonstruo(int puntosDefensa, int puntosAtaque){
        super();

        this.puntosDefensa = puntosDefensa;
        this.puntosAtaque = puntosAtaque;

        this.modo = new ModoDefensa();
        this.puntos = this.puntosDefensa;
    }

    public void cambiarModo()
    {
        this.modo.cambiarModo(this);
        if (this.enAtaque())
        {
            this.puntos = this.puntosAtaque;
        } else
        {
            this.puntos = this.puntosDefensa;
        }
    }

    protected void establecerModo(Modo modoNuevo)
    {
        this.modo = modoNuevo;
    }

    public int getPuntos()
    {
        return this.puntos;
    }

    public boolean enAtaque()
    {
        return modo instanceof ModoAtaque;
    }

    public boolean enDefensa()
    {
        return modo instanceof ModoDefensa;
    }

    public int getEstrellas()
    {
        return this.estrellas;
    }

	public void atacarA(Jugador jugador, Jugador oponente, CartaMonstruo cartaOponente) {
		
		int diferenciaDePuntos = this.calcularDiferencia(cartaOponente);
		
		if(cartaOponente.enAtaque()){
			
			if (diferenciaDePuntos > 0){
				
				oponente.destruirMonstruo(cartaOponente);
				oponente.disminuirPuntosVida(Math.abs(diferenciaDePuntos));
			}
			else if (diferenciaDePuntos < 0){
				
				jugador.destruirMonstruo(this);
				jugador.disminuirPuntosVida(Math.abs(diferenciaDePuntos));
			}
			else{
				
				oponente.destruirMonstruo(cartaOponente);
				jugador.destruirMonstruo(this);
			}
		}
		else{
			if (diferenciaDePuntos > 0){
				
				oponente.destruirMonstruo(cartaOponente);
			}
			else if (diferenciaDePuntos < 0){
				
				jugador.disminuirPuntosVida(Math.abs(diferenciaDePuntos));
			}
			else{
				//No pasa nada
			}
		}
	}

	private int calcularDiferencia(CartaMonstruo cartaOponente) {
		
		return (this.getPuntos() - cartaOponente.getPuntos());
	}
}