package Vista;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class FaseActual {

	private Vista vista;
	private Label faseActual;
	
	public FaseActual(Vista vista) {
		this.vista = vista;
		this.faseActual = new Label();
		
		this.faseActual.setText("Fase: " + this.vista.getControlador().getNombreFaseActual());
		this.faseActual.setPrefSize((this.vista.getResolucionHorizontal()*(1-0.520)), this.vista.getResolucionVertical()*0.157);
		this.faseActual.setFont(new Font("Bauhaus 93", this.vista.getResolucionVertical()*0.05));
		this.faseActual.setTextFill(Color.web("#910101"));
		
	}

	public Label getDisplayFaseActual() {

		return faseActual;
	}

	public void actualizar() {

		this.faseActual.setText("Fase: " + this.vista.getControlador().getNombreFaseActual());
	}

}
