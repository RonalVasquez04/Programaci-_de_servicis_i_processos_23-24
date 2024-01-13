package act2_8_Pont1carril_waitNotify;

import act_5_incidencia.main;

public class Main {
    public static void main(String[] args) {
        pont p1 = new pont();
        Thread fil1 = new Thread(new Cotxe("Ford", "esquerra", p1), "cotxe1");
        Thread fil2 = new Thread(new Cotxe("Ford1", "dreta", p1), "cotxe2");
        Thread fil3 = new Thread(new Cotxe("Ford2", "dreta", p1), "furgoneta1");
        Thread fil4 = new Thread(new Cotxe("Ford2", "esquerra", p1), "furgoneta2");

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
