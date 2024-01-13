import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class ClientUDP implements Runnable {

    public ClientUDP() {
        System.out.println("[" + Thread.currentThread().getName() + "] Creat el Servidor");
    }

    @Override
    public void run() {
        Boolean acabat = false;
        Scanner sc = new Scanner(System.in);

        Integer portServidor = 10000;
        Integer portClient = 9999;
        String datos = "";
        System.out.println("Operaciones permitidas : \n   -> +   \n   -> -  \n   -> *  \n   -> /");
        System.out.println("Formato : num1 , num2 , operacion ");
        System.out.print("Dime la operación a realizar : ");
        datos = sc.nextLine();

        try {

            do {
                if (datos.equals("acabat")) {
                    acabat = true;
                }
                InetAddress direccion = InetAddress.getByName("127.0.0.1");
                byte[] buffer = new byte[1024];
                System.out
                        .println("[" + Thread.currentThread().getName() + "] " + "Text a enviar al Servidor: "
                                + datos);

                buffer = datos.getBytes("UTF-8");
                DatagramSocket socketUDP = new DatagramSocket(portClient);
                DatagramPacket datagram = new DatagramPacket(buffer, buffer.length, direccion, portServidor);
                System.out.println(
                        "[" + Thread.currentThread().getName() + "] Enviant datagrama al port " + portServidor);

                socketUDP.send(datagram);

                if (!socketUDP.isClosed())
                    socketUDP.close();

                //////////////////////////////////////////////////////////////////////////////

                if (!acabat) {
                    buffer = new byte[1024];

                    socketUDP = new DatagramSocket(portClient);

                    datagram = new DatagramPacket(buffer, buffer.length);
                    System.out.println(
                            "[" + Thread.currentThread().getName() + "] Esperant resultat al port " + portClient
                                    + "...");

                    socketUDP.receive(datagram);

                    String datosReceive = new String(datagram.getData(), "UTF-8");

                    System.out.println("[" + Thread.currentThread().getName() + "] Missatge rebut : " + datosReceive);

                    if (!socketUDP.isClosed())
                        socketUDP.close();

                    System.out.println("Operaciones permitidas : \n -> + \n -> - \n -> * \n ->/");
                    System.out.println("Formato : num1 , num2 , operacion ");
                    System.out.print("Dime la operación a realizar : ");
                    datos = sc.next();

                }
            } while (!acabat);

        } catch (Exception e) {
            // TODO: handle exception
        }

    }

}
