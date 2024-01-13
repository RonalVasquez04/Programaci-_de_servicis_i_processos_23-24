public class Act1ProcessIsAlive {
    public static void main(String[] args) {

        String[] command = new String[] { "sleep10" };

        try {
            ProcessBuilder processBuilder = new ProcessBuilder("notepad.exe");
            Process proceso = processBuilder.start();
            while (proceso.isAlive()) {
                System.out.println("El proceso sigue en ejecución...");
                Thread.sleep(3000);
            }

            System.out.println("El proceso finalizo");
        } catch (Exception e) {
            // TODO: handle exception
        }

    }
}