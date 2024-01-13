import java.io.*;

public class Task1_3_Comptador {
    public static void main(String[] args) {
        try {

            while (true) {

                Process process = new ProcessBuilder("wc","-m","-w").start();
                
                BufferedWriter processInput = new BufferedWriter(new OutputStreamWriter(process.getOutputStream())); // getoutputstream le damos los datos al proceso


                BufferedReader processOutput = new BufferedReader(new InputStreamReader(process.getInputStream())); // Obtiene resultado de proceso getinputstream
                

                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                String line;
                System.out.print("comptadortxt> ");
                line = reader.readLine();

                if (line.isEmpty()) {
                    break;
                }

                processInput.write(line);
                processInput.flush();
                processInput.close();

                String outputLine;
                while ((outputLine = processOutput.readLine())!=null) {
                    System.out.println("Paraules  lletres");
                    System.out.println("========  =======");
                    System.out.println(outputLine);
                }


            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
