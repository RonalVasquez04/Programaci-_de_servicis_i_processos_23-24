import javax.swing.JOptionPane;

public class main {
    public static void main(String[] args) {
        String puerto = JOptionPane.showInputDialog("Dime el puerto que se utilizara:");
        String cantidadUsuarios = JOptionPane.showInputDialog("Cuántos clientes quieres crear?:");

        // JOptionPane.showMessageDialog(null, "Texto ingresado: " + cantidadUsuarios);

        Thread[] clientes = new Thread[Integer.parseInt(cantidadUsuarios)];

        for (int i = 0; i < Integer.parseInt(cantidadUsuarios); i++) {
            String nombre = JOptionPane.showInputDialog("Introduce el nick del cliente " + (i + 1) + ":");
            clientes[i] = new Thread(new UDPMultiChat(nombre, Integer.parseInt(puerto)));
            clientes[i].start();
        }

        try {
            for (int i = 0; i < Integer.parseInt(cantidadUsuarios); i++) {
                clientes[i].join();
            }
            JOptionPane.showMessageDialog(null, "CHAT FINALIZADO");
        } catch (Exception e) {

        }

    }
}