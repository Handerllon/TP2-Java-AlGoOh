package interfaceJuego;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class CreadorDeBotones {
	
	

	public Button nuevoBoton (Button boton, Stage primaryStage){
		
		boton.setPrefSize(95.4, 139);
		
		Popup popup = new Popup();
		
		VBox vbox = new VBox();
		
		vbox = this.crearVBox(vbox, popup);
		
		popup.getContent().addAll(vbox);
		
		boton.setOnAction(new EventHandler<ActionEvent>(){
			
			public void handle(ActionEvent event){
				
				popup.show(primaryStage);
				javafx.geometry.Point2D point = boton.localToScene(0.0,0.0);
				popup.setX(primaryStage.getX() + point.getX());
				popup.setY(primaryStage.getY() + point.getY());
			}
			
		});
		
		return boton;
	}

	private VBox crearVBox(VBox vbox, Popup popup) {
		
		Button b1 = new Button("Algo");
		Button b2 = new Button("Cerrar");
		
		b2.setOnAction(new EventHandler<ActionEvent>(){
			
			public void handle(ActionEvent event){
				
				popup.hide();
			}
			
		});
		
		vbox.getChildren().addAll(b1,b2);
		
		return vbox;
	}
	
	
}
