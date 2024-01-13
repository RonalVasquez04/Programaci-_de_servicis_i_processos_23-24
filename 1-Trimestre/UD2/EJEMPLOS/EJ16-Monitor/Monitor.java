package Exemple2_16_ProdCons_Monitor;

public class Monitor {
	//atributs PROTEGITS
	private int dada; //buffer
	private int tempsEsperaAccio;

	//atributs PROTEGITS CONDICIO
	//control de l’estat de la cua, condició per executar
	private boolean dadaPlena;
	private boolean dadaBuida;

	//CONSTRUCTOR
	public Monitor(int tempsEsperaAccio) {
		dada = 0;			//valor inicial
		this.tempsEsperaAccio = tempsEsperaAccio;

		dadaPlena = false;	//Inicialment NO es plena
		dadaBuida = true;	//Inicialment es buida
		System.out.println("["+Thread.currentThread().getName()+"] Creació d'un MONITOR");		
	}
	//--------------------------------------
	public synchronized int consumir(){
		int valor = -1;
		try {
			// Si la dada es troba buida envia el fil a la cua d'espera
			while(dadaBuida) { //ix quan cuaBuida és false
				System.out.println("[" +Thread.currentThread().getName() 
						+ "] Dada BUIDA! Dorm zZz...");
				wait(); 
			}//while

			valor = dada;
			
			Thread.sleep((int)(Math.random() * tempsEsperaAccio));
			dadaBuida = true; // La dada es troba novament buida
			dadaPlena = false;// La dada no està plena perquè s'ha consumit un número

			//Si la dada era plena i s'ha consumit un element, desperta fils Productors bloquejats
			System.out.println("[" +Thread.currentThread().getName() 
					+ "] <Dada BUIDA: Desperta PRODUCTOR per produir element>");
			notifyAll();


		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// Retorna el número al fil consumidor
		return(valor);
	}//consumir

	//--------------------------------------
	public synchronized void produir(int num){
		try {
			// Si la dada està plena, no hi entren més números, enviem el fil a la cua d'espera
			while(dadaPlena) {
				//Si cuaPlena és false, podrà continuar produint

				System.out.println("[" +Thread.currentThread().getName() 
						+ "] Dada PLENA! Dorm zZz...");
				wait(); 
			}//while
			// afegit el nou número a la dada
			dada = num;
			
			Thread.sleep((int)(Math.random() * tempsEsperaAccio));
			// La dada està plena
			dadaPlena = true;

			//dadaBuida és fals perquè acaben de produir un número
			dadaBuida = false;
			//Si s'ha produit un primer element, desperta fils Consumidors bloquejats
			System.out.println("[" +Thread.currentThread().getName() 
					+ "] <Dada PLENA: Desperta CONSUMIDOR per consumir element>");
			notifyAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}//put
}//class
