public class main {
    public static void main(String[] args) {

        Thread client = new Thread(new ClientUDP(), "ClientUDP");
        Thread servidor = new Thread(new ServidorUDP(), "servidorUDP");

        try {
            servidor.start();
            Thread.sleep(1000);
            client.start();

            client.join();
            servidor.join();

            System.out.println("Programa finalizat");
        } catch (Exception e) {
            // TODO: handle exception
        }

    }
}