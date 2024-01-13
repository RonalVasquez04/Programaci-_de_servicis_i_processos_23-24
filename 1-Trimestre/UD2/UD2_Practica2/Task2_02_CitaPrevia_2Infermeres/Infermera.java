public class Infermera {
    private QuadreCites quadreCites;
    private String nombre = "";

    public Infermera(QuadreCites quadreCites, String nombre) {
        this.quadreCites = quadreCites;
        this.nombre = nombre;
    }

    public synchronized void atendrePacient(String pacient) {
        System.out
                .println("[" + pacient + "] " + nombre + " busca un espai al quadre de Cita Previa per a " + pacient
                        + ".");

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
