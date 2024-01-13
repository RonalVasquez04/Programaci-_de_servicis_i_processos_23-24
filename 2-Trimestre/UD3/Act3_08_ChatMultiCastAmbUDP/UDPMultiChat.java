import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class UDPMultiChat implements Runnable {

    private Integer puerto;
    private String nom;
    JTextField texto;
    JButton btnEnviar;
    JButton btnSalir;
    JFrame frame;
    JTextArea chat;
    Boolean salir = false;

    private InetAddress group;
    private MulticastSocket chatMultiCast;

    public UDPMultiChat(String nombre, Integer port) {
        this.nom = nombre;
        this.puerto = port;

        Border customBorder = BorderFactory.createLineBorder(java.awt.Color.BLUE, 2, true);

        frame = new JFrame("Ventana Vacía");
        texto = new JTextField();
        texto.setBounds(10, 10, 460, 30);
        texto.setBorder(customBorder);

        btnEnviar = new JButton();
        btnEnviar.setText(" ENVIAR ");
        btnEnviar.setBounds(480, 10, 90, 29);

        btnSalir = new JButton();
        btnSalir.setText("SALIR");
        btnSalir.setBounds(480, 60, 90, 29);

        chat = new JTextArea();
        chat.setBounds(10, 60, 460, 380);
        chat.setEditable(false);
        chat.setBorder(customBorder);

        frame.setSize(600, 500);
        frame.setTitle("VENTANA DE CHAT UDP - Nick: " + this.nom);
        frame.setLocation(350, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.add(chat);
        frame.add(texto);
        frame.add(btnEnviar);
        frame.add(btnSalir);
        frame.setVisible(true);

    }

    @Override
    public void run() {
        try {

            group = InetAddress.getByName("224.0.0.1");
            chatMultiCast = new MulticastSocket(puerto);
            chatMultiCast.joinGroup(group);

            String mensajeInicio = "*** Se ha unido al chat: " + this.nom + " ***";
            byte[] bufferInicio = mensajeInicio.getBytes("UTF-8");
            DatagramPacket datagramUnido = new DatagramPacket(bufferInicio, bufferInicio.length, group, puerto);
            chatMultiCast.send(datagramUnido);

            btnEnviar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String mensaje = texto.getText();
                    if (!mensaje.isEmpty()) {
                        try {
                            mensaje = (nom + ": " + mensaje);
                            byte[] buffer = mensaje.getBytes();
                            DatagramPacket datagram = new DatagramPacket(buffer, buffer.length, group, puerto);
                            chatMultiCast.send(datagram);
                            texto.setText("");
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }

                }
            });

            btnSalir.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    try {
                        String mensaje = ("*** Abandona el chat: " + nom);
                        byte[] buffer = mensaje.getBytes("UTF-8");
                        DatagramPacket datagram = new DatagramPacket(buffer, buffer.length, group, puerto);
                        chatMultiCast.send(datagram);
                        frame.dispose();
                        salir = true;
                        chatMultiCast.leaveGroup(group);
                        chatMultiCast.close();
                    } catch (Exception er) {
                        // TODO: handle exception
                    }

                }
            });

            while (!salir) {
                byte[] buffer = new byte[1024];
                DatagramPacket datagram = new DatagramPacket(buffer, buffer.length);
                chatMultiCast.receive(datagram);
                String mensaje = new String(datagram.getData(), "UTF-8");
                chat.append(mensaje + "\n");

            }

        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    public String getNom() {
        return nom;
    }

    public Integer getPuerto() {
        return puerto;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPuerto(Integer puerto) {
        this.puerto = puerto;
    }
}
