package Exemple2_15_ProdCons_Inicial;

public class Consumidor implements Runnable{

	//Attib
	private Dada dada;
	private int numElementsConsumits;

	//Constructor
	public Consumidor(Dada dada, int nElements) {
		this.dada = dada;
		this.numElementsConsumits = nElements;
		System.out.println("["+Thread.currentThread().getName()+"]Creació d'un CONSUMIDOR que consumirà "
				+ nElements +" elements");
	}

	public void run() {
		int numero = -1; //Valor predefinit
		//Consumeix numElementsConsumits numeros
		for( int i=0; i < numElementsConsumits; i++ ){
			numero = dada.consumir();	//consumeix la dada
			System.out.println( "["+Thread.currentThread().getName()+"] Número consumit: "+ numero);
		}//for
	}//run
}//class


