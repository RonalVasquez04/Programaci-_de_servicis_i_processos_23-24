public class act2_3_TicTac2 implements Runnable {

    private String nom = null;
    public act2_3_TicTac2(String nom) {
        this.nom = nom;

    }

    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.print(this.nom + " ");

        }
    }

    public static void main(String[] args) {
        System.out.println("[Fil ppal] Iniciat rellotge...");
        act2_3_TicTac2 tic = new act2_3_TicTac2("TIC");
        act2_3_TicTac2 tac = new act2_3_TicTac2("TAC");

        Thread fill1 =  new Thread(tic, tic.nom);
        Thread fill2 =  new Thread(tac, tac.nom);


        fill1.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        fill2.start();
        try {


            fill1.join();
            fill2.join();
        } catch (Exception e) {
            // TODO: handle exception
        }
        System.out.println("\n[Fil ppal] Finalizat rellotge");
    }
}
