package Vista.areaDeJuego;

import Modelo.Modelo;
import Modelo.ObservadorDeModelo;
import Modelo.carta.campo.CartaCampo;
import Vista.Botones.BotonCampoEnRegion;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class RegionCampoVista implements ObservadorDeModelo
{
    private Stage stage;
    private Modelo modelo;
    private BotonCampoEnRegion botonCampoJugador;
    private BotonCampoEnRegion botonCampoOponente;

    public RegionCampoVista(Stage primaryStage, Modelo modelo)
    {
        stage = primaryStage;
        this.modelo = modelo;

        this.botonCampoJugador = new BotonCampoEnRegion(primaryStage);
        this.botonCampoOponente = new BotonCampoEnRegion(primaryStage);
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
    public void actualizar()
    {
        this.botonCampoJugador.clear();
        this.botonCampoOponente.clear();
        this.actualizarRegionJugador(this.modelo.obtenerCartasEnRegionCampoJugador());
        this.actualizarRegionOponente(this.modelo.obtenerCartasEnRegionCampoOponente());
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
