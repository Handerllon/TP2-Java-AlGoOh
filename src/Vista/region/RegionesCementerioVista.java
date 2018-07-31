package Vista.region;

import Modelo.observadores.ObservadorDeModelo;
import Vista.Vista;
import javafx.scene.control.Button;

public class RegionesCementerioVista implements ObservadorDeModelo
{
    private static String estiloRegion = "-fx-background-color: Transparent";
    private static String rutaImagenReversoCarta = "resources/imagenes/cartaReverso.jpg";
    private static double porcentajeDeAnchoDeLaCarta = 0.0496;
    private static double porcentajeDeAltoDeLaCarta = 0.1287;
    private Vista vista;
    private Button botonCementerioJugador;
    private Button botonCementerioOponente;

    // --------------------------------------------------------------------
    // Métodos de construcción e inicialización.
    // --------------------------------------------------------------------
    public RegionesCementerioVista(Vista vista)
    {
        this.vista = vista;
        // TODO: Para cuando se implemente los observadores puntuales:
        // vista.getModelo().registrarObsevador(this);

        this.botonCementerioJugador = this.inicializarBoton();
        this.botonCementerioOponente = this.inicializarBoton();
    }

    private Button inicializarBoton()
    {

        Button boton = new Button();

        boton.setPrefSize((this.vista.getResolucionHorizontal() * porcentajeDeAnchoDeLaCarta), (this.vista.getResolucionVertical() * porcentajeDeAltoDeLaCarta));
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
        //TODO: cuando hay al menos 1 carta en el cementerio, se debería mostrar 'rutaImagenReversoCarta'.
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
