import java.util.List;

public class Pedido {
    private Usuario usuario;
    private List<ItemCarrinho> itens;
    private double total;

    public Pedido(Usuario usuario, List<ItemCarrinho> itens, double total) {
        this.usuario = usuario;
        this.itens = itens;
        this.total = total;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public List<ItemCarrinho> getItens() {
        return itens;
    }

    public double getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return "Pedido de " + usuario.getNome() + " com total de R$" + total;
    }
}
