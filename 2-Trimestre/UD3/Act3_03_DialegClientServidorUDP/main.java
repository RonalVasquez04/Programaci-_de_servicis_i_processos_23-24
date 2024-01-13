public class main {
    public static void main(String[] args) {

        try {
            Thread clientUDP = new Thread(new ClientUDP(), "ClientUDP");
            Thread servidorUDP = new Thread(new ServidorUDP(), "ServidorUDP");

            servidorUDP.start();
            Thread.sleep(2000);
            clientUDP.start();

            clientUDP.join();
            servidorUDP.join();

            System.out.println("[" + Thread.currentThread().getName() + "] Programa finalizat");
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

}
