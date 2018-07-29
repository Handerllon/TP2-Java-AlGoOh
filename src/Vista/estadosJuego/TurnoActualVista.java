package Vista.estadosJuego;

import Vista.Vista;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class TurnoActualVista
{
    private Vista vista;
    private Label labelTurnoActual;

    public TurnoActualVista(Vista vista)
    {
        this.vista = vista;
        this.labelTurnoActual = new Label();

        this.labelTurnoActual.setPrefSize((this.vista.getResolucionHorizontal() * (1 - 0.520)), this.vista.getResolucionVertical() * 0.157);
        this.labelTurnoActual.setFont(new Font("Bauhaus 93", this.vista.getResolucionVertical() * 0.05));
        this.labelTurnoActual.setTextFill(Color.web("#910101"));

        this.actualizar();
    }

    public Label getDisplayTurnoActual()
    {
        return labelTurnoActual;
    }

    public void actualizar()
    {
        this.labelTurnoActual.setText("Turno de: " + this.vista.getControlador().getNombreJugadorActual());
    }
}
