public class Pacient extends Thread {
    private String nom;
    private Infermera infermera;

    public Pacient(String nom, Infermera infermera) {
        this.nom = nom;
        this.infermera = infermera;
    }

    @Override
    public void run() {
        System.out.println("[" + nom + "] Entrant a l'Ambulatori...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("[" + nom + "] Sol·licitant la Cita Prèvia a infermera...");
        infermera.atendrePacient(nom);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("[" + nom + "] Eixint de l'ambulatori...");
    }
}