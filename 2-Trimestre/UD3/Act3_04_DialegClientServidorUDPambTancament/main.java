import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Dime la palabra de cierre de diálogo : ");
        String Word = sc.nextLine();

        System.out.print("Dime el puerto donde escucha el ClientUDP : ");
        int portClient = sc.nextInt();

        System.out.print("Dime el puerto donde escucha el ServidorUDP : ");
        int portServidor = sc.nextInt();

        Thread client = new Thread(new ClientUDP(portClient, portServidor, Word), "ClientUDP");
        Thread servidor = new Thread(new ServidorUDP(portServidor, portClient, Word), "ServidorUDP");

        try {
            servidor.start();
            Thread.sleep(2000);
            client.start();

            client.join();
            servidor.join();

            System.out.println("[" + Thread.currentThread().getName() + "] Programa finalizat");
        } catch (Exception e) {
            // TODO: handle exception
        }

    }
}