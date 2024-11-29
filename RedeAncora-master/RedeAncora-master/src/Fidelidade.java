public class Fidelidade {
    private Usuario usuario;
    private int pontos;

    public Fidelidade(Usuario usuario, int pontos) {
        this.usuario = usuario;
        this.pontos = pontos;
    }

    public int getPontos() {
        return pontos;
    }

    public void adicionarPontos(int pontos) {
        this.pontos += pontos;
    }

    public boolean resgatarPontos(int pontos) {
        if (this.pontos >= pontos) {
            this.pontos -= pontos;
            return true;
        }
        return false;
    }
}
