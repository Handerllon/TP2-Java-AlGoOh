package Vista;

import Modelo.carta.Carta;

public interface ObjectoObservador<T extends Carta>
{
    void update();
}
