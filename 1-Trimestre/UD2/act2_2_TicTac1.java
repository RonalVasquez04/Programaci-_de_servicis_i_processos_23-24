public class act2_2_TicTac1 extends Thread {
    public act2_2_TicTac1(String nom) {
        this.setName(nom);

    }

    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print(this.getName() + " ");

        }
    }

    public static void main(String[] args) {
        System.out.println("[Fil ppal] Iniciat rellotge...");
        act2_2_TicTac1 tic = new act2_2_TicTac1("TIC");
        act2_2_TicTac1 tac = new act2_2_TicTac1("TAC");


        tic.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tac.start();
        try {


            tic.join();
            tac.join();
        } catch (Exception e) {
            // TODO: handle exception
        }
        System.out.println("\n[Fil ppal] Finalizat rellotge");
    }
}
