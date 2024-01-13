import java.util.concurrent.*;

public class act2_3_ThreadComptador extends Thread {

    private int comptador;

    public act2_3_ThreadComptador(String nom) {
        comptador = 0;
        this.setName(nom);
    }

    public void run() {
        while (comptador < 5) {
            String nomAct = this.getName();
            System.out.println("[" + nomAct + "] comptador = " + comptador);
            comptador++;
            if (comptador == 1) {
                System.out.println("[Fil ppal] Executant nou fil " + this.getName());
            }

        }
    }

    public static void main(String[] args) {
        System.out.println("[Fil ppal] Comptador amb fils heretant de Thread\n");

        act2_3_ThreadComptador[] objectes = new act2_3_ThreadComptador[3];

        for (int i = 0; i < objectes.length; i++) {
            String nomHilo = "Fil" + (i + 1);
            System.out.println("[Fil ppal] Creant nou fil " + nomHilo);
            objectes[i] = new act2_3_ThreadComptador(nomHilo);
            objectes[i].start();
            try {
                sleep(100);
            } catch (Exception e) {
                // TODO: handle exception
            }
        }

        for (int i = 0; i < objectes.length; i++) {
            try {
                objectes[i].join();
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
        System.out.println("[Fil ppal] els 3 fils han finalizat");

    }
}
