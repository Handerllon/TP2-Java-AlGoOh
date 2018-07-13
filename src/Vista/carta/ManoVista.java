package Vista.carta;

import Modelo.Modelo;
import Modelo.ObservadorModelo;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class ManoVista implements ObservadorModelo
{
    private Modelo modelo;
    private Stage stage;
    private FlowPane manoJugador;
    private FlowPane manoOponente;

    public ManoVista(Stage primaryStage, Modelo modelo)
    {

        

        this.stage = primaryStage;
        this.modelo = modelo;
        
        this.manoJugador = this.inicializarRegion();
        this.manoOponente = this.inicializarRegion();

    }

    public FlowPane inicializarRegion()
    {
    	return new FlowPane();
    }

    public FlowPane getManoJugador()
    {

        return manoJugador;
    }
    
    public FlowPane getManoOponente()
    {

        return manoOponente;
    }

    @Override
    public void actualizar()
    {
        // TODO: pedir las cartas a la mano y actualizar las vistas.
    }
}
