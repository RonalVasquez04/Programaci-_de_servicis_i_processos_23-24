public class main {
    public static void main(String[] args) {

        Pont p1 = new Pont();
        Thread fil1 = new Thread(new Cotxe("cotxe1", "esquerra", p1), "cotxe1");
        Thread fil2 = new Thread(new Cotxe("cotxe2", "dreta", p1), "cotxe2");
        Thread fil3 = new Thread(new Cotxe("furgoneta1", "dreta", p1), "furgoneta1");
        Thread fil4 = new Thread(new Cotxe("furgoneta2", "esquerra", p1), "furgoneta2");

        fil1.start();
        fil2.start();
        fil3.start();
        fil4.start();

        try {
            fil1.join();
            fil2.join();
            fil3.join();
            fil4.join();
        } catch (Exception e) {
            // TODO: handle exception
        }
        System.out.println("[" + Thread.currentThread().getName() + "] Finalizació fil ppal");
    }
}
