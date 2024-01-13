import java.io.*;

public class act1_6_processInputStream {
    public static void main(String[] args) {
        String[] command = new String[] { "df", "-h", "/" };
        ProcessBuilder pb = new ProcessBuilder(command);

        try {
            Process p = pb.start();

            InputStreamReader isr = new InputStreamReader(p.getInputStream());
            BufferedReader br = new BufferedReader(isr);

            int codRet = p.waitFor();

            System.out.println(codRet == 0 ? "Execucio correcta" : "Execucio erronea");

            String linea = null;

            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
            // TODO: handle exception
        }
    }
}