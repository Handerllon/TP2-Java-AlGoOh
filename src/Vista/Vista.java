package Vista;

import Controlador.ControladorInterfaz;
import Controlador.excepciones.*;
import Modelo.ModeloObservable;
import Modelo.carta.Sacrificio;
import Modelo.carta.excepciones.ManoLlena;
import Modelo.carta.monstruo.CartaMonstruo;
import Modelo.observadores.ObservadorDeModelo;
import Vista.escena.Escena;
import Vista.escena.EscenaBienvenida;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.Optional;

public class Vista implements ObservadorDeModelo
{
    private static ModeloObservable modelo;
    private static ControladorInterfaz controlador;
    private static double RESOLUCION_HORIZONTAL;
    private static double RESOLUCION_VERTICAL;
    private Escena escena;
    private Stage primaryStage;

    // --------------------------------------------------------------------
    // Métodos de construcción e inicialización.
    // --------------------------------------------------------------------
    public Vista(ModeloObservable modelo, ControladorInterfaz controlador, Stage primaryStage)
    {
        this.primaryStage = primaryStage;
        this.RESOLUCION_HORIZONTAL = Screen.getPrimary().getVisualBounds().getWidth();
        this.RESOLUCION_VERTICAL = Screen.getPrimary().getVisualBounds().getHeight();

        this.modelo = modelo;
        modelo.registrarObsevador(this);

        this.controlador = controlador;

        this.escena = EscenaBienvenida.getInstancia(this.modelo, this);
    }

    // --------------------------------------------------------------------
    // Ejecucion.
    // --------------------------------------------------------------------

    public void mostrar()
    {
        this.escena.mostrar();
    }

    public void setProximaEscena(Escena escena)
    {
        this.escena = escena;
    }

    // --------------------------------------------------------------------
    // Métodos de consulta.
    // --------------------------------------------------------------------
    public Stage getPrimaryStage()
    {
        return this.primaryStage;
    }

    public GridPane getGridPaneEscena()
    {
        return this.escena.getGridPaneEscena();
    }

    public ControladorInterfaz getControlador()
    {
        return this.controlador;
    }

    public double getResolucionHorizontal()
    {
        return this.RESOLUCION_HORIZONTAL;
    }

    public double getResolucionVertical()
    {
        return this.RESOLUCION_VERTICAL;
    }

    public ModeloObservable getModelo()
    {
        return this.modelo;
    }

    public void mostrarJugadorActual()
    {
        this.escena.mostrarJugadorActual();
    }

    public void mostrarFaseActual()
    {
    	
        this.escena.mostrarFaseActual();
        
    }


	// --------------------------------------------------------------------
    // Métodos de observador de modelo.
    // --------------------------------------------------------------------
    @Override
    public void huboCambios()
    {
        this.escena.actualizarEstado();
    }

    // --------------------------------------------------------------------
    // Métodos de terminación.
    // --------------------------------------------------------------------
    public void finDeJuego()
    {
        this.escena.finDeJuego();
        this.escena.mostrar();
    }

    public void confirmarSalirPrograma()
    {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cerrar AlGoOh");
        alert.setHeaderText("Está seguro que desea salir?");
        alert.setContentText("Confirmación");

        ButtonType buttonTypeOne = new ButtonType("Si");
        ButtonType buttonTypeCancel = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne)
        {
            this.getControlador().cerrarPrograma();
        } else
        {
            // El usuario cerro la ventana o apretó NO.
        }
    }

    public void cerrar()
    {
        this.escena.cerrar();
    }

    // -------------------------------
    // Avisos de advertencias.
    // -------------------------------
    // TODO: los mandamos a alguna clase?
    public void mostrarError(NoSePuedeTomarCartaError error)
    {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Advertencia");
        alert.setHeaderText(null);
        alert.setContentText(error.getNombreResponsable() + " :No se puede tomar carta. " + error.getEstadoVerificador()
                .getNombre());

        alert.showAndWait();
    }

    public void mostrarError(NoSePuedeEnviarCartaMonstruoARegionError error)
    {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Advertencia");
        alert.setHeaderText(null);
        alert.setContentText(error.getNombreResponsable() + " :La carta monstruo no puede ser envíada a la región monstruo. " + error
                .getEstadoVerificador()
                .getNombre());

        alert.showAndWait();
    }

    public void mostrarError(NoSePuedeEnviarMyTARegionError error)
    {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Advertencia");
        alert.setHeaderText(null);
        alert.setContentText(error.getNombreResponsable() + " :La carta MyT no puede ser envíada a la región MyT. " + error.getEstadoVerificador()
                .getNombre());

        alert.showAndWait();
    }

    public void mostrarError(NoSePuedeUsarMyTError error)
    {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Advertencia");
        alert.setHeaderText(null);
        alert.setContentText(error.getNombreResponsable() + " :La carta elegida no puede ser utilizada. " + error.getEstadoVerificador().getNombre());

        alert.showAndWait();
    }

    public void mostrarError(NoSePuedeEnviarARegionCampoError error)
    {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Advertencia");
        alert.setHeaderText(null);
        alert.setContentText(error.getNombreResponsable() + " :La carta campo no puede ser envíada a la región campo. " + error.getEstadoVerificador()
                .getNombre());

        alert.showAndWait();
    }

    public void mostrarError(NoSePuedeCambiarOrientacionError error)
    {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Advertencia");
        alert.setHeaderText(null);
        alert.setContentText(error.getNombreResponsable() + " :No se puede cambiar la orientacion de la carta elegida. " + error.getEstadoVerificador()
                .getNombre());

        alert.showAndWait();
    }

    public void mostrarError(NoSePuedeAtacarError error)
    {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Advertencia");
        alert.setHeaderText(null);
        alert.setContentText(error.getNombreResponsable() + " :No se puede atacar. " + error.getEstadoVerificador().getNombre());

        alert.showAndWait();
    }

    public void avisoManoLlena(ManoLlena error)
    {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Aviso");
        alert.setHeaderText(null);
        alert.setContentText(error.getNombreResponsable() + " :La mano estaba llena y se descartó una carta al azar. ");

        alert.showAndWait();
    }

    // -------------------------------
    // Solicitudes al usuario.
    // -------------------------------
    public Sacrificio pedirSacrificios()
    {
        // TODO.
        return null;
    }

    public CartaMonstruo solicitarCartaAAtacar()
    {
        // TODO.
        return null;
    }
}
