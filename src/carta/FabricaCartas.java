package carta;

import carta.campo.Sogen;
import carta.campo.Wasteland;
import carta.excepciones.CartaInvalidaError;
import carta.magica.DarkHole;
import carta.magica.Fissure;
import carta.magica.PotOfGreed;
import carta.monstruo.*;
import carta.trampa.MagicCylinder;
import carta.trampa.Reinforcements;

import java.util.ArrayList;

public class FabricaCartas extends Carta
{

    public FabricaCartas()
    {
    }

    public ArrayList<String> obtenerNombresMonstruosNormales()
    {
        ArrayList<String> nombres = new ArrayList<String>();

        nombres.add("Alexandrite Dragon");
        nombres.add("Amphibian Beast");
        nombres.add("Ancient Brain");
        nombres.add("Ancient Tool");
        nombres.add("Bitron");
        nombres.add("Blue-Eyes White Dragon");
        nombres.add("Charcoal Inpachi");
        nombres.add("Gaia The Fierce Knight");

        return nombres;
    }

    public ArrayList<String> obtenerNombresMonstruosNoNormales()
    {
        ArrayList<String> nombres = new ArrayList<String>();

        nombres.add("Blue-Eyes Ultimate Dragon");
        nombres.add("Exodia The Forbidden One");
        nombres.add("Jinzo7");
        nombres.add("Left Arm Of The Forbidden One");
        nombres.add("Left Leg Of The Forbidden One");
        nombres.add("Man Eater Bug");
        nombres.add("Right Arm Of The Forbidden One");
        nombres.add("Right Leg Of The Forbidden One");

        return nombres;
    }

    public ArrayList<String> obtenerNombresMagicas()
    {
        ArrayList<String> nombres = new ArrayList<String>();

        nombres.add("Dark Hole");
        nombres.add("Fissure");
        nombres.add("Pot Of Greed");

        return nombres;
    }

    public ArrayList<String> obtenerNombresCampo()
    {
        ArrayList<String> nombres = new ArrayList<String>();

        nombres.add("Sogen");
        nombres.add("Wasteland");

        return nombres;
    }

    public ArrayList<String> obtenerNombresTrampas()
    {
        ArrayList<String> nombres = new ArrayList<String>();

        nombres.add("Magic Cylinder");
        nombres.add("Reinforcements");

        return nombres;
    }

    public Carta obtenerCarta(String nombreCarta)
    {
        switch (nombreCarta)
        {
            // Monstruos.
            case "Alexandrite Dragon":
                return new AlexandriteDragon();
            case "Amphibian Beast":
                return new AmphibianBeast();
            case "Ancient Brain":
                return new AncientBrain();
            case "Ancient Tool":
                return new AncientTool();
            case "Bitron":
                return new Bitron();
            case "Blue-Eyes Ultimate Dragon":
                return new BlueEyesUltimateDragon();
            case "Blue-Eyes White Dragon":
                return new BlueEyesWhiteDragon();
            case "Charcoal Inpachi":
                return new CharcoalInpachi();
            case "Exodia The Forbidden One":
                return new ExodiaTheForbiddenOne();
            case "Gaia The Fierce Knight":
                return new GaiaTheFierceKnight();
            case "Jinzo7":
                return new Jinzo7();
            case "Left Arm Of The Forbidden One":
                return new LeftArmOfTheForbiddenOne();
            case "Left Leg Of The Forbidden One":
                return new LeftLegOfTheForbiddenOne();
            case "Man Eater Bug":
                return new ManEaterBug();
            case "Right Arm Of The Forbidden One":
                return new RightArmOfTheForbiddenOne();
            case "Right Leg Of The Forbidden One":
                return new RightLegOfTheForbiddenOne();
            // Magicas.
            case "Dark Hole":
                return new DarkHole();
            case "Fissure":
                return new Fissure();
            case "Pot Of Greed":
                return new PotOfGreed();
            // Campo.
            case "Sogen":
                return new Sogen();
            case "Wasteland":
                return new Wasteland();
            //Trampa.
            case "Magic Cylinder":
                return new MagicCylinder();
            case "Reinforcements":
                return new Reinforcements();
            default:
                throw new CartaInvalidaError();
        }
    }
}
