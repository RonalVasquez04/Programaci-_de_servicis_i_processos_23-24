import java.util.ArrayList;
import java.util.List;

public class main {
    public static void main(String[] args) {

        List<Refresc> listRefrescos = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            Refresc refresc = Refresc.crearRefrescAleatori();
            listRefrescos.add(refresc);
        }

        Thread[] clientes = new Thread[4];
        Thread servidor = new Thread(new Servidor(listRefrescos, clientes.length - 1));
        Integer num = 0;
        try {
            servidor.start();

            for (int i = 0; i < clientes.length; i++) {
                clientes[i] = new Thread(new Client(), "Cliente" + (num + 1));
                clientes[i].start();
                clientes[i].join();
                num++;
            }

            System.out.println("Todos los clientes han terminado.");
            servidor.join();

            System.out.println("Servidor cerrado");

        } catch (Exception e) {
            // TODO: handle exception
        }

    }

}