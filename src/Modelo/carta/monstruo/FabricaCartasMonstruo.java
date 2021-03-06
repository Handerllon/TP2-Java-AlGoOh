package Modelo.carta.monstruo;

import Modelo.Jugador;
import Modelo.carta.excepciones.CartaInvalidaError;

import java.util.ArrayList;

public class FabricaCartasMonstruo
{
    private Jugador jugador, oponente;

    public FabricaCartasMonstruo(Jugador jugador, Jugador oponente)
    {
        this.jugador = jugador;
        this.oponente = oponente;
    }

    public ArrayList<String> getNombresMonstruosNormales()
    {
        ArrayList<String> nombres = new ArrayList<String>();

        String nombre;
        nombre = new AlexandriteDragon(jugador, oponente).getNombre();
        nombres.add(nombre);
        nombre = new AmphibianBeast(jugador, oponente).getNombre();
        nombres.add(nombre);
        nombre = new AncientBrain(jugador, oponente).getNombre();
        nombres.add(nombre);
        nombre = new AncientTool(jugador, oponente).getNombre();
        nombres.add(nombre);
        nombre = new Bitron(jugador, oponente).getNombre();
        nombres.add(nombre);
        nombre = new BlueEyesWhiteDragon(jugador, oponente).getNombre();
        nombres.add(nombre);
        nombre = new CharcoalInpachi(jugador, oponente).getNombre();
        nombres.add(nombre);
        nombre = new GaiaTheFierceKnight(jugador, oponente).getNombre();
        nombres.add(nombre);

        return nombres;
    }

    public ArrayList<String> getNombresMonstruosNoNormales()
    {
        ArrayList<String> nombres = new ArrayList<String>();

        String nombre;
        nombre = new BlueEyesUltimateDragon(jugador, oponente).getNombre();
        nombres.add(nombre);
        nombre = new ExodiaTheForbiddenOne(jugador, oponente).getNombre();
        nombres.add(nombre);
        nombre = new Jinzo7(jugador, oponente).getNombre();
        nombres.add(nombre);
        nombre = new LeftArmOfTheForbiddenOne(jugador, oponente).getNombre();
        nombres.add(nombre);
        nombre = new LeftLegOfTheForbiddenOne(jugador, oponente).getNombre();
        nombres.add(nombre);
        nombre = new ManEaterBug(jugador, oponente).getNombre();
        nombres.add(nombre);
        nombre = new RightArmOfTheForbiddenOne(jugador, oponente).getNombre();
        nombres.add(nombre);
        nombre = new RightLegOfTheForbiddenOne(jugador, oponente).getNombre();
        nombres.add(nombre);

        return nombres;
    }

    public CartaMonstruo getCarta(String nombreCarta)
    {
        switch (nombreCarta)
        {
            // Monstruos.
            case "Alexandrite Dragon":
                return new AlexandriteDragon(jugador, oponente);
            case "Amphibian Beast":
                return new AmphibianBeast(jugador, oponente);
            case "Ancient Brain":
                return new AncientBrain(jugador, oponente);
            case "Ancient Tool":
                return new AncientTool(jugador, oponente);
            case "Bitron":
                return new Bitron(jugador, oponente);
            case "Blue-Eyes Ultimate Dragon":
                return new BlueEyesUltimateDragon(jugador, oponente);
            case "Blue-Eyes White Dragon":
                return new BlueEyesWhiteDragon(jugador, oponente);
            case "Charcoal Inpachi":
                return new CharcoalInpachi(jugador, oponente);
            case "Exodia The Forbidden One":
                return new ExodiaTheForbiddenOne(jugador, oponente);
            case "Gaia The Fierce Knight":
                return new GaiaTheFierceKnight(jugador, oponente);
            case "Jinzo #7":
                return new Jinzo7(jugador, oponente);
            case "Left Arm Of The Forbidden One":
                return new LeftArmOfTheForbiddenOne(jugador, oponente);
            case "Left Leg Of The Forbidden One":
                return new LeftLegOfTheForbiddenOne(jugador, oponente);
            case "Man-Eater Bug":
                return new ManEaterBug(jugador, oponente);
            case "Right Arm Of The Forbidden One":
                return new RightArmOfTheForbiddenOne(jugador, oponente);
            case "Right Leg Of The Forbidden One":
                return new RightLegOfTheForbiddenOne(jugador, oponente);
            default:
                throw new CartaInvalidaError();
        }
    }
}
