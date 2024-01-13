public class Main {
    public static void main(String[] args) {
        System.out.println("[Fil ppal] Versió Productor-Consumidor amb 1 element amb un Monitor");
        Monitor monitor = new Monitor(3000);

        int numElementsAprocessar = 5;

        Thread productor = new Thread(new Productor(monitor, numElementsAprocessar),"PRODUCTOR");
        Thread consumidor = new Thread(new Consumidor(monitor, numElementsAprocessar),"CONSUMIDOR");

        System.out.println("[Fil ppal] Inici fils.....");
        consumidor.start();
        productor.start();
    }
}
