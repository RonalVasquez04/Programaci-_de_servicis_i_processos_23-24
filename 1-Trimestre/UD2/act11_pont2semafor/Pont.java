public class Pont {

    private Boolean pasoEsquerra;
    private Boolean pasoDreta;
    private SemaforSincron semaforSincron;

    public Pont(SemaforSincron semaforSincron) {

        this.semaforSincron = semaforSincron;

        pasoEsquerra = true;
        pasoDreta = true;

    }

    public void setPaso(Boolean paso) {
        this.pasoEsquerra = paso;
    }

    public Boolean isPontDisponible(String direccion) {

        if (direccion == "esquerra") {
            if (pasoEsquerra) {
                return true;
            } else {
                return false;
            }
        } else {
            if (pasoDreta) {
                return true;
            } else {
                return false;
            }
        }

    }

    public void entraEnPuente(String direccion) {
        for (int i = 0; i < 10000; i++) {

        }
        if (direccion == "esquerra") {
            this.pasoEsquerra = false;
        } else {
            this.pasoDreta = false;
        }

        System.out
                .println("[" + Thread.currentThread().getName() + "] ** Entrant al Pont  --- " + direccion + " --- **");
    }

    public void usarPont(String direccion) {
        // semaforSincron.metodeWait(direccion);
        try {
            System.out.println("[" + Thread.currentThread().getName() + "] ** Travessant el pont per anar cap a "
                    + direccion + " **");

            Thread.sleep(10000 / 10);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void deixaPont(String direccion) {
        System.out.println("[" + Thread.currentThread().getName() + "] ** Eixint del pont  --- " + direccion+ " --- **");
        if (direccion == "esquerra") {
            this.pasoEsquerra = true;
        } else if (direccion == "dreta") {
            this.pasoDreta = true;
        }
        // semaforSincron.metodeWakeUp(direccion);
    }

    public void metodeWait(String direccion) {
        try {

            // while (!isPontDisponible(direccion)) {
            // System.out.println(
            // "[" + Thread.currentThread().getName() + "] entraPont() -> El pont NO está
            // disponible --- "
            // + direccion + " --- , espera zZzZz...");
            // disponible.await();
            // }
            semaforSincron.metodeWait(direccion);
            entraEnPuente(direccion);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void metodeCanviaEstatPont(String direccion) {
        deixaPont(direccion);
        System.out.println(
                "[" + Thread.currentThread().getName()
                        + "]  ixPont() -> El pont ja esta disponible, desperta vehicles...");
        semaforSincron.metodeWakeUp(direccion);
    }
}
