package interfaceJuego;

import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class ManoInterface {

	private FlowPane flowPane;
	private Stage stage;
	
	public ManoInterface(Stage primaryStage){
		
		flowPane = new FlowPane();
		
		stage = primaryStage;
		
	}
	
	public void inicializarRegion(){
		

	}
	
	public FlowPane getNodo(){
		
		return flowPane;
	}
	
	
	
}
