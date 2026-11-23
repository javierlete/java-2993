package com.ipartek.formacion.oop.pruebas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class InterfacesPrueba {
	public static void main(String[] args) {
		Collection<Rodable> rodables = new ArrayList<>();

		rodables.add(new Balon());
		rodables.add(new Naranja());

		Iterable<Rodable> iterable = rodables;
		
		Iterator<Rodable> iterator = iterable.iterator();
		
		while(iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		
		for (Rodable rodable : iterable) {
			if (rodable instanceof Comestible c) {
				c.comer();
			}

			rodable.rodar();

			if (rodable instanceof Comestible c) {
				c.comer();
			}
		}
	}
}

interface Rodable {
	void rodar();
}

interface Comestible {
	void comer();
}

class Fruto {
}

class ObjetoDeporte {
}

class Balon extends ObjetoDeporte implements Rodable {

	@Override
	public void rodar() {
		System.out.println("BALON rodando");
	}
}

class Naranja extends Fruto implements Comestible, Rodable {
	private boolean porElSuelo = false;

	@Override
	public void rodar() {
		System.out.println("NARANJA rodando");
		porElSuelo = true;
	}

	@Override
	public void comer() {
		if (porElSuelo) {
			System.out.println("PUAG que asco");
		} else {
			System.out.println("ÑAM que rica");
		}
	}
}
