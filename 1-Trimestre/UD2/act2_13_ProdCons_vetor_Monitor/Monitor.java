import java.util.Stack;

public class Monitor{

    private Stack<Integer> dada;

    private int tempsEsperaAccio;

    private boolean dadaPlena;
    private boolean dadaBuida;

    public Monitor(int tempsEsperaAccio){


        dada = new Stack<Integer>();
        this.tempsEsperaAccio = tempsEsperaAccio;

        dadaPlena = false;
        dadaBuida = true;
        System.out.println("Creació d'un MONITOR");
    }

    public synchronized int consumir(){
        int valor = -1;
        try {
            while (dadaBuida) {
                System.out.println("[" + Thread.currentThread().getName() + "] <Dada BUIDA: Espera per AGAFAR element zzz>");
                wait();
            }

            valor = dada.pop();

            Thread.sleep((int)(Math.random() * tempsEsperaAccio));
            dadaBuida = true;
            dadaPlena = false;

            System.out.println("[" + Thread.currentThread().getName() + "] <Dada BUIDA: Desperta PRODUCTOR per produir element>");
            notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return(valor);
    }

    public synchronized void produir(int num){
        
        try {
            while (dadaPlena) {
                System.out.println("[" + Thread.currentThread().getName() + "] <Dada PLENA: Espera per DEIXAR element zzz>");
                wait();
            }

            dada.push(num);

            Thread.sleep((int)(Math.random()*tempsEsperaAccio));

            dadaPlena = true;

            dadaBuida = false;

            System.out.println("[" + Thread.currentThread().getName() + "] <Dada PLENA: Desperta CONSUMIDOR per consumir element>");

            notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}