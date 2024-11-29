public class Usuario {
    private String nome;
    private int pontosFidelidade;

    public Usuario(String nome) {
        this.nome = nome;
        this.pontosFidelidade = 0;
    }

    public String getNome() {
        return nome;
    }

    public int getPontosFidelidade() {
        return pontosFidelidade;
    }

    public void adicionarPontos(int pontos) {
        this.pontosFidelidade += pontos;
    }
}
