package carta.monstruo;

import AlGoOh.Jugador;
import carta.CartaMonstruo;
import carta.excepciones.CartaInvalidaError;

import java.util.ArrayList;

public class FabricaCartasMonstruo
{
    private Jugador jugador, oponente;

    public FabricaCartasMonstruo(Jugador jugador, Jugador oponente)
    {
        this.jugador = jugador;
        this.oponente = oponente;
    }

    public ArrayList<String> obtenerNombresMonstruosNormales()
    {
        ArrayList<String> nombres = new ArrayList<String>();

        String nombre;
        nombre = new AlexandriteDragon(jugador, oponente,"resources/imagenes/monstruo/AlexandriteDragon.png").obtenerNombre();
        nombres.add(nombre);
        nombre = new AmphibianBeast(jugador, oponente,"resources/imagenes/monstruo/AmphibianBeast.jpg").obtenerNombre();
        nombres.add(nombre);
        nombre = new AncientBrain(jugador, oponente,"resources/imagenes/monstruo/AncientBrain.png").obtenerNombre();
        nombres.add(nombre);
        nombre = new AncientTool(jugador, oponente,"resources/imagenes/monstruo/AncientTool.png").obtenerNombre();
        nombres.add(nombre);
        nombre = new Bitron(jugador, oponente,"resources/imagenes/monstruo/Bitron.png").obtenerNombre();
        nombres.add(nombre);
        nombre = new BlueEyesWhiteDragon(jugador, oponente,"resources/imagenes/monstruo/BlueEyesWhiteDragon.png").obtenerNombre();
        nombres.add(nombre);
        nombre = new CharcoalInpachi(jugador, oponente,"resources/imagenes/monstruo/CharcoalInpachi.png").obtenerNombre();
        nombres.add(nombre);
        nombre = new GaiaTheFierceKnight(jugador, oponente,"resources/imagenes/monstruo/GaiaTheFierceKnight.png").obtenerNombre();
        nombres.add(nombre);

        return nombres;
    }

    public ArrayList<String> obtenerNombresMonstruosNoNormales()
    {
        ArrayList<String> nombres = new ArrayList<String>();

        String nombre;
        nombre = new BlueEyesUltimateDragon(jugador, oponente,"resources/imagenes/monstruo/BlueEyesUltimateDragon.png").obtenerNombre();
        nombres.add(nombre);
        nombre = new ExodiaTheForbiddenOne(jugador, oponente,"resources/imagenes/monstruo/ExodiaTheForbiddenOne.png").obtenerNombre();
        nombres.add(nombre);
        nombre = new Jinzo7(jugador, oponente,"resources/imagenes/monstruo/Jinzo7.jpg").obtenerNombre();
        nombres.add(nombre);
        nombre = new LeftArmOfTheForbiddenOne(jugador, oponente,"resources/imagenes/monstruo/LeftArmOfTheForbiddenOne.png").obtenerNombre();
        nombres.add(nombre);
        nombre = new LeftLegOfTheForbiddenOne(jugador, oponente,"resources/imagenes/monstruo/LeftLegOfTheForbiddenOne.png").obtenerNombre();
        nombres.add(nombre);
        nombre = new ManEaterBug(jugador, oponente,"resources/imagenes/monstruo/ManEaterBug.jpg").obtenerNombre();
        nombres.add(nombre);
        nombre = new RightArmOfTheForbiddenOne(jugador, oponente,"resources/imagenes/monstruo/RightArmOfTheForbiddenOne.png").obtenerNombre();
        nombres.add(nombre);
        nombre = new RightLegOfTheForbiddenOne(jugador, oponente,"resources/imagenes/monstruo/RightLegOfTheForbiddenOne.png").obtenerNombre();
        nombres.add(nombre);

        return nombres;
    }

    public CartaMonstruo obtenerCarta(String nombreCarta)
    {
        switch (nombreCarta)
        {
            // Monstruos.
            case "Alexandrite Dragon":
                return new AlexandriteDragon(jugador, oponente,"resources/imagenes/monstruo/AlexandriteDragon.png");
            case "Amphibian Beast":
                return new AmphibianBeast(jugador, oponente,"resources/imagenes/monstruo/AmphibianBeast.jpg");
            case "Ancient Brain":
                return new AncientBrain(jugador, oponente,"resources/imagenes/monstruo/AncientBrain.png");
            case "Ancient Tool":
                return new AncientTool(jugador, oponente,"resources/imagenes/monstruo/AncientTool.png");
            case "Bitron":
                return new Bitron(jugador, oponente,"resources/imagenes/monstruo/Bitron.png");
            case "Blue-Eyes Ultimate Dragon":
                return new BlueEyesUltimateDragon(jugador, oponente,"resources/imagenes/monstruo/BlueEyesUltimateDragon.png");
            case "Blue-Eyes White Dragon":
                return new BlueEyesWhiteDragon(jugador, oponente,"resources/imagenes/monstruo/BlueEyesWhiteDragon.png");
            case "Charcoal Inpachi":
                return new CharcoalInpachi(jugador, oponente,"resources/imagenes/monstruo/CharcoalInpachi.png");
            case "Exodia The Forbidden One":
                return new ExodiaTheForbiddenOne(jugador, oponente,"resources/imagenes/monstruo/ExodiaTheForbiddenOne.png");
            case "Gaia The Fierce Knight":
                return new GaiaTheFierceKnight(jugador, oponente,"resources/imagenes/monstruo/GaiaTheFierceKnight.png");
            case "Jinzo #7":
                return new Jinzo7(jugador, oponente,"resources/imagenes/monstruo/Jinzo7.jpg");
            case "Left Arm Of The Forbidden One":
                return new LeftArmOfTheForbiddenOne(jugador, oponente,"resources/imagenes/monstruo/LeftArmOfTheForbiddenOne.png");
            case "Left Leg Of The Forbidden One":
                return new LeftLegOfTheForbiddenOne(jugador, oponente,"resources/imagenes/monstruo/LeftLegOfTheForbiddenOne.png");
            case "Man-Eater Bug":
                return new ManEaterBug(jugador, oponente,"resources/imagenes/monstruo/ManEaterBug.jpg");
            case "Right Arm Of The Forbidden One":
                return new RightArmOfTheForbiddenOne(jugador, oponente,"resources/imagenes/monstruo/RightArmOfTheForbiddenOne.png");
            case "Right Leg Of The Forbidden One":
                return new RightLegOfTheForbiddenOne(jugador, oponente,"resources/imagenes/monstruo/RightLegOfTheForbiddenOne.png");
            default:
                throw new CartaInvalidaError();
        }
    }
}
