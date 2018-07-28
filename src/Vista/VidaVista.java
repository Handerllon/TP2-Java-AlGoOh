package Vista;

import Modelo.observadores.ObservadorDeModelo;
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

        this.vidaJugador = new Label();
        this.vidaOponente = new Label();

        this.inicializar();
    }

    private void inicializar()
    {

        // TODO: número mágico. Para qué se divide por 1920?
        this.vidaJugador.setPrefSize((this.vista.getResolucionHorizontal() * 200) / 1920, (this.vista.getResolucionVertical() * 200) / 1080);
        this.vidaOponente.setPrefSize((this.vista.getResolucionHorizontal() * 200) / 1920, (this.vista.getResolucionVertical() * 200) / 1080);

        this.vidaJugador.setText("    Vida: \n" + this.vista.getModelo().getJugador().getPuntosDeVida());
        this.vidaOponente.setText("   Vida: \n" + this.vista.getModelo().getOponente().getPuntosDeVida());

        // TODO: número mágico. Por qué no se usa algún porcentaje de alguna medida? por ej., de la resolución horiz.?
        this.vidaJugador.setFont(new Font("Times New Roman", 50));
        this.vidaOponente.setFont(new Font("Times New Roman", 50));

        this.vidaJugador.setTextFill(Color.web("#910101"));
        this.vidaOponente.setTextFill(Color.web("#910101"));
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
        this.vidaJugador.setText("    Vida: \n" + this.vista.getModelo().getJugador().getPuntosDeVida());
        this.vidaOponente.setText("   Vida: \n" + this.vista.getModelo().getOponente().getPuntosDeVida());
    }
}
