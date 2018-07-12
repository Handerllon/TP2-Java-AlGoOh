package vistaJuego;

import java.util.ArrayList;

import AlGoOh.Jugador;
import carta.Carta;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class ManoVista implements Observador {

	private FlowPane flowPane;
	private Stage stage;
	
	public ManoVista(Stage primaryStage, Jugador jugador){
		
		flowPane = new FlowPane();
		
		stage = primaryStage;
		
		jugador.obtenerMano().subscribir(this);
		
	}
	
	public void inicializarRegion(){
		

	}
	
	public FlowPane getNodo(){
		
		return flowPane;
	}

	@Override
	public void update(ArrayList arrayList) {
		// TODO Auto-generated method stub
		
	}

	
	
	
}
