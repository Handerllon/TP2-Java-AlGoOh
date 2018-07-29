package Vista.region;

import Modelo.observadores.ObservadorDeModelo;
import Vista.Vista;
import javafx.scene.control.Button;

public class RegionesCementerioVista implements ObservadorDeModelo
{
    private static String estiloRegion = "-fx-background-color: Transparent";
    private Vista vista;
    private Button botonCementerioJugador;
    private Button botonCementerioOponente;

    // --------------------------------------------------------------------
    // Métodos de construcción e inicialización.
    // --------------------------------------------------------------------
    public RegionesCementerioVista(Vista vista)
    {
        this.vista = vista;

        this.botonCementerioJugador = this.inicializarBoton();
        this.botonCementerioOponente = this.inicializarBoton();
    }

    private Button inicializarBoton()
    {

        Button boton = new Button();

        
        boton.setPrefSize((this.vista.getResolucionHorizontal()*95.4)/1920, (this.vista.getResolucionVertical()*130)/1080);
        boton.setStyle(estiloRegion);

        return boton;
    }

    public Button getCementerioJugador()
    {

        return botonCementerioJugador;
    }

    public Button getCementerioOponente()
    {

        return botonCementerioOponente;
    }

    // --------------------------------------------------------------------
    // Métodos de observador de modelo.
    // --------------------------------------------------------------------
    @Override
    public void huboCambios()
    {
        //TODO Ver que dibujarEscena en el cementerio
    }
}
