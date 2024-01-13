public class Infermera {
    private QuadreCites quadreCites;

    public Infermera(QuadreCites quadreCites) {
        this.quadreCites = quadreCites;
    }

    public synchronized void atendrePacient(String pacient) {
        System.out
                .println("[" + pacient + "] Infermera busca un espai al quadre de Cita Previa per a " + pacient + ".");

        int tornLliure = quadreCites.cercaCitaLliure();
        if (tornLliure != -1) {
            quadreCites.assignaCitaAQuadre(pacient, tornLliure);
            System.out.println("[" + pacient + "] Infermera assigna la Cita Previa per a " + pacient + " a les "
                    + quadreCites.retornaCita(tornLliure) + "h");
        } else {
            System.out.println("[" + pacient + "] Infermera indica que no queden torns, Cita Previa impossible");
        }
    }
}
