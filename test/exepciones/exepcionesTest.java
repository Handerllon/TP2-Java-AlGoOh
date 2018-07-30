package exepciones;

import Modelo.carta.Sacrificio;
import Modelo.carta.campo.CartaCampo;
import Modelo.carta.campo.FabricaCartasCampo;
import Modelo.carta.excepciones.CartaInvalidaError;
import Modelo.carta.excepciones.NoHayCartasParaSacrificarError;
import Modelo.carta.excepciones.NoHayTresDragonesBlancosParaSacrificioError;
import Modelo.carta.magica.CartaMagica;
import Modelo.carta.magica.FabricaCartasMagicas;
import Modelo.carta.monstruo.*;
import Modelo.carta.trampa.CartaTrampa;
import Modelo.carta.trampa.FabricaCartasTrampa;
import Modelo.region.RegionMonstruos;
import Modelo.region.excepciones.RegionSinEspacioLibre;
import org.junit.Test;

public class exepcionesTest
{
    @Test(expected = CartaInvalidaError.class)
    public void test02CartaInvalidaErrorEnFabricaDeCartasCampo()
    {

        FabricaCartasCampo fabrica = new FabricaCartasCampo(null, null);

        CartaCampo algo = fabrica.getCarta("CartaSinNombre");
    }

    @Test(expected = CartaInvalidaError.class)
    public void test03CartaInvalidaErrorEnFabricaDeCartasMagica()
    {

        FabricaCartasMagicas fabrica = new FabricaCartasMagicas(null, null);

        CartaMagica algo = fabrica.getCarta("CartaSinNombre");
    }

    @Test(expected = CartaInvalidaError.class)
    public void test04CartaInvalidaErrorEnFabricaDeCartasTrampa()
    {

        FabricaCartasTrampa fabrica = new FabricaCartasTrampa(null, null);

        CartaTrampa algo = fabrica.getCarta("CartaSinNombre");
    }

    @Test(expected = CartaInvalidaError.class)
    public void test05CartaInvalidaErrorEnFabricaDeCartasMonstruo()
    {

        FabricaCartasMonstruo fabrica = new FabricaCartasMonstruo(null, null);

        CartaMonstruo algo = fabrica.getCarta("CartaSinNombre");
    }

    @Test(expected = NoHayCartasParaSacrificarError.class)
    public void test06NoHayCartasParaSacrificarError()
    {

        CartaMonstruo carta = new BlueEyesWhiteDragon(null, null);

        //No hay cartas para sacrificar, y la Modelo.carta requiere sacrificios para ser invocada
        Sacrificio sacrificio = new Sacrificio();

        carta.summon(sacrificio);
    }

    @Test(expected = NoHayTresDragonesBlancosParaSacrificioError.class)
    public void test07NoHayCartasParaSacrificarError()
    {

        CartaMonstruo carta = new BlueEyesUltimateDragon(null, null);

        //No hay cartas para sacrificar, y la Modelo.carta requiere sacrificios para ser invocada
        Sacrificio sacrificio = new Sacrificio();

        carta.summon(sacrificio);
    }

    @Test(expected = RegionSinEspacioLibre.class)
    public void test09RegionSinEspacioLibre()
    {

        RegionMonstruos region = new RegionMonstruos(null);

        CartaMonstruo carta = new GaiaTheFierceKnight(null, null);

        for (int i = 0; i < 6; i++)
        {

            region.colocarCarta(carta);
        }
    }
}
	
