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

        // TODO: número mágico. Para qué se divide por 1920?
        this.vidaJugador.setPrefSize((this.vista.getResolucionHorizontal() * 200) / 1920, (this.vista.getResolucionVertical() * 200) / 1080);
        this.vidaOponente.setPrefSize((this.vista.getResolucionHorizontal() * 200) / 1920, (this.vista.getResolucionVertical() * 200) / 1080);

        // TODO: número mágico. Por qué no se usa algún porcentaje de alguna medida? por ej., de la resolución horiz.?
        this.vidaJugador.setFont(new Font("Bauhaus 93", this.vista.getResolucionVertical() * 0.05));
        this.vidaOponente.setFont(new Font("Bauhaus 93", this.vista.getResolucionVertical() * 0.05));

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
    public void cambiaronLosPuntosDeVida()
    {

    }
}
