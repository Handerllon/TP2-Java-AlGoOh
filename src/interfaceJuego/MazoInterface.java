package interfaceJuego;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;

public class MazoInterface {

	private Button boton;
	private Stage stage;
	
	public MazoInterface(Stage primaryStage){
		
		boton = new Button();
		
		stage = primaryStage;
		
	}
	
	public void inicializar(){
		

    	boton.setPrefSize(95.4, 139);
    	//boton.setBackground(new Background(new BackgroundFill(new ImagePattern(new Image(getClass().getClassLoader()
        		//.getResource("resources/imagenes/tablero/Back.jpg").toString())), CornerRadii.EMPTY, Insets.EMPTY)));
	}
	
	public Button getNodo(){
		
		return boton;
	}
	
	
	
}
