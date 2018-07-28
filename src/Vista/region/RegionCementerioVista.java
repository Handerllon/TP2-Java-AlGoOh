package Vista.region;

import Modelo.observadores.ObservadorDeModelo;
import Vista.Vista;
import javafx.scene.control.Button;

public class RegionCementerioVista implements ObservadorDeModelo
{
    private static String estiloRegion = "-fx-background-color: Transparent";
    private Vista vista;
    private Button botonCementerioJugador;
    private Button botonCementerioOponente;

    public RegionCementerioVista(Vista vista)
    {
        this.vista = vista;

        this.botonCementerioJugador = this.inicializarBoton();
        this.botonCementerioOponente = this.inicializarBoton();
    }

    private Button inicializarBoton()
    {

        Button boton = new Button();

        // TODO: número mágico.
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
    public void huboCambios()
    {
        //TODO Ver que dibujarEscena en el cementerio
    }
}