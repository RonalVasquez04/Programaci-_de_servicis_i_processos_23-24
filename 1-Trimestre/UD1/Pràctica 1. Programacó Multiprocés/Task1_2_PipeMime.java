import java.io.*;
import java.util.Arrays;
import java.util.List;

public class Task1_2_PipeMime {
    public static void main(String[] args) {
        try {

            ProcessBuilder[] builders = {
                    new ProcessBuilder("cat", "/var/log/syslog"),
                    new ProcessBuilder("grep", "dhcp4"),
                    new ProcessBuilder("tail", "-n", "10")
            };

            List<Process> processes = ProcessBuilder.startPipeline(Arrays.asList(builders));

            Process last = processes.get(processes.size() - 1);

            try (InputStream is = last.getInputStream();
                    Reader isr = new InputStreamReader(is);
                    BufferedReader r = new BufferedReader(isr)) {
                        String line;
                        while ((line = r.readLine())!= null) {
                            System.out.println(line);
                        }
                long count = r.lines().count();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
