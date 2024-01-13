import java.util.concurrent.Semaphore;

public class Caixa {
    String name;
    private boolean compraFinalizada = false;
    private final Semaphore semafor;

    public Caixa(String name) {
        semafor = new Semaphore(2);
        this.name = name;
        System.out.println("Oberta la caixa <" + name + ">");
    }

    public synchronized void agafaCompra() {
        try {
            semafor.acquire();

            System.out.println("<" + this.name + "> Llegint la compra de [" + Thread.currentThread().getName() + "]");

        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    public synchronized void cobramentCompra(float precio) {
        System.out.println("<" + this.name + "> Import de la compra de [" + Thread.currentThread().getName()
                + "] és de " + precio + " euros");
    }

    public synchronized void ticketCompra() {
        System.out.println("<" + this.name + "> Donant ticket compra a ["
                + Thread.currentThread().getName() + "]");
        System.out.println("<" + this.name + "> La <" + name + "> ja está disponible, desperta fils..");
        semafor.release();
        compraFinalizada = true;
        notifyAll();
    }

    public synchronized void esperarProcesoCompletado() {
        try {
            while (!compraFinalizada) {
                wait();
            }
        } catch (Exception e) {
            // TODO: handle exception
        }

    }
}
