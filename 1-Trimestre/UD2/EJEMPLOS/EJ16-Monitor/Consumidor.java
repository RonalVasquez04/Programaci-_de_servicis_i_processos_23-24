package Exemple2_16_ProdCons_Monitor;

public class Consumidor implements Runnable{
	//Monitor, gestiona la comunicació i la sincronització entre fils
	private Monitor monitor;
	private int numElementsConsumits;

	//Constructor 
	public Consumidor( Monitor monitor, int nElements) {
		this.monitor = monitor;
		this.numElementsConsumits = nElements;
		System.out.println("["+Thread.currentThread().getName()+"] Creació d'un CONSUMIDOR que consumirà "
		+ nElements +" elements");
	}

	public void run() {
		int num; //variable del número que li passa el productor
		// Rep numElementsConsumits números des del productor al MONITOR
		for( int i=0; i < numElementsConsumits; i++ ){
			num = monitor.consumir(); //agafa el número i el mostra
			System.out.println( "[" +Thread.currentThread().getName() 
					+ "] Número agafat: "+num );
			try { //espera 2 segons per agafar-ne un altre
				Thread.sleep((int)(Math.random() * 2000 ));
			} catch( InterruptedException e ) {}
		}//for
	}
}//class
