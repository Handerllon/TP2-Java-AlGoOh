package Vista.estadosJuego;

import Vista.Vista;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class FaseActualVista
{
    private Vista vista;
    private Label labelFaseActual;

    public FaseActualVista(Vista vista)
    {
        this.vista = vista;
        this.labelFaseActual = new Label();

        this.labelFaseActual.setPrefSize((this.vista.getResolucionHorizontal() * (1 - 0.520)), this.vista.getResolucionVertical() * 0.157);
        this.labelFaseActual.setFont(new Font("Bauhaus 93", this.vista.getResolucionVertical() * 0.05));
        this.labelFaseActual.setTextFill(Color.web("#910101"));

        actualizar();
    }

    public Label getLabelFaseActual()
    {
        return labelFaseActual;
    }

    public void actualizar()
    {
        this.labelFaseActual.setText("Fase: " + this.vista.getControlador().getNombreFaseActual());
    }
}
