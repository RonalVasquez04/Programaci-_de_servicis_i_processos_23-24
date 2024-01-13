import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ServidorUDP implements Runnable {

    private final int portEscucha;
    private final int portDesti;
    private String palabraClave;

    public ServidorUDP(int port, int portDesti, String palabra) {
        this.portEscucha = port;
        this.portDesti = portDesti;
        this.palabraClave = palabra;
        System.out.println("[" + Thread.currentThread().getName() + "] ServidorUDP creat");
    }

    @Override
    public void run() {

        try {

            Scanner sc = new Scanner(System.in);

            String texto = "";

            Boolean detener = false;

            do {
                if (!detener) {
                    byte[] buffer = new byte[1024];

                    DatagramSocket socketUDP = new DatagramSocket(portEscucha);
                    DatagramPacket datagram = new DatagramPacket(buffer, buffer.length);
                    System.out.println(
                            "[" + Thread.currentThread().getName() + "] Esperant datagrama al port " + portEscucha
                                    + "...");
                    socketUDP.receive(datagram);

                    String mensaje = new String(datagram.getData(), "UTF-8");

                    System.out.println("[" + Thread.currentThread().getName() + "] Missatge rebut : " + mensaje);

                    if (!socketUDP.isClosed()) {
                        socketUDP.close();
                    }

                    if (mensaje.trim().equals(palabraClave)) {
                        detener = true;
                    } else {
                        InetAddress direccion = InetAddress.getByName("127.0.0.1");

                        byte[] bufferEnviar = new byte[1024];
                        System.out.print("[" + Thread.currentThread().getName() + "] Dime la respuesta : ");
                        texto = sc.nextLine();
                        // String mensajeEnviar = String.valueOf(datagram.getLength());
                        bufferEnviar = texto.getBytes("UTF-8");

                        DatagramSocket socketClient = new DatagramSocket();

                        if (texto.equals(palabraClave)) {
                            detener = true;
                        }

                        DatagramPacket datagramClient = new DatagramPacket(bufferEnviar, bufferEnviar.length, direccion,
                                portDesti);

                        System.out.println(
                                "[" + Thread.currentThread().getName() + "] Enviant datagrama al port " + portDesti
                                        + "...");

                        socketClient.send(datagramClient);

                        if (!socketClient.isClosed()) {
                            socketClient.close();
                        }
                    }
                }

            } while (!detener);

            //////////////////////////////////////////////////////////////////////////

        } catch (

        Exception e) {
            // TODO: handle exception
        }

    }

}
