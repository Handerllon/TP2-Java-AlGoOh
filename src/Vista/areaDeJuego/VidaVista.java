package Vista.areaDeJuego;


import Modelo.ObservadorDeModelo;
import Vista.Vista;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class VidaVista implements ObservadorDeModelo {

	private Vista vista;
	private Label vidaJugador;
	private Label vidaOponente;
	
	public VidaVista(Vista vista){
		
		this.vista = vista;
	
		this.vidaJugador = new Label();
		this.vidaOponente = new Label();
		
		this.inicializar();
		
	}
	
	private void inicializar() {
		
		this.vidaJugador.setPrefSize((this.vista.obtenerResolucionHorizontal()*200)/1920, (this.vista.obtenerResolucionVertical()*200)/1080);
		this.vidaOponente.setPrefSize((this.vista.obtenerResolucionHorizontal()*200)/1920, (this.vista.obtenerResolucionVertical()*200)/1080);
		
		this.vidaJugador.setText("    Vida: \n"
				+ "    8000");
		
		this.vidaOponente.setText("    Vida: \n"
				+ "    8000");
		
		this.vidaJugador.setFont(new Font("Times New Roman", 50));
		this.vidaOponente.setFont(new Font("Times New Roman", 50));
		
		this.vidaJugador.setTextFill(Color.web("#910101"));		
		this.vidaOponente.setTextFill(Color.web("#910101"));
		
	}

	public Label obtenerVidaJugador(){
		
		return this.vidaJugador;
	}
	
	public Label obtenerVidaOponente(){
		
		return this.vidaOponente;
	}

	@Override
	public void actualizarEstado() {
		
		int nuevaVidaJugador = this.vista.obtenerModelo().obtenerJugador().getPuntosDeVida();
		int nuevaVidaOponente = this.vista.obtenerModelo().obtenerOponente().getPuntosDeVida();
		
		this.vidaJugador.setText("    Vida: \n"
				+ "    " + Integer.toString(nuevaVidaJugador));
		
		this.vidaOponente.setText("    Vida: \n"
				+ "    " + Integer.toString(nuevaVidaOponente));
	}

}
