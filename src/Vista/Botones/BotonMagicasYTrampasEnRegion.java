package Vista.Botones;

import Modelo.carta.Carta;
import Modelo.carta.magica.CartaMagica;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class BotonMagicasYTrampasEnRegion extends Button {

	private static String estiloRegion = "-fx-background-color: Transparent ; -fx-border-width: 5px ; -fx-border-color: Black";
	private Carta carta;
    private Stage stage;
    private Button botonDeLaCarta;

    public BotonMagicasYTrampasEnRegion(Stage primaryStage)
    {
    	this.stage = primaryStage;
    	this.botonDeLaCarta = new Button();
		
		// TODO: generalizar el hardcodeo de los numeros.
    	this.botonDeLaCarta.setPrefSize(95.4, 139);
    	this.botonDeLaCarta.setStyle(estiloRegion);
    }

    public Button obtenerBoton()
    {

        return this.botonDeLaCarta;
    }

    public void clear()
    {
    	this.botonDeLaCarta.setPrefSize(95.4, 139);
    	this.botonDeLaCarta.setStyle(estiloRegion);
    }
    
    public void actualizar(Carta carta) {
    	
    	this.carta = carta;
    	this.botonDeLaCarta = this.crearBotonParaCartaEnRegion();
    	
    }

	private Button crearBotonParaCartaEnRegion() {
		Button botonEnRegion = new Button();
		
		//----- Fondo del boton--------
		botonEnRegion.setPrefSize(95.4, 139);
		botonEnRegion.setBackground(new Background(new BackgroundFill(new ImagePattern(new Image(getClass().getClassLoader()
                .getResource(this.carta.obtenerLocacionDeImagen()).toString())), CornerRadii.EMPTY, Insets.EMPTY)));
		//------------------------------
		
		if(this.carta.getClass() == CartaMagica.class){
			
			this.crearBotonParaCartaMagicaEnRegion(botonEnRegion);
		}
		
		return botonEnRegion;
	}
	
	private void crearBotonParaCartaMagicaEnRegion(Button botonEnRegion) {
		
		Popup popup = new Popup();

        VBox vbox = new VBox();

        vbox = this.crearVBoxCartaMagica(vbox, popup);

        popup.getContent().addAll(vbox);

        botonEnRegion.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event)
            {

                popup.show(stage);
                javafx.geometry.Point2D point = botonEnRegion.localToScene(0.0, 0.0);
                popup.setX(stage.getX() + point.getX());
                popup.setY(stage.getY() + point.getY());
            }
        });
		
	}
	

	
	private VBox crearVBoxCartaMagica(VBox vbox, Popup popup) {
		//TODO: Implementar los event handler de cada opcion
        Button b1 = new Button("Usar");
        Button b5 = new Button("Cerrar");

        b5.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event)
            {

                popup.hide();
            }
        });

        vbox.getChildren().addAll(b1,b5);

        return vbox;
	}

}
