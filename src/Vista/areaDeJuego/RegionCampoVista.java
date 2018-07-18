package Vista.areaDeJuego;

import Modelo.Modelo;
import Modelo.ObservadorDeModelo;
import Modelo.carta.campo.CartaCampo;
import Vista.Vista;
import Vista.Botones.BotonCampoEnRegion;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class RegionCampoVista implements ObservadorDeModelo
{
    private Stage stage;
    private Vista vista;
    private BotonCampoEnRegion botonCampoJugador;
    private BotonCampoEnRegion botonCampoOponente;

    public RegionCampoVista(Stage primaryStage, Vista vista)
    {
        stage = primaryStage;
        this.vista = vista;

        this.botonCampoJugador = new BotonCampoEnRegion(this.vista);
        this.botonCampoOponente = new BotonCampoEnRegion(this.vista);
    }

    public Button getRegionCampoJugador()
    {

        return botonCampoJugador.obtenerBoton();
    }

    public Button getRegionCampoOponente()
    {

        return botonCampoOponente.obtenerBoton();
    }

    @Override
    public void actualizarEstado()
    {
        this.botonCampoJugador.clear();
        this.botonCampoOponente.clear();
        this.actualizarRegionJugador(this.vista.obtenerModelo().obtenerCartasEnRegionCampoJugador());
        this.actualizarRegionOponente(this.vista.obtenerModelo().obtenerCartasEnRegionCampoOponente());
    }

    private void actualizarRegionJugador(CartaCampo unaCartaCampo)
    {
        this.botonCampoJugador.actualizarImagen(unaCartaCampo);
    }

    private void actualizarRegionOponente(CartaCampo unaCartaCampo)
    {
        this.botonCampoOponente.actualizarImagen(unaCartaCampo);
    }
}
