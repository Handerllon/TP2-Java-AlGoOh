package Modelo.carta.ataque;

import Modelo.carta.monstruo.CartaMonstruo;

public interface Ataque
{
    void ejecutar(CartaMonstruo atacante, CartaMonstruo atacada);

    void ejecutar(CartaMonstruo atacante);
}
