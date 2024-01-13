import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Task1_5_HashMd5streams{
    public static void main(String[] args) {
         try {

            while (true) {

                Process process = new ProcessBuilder("md5sum").start();

                BufferedWriter processInput = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
                BufferedReader processOutput = new BufferedReader(new InputStreamReader(process.getInputStream()));

                BufferedReader leer = new BufferedReader(new InputStreamReader(System.in));
                String line;
                System.out.print("md5txt>");
                line = leer.readLine();
                if (line.isEmpty()) {
                    break;
                }

                processInput.write(line);
                processInput.flush();
                processInput.close();

                String outputline;

                while ((outputline = processOutput.readLine()) != null) {
                    System.out.println("Codificació MD5 de la cadena : "+ line + ":");
                    System.out.println(outputline);
                    
                }
            }

        } catch (Exception e) {
            // TODO: handle exception
        }

    }
}