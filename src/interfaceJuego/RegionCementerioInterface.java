package interfaceJuego;

import javafx.scene.control.Button;
import javafx.stage.Stage;

public class RegionCementerioInterface {

	
	private Button boton;
	private Stage stage;
	
	public RegionCementerioInterface(Stage primaryStage){
		
		boton = new Button();
		
		stage = primaryStage;
		
	}
	
	public void inicializarRegion(){
		

    	boton.setPrefSize(95.4, 139);

    	boton.setStyle("-fx-background-color: Transparent");
	}
	
	public Button getNodo(){
		
		return boton;
	}
	
}
