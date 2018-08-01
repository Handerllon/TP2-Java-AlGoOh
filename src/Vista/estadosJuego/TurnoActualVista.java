package Vista.estadosJuego;

import Controlador.observadores.ObservadorDeControlador;
import Vista.Vista;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class TurnoActualVista implements ObservadorDeControlador
{
    private Vista vista;
    private Label labelTurnoActual;
    private Label labelFaseActual;
    private double porcentajeDePantallaHorizontal = 0.48;
    private double porcentajeDePantallaVertical = 0.157;
    private double porcentajeDeTamanioDeFuente = 0.05;
    private GridPane grid;
    private VBox paneEstados;

    public TurnoActualVista(Vista vista)
    {
        this.vista = vista;

        this.vista.getControlador().registrarObsevador(this);

        this.inicializarEscena();

        this.seTerminoElTurno();
    }

    private void inicializarEscena()
    {
        this.grid = new GridPane();

        this.labelTurnoActual = new Label();

        int fontSizeInPoints = (int) Math.floor(this.vista.getResolucionVertical() * porcentajeDeTamanioDeFuente);

        this.labelTurnoActual.setPrefSize((this.vista.getResolucionHorizontal() * porcentajeDePantallaHorizontal), this.vista.getResolucionVertical() * porcentajeDePantallaVertical);
        this.labelTurnoActual.setFont(new Font("Bauhaus 93", fontSizeInPoints));
        this.labelTurnoActual.setTextFill(Color.web("#910101"));
        labelTurnoActual.setAlignment(Pos.CENTER);

        this.labelFaseActual = new Label();

        this.labelFaseActual.setPrefSize((this.vista.getResolucionHorizontal() * porcentajeDePantallaHorizontal), this.vista.getResolucionVertical() * porcentajeDePantallaVertical);
        this.labelFaseActual.setFont(new Font("Bauhaus 93", fontSizeInPoints));
        this.labelFaseActual.setTextFill(Color.web("#910101"));
        labelFaseActual.setAlignment(Pos.CENTER);

        // -------------------------------
        // Setting grid.
        // -------------------------------
        double lblSeparationInPixels = 20;
        this.paneEstados = new VBox(lblSeparationInPixels, labelTurnoActual, labelFaseActual);

        this.paneEstados.setStyle(
                "-fx-border-color: black, red;" +
                        "-fx-border-width: 5, 5;" +
                        "-fx-border-radius: 0, 0;" +
                        "-fx-border-insets: 0, 5;" +
                        "-fx-border-style: solid inside, dotted outside;");

        this.grid.setPadding(new Insets(10));
        this.grid.setHgap(lblSeparationInPixels);
        this.grid.setVgap(lblSeparationInPixels);
        this.grid.setMinWidth(500);

        this.grid.addRow(0, paneEstados);
    }

    public VBox getPaneEstados()
    {
        return this.paneEstados;
    }

    // --------------------------------------------------------------------
    // Metodos por ser un observador de Controlador.
    // --------------------------------------------------------------------
    @Override
    public void seTerminoElTurno()
    {
        this.labelTurnoActual.setText("TURNO DE " + this.vista.getControlador().getNombreJugadorActual());
        this.vista.actualizarDibujo();
    }

    @Override
    public void seTerminoLaFase()
    {
        this.labelFaseActual.setText("FASE " + this.vista.getControlador().getNombreFaseActual());
    }
}
