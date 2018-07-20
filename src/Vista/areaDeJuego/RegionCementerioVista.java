package Vista.areaDeJuego;

import Modelo.ObservadorDeModelo;
import Vista.Vista;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class RegionCementerioVista implements ObservadorDeModelo
{
    private static String estiloRegion = "-fx-background-color: Transparent";
    private Stage stage;
    private Vista vista;
    private Button botonCementerioJugador;
    private Button botonCementerioOponente;

    public RegionCementerioVista(Stage primaryStage, Vista vista)
    {

        this.stage = primaryStage;
        this.vista = vista;

        this.botonCementerioJugador = this.inicializarBoton();
        this.botonCementerioOponente = this.inicializarBoton();
    }

    private Button inicializarBoton()
    {

        Button boton = new Button();

        // TODO: generalizar el hardcodeo de los numeros.
        boton.setPrefSize(95.4, 139);
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

    @Override
    public void actualizarEstado()
    {
        //TODO Ver que dibujarEscena en el cementerio
    }
}
