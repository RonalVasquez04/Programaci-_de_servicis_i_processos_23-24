import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class Servidor implements Runnable {

    private List<Refresc> listRefrescos = new ArrayList<>();
    private int clientesAct;

    public Servidor(List<Refresc> lista, int clientes) {
        this.listRefrescos = lista;
        this.clientesAct = clientes + 1;
        System.out.println("Servidor arrancado y esperando conexiones...");
        System.out
                .println("La maquina tiene: " + listRefrescos.size() + " referescos");

    }

    @Override
    public void run() {

        try {
            ServerSocket servidorSocket = new ServerSocket(9999);

            while (clientesAct != 0) {
                System.out
                        .println("\n ====== Servidor ====== \n\nA la maquina le quedan: " + listRefrescos.size()
                                + " referescos");
                System.out.println("Esperando...\n");

                Socket socketClient = servidorSocket.accept();

                System.out.println("\n ====== Servidor ====== \n \nPetición de cliente -> "
                        + socketClient.getInetAddress() + " --- " + socketClient.getLocalPort());

                /////////// Entrada datos ///////////
                ObjectInputStream inputStream = new ObjectInputStream(socketClient.getInputStream());
                int cantidad = inputStream.readInt();

                /////////// Salida datos ///////////
                List<Refresc> listRefrescosCliente = new ArrayList<>();

                if (!listRefrescos.isEmpty()) {

                    if (listRefrescos.size() < cantidad) {
                        System.out.println("No hay suficientes refrescos!");
                        System.out.println(
                                "Se han servido los últimos " + listRefrescos.size() + " refrescos que quedaban");
                        for (int i = 0; i < listRefrescos.size() && !listRefrescos.isEmpty(); i++) {
                            int numAleatori = new Random().nextInt(listRefrescos.size());
                            listRefrescosCliente.add(listRefrescos.get(numAleatori));
                            listRefrescos.remove(numAleatori);
                        }
                    } else {
                        for (int i = 0; i < cantidad && !listRefrescos.isEmpty(); i++) {
                            int numAleatori = new Random().nextInt(listRefrescos.size());
                            listRefrescosCliente.add(listRefrescos.get(numAleatori));
                            listRefrescos.remove(numAleatori);
                        }
                    }

                } else {
                    System.out.println("No hay refrescos!");
                }

                ObjectOutputStream outputStream = new ObjectOutputStream(socketClient.getOutputStream());
                outputStream.writeObject(listRefrescosCliente);
                outputStream.flush();
                socketClient.close();
                clientesAct--;
            }

        } catch (Exception e) {
        }

    }
}
