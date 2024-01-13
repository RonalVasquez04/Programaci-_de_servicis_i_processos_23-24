import java.util.Random;

public class Client implements Runnable {

    Caixa caixa;

    public Client(Caixa caixa) {
        this.caixa = caixa;

    }

    @Override
    public void run() {
        Random random = new Random();

        // Generar un valor float aleatorio entre 5 y 10
        float valorAleatorio = random.nextFloat() * (10 - 5) + 5;
        System.out.println("[" + Thread.currentThread().getName() + "] El client ha fet la recollida de productes");
        System.out.println("[" + Thread.currentThread().getName() + "]  Cliente va a la caixa <" + caixa.name + ">");
        System.out.println("[" + Thread.currentThread().getName() + "] Encuant-se amb la compra a la caixa");

        try {

            caixa.agafaCompra();
            caixa.cobramentCompra(valorAleatorio);
            caixa.ticketCompra();
            caixa.esperarProcesoCompletado();
        } catch (Exception e) {
            // TODO: handle exception
        }

        System.out.println("[" + Thread.currentThread().getName() + "] Recollint la compra a <" + caixa.name + ">");
        System.out.println("[" + Thread.currentThread().getName() + "] Eixint del supermercat");

    }
}