public class Pagamento {
    private double valor;
    private String metodoDePagamento;

    public Pagamento(double valor, String metodoDePagamento) {
        this.valor = valor;
        this.metodoDePagamento = metodoDePagamento;
    }

    public Pagamento() {

    }

    public void processarPagamento() {
        System.out.println("Pagamento de R$ " + valor + " realizado via " + metodoDePagamento + ".");
    }
}
