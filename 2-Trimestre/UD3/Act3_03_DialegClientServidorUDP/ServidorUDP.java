import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ServidorUDP implements Runnable {

    public ServidorUDP() {
        System.out.println("[" + Thread.currentThread().getName() + "]" + " Creat el servidorUDP");
    }

    @Override
    public void run() {
        try {
            byte[] buffer = new byte[1024];
            final int port = 10000;
            final int portClient = 9999;

            DatagramSocket socketUDP = new DatagramSocket(port);
            DatagramPacket datagrama = new DatagramPacket(buffer, buffer.length);
            System.out.println("[" + Thread.currentThread().getName() + "] Esperant datagrama al port " + port + "...");
            socketUDP.receive(datagrama);

            String missatge = new String(datagrama.getData(), "UTF-8");

            System.out.println("[" + Thread.currentThread().getName() + "] Missatge rebut : " + missatge);
            if (!socketUDP.isClosed())
                socketUDP.close();

            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            InetAddress direccioDesti = InetAddress.getByName("127.0.0.1");

            buffer = new byte[1024];

            String texto = String.valueOf(datagrama.getLength());
            buffer = texto.getBytes("UTF-8");

            socketUDP = new DatagramSocket();

            DatagramPacket datagramaCliente = new DatagramPacket(buffer, buffer.length, direccioDesti,
                    portClient);

            System.out.println(
                    "[" + Thread.currentThread().getName() + "] Enviant datagrama al port " + portClient + "...");

                    socketUDP.send(datagramaCliente);

            if (!socketUDP.isClosed())
            socketUDP.close();

        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}