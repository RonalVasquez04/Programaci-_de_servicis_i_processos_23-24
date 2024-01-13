public class QuadreCites {

    // Atributs
    public String hores[]; // vector d'hores
    public String torns[]; // vector de torns

    // Constructor
    public QuadreCites() {
        hores = new String[8];
        torns = new String[8];

        System.out.println("[" + Thread.currentThread().getName()
                + "] Inicialitza quadre de Cita Prèvia");

        int hora = 10;
        int k = 0;

        // Inicialització vector d'hores
        for (int i = 0; i < 2; i++) {
            int min = 0;
            for (int j = 0; j < 4; j++) {
                hores[k++] = String.valueOf(hora) // Convertim l'hora en String
                        + ":" + String.valueOf(min); // Convertim els minuts en String
                min += 15; // Sumem 15 min. per a la pròxima iteració.
            } // for (min)
            hora++; // Una vegada recorreguts tots els cuarts de la primera hora, passem a la
                    // següent.
        } // for (hora)

        // Inicialitza vctor de torns a "Ningu", vol dir que no hi ha ningu al torn
        // assignat
        for (int i = 0; i < 8; i++) { // cada quart d'hora
            torns[i] = "Ningu";
        } // for (torns)

    }// Constructor

    // -----------------------------------------
    public int cercaCitaLliure() {
        for (int i = 0; i < 8; i++) {
            if (torns[i] == "Ningu") { // Cerca un espai buit (Ningu)
                return i; // Retorna la posició del torn lliure
            } // endif
        } // for
        return -1; // Si no hi ha cap lloc, retorna -1
    }// cercaCitaLliure

    // -----------------------------------------
    public void assignaCitaAQuadre(String nomPacient, int i) { // Passant-li un pacient i la posició, el guarda en el
                                                               // vector
        torns[i] = nomPacient;
    }// assignaCitaAQuadre

    // -----------------------------------------
    public String retornaCita(int i) {
        return hores[i];
    }// retornaCita

    // -----------------------------------------
    public void imprimeixQuadre() {
        System.out.println("\n\nMostrant taula de Cites Prèvies:");
        System.out.println("================================");

        System.out.println("    HORA:        PACIENT:");
        for (int i = 0; i < 8; i++) {
            System.out.println("  " + hores[i] + "h   -->  " + torns[i]);
        } // for
    }// imprimeixQuadre

}// class
