package vistaJuego;

import java.util.ArrayList;

import AlGoOh.Jugador;
import carta.Carta;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class RegionCementerioVista implements Observador {

	
	private Button boton;
	private Stage stage;
	
	public RegionCementerioVista(Stage primaryStage, Jugador jugador){
		
		boton = new Button();
		
		stage = primaryStage;
		
		jugador.obtenerCementerio().subscribir(this);
	}
	
	public void inicializarRegion(){
		

    	boton.setPrefSize(95.4, 139);

    	boton.setStyle("-fx-background-color: Transparent");
	}
	
	public Button getNodo(){
		
		return boton;
	}

	@Override
	public void update(ArrayList arrayList) {
		// TODO Auto-generated method stub
		
	}

}
