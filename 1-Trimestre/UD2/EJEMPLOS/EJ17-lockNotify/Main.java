package Exemple2_17_ProdCons_Monitor_lockNotify;

public class Main {

	public static void main(String[] args) {
		System.out.println("["+Thread.currentThread().getName()+"] Versió Productor-Consumidor amb 1 element amb un Monitor usant lock&Condition");
		//creacío d'un monitor
		//per comunicar i sincronitzar productor / consumidor
		Monitor monitor = new Monitor(3000);	//tempsEsperaAccio
		int numElementsAprocessar = 5;
		
		//crea els fils per al productor i consumidor
		// on s'els assigna un nou objecte amb els arguments: 
		//Monitor i la quantitat items a processar
		Thread productor = new Thread(new Productor(monitor, numElementsAprocessar), "PRODUCTOR");
		Thread consumidor = new Thread(new Consumidor(monitor, numElementsAprocessar), "CONSUMIDOR");
		
		//Executem els fils
		System.out.println("["+Thread.currentThread().getName()+"] Inici fils.....");
		consumidor.start();
		productor.start();	
		
		try {
			productor.join();
			consumidor.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("["+Thread.currentThread().getName()+"] Finalització programa Productor-Consumidor amb 1 element amb un Monitor usant lock&Condition");
	}//main
}//class
