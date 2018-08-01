package Vista.region;

import Modelo.Jugador;
import Modelo.observadores.ObservadorDeModelo;
import Vista.Vista;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;

public class RegionesCementerioVista implements ObservadorDeModelo
{
    //    private static String estiloRegion = "-fx-background-color: Transparent";
    private static String rutaImagenReversoCarta = "resources/imagenes/cartaReverso.jpg";
    private static double porcentajeDeAnchoDeLaCarta = 0.0496;
    private static double porcentajeDeAltoDeLaCarta = 0.1287;
    private Image imagenReversoCarta;
    private Vista vista;
    private StackPane stackJugador1, stackJugador2;
    private double porcentajeDeTamanioDeFuente = 0.02;
    private Jugador jugador1, jugador2;
    private Button botonCartaJugador1, botonCartaJugador2;

    // --------------------------------------------------------------------
    // Métodos de construcción e inicialización.
    // --------------------------------------------------------------------
    public RegionesCementerioVista(Vista vista)
    {
        this.vista = vista;

        vista.getModelo().registrarObsevador(this);

        this.jugador1 = vista.getModelo().getJugador();
        this.jugador2 = vista.getModelo().getOponente();

        this.stackJugador1 = new StackPane();
        this.stackJugador2 = new StackPane();

        this.imagenReversoCarta = new Image(rutaImagenReversoCarta);

        String color = "0x6d817e";
        double transparencia = 0.8;
        Background backgroundMazoCementerioVacio = new Background(new BackgroundFill(Color.web(color, transparencia), CornerRadii.EMPTY, Insets.EMPTY));

        this.botonCartaJugador1 = new Button();
        this.botonCartaJugador1.setPrefSize(this.vista.getResolucionHorizontal() * porcentajeDeAnchoDeLaCarta,
                this.vista.getResolucionVertical() * porcentajeDeAltoDeLaCarta);
        this.botonCartaJugador1.setStyle("-fx-border-width: 5px ; -fx-border-color: Black");
        this.botonCartaJugador1.setBackground(backgroundMazoCementerioVacio);

        this.botonCartaJugador2 = new Button();
        this.botonCartaJugador2.setPrefSize(this.vista.getResolucionHorizontal() * porcentajeDeAnchoDeLaCarta,
                this.vista.getResolucionVertical() * porcentajeDeAltoDeLaCarta);
        this.botonCartaJugador2.setStyle("-fx-border-width: 5px ; -fx-border-color: Black");
        this.botonCartaJugador2.setBackground(backgroundMazoCementerioVacio);

        this.stackJugador1.setAlignment(Pos.CENTER_LEFT);
        this.stackJugador2.setAlignment(Pos.CENTER_RIGHT);

        this.stackJugador1.getChildren().addAll(this.botonCartaJugador1);
        this.stackJugador2.getChildren().addAll(this.botonCartaJugador2);

        // Se actualiza la región.
        this.ingresoCartaARegion();
    }

    private void actualizarStackPaneDe(Jugador jugador, StackPane stackPaneJugador)
    {
        Button mazoCementerio = new Button();
        mazoCementerio.setPrefSize(this.vista.getResolucionHorizontal() * porcentajeDeAnchoDeLaCarta,
                this.vista.getResolucionVertical() * porcentajeDeAltoDeLaCarta);
        mazoCementerio.setStyle("-fx-border-width: 5px ; -fx-border-color: Black");
        mazoCementerio.setBackground(new Background(new BackgroundFill(new ImagePattern(this.imagenReversoCarta), CornerRadii.EMPTY, Insets.EMPTY)));

        int fontSizeInPoints = (int) Math.floor(this.vista.getResolucionVertical() * porcentajeDeTamanioDeFuente);

        Label label = new Label();
        label.setAlignment(Pos.CENTER);

        // Style.
        label.setPrefSize((this.vista.getResolucionHorizontal() * porcentajeDeAnchoDeLaCarta), this.vista.getResolucionVertical() * porcentajeDeAltoDeLaCarta);
        label.setTextFill(Color.web("#910101"));
        label.setFont(new Font("Bauhaus 93", fontSizeInPoints));

        label.setText(Integer.toString(this.vista.getModelo().getCantidadCartasCementerioDe(jugador)));

        stackPaneJugador.getChildren().addAll(mazoCementerio, label);
    }

    public StackPane getCementerioJugador1()
    {

        return stackJugador1;
    }

    public StackPane getCementerioJugador2()
    {

        return stackJugador2;
    }

    // --------------------------------------------------------------------
    // Métodos de observador de modelo.
    // --------------------------------------------------------------------
    @Override
    public void seTomoCartaDeMazo()
    {

    }

    @Override
    public void ingresoCartaAMano()
    {

    }

    @Override
    public void egresoCartaAMano()
    {

    }

    @Override
    public void ingresoCartaARegion()
    {
        if (this.vista.getModelo().getCantidadCartasCementerioDe(jugador1) > 0)
        {
            this.actualizarStackPaneDe(jugador1, stackJugador1);
        }

        if (this.vista.getModelo().getCantidadCartasCementerioDe(jugador2) > 0)
        {
            this.actualizarStackPaneDe(jugador2, stackJugador2);
        }

        this.vista.actualizarDibujo();
    }

    @Override
    public void egresoCartaARegion()
    {

    }

    @Override
    public void cambiaronLosPuntosDeVida()
    {

    }

    @Override
    public void cartaCambioDeOrientacion()
    {

    }

    @Override
    public void cartaCambioDeModo()
    {

    }
}
