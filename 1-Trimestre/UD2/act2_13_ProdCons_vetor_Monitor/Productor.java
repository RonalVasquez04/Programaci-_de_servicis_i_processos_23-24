public class Productor implements Runnable{

    private Monitor monitor;
    private int numElementsGenerats;

    public Productor(Monitor monitor, int nElements){
        this.monitor = monitor;
        this.numElementsGenerats = nElements;

        System.out.println("Creació d'un PRODUCTOR que produirà" + nElements + "elements");

    }

    public void run(){
        int numero = -1;

        for (int i = 0; i < numElementsGenerats; i++) {
           numero = (int)(10*Math.random());
           monitor.produir(numero);
           System.out.println("["+Thread.currentThread().getName()+"] Produït el número: " + numero);
           
        }

    }

    
}
