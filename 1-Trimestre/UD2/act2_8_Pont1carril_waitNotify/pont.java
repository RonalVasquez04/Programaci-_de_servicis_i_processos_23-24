package act2_8_Pont1carril_waitNotify;

public class pont {


    private Boolean paso;

    public pont() {
        paso = true;
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
            System.out.println("[" + Thread.currentThread().getName() + "] ** Travessant el pont per anar cap a " + direccion  + " **");

            Thread.sleep(30000/10);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void deixaPont() {
        System.out.println("[" + Thread.currentThread().getName() + "] ** Eixint del pont **");
        this.paso = true;
    }

    public synchronized void metodeWait() {
        try {
            while (!isPontDisponible()) {
                System.out.println(
                        "[" + Thread.currentThread().getName() + "] entraPont() -> El pont NO está disponible, espera zZzZz...");
                wait();
            }
            entraEnPuente();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public synchronized void metodeCanviaEstatPont() {
        deixaPont();
        System.out.println(
                "[" + Thread.currentThread().getName() + "]  ixPont() -> El pont ja esta disponible, desperta vehicles...");
        notifyAll();
    }
}
