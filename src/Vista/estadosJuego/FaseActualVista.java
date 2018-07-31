package Vista.estadosJuego;

import Controlador.observadores.ObservadorDeControlador;
import Vista.Vista;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class FaseActualVista implements ObservadorDeControlador
{
    private Vista vista;
    private Label labelFaseActual;
    private double porcentajeDePantallaHorizontal = 0.48;
    private double porcentajeDePantallaVertical = 0.157;
    private double porcentajeDeTamanioDeFuente = 0.05;

    public FaseActualVista(Vista vista)
    {
        this.vista = vista;

        this.vista.getControlador().registrarObsevador(this);

        this.labelFaseActual = new Label();

        this.labelFaseActual.setPrefSize((this.vista.getResolucionHorizontal() * porcentajeDePantallaHorizontal), this.vista.getResolucionVertical() * porcentajeDePantallaVertical);
        this.labelFaseActual.setFont(new Font("Bauhaus 93", this.vista.getResolucionVertical() * porcentajeDeTamanioDeFuente));
        this.labelFaseActual.setTextFill(Color.web("#910101"));

        seTerminoLaFase();
    }

    public Label getLabelFaseActual()
    {
        return labelFaseActual;
    }

    // --------------------------------------------------------------------
    // Metodos por ser un observador de Controlador.
    // --------------------------------------------------------------------
    @Override
    public void seTerminoElTurno()
    {

    }

    @Override
    public void seTerminoLaFase()
    {
        this.labelFaseActual.setText("Fase: " + this.vista.getControlador().getNombreFaseActual());
    }
}
