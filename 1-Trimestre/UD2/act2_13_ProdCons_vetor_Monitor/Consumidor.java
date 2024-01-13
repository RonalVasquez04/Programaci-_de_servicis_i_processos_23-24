public class Consumidor implements Runnable{
    
    public Monitor monitor;
    private int numElementsConsumits;

    public Consumidor(Monitor monitor, int nElements){
        this.monitor = monitor;
        this.numElementsConsumits = nElements;
        System.out.println("Creació d'un CONSUMIDOR que consumirà" + nElements +"elements");

    }

    public void run(){
        int numero =  -1;
        for (int i = 0; i < numElementsConsumits; i++) {
            numero = monitor.consumir();
            System.out.println("["+Thread.currentThread().getName()+"] Número agafat:" + numero);
            
        }
    }
}
