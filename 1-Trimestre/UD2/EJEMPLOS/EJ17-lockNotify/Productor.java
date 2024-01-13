package Exemple2_17_ProdCons_Monitor_lockNotify;

public class Productor implements Runnable{
	//Monitor, gestiona la comunicació i la sincronització entre fils
	private Monitor monitor;
	private int numElementsGenerats;
	
	//Cnstructor
	public Productor( Monitor monitor, int nElements) {
		this.monitor = monitor;
		this.numElementsGenerats = nElements;
		System.out.println("["+Thread.currentThread().getName()+"]Creació d'un PRODUCTOR que produirà "
		+ nElements +" elements");
	}
	//variable del número a passar al consumidor
	private int numero = 0;

	public void run() {
		// Posa numElementsGenerats números aleatoris a disposició del consumidor
		for( int i=0; i < numElementsGenerats; i++ ) {
			numero = (int)(10*Math.random());
			monitor.produir(numero); //deixa el número al MONITOR
			System.out.println("[" +Thread.currentThread().getName() 
					+ "] Produït el número :"+numero );
			try { //espera per produir-ne un de nou
				Thread.sleep((int)(Math.random() * 2000));
			} catch( InterruptedException e ) {}
		}//for
	}
}//class
