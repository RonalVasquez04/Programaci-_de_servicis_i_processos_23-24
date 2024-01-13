public class SemaforSincron {
    private boolean puenteUsoDreta;
    private boolean puenteUsoEsquerra;

    public SemaforSincron() {
        puenteUsoEsquerra = true;
        puenteUsoDreta = true;
    }

    public synchronized void metodeWait(String direccion) {
        while (!puenteEnUso(direccion)) {
            try {
                System.out.println("[" + Thread.currentThread().getName() + "]  Wait () -->");
                wait();
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
        if (direccion == "esquerra") {
            puenteUsoEsquerra = false;
        } else {
            puenteUsoDreta = false;
        }
    }

    public synchronized void metodeWakeUp(String direccion) {
        // setPuenteUso(false, direccion);
        if (direccion == "esquerra") {
            puenteUsoEsquerra = true;
        } else {
            puenteUsoDreta = true;
        }
        System.out.println("[" + Thread.currentThread().getName() + "] Notify() --> ");
        notify();
    }

    public boolean puenteEnUso(String direccion) {
        if (direccion == "esquerra") {
            return puenteUsoEsquerra;
        } else {
            return puenteUsoDreta;
        }
    }

    public void setPuenteUso(boolean puenteUso, String direccion) {

        if (direccion == "esquerra") {
            this.puenteUsoEsquerra = puenteUso;
        } else {
            this.puenteUsoDreta = puenteUso;
        }
    }
}
