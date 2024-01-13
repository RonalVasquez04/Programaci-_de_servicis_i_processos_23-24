package Exemple2_18_Interrupcio;

public class Main {

	public static void main(String[] args) {
		try {
			Thread fsleep = new Thread(new FilSleep("Fil1"));
			Thread fnsleep = new Thread(new FilNoSleep("Fil2"));

			fsleep.start();
			fnsleep.start();

			Thread.sleep(5000);		//interrupcio del fil ppal

			fsleep.interrupt();		//interrupcio del fil Sleep
			fnsleep.interrupt();	//interrupcio del fil NoSleep
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}//main
}//class
