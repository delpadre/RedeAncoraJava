public class Produto {
    private String nome;
    private double preco;
    private Estoque estoque;

    public Produto(String nome, double preco, Estoque estoque) {
        this.nome = nome;
        this.preco = preco;
        this.estoque = estoque;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public Estoque getEstoque() {
        return estoque;
    }
}
