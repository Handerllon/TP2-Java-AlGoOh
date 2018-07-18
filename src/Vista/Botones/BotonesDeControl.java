package Vista.Botones;

import Modelo.Modelo;
import Vista.Vista;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class BotonesDeControl extends Button{
	
	private Button botonFinDeTurno;
	private Button botonFinDeFase;
	private Stage stage;
	private Vista vista;
	
	public BotonesDeControl(Stage stage, Vista vista){
		
		this.stage = stage;
		this.vista = vista;
		this.botonFinDeFase = this.crearBotonFinDeFase();
		this.botonFinDeTurno = this.crearBotonFinDeTurno();
		
	}
	
	private Button crearBotonFinDeTurno() {
		Button boton = new Button("FIN DE TURNO");
		
		
		boton.setOnAction(e -> finDeTurnoBtn_click());
		
		return boton;
	}

	private Button crearBotonFinDeFase() {
		Button boton = new Button("FIN DE FASE");
		
		boton.setOnAction(e -> finDeFaseBtn_click());
		
		return boton;
	}
	
	private void finDeTurnoBtn_click() {
		
		this.vista.obtenerControlador().finDeTurno();
		
	}

	private void finDeFaseBtn_click() {
		
		this.vista.obtenerControlador().finDeFase();
	}
	
	public Button getBotonFinDeTurno(){
		
		return this.botonFinDeTurno;
	}
	
	public Button getBotonFinDeFase(){
		
		return this.botonFinDeFase;
	}
	
	
}
