package Modelo.observadores;

import Modelo.region.Region;

public interface ObservadorRegion<R extends Region>
{
    void ingresoCarta(R region);

    void egresoCarta(R region);

    // Notifica si hubo tanto una agregación como una remoción.
    void huboCambios();
}
