import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ClientUDP implements Runnable {

    public ClientUDP() {
        System.out.println("[" + Thread.currentThread().getName() + "]" + " Creat el clienteUDP");
    }

    @Override
    public void run() {
        try {
            Scanner sc = new Scanner(System.in);

            byte[] buffer = new byte[1024];
            final int PORTDesti = 10000;
            final int PORTLocal = 9999;

            InetAddress direccioDesti = InetAddress.getByName("127.0.0.1");

            System.out.print("Dime el mensaje : ");
            String Missatge = sc.nextLine();
            System.out
                    .println("[" + Thread.currentThread().getName() + "] " + "Text a enviar al Servidor: " + Missatge);

            buffer = Missatge.getBytes("UTF-8");

            DatagramSocket socketUDP = new DatagramSocket();

            DatagramPacket datagrama = new DatagramPacket(buffer, buffer.length, direccioDesti, PORTDesti);
            System.out.println(
                    "[" + Thread.currentThread().getName() + "] Enviant datagrama al port " + PORTDesti + "...");
            socketUDP.send(datagrama);

            if (!socketUDP.isClosed())
                socketUDP.close();

            /////////////////////////////////////////////////////////////////
            /////////////////////////////////////////////////////////////////
            /////////////////////////////////////////////////////////////////

            buffer = new byte[1024];

            socketUDP = new DatagramSocket(PORTLocal);

            datagrama = new DatagramPacket(buffer, buffer.length);

            System.out.println(
                    "[" + Thread.currentThread().getName() + "] Esperant datagrama al port " + PORTLocal + "...");

                    socketUDP.receive(datagrama);

            String missatgeReceive = new String(datagrama.getData(), "UTF-8");

            System.out.println("[" + Thread.currentThread().getName() + "] Missatge rebut : " + missatgeReceive);

            if (!socketUDP.isClosed())
            socketUDP.close();

        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}