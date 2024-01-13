package Exemple2_15_ProdCons_Inicial;

public class Main {

	public static void main(String[] args) {
		System.out.println("[" + Thread.currentThread().getName() + "] Versió Productor-Consumidor amb 1 element");
		Dada dada = new Dada(3000); // tempsEsperaAccio

		int numElements = 5; // Num elements a produir i consumir
		Thread productor = new Thread(new Productor(dada, numElements), "Productor");
		Thread consumidor = new Thread(new Consumidor(dada, numElements), "Consumidor");

		productor.start();
		consumidor.start();

		try {
			productor.join();
			consumidor.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(
				"[" + Thread.currentThread().getName() + "] Finalització programa Productor-Consumidor amb 1 element");
	}// main
}// class
