import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Caixa {
    String name;
    private boolean compraFinalizada = false;
    private Lock cadenat;
    private Condition disponible;

    public Caixa(String name) {
        this.name = name;
        cadenat = new ReentrantLock();
        disponible = cadenat.newCondition();
        System.out.println("Oberta la caixa <" + name + ">");
    }

    public void agafaCompra() {
        cadenat.lock();
        System.out.println("<" + this.name + "> Llegint la compra de [" + Thread.currentThread().getName() + "]");
        cadenat.unlock();

    }

    public void cobramentCompra(float precio) {
        cadenat.lock();
        System.out.println("<" + this.name + "> Import de la compra de [" + Thread.currentThread().getName()
                + "] és de " + precio + " euros");
        cadenat.unlock();
    }

    public void ticketCompra() {
        cadenat.lock();

        System.out.println("<" + this.name + "> Donant ticket compra a ["
                + Thread.currentThread().getName() + "]");
        System.out.println("<" + this.name + "> La Caixa ja está disponible, desperta fils..");
        compraFinalizada = true;
        disponible.signalAll();
        cadenat.unlock();
    }

    public void esperarProcesoCompletado() {
        try {
            cadenat.lock();
            while (!compraFinalizada) {
                disponible.await();
            }
            cadenat.unlock();
        } catch (Exception e) {
            // TODO: handle exception
        }

    }
}
