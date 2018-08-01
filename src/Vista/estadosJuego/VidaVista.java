package Vista.estadosJuego;

import Modelo.Jugador;
import Modelo.observadores.ObservadorDeModelo;
import Vista.Vista;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class VidaVista implements ObservadorDeModelo
{
    private double resolucionHorizontalPantalla;
    private double resolucionVerticalPantalla;
    private String nombreJugador1, nombreJugador2;
    private Jugador jugador1, jugador2;
    private Vista vista;
    private Label vidaJugador1, vidaJugador2;
    private double porcentajeDePantallaHorizontal = 10.41;
    private double porcentajeDePantallaVertical = 18.51;
    private double porcentajeDeTamanioDeFuente = 0.05;

    public VidaVista(Vista vista)
    {
        this.vista = vista;
        this.resolucionHorizontalPantalla = this.vista.getResolucionHorizontal();
        this.resolucionVerticalPantalla = this.vista.getResolucionVertical();
        this.jugador1 = this.vista.getModelo().getJugador();
        this.jugador2 = this.vista.getModelo().getOponente();

        this.nombreJugador1 = this.vista.getModelo().getJugador().getNombre();
        this.nombreJugador2 = this.vista.getModelo().getOponente().getNombre();

        vista.getModelo().registrarObsevador(this);

        this.inicializar();

        this.cambiaronLosPuntosDeVida();
    }

    public Label getVidaJugador1()
    {
        return this.vidaJugador1;
    }

    public Label getVidaJugador2()
    {
        return this.vidaJugador2;
    }

    private void inicializar()
    {
        this.vidaJugador1 = new Label();
        this.vidaJugador2 = new Label();

        this.vidaJugador1.setPrefSize(this.resolucionHorizontalPantalla * this.porcentajeDePantallaHorizontal, this.resolucionVerticalPantalla * this.porcentajeDePantallaVertical);
        this.vidaJugador2.setPrefSize(this.resolucionHorizontalPantalla * this.porcentajeDePantallaHorizontal, this.resolucionVerticalPantalla * this.porcentajeDePantallaVertical);

        int fontSizeInPoints = (int) Math.floor(this.vista.getResolucionVertical() * porcentajeDeTamanioDeFuente);

        this.vidaJugador1.setFont(new Font("Bauhaus 93", fontSizeInPoints));
        this.vidaJugador2.setFont(new Font("Bauhaus 93", fontSizeInPoints));

        this.vidaJugador1.setTextFill(Color.web("#910101"));
        this.vidaJugador2.setTextFill(Color.web("#910101"));

        this.vidaJugador1.setAlignment(Pos.CENTER);
        this.vidaJugador2.setAlignment(Pos.CENTER);

        this.vidaJugador1.setStyle(
                "-fx-border-color: green, red;" +
                        "-fx-border-width: 5, 5;" +
                        "-fx-border-radius: 0, 0;" +
                        "-fx-border-insets: 0, 5;" +
                        "-fx-border-style: solid inside, dotted outside;");

        this.vidaJugador2.setStyle(
                "-fx-border-color: green, red;" +
                        "-fx-border-width: 5, 5;" +
                        "-fx-border-radius: 0, 0;" +
                        "-fx-border-insets: 0, 5;" +
                        "-fx-border-style: solid inside, dotted outside;");
    }

    @Override
    public void cambiaronLosPuntosDeVida()
    {
        this.vidaJugador1.setText(this.nombreJugador1 + "\n" + "LP: " + this.jugador1.getPuntosDeVida());
        this.vidaJugador2.setText(this.nombreJugador2 + "\n" + "LP: " + this.jugador2.getPuntosDeVida());
        this.vista.actualizarDibujo();
    }

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

    }

    @Override
    public void egresoCartaARegion()
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
