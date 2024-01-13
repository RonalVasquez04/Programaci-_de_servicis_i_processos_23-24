package act2_9_Pont1carril_lockCondition;

import java.util.concurrent.locks.*;;

public class Pont {

    private Lock cadenat;
    private Condition disponible;
    private Boolean paso;

    public Pont() {
        paso = true;
        cadenat = new ReentrantLock();
        disponible = cadenat.newCondition();
    }

    public void setPaso(Boolean paso) {
        this.paso = paso;
    }

    public Boolean isPontDisponible() {
        if (paso) {
            return true;
        } else {
            return false;
        }
    }

    public void entraEnPuente() {
        for (int i = 0; i < 10000; i++) {

        }

        this.paso = false;
        System.out.println("[" + Thread.currentThread().getName() + "] ** Entrant al Pont **");
    }

    public void usarPont(String direccion) {
        try {
            System.out.println("[" + Thread.currentThread().getName() + "] ** Travessant el pont per anar cap a "
                    + direccion + " **");

            Thread.sleep(10000 / 10);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void deixaPont() {
        System.out.println("[" + Thread.currentThread().getName() + "] ** Eixint del pont **");
        this.paso = true;
    }

    public void metodeWait() {
        try {
            cadenat.lock();
            while (!isPontDisponible()) {
                System.out.println(
                        "[" + Thread.currentThread().getName()
                                + "] entraPont() -> El pont NO está disponible, espera zZzZz...");
                disponible.await();
            }
            entraEnPuente();
            cadenat.unlock();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void metodeCanviaEstatPont() {
        cadenat.lock();
        deixaPont();
        System.out.println(
                "[" + Thread.currentThread().getName()
                        + "]  ixPont() -> El pont ja esta disponible, desperta vehicles...");
        disponible.signalAll();
        cadenat.unlock();
    }
}
