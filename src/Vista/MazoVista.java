package Vista;

import Modelo.Jugador;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;

import java.util.ArrayList;

public class MazoVista implements ObjectoObservador {

	private Button boton;
	private Stage stage;
	
	public MazoVista(Stage primaryStage, Jugador jugador){
		
		boton = new Button();
		
		stage = primaryStage;
		
		//jugador.obtenerMazo().agregarObsevador(this);
		
	}
	
	public void inicializar(){
		

    	boton.setPrefSize(95.4, 139);
    	boton.setBackground(new Background(new BackgroundFill(new ImagePattern(new Image(getClass().getClassLoader()
        		.getResource("resources/imagenes/tablero/Back.jpg").toString())), CornerRadii.EMPTY, Insets.EMPTY)));
	}
	
	public Button getNodo(){
		
		return boton;
	}

	@Override
	public void update() {
		// TODO: le pido la cantidad de cartas al mazo.
	}
}
