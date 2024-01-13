import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientUDP implements Runnable {

    JTextField texto;
    JButton btnEnviar;

    public ClientUDP() {

        System.out.println("[" + Thread.currentThread().getName() + "] Creat el Servidor");
    }

    @Override
    public void run() {
        JFrame frame = new JFrame("Ventana Vacía");
        texto = new JTextField();
        texto.setBounds(10, 170, 565, 40);
        btnEnviar = new JButton();
        btnEnviar.setText(" ENVIAR ");
        btnEnviar.setBounds(190, 240, 200, 30);

        btnEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Integer portServidor = 10000;
                    Integer portClient = 9999;

                    InetAddress direccion = InetAddress.getByName("127.0.0.1");
                    byte[] buffer = new byte[1024];

                    String datos = texto.getText();

                    buffer = datos.getBytes("UTF-8");
                    DatagramSocket socketUDP = new DatagramSocket(portClient);
                    DatagramPacket datagram = new DatagramPacket(buffer, buffer.length, direccion, portServidor);

                    socketUDP.send(datagram);
                    if (datos.equals("acabat")) {
                        frame.dispose();
                    }
                    texto.setText("");
                    texto.requestFocus();

                    if (!socketUDP.isClosed()) {
                        socketUDP.close();
                    }

                } catch (Exception er) {
                    er.printStackTrace();
                }
            }
        });

        frame.setSize(600, 500);
        frame.setTitle("CLientUDP");
        frame.setLocation(350, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.add(texto);
        frame.add(btnEnviar);

        frame.setVisible(true);
    }

}
