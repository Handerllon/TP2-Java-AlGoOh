package carta.monstruo;

import carta.ModoDefensa;

public class CharcoalInpachi extends CartaMonstruo
{
    public CharcoalInpachi()
    {
        this.puntosAtaque = 100;
        this.puntosDefensa = 2100;
        this.nivel = 1;
        this.nombre = "Charcoal Inpachi";

        this.modo = new ModoDefensa(this.puntosDefensa);
    }
}
