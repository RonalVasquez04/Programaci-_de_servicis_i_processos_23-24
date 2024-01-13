package Exemple2_18_Interrupcio;

public class FilSleep implements Runnable{
	//attr
	String nom;

	//Constructor
	FilSleep (String nom) { //constructor
		this.nom =  nom;
	}

	public void run() { //escriu adéu 5 vegades
		try {
			for(int i=0; i<5; i++) {
				System.out.println("["+this.nom+"]");

				Thread.sleep(1000);

			}//for
		} catch (InterruptedException e) {
			System.err.println("["+ this.nom +"] **INTERROMPUT**");
		}
	}//run
}//class
