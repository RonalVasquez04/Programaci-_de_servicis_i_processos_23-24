package act2_8_Pont1carril_waitNotify;

public class Cotxe implements Runnable {
    private String nom;
    private String direccion;
    pont puente;

    public Cotxe(String nombre, String direccion, pont puente) {
        this.puente = puente;
        this.direccion = direccion;
        this.nom = nombre;

        System.out.println("[" + Thread.currentThread().getName() + "]" + " Vehicle " + this.nom + " arriba al pont");
    }

    public void run() {
        System.out.println("[" + Thread.currentThread().getName() + "] Vehicle vol travessar Pont per anar cap a la "
                + direccion);

        puente.metodeWait();
        puente.usarPont(direccion);
        puente.metodeCanviaEstatPont();
    }
}
