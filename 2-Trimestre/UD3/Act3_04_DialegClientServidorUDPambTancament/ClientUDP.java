import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ClientUDP implements Runnable {

    private final Integer portEscucha;
    private final Integer portDesti;
    String palabraClave;

    public ClientUDP(int port, int portDesti, String palabra) {
        this.portEscucha = port;
        this.portDesti = portDesti;
        this.palabraClave = palabra;
        System.out.println("[" + Thread.currentThread().getName() + "] ClientUDP creat");
    }

    @Override
    public void run() {
        try {

            Scanner sc = new Scanner(System.in);
            System.out.print("Dime el mensaje : ");
            String mensaje = sc.nextLine();
            String textRecibido = "";
            Boolean finalizat = false;

            do {
                if (mensaje.equals(palabraClave)) {
                    finalizat = true;
                }
                InetAddress direccion = InetAddress.getByName("127.0.0.1");
                byte[] buffer = new byte[1024];
                System.out
                        .println("[" + Thread.currentThread().getName() + "] " + "Text a enviar al Servidor: "
                                + mensaje);

                buffer = mensaje.getBytes("UTF-8");
                DatagramSocket socketUDP = new DatagramSocket();
                DatagramPacket datagram = new DatagramPacket(buffer, buffer.length, direccion, portDesti);
                System.out.println("[" + Thread.currentThread().getName() + "] Enviant datagrama al port " + portDesti);

                socketUDP.send(datagram);

                if (!socketUDP.isClosed())
                    socketUDP.close();

                //////////////////////////////////////////////////////////////////////////////

                if (!finalizat) {
                    buffer = new byte[1024];

                    socketUDP = new DatagramSocket(portEscucha);

                    datagram = new DatagramPacket(buffer, buffer.length);
                    System.out.println(
                            "[" + Thread.currentThread().getName() + "] Esperant datagrama al port " + portEscucha
                                    + "...");

                    socketUDP.receive(datagram);

                    String mensajeReceive = new String(datagram.getData(), "UTF-8");

                    System.out.println("[" + Thread.currentThread().getName() + "] Missatge rebut : " + mensajeReceive);

                    if (!socketUDP.isClosed())
                        socketUDP.close();

                    if (mensajeReceive.trim().equals(palabraClave)) {
                        finalizat = true;
                    } else {
                        System.out.print("[" + Thread.currentThread().getName() + "] Dime el mensaje : ");
                        mensaje = sc.nextLine();
                    }

                }
            } while (!finalizat);

        } catch (Exception e) {
            // TODO: handle exception
        }

    }

}
