public class Caixa {
    String name;
    private boolean compraFinalizada = false;

    public Caixa(String name) {
        this.name = name;
        System.out.println("Oberta la caixa <" + name + ">");
    }

    public synchronized void agafaCompra() {
        try {
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
        System.out.println("<" + this.name + "> La Caixa ja está disponible, desperta fils..");
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
