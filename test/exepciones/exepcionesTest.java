package exepciones;

import org.junit.Test;

import AlGoOh.Jugador;
import AlGoOh.excepciones.JugadorSinVida;
import areaDeJuego.RegionMonstruos;
import areaDeJuego.excepciones.RegionSinEspacioLibre;
import carta.CartaCampo;
import carta.CartaMagica;
import carta.CartaMonstruo;
import carta.CartaTrampa;
import carta.Mano;
import carta.Sacrificio;
import carta.campo.FabricaCartasCampo;
import carta.excepciones.CartaInvalidaError;
import carta.excepciones.CartaNoExisteEnRegion;
import carta.excepciones.NoHayCartasParaSacrificarError;
import carta.excepciones.NoHayTresDragonesBlancosParaSacrificioError;
import carta.excepciones.NoSePuedenAgregarMasCartasALaMano;
import carta.magica.FabricaCartasMagicas;
import carta.monstruo.BlueEyesUltimateDragon;
import carta.monstruo.BlueEyesWhiteDragon;
import carta.monstruo.FabricaCartasMonstruo;
import carta.monstruo.GaiaTheFierceKnight;
import carta.trampa.FabricaCartasTrampa;

public class exepcionesTest {

	
	@Test(expected=CartaNoExisteEnRegion.class)
	public void test01CartaNoExisteEnRegion(){
		
		RegionMonstruos region = new RegionMonstruos(null);
		
		CartaMonstruo unaCartaMonstruo = new GaiaTheFierceKnight(null,null,null);
		
		region.removerCarta(unaCartaMonstruo);
		
	}
	
	@Test(expected = CartaInvalidaError.class)
	public void test02CartaInvalidaErrorEnFabricaDeCartasCampo(){
		
		FabricaCartasCampo fabrica = new FabricaCartasCampo(null,null);
		
		CartaCampo algo = fabrica.obtenerCarta("CartaSinNombre");
	}
	
	@Test(expected = CartaInvalidaError.class)
	public void test03CartaInvalidaErrorEnFabricaDeCartasMagica(){
		
		FabricaCartasMagicas fabrica = new FabricaCartasMagicas(null,null);
		
		CartaMagica algo = fabrica.obtenerCarta("CartaSinNombre");
	}
	

	@Test(expected = CartaInvalidaError.class)
	public void test04CartaInvalidaErrorEnFabricaDeCartasTrampa(){
		
		FabricaCartasTrampa fabrica = new FabricaCartasTrampa(null,null);
		
		CartaTrampa algo = fabrica.obtenerCarta("CartaSinNombre");
	}
	

	@Test(expected = CartaInvalidaError.class)
	public void test05CartaInvalidaErrorEnFabricaDeCartasMonstruo(){
		
		FabricaCartasMonstruo fabrica = new FabricaCartasMonstruo(null,null);
		
		CartaMonstruo algo = fabrica.obtenerCarta("CartaSinNombre");
	}
	
	@Test(expected = NoHayCartasParaSacrificarError.class)
	public void test06NoHayCartasParaSacrificarError(){
		
		CartaMonstruo carta = new BlueEyesWhiteDragon(null,null,null);
		
		//No hay cartas para sacrificar, y la carta requiere sacrificios para ser invocada
		Sacrificio sacrificio = new Sacrificio();
		
		carta.invocar(sacrificio);
	}
	
	@Test(expected = NoHayTresDragonesBlancosParaSacrificioError.class)
	public void test07NoHayCartasParaSacrificarError(){
		
		CartaMonstruo carta = new BlueEyesUltimateDragon(null,null,null);
		
		//No hay cartas para sacrificar, y la carta requiere sacrificios para ser invocada
		Sacrificio sacrificio = new Sacrificio();
		
		carta.invocar(sacrificio);
	}
	
	@Test(expected = NoSePuedenAgregarMasCartasALaMano.class)
	public void test08NoSePuedenAgregarCartasALaMano(){
		
		Mano mano = new Mano();
		
		CartaMonstruo carta = new GaiaTheFierceKnight(null,null,null);
		
		for(int i = 0; i<8 ; i++){
			
			mano.agregarCarta(carta);
			
		}
	}
	
	@Test(expected = RegionSinEspacioLibre.class)
	public void test09RegionSinEspacioLibre(){
		
		RegionMonstruos region = new RegionMonstruos(null);
		
		CartaMonstruo carta = new GaiaTheFierceKnight(null,null,null);
		
		for (int i = 0; i < 6; i++) {
		
		region.colocarCarta(carta);
		
		}
	}
	
	@Test(expected = JugadorSinVida.class)
	public void test10JugadorSinVida(){
		
		Jugador jugador = new Jugador("AAAA");
		
		jugador.disminuirPuntosVida(9000);
				
	}
	
}
	
