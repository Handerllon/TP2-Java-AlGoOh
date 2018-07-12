package vistaJuego;

import java.util.ArrayList;

import carta.Carta;

public interface Observador<T extends Carta> {

	public void update(ArrayList<T> arrayList);
	
	
}
