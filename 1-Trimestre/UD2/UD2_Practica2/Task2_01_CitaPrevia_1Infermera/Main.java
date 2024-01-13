public class Main {
    public static void main(String[] args) {
        QuadreCites quadreCites = new QuadreCites();
        Infermera infermera = new Infermera(quadreCites);

        for (int i = 1; i <= 10; i++) {
            new Pacient("Pacient" + i, infermera).start();
        }

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\nLlistat complet de torns del dia:");
        quadreCites.imprimeixQuadre();
    }
};
