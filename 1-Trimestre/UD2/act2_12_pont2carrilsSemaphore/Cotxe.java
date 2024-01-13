public class Cotxe implements Runnable {

    private String nom;
    private String direccion;
    Pont puente;

    public Cotxe(String nombre, String direccion, Pont puente) {
        this.puente = puente;
        this.direccion = direccion;
        this.nom = nombre;

        System.out.println("[" + Thread.currentThread().getName() + "]" + " Vehicle " + this.nom + " arriba al pont");
    }

    public void run() {
        System.out.println("[" + Thread.currentThread().getName() + "] Vehicle vol travessar Pont per anar cap a la "
                + direccion);

        puente.metodeWait(direccion);
        puente.usarPont(direccion);
        puente.metodeCanviaEstatPont(direccion);
    }
}