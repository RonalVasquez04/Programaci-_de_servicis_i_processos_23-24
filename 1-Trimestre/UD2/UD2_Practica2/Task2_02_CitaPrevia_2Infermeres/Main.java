public class Main {
    public static void main(String[] args) {
        QuadreCites quadreCites = new QuadreCites();

        Infermera infermera = new Infermera(quadreCites, "enfermera1");
        Infermera infermera2 = new Infermera(quadreCites, "enfermera2");

        for (int i = 1; i <= 13; i++) {
            if (i % 2 == 0) {
                new Pacient("Pacient" + i, infermera2).start();
            } else {
                new Pacient("Pacient" + i, infermera).start();
            }
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
