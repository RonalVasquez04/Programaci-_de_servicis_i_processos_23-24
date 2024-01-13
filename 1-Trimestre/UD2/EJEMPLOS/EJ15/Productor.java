package Exemple2_15_ProdCons_Inicial;

public class Productor implements Runnable {

	// Attib
	private Dada dada;
	private int numElementsGenerats;

	// Constructor
	public Productor(Dada dada, int nElements) {
		this.dada = dada;
		this.numElementsGenerats = nElements;
		System.out.println("[" + Thread.currentThread().getName() + "]Creació d'un PRODUCTOR que produirà "
				+ nElements + " elements");
	}

	public void run() {
		int numero = -1; // Valor predefinit
		// Produeix numElementsConsumits numeros
		for (int i = 0; i < numElementsGenerats; i++) {
			numero = (int) (10 * Math.random());
			dada.produir(numero); // produeix la dada
			System.out.println("[" + Thread.currentThread().getName() + "]  Produït el número: " + numero);
		} // for
	}// run
}// class
