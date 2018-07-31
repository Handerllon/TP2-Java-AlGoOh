package Vista.estadosJuego;

import Modelo.observadores.ObservadorDeModelo;
import Vista.Vista;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class VidaVista implements ObservadorDeModelo
{
    private Vista vista;
    private Label vidaJugador;
    private Label vidaOponente;
    private double porcentajeDePantallaHorizontal = 10.41;
    private double porcentajeDePantallaVertical = 18.51;
    private double porcentajeDeTamanioDeFuente = 0.05;

    public VidaVista(Vista vista)
    {
        this.vista = vista;
        // TODO: Para cuando se implemente los observadores puntuales:
        // vista.getModelo().registrarObsevador(this);

        this.vidaJugador = new Label();
        this.vidaOponente = new Label();

        this.inicializar();
    }

    private void inicializar()
    {
        this.vidaJugador.setPrefSize(this.vista.getResolucionHorizontal() * porcentajeDePantallaHorizontal, this.vista.getResolucionVertical() * porcentajeDePantallaVertical);
        this.vidaOponente.setPrefSize(this.vista.getResolucionHorizontal() * porcentajeDePantallaHorizontal, this.vista.getResolucionVertical() * porcentajeDePantallaVertical);

        this.vidaJugador.setFont(new Font("Bauhaus 93", this.vista.getResolucionVertical() * porcentajeDeTamanioDeFuente));
        this.vidaOponente.setFont(new Font("Bauhaus 93", this.vista.getResolucionVertical() * porcentajeDeTamanioDeFuente));

        this.vidaJugador.setTextFill(Color.web("#910101"));
        this.vidaOponente.setTextFill(Color.web("#910101"));

        this.huboCambios();
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
    public void huboCambios()
    {
        this.vidaJugador.setText(this.vista.getModelo().getJugador().getNombre() + "\n" + this.vista.getModelo().getJugador().getPuntosDeVida());
        this.vidaOponente.setText(this.vista.getModelo().getOponente().getNombre() + "\n" + this.vista.getModelo().getOponente().getPuntosDeVida());
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
