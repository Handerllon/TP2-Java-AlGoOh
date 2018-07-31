package Vista.estadosJuego;

import Controlador.observadores.ObservadorDeControlador;
import Vista.Vista;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class TurnoActualVista implements ObservadorDeControlador
{
    private Vista vista;
    private Label labelTurnoActual;
    private double porcentajeDePantallaHorizontal = 0.48;
    private double porcentajeDePantallaVertical = 0.157;
    private double porcentajeDeTamanioDeFuente = 0.05;

    public TurnoActualVista(Vista vista)
    {
        this.vista = vista;

        this.vista.getControlador().registrarObsevador(this);

        this.labelTurnoActual = new Label();

        this.labelTurnoActual.setPrefSize((this.vista.getResolucionHorizontal() * porcentajeDePantallaHorizontal), this.vista.getResolucionVertical() * porcentajeDePantallaVertical);
        this.labelTurnoActual.setFont(new Font("Bauhaus 93", this.vista.getResolucionVertical() * porcentajeDeTamanioDeFuente));
        this.labelTurnoActual.setTextFill(Color.web("#910101"));

        this.seTerminoElTurno();
    }

    public Label getDisplayTurnoActual()
    {
        return labelTurnoActual;
    }

    // --------------------------------------------------------------------
    // Metodos por ser un observador de Controlador.
    // --------------------------------------------------------------------
    @Override
    public void seTerminoElTurno()
    {
        this.labelTurnoActual.setText("Turno de: " + this.vista.getControlador().getNombreJugadorActual());
        this.vista.actualizarDibujo();
    }

    @Override
    public void seTerminoLaFase()
    {

    }
}
