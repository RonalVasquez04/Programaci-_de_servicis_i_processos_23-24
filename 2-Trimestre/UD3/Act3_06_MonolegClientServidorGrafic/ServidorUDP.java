import java.net.DatagramPacket;
import java.net.DatagramSocket;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class ServidorUDP implements Runnable {

    public ServidorUDP() {

        System.out.println("[" + Thread.currentThread().getName() + "] Creado el Servidor");
    }

    @Override
    public void run() {

        JFrame frame = new JFrame("Ventana Vacía");
        JTextArea texto = new JTextArea();

        texto.setBounds(10, 10, 565, 300);
        frame.setSize(600, 500);
        frame.setTitle("ServidorUDP");
        texto.setEditable(false);
        frame.setLocation(1000, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.add(texto);

        frame.setVisible(true);

        int port = 10000;
        try {
            DatagramSocket socketUDP = new DatagramSocket(port);

            do {
                byte[] buffer = new byte[1024];
                DatagramPacket datagrama = new DatagramPacket(buffer, buffer.length);

                socketUDP.receive(datagrama);
                String datos = new String(datagrama.getData(), "UTF-8");

                if (datos.trim().equals("acabat")) {
                    break;
                } else {

                    texto.append(datos + "\n");

                }

            } while (true);

            if (!socketUDP.isClosed()) {
                socketUDP.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
