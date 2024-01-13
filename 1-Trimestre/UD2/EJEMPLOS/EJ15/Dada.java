package Exemple2_15_ProdCons_Inicial;

public class Dada
{
	//attr
	private int element;
	private int tempsEsperaAccio;

	//Constructor
	public Dada(int tempsEsperaAccio) {
		element = -1;
		this.tempsEsperaAccio = tempsEsperaAccio;
		System.out.println("["+Thread.currentThread().getName()+"] Creacio de la DADA amb un temps de processament de "
		+ tempsEsperaAccio + " secs.");
	}

	
	//---------- Llig l'element de Dada
	public int consumir(){
		try {
			Thread.sleep((int)(Math.random() * tempsEsperaAccio));
		} catch(InterruptedException e) {}
		return element;
	}//get

	//--------- Escriu l'element de Dada
	public void produir(int value){
		element = value;
		try {
			Thread.sleep((int)(Math.random() * tempsEsperaAccio));
		} catch( InterruptedException e) {}
	}//put
}//class