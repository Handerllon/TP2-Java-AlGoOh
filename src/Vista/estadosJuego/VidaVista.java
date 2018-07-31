package Vista.estadosJuego;

import Modelo.Jugador;
import Modelo.observadores.ObservadorDeModelo;
import Vista.Vista;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class VidaVista implements ObservadorDeModelo
{
    private double resolucionHorizontalPantalla;
    private double resolucionVerticalPantalla;
    private String nombreJugador;
    private String nombreOponente;
    private Jugador jugador;
    private Jugador oponente;
    private Vista vista;
    private Label vidaJugador;
    private Label vidaOponente;
    private double porcentajeDePantallaHorizontal = 10.41;
    private double porcentajeDePantallaVertical = 18.51;
    private double porcentajeDeTamanioDeFuente = 0.05;

    public VidaVista(Vista vista)
    {
        this.vista = vista;
        this.resolucionHorizontalPantalla = this.vista.getResolucionHorizontal();
        this.resolucionVerticalPantalla = this.vista.getResolucionVertical();
        this.jugador = this.vista.getModelo().getJugador();
        this.oponente = this.vista.getModelo().getOponente();

        this.nombreJugador = this.vista.getModelo().getJugador().getNombre();
        this.nombreOponente = this.vista.getModelo().getOponente().getNombre();

        vista.getModelo().registrarObsevador(this);

        this.vidaJugador = new Label();
        this.vidaOponente = new Label();

        this.vidaJugador.setPrefSize(this.resolucionHorizontalPantalla * this.porcentajeDePantallaHorizontal, this.resolucionVerticalPantalla * this.porcentajeDePantallaVertical);
        this.vidaOponente.setPrefSize(this.resolucionHorizontalPantalla * this.porcentajeDePantallaHorizontal, this.resolucionVerticalPantalla * this.porcentajeDePantallaVertical);

        this.vidaJugador.setFont(new Font("Bauhaus 93", this.resolucionVerticalPantalla * this.porcentajeDeTamanioDeFuente));
        this.vidaOponente.setFont(new Font("Bauhaus 93", this.resolucionVerticalPantalla * this.porcentajeDeTamanioDeFuente));

        this.vidaJugador.setTextFill(Color.web("#910101"));
        this.vidaOponente.setTextFill(Color.web("#910101"));

        this.cambiaronLosPuntosDeVida();
    }

    public Label getVidaJugador()
    {
        return this.vidaJugador;
    }

    public Label getVidaOponente()
    {
        return this.vidaOponente;
    }

    @Override
    public void cambiaronLosPuntosDeVida()
    {
        this.vidaJugador.setText(this.nombreJugador + "\n" + this.jugador.getPuntosDeVida());
        this.vidaOponente.setText(this.nombreOponente + "\n" + this.oponente.getPuntosDeVida());
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
