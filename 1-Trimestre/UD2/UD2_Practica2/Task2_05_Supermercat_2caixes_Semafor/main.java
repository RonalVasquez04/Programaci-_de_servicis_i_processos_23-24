public class main {
    public static void main(String[] args) {
        System.out.println("[" + Thread.currentThread().getName() + "] Programa del supermercat amb 1 caixa");
        System.out.println("==================================================================");

        Caixa caixa = new Caixa("caixa1");
        Caixa caixa1 = new Caixa("caixa2");
        Thread cliente1 = new Thread(new Client(caixa), "cliente1");
        Thread cliente2 = new Thread(new Client(caixa1), "cliente2");
        Thread cliente3 = new Thread(new Client(caixa), "cliente3");
        Thread cliente4 = new Thread(new Client(caixa1), "cliente3");
        try {
            cliente1.start();
            cliente2.start();
            cliente3.start();
            cliente4.start();

            cliente1.join();
            cliente2.join();
            cliente3.join();
            cliente4.join();
        } catch (Exception e) {
            // TODO: handle exception
        }

        System.out.println("[" + Thread.currentThread().getName() + "] Finalització fil ppal");
    }

}
