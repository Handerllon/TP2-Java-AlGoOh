package Vista.Botones;

import Modelo.carta.campo.CartaCampo;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;

public class BotonCampoEnRegion extends Button {
	
    private static String estiloRegion = "-fx-background-color: Transparent";
	private Button boton;
	private CartaCampo carta;
	private Stage stage;
	
	public BotonCampoEnRegion(Stage primaryStage){
		
		this.stage = primaryStage;
		
		boton = new Button();
		
		// TODO: generalizar el hardcodeo de los numeros.
        boton.setPrefSize(95.4, 139);
		boton.setStyle(estiloRegion);
		
	}
	
	public Button obtenerBoton(){
		
		return boton;
	}
	
	public void clear(){
		
		boton.setStyle(estiloRegion);
		
	}
	
	public void actualizarImagen(CartaCampo unaCartaCampo){
		
		this.carta = unaCartaCampo;
		this.boton = this.crearBotonParaCartaEnRegion();
		
	}

	private Button crearBotonParaCartaEnRegion() {
		Button botonEnRegion = new Button();
				
		//TODO: Hacer opciones que tiene una cartaCampo una vez que fue jugada
		botonEnRegion.setPrefSize(95.4, 139);
		botonEnRegion.setBackground(new Background(new BackgroundFill(new ImagePattern(new Image(getClass().getClassLoader()
                .getResource(this.carta.obtenerLocacionDeImagen()).toString())), CornerRadii.EMPTY, Insets.EMPTY)));
				
		return botonEnRegion;
	}
}
