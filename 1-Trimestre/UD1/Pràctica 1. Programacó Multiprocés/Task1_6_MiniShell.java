import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;

public class Task1_6_MiniShell {
    public static void main(String[] args) {
        try {
            while (true) {

                // ProcessBuilder processCAT = new ProcessBuilder("cat");
                // ProcessBuilder processLS = new ProcessBuilder("ls");

                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                String line;
                System.out.println("minishell> ");
                line = reader.readLine();
                if (line.isEmpty()) {
                    System.out.println("Finalizació del programa");
                    break;
                }

                String[] linea = line.split(" ");

                if (linea[0].compareToIgnoreCase("cat") == 0
                        || linea[0].compareToIgnoreCase("ls") == 0
                        || linea[0].compareToIgnoreCase("grep") == 0) {
                    System.out.print(">Executant comandament : ");
                    for (String ll : linea) {
                        System.out.print(" " + ll);
                    }
                    System.out.println("\n");
                    ProcessBuilder ppp = new ProcessBuilder(linea);
                    Process process = ppp.start();

                    BufferedReader processOutput = new BufferedReader(new InputStreamReader(process.getInputStream()));

                    String outputline;
                    while ((outputline = processOutput.readLine()) != null) {
                        System.out.println(outputline);
                    }
                    System.out.println("\nExecució Correcta\n");
                } else {
                    System.out.println("\nComandament desconegut: " + linea[0]+"\n");
                }

            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}