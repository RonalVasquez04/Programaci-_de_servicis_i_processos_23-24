import java.util.Arrays;
import java.util.Scanner;

public class act1 {

    public static void main(String[] args) {

        String[] comandament = new String[] { "ls", "-l" };
        String[] comandament1 = new String[] { "lsd ", "-l" };

        try {
            Runtime runtime = Runtime.getRuntime();
            Process proceso = runtime.exec(comandament);

            System.out.println("Comando " + Arrays.toString(comandament) + " se ha ejecutado correctamente");
            proceso = runtime.exec(comandament1);
        } catch (Exception e) {
            System.out.println("Comando " + Arrays.toString(comandament1) + " no se ha ejecutado correctamente");
        }
    }
}
