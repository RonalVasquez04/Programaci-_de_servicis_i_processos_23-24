import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProcessBuilderPipes {
    public static void main(String[] args) {
        ProcessBuilder process = new ProcessBuilder("if config -a");
        ProcessBuilder process2 = new ProcessBuilder("grep lo");

        List<ProcessBuilder> builders = new ArrayList<>();

        builders.add(process);
        builders.add(process2);

        try {

        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}