import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Client implements Runnable {

    public static Scanner sc = new Scanner(System.in);
    private Integer cantidad;
    Socket socketClient;

    public Client() {

    }

    @Override
    public void run() {

        try {
            System.out.println("\n ====== CLIENTE ======\n\n¿ " + Thread.currentThread().getName()
                    + ", cuantos refrescos quieres?");
            cantidad = sc.nextInt();
            socketClient = new Socket("localhost", 9999);
            System.out.println("Conectando al servidor ...");

            ObjectOutputStream outputStream = new ObjectOutputStream(socketClient.getOutputStream());

            outputStream.writeInt(cantidad);
            outputStream.flush();

            ObjectInputStream inputStream = new ObjectInputStream(socketClient.getInputStream());

            List<Refresc> listaRecibida = (List<Refresc>) inputStream.readObject();
            for (Refresc refresc : listaRecibida) {
                System.out.println(
                        "[" + Thread.currentThread().getName() + "] Ha llegado un refresco de -> " + refresc.getNom());
            }

        } catch (Exception e) {
        }

    }
}
