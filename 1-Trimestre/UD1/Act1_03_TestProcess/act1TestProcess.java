import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class act1TestProcess {

    public static void main(String[] args) {

        String[][] comandaments = new String[][] { new String[] { "df", "-h", "/" },
                new String[] { "df", "-argument", "/" },
                new String[] { "commandoInexistente" } };

        try {

            for (String[] comandos : comandaments) {
                ProcessBuilder pb = new ProcessBuilder(comandos);
                pb.inheritIO();
                System.out.println("\n----- " + Arrays.toString(comandos) + " -----\n");
                Process p = pb.start();
                int cod = p.waitFor();
            }

        } catch (Exception e) {
            System.out.println("Error en l'execució");
        }
    }

}