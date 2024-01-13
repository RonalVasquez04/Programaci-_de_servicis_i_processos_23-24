import java.util.*;
import java.util.Map.Entry;
public class Task1_1_ProcessEnvironment{
    public static void main(String[] args) {
    
        String [] comando = new String[]{"sleep","10"};

        ProcessBuilder pb = new ProcessBuilder(comando);
        try {
            Map<String, String> mm = pb.environment();

            for (Entry<String, String> entry : mm.entrySet()){
                String key = entry.getKey();
                String value = entry.getValue();
                System.out.println("Variable: "+ key + " VALOR :  " + value);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}