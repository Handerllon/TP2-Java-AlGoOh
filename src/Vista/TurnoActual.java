package Vista;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class TurnoActual {

	private Vista vista;
	private Label turnoActual;
	
	
	public TurnoActual(Vista vista) {

		this.vista = vista;
		this.turnoActual = new Label();
		
		this.turnoActual.setText("Turno de: " + this.vista.getControlador().getNombreJugadorActual());
		this.turnoActual.setPrefSize((this.vista.getResolucionHorizontal()*(1-0.520)), this.vista.getResolucionVertical()*0.157);
		this.turnoActual.setFont(new Font("Bauhaus 93", this.vista.getResolucionVertical()*0.05));
		this.turnoActual.setTextFill(Color.web("#910101"));
	}

	public Label getDisplayTurnoActual() {
		
		return turnoActual;
	}

	public void actualizar() {
		
		this.turnoActual.setText("Turno de: " + this.vista.getControlador().getNombreJugadorActual());
	}

}
