package Exemple2_17_ProdCons_Monitor_lockNotify;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Monitor {
	//atributs PROTEGITS
	private int dada; //buffer
	private int tempsEsperaAccio;

	//atributs PROTEGITS CONDICIO
	//control de l’estat de la cua, condició per executar
	private Lock bloqueig;
	private boolean dadaPlena;
	private boolean dadaBuida;
	private Condition productor;
	private Condition consumidor;

	//CONSTRUCTOR
	public Monitor(int tempsEsperaAccio) {
		dada = 0;			//valor inicial
		this.tempsEsperaAccio = tempsEsperaAccio;
		dadaPlena = false;	//Inicialment NO es plena
		dadaBuida = true;	//Inicialment es buida

		bloqueig = new ReentrantLock();
		productor = bloqueig.newCondition();
		consumidor = bloqueig.newCondition();
		System.out.println("["+Thread.currentThread().getName()+"] Creació d'un MONITOR");		
	}
	//--------------------------------------
	public int consumir(){
		int valor = -1;
		bloqueig.lock();
		try {
			// Si la dada es troba buida envia el fil a la cua d'espera
			while(dadaBuida) { //ix quan cuaBuida és false
				System.out.println("[" +Thread.currentThread().getName() 
						+ "] Dada BUIDA! Dorm zZz...");
				consumidor.await(); 
			}//while

			valor = dada;
			
			Thread.sleep((int)(Math.random() * tempsEsperaAccio));
			dadaBuida = true; // La dada es troba buida
			dadaPlena = false;// La dada no està plena perquè s'ha consumit un número

			//Si la dada era plena i s'ha consumit un element, desperta fils Productors bloquejats
			System.out.println("[" +Thread.currentThread().getName() 
					+ "] <Dada BUIDA: Desperta PRODUCTOR per produir element>");
			productor.signal();


		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		bloqueig.unlock();
		// Retorna el número al fil consumidor
		return(valor);
	}//consumir

	//--------------------------------------
	public void produir(int num){
		bloqueig.lock();
		try {
			// Si la dada està plena, no hi entren més números, enviem el fil a la cua d'espera
			while(dadaPlena) {
				//Si cuaPlena és false, podrà continuar produint

				System.out.println("[" +Thread.currentThread().getName() 
						+ "] Dada PLENA! Dorm zZz...");
				productor.await(); 
			}//while
			// afegit el nou número a la dada
			dada = num;
			
			Thread.sleep((int)(Math.random() * tempsEsperaAccio));
			// La dada està plena
			dadaPlena = true;

			//dadaaBuida és false perquè acaben de produir un número
			dadaBuida = false;
			//Si s'ha produit un primer element, desperta fils Consumidors bloquejats
			System.out.println("[" +Thread.currentThread().getName() 
					+ "] <Dada PLENA: Desperta CONSUMIDOR per consumir element>");

			consumidor.signal();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		bloqueig.unlock();
	}//produir
}//class
