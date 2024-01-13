import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ServidorUDP implements Runnable {

    public ServidorUDP() {
        System.out.println("[" + Thread.currentThread().getName() + "] Creado el Servidor");
    }

    @Override
    public void run() {
        int port = 10000;
        try {
            InetAddress direccion = InetAddress.getByName("127.0.0.1");
            DatagramSocket socketUDP = new DatagramSocket(port);

            Boolean acabat = false;
            Integer portCliente;

            do {
                byte[] buffer = new byte[1024];
                DatagramPacket datagrama = new DatagramPacket(buffer, buffer.length);
                System.out
                        .println("[" + Thread.currentThread().getName() + "] Esperando operación en el puerto " + port);
                socketUDP.receive(datagrama);
                String datos = new String(datagrama.getData(), "UTF-8");

                if (datos.trim().equals("acabat")) {
                    acabat = true;
                } else {
                    System.out.println("[" + Thread.currentThread().getName() + "] Mensaje recibido: " + datos);

                    String[] parts = datos.trim().split(",");
                    String resultado = "";

                    if (parts[2].equals("+")) {
                        Integer num = Integer.parseInt(parts[0]) + Integer.parseInt(parts[1]);
                        resultado = String.valueOf(num);
                    } else if (parts[2].equals("-")) {
                        Integer num = Integer.parseInt(parts[0]) - Integer.parseInt(parts[1]);
                        resultado = String.valueOf(num);
                    } else if (parts[2].equals("*")) {
                        Integer num = Integer.parseInt(parts[0]) * Integer.parseInt(parts[1]);
                        resultado = String.valueOf(num);
                    } else if (parts[2].equals("/")) {
                        Integer num = Integer.parseInt(parts[0]) / Integer.parseInt(parts[1]);
                        resultado = String.valueOf(num);
                    } else {
                        resultado = "otro";
                    }

                    buffer = resultado.getBytes("UTF-8");
                    DatagramPacket data = new DatagramPacket(buffer, buffer.length, datagrama.getSocketAddress());
                    System.out.println(
                            "[" + Thread.currentThread().getName() + "] Enviando datagrama al puerto : "
                                    + datagrama.getPort());
                    socketUDP.send(data);
                }

            } while (!acabat);

            if (!socketUDP.isClosed()) {
                socketUDP.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
