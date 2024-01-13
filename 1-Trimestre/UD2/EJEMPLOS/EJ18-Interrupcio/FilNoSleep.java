package Exemple2_18_Interrupcio;

public class FilNoSleep implements Runnable{
	//attr
	String nom;

	//Constructor
	FilNoSleep (String nom) { //constructor
		this.nom =  nom;
	}

	public void run() { //escriu adéu 5 vegades

		while(!Thread.currentThread().isInterrupted()) {
			for(long i=0; i<1000000000L; i++) {
				//No fer res
			}//for
			System.out.println("["+this.nom+"]");
		}
		System.out.println("["+ this.nom +"] **INTERROMPUT**");
	}//run
}//class
