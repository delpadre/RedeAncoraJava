import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private Scanner scanner;
    private Carrinho carrinho;
    private List<Produto> produtosDisponiveis;

    public Menu() {
        this.scanner = new Scanner(System.in);
        this.carrinho = new Carrinho();
        this.produtosDisponiveis = new ArrayList<>();
        inicializarProdutos();
    }

    private void inicializarProdutos() {
        // Criando os produtos com os estoques
        Estoque estoqueRoda = new Estoque(10); // Estoque de 10 rodas
        Produto produtoRoda = new Produto("Roda", 1200.00, estoqueRoda);
        produtosDisponiveis.add(produtoRoda);

        Estoque estoquePneu = new Estoque(20); // Estoque de 20 pneus
        Produto produtoPneu = new Produto("Pneu", 500.00, estoquePneu);
        produtosDisponiveis.add(produtoPneu);

        Estoque estoqueMotor = new Estoque(5); // Estoque de 5 motores
        Produto produtoMotor = new Produto("Motor", 6000.00, estoqueMotor);
        produtosDisponiveis.add(produtoMotor);

        Estoque estoqueFreio = new Estoque(15); // Estoque de 15 sistemas de freio
        Produto produtoFreio = new Produto("Sistema de Freio", 700.00, estoqueFreio);
        produtosDisponiveis.add(produtoFreio);

        Estoque estoqueSuspensao = new Estoque(8); // Estoque de 8 suspensões
        Produto produtoSuspensao = new Produto("Suspensão", 1500.00, estoqueSuspensao);
        produtosDisponiveis.add(produtoSuspensao);

        // Verifique se os produtos foram corretamente inicializados
        for (Produto p : produtosDisponiveis) {
            System.out.println(p.getNome() + " - " + p.getPreco() + " - Estoque: " + p.getEstoque().getQuantidade());
        }
    }

    public void exibirMenu() {
        int opcao = 0;
        do {
            System.out.println("\nBem-vindo ao sistema de peças de carro!");
            System.out.println("1. Comprar");
            System.out.println("2. Visualizar carrinho");
            System.out.println("3. Finalizar compra");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            switch (opcao) {
                case 1:
                    realizarCompra();
                    break;
                case 2:
                    visualizarCarrinho();
                    break;
                case 3:
                    finalizarCompra();
                    break;
                case 4:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 4);
    }

    private void realizarCompra() {
        System.out.println("\nPeças de carro disponíveis:");
        for (int i = 0; i < produtosDisponiveis.size(); i++) {
            Produto produto = produtosDisponiveis.get(i);
            System.out.println((i + 1) + ". " + produto.getNome() + " - R$" + produto.getPreco());
        }
        System.out.print("Escolha o número da peça: ");
        int escolha = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer

        if (escolha < 1 || escolha > produtosDisponiveis.size()) {
            System.out.println("Escolha inválida.");
            return;
        }

        Produto produtoEscolhido = produtosDisponiveis.get(escolha - 1);

        System.out.print("Digite a quantidade: ");
        int quantidade = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer

        if (produtoEscolhido.getEstoque().reduzirQuantidade(quantidade)) {
            ItemCarrinho item = new ItemCarrinho(produtoEscolhido, quantidade);
            carrinho.adicionarItem(item);
            System.out.println("Peça adicionada ao carrinho!");
        } else {
            System.out.println("Quantidade insuficiente no estoque!");
        }
    }

    private void visualizarCarrinho() {
        System.out.println("\nItens no carrinho:");
        if (carrinho.getItens().isEmpty()) {
            System.out.println("Carrinho vazio.");
        } else {
            for (ItemCarrinho item : carrinho.getItens()) {
                System.out.println(item.getProduto().getNome() + " - R$" + item.getProduto().getPreco() + " x " + item.getQuantidade());
            }
        }
    }

    private void finalizarCompra() {
        double total = carrinho.calcularTotal();
        System.out.println("Total: R$" + total);

        // Perguntar se deseja aplicar um cupom
        System.out.print("Deseja aplicar um cupom? (sim/não): ");
        String resposta = scanner.nextLine();
        double desconto = 0.0;
        if (resposta.equalsIgnoreCase("sim")) {
            mostrarCuponsDisponiveis();  // Exibir cupons disponíveis
            System.out.print("Digite o código do cupom: ");
            String codigoCupom = scanner.nextLine();
            desconto = aplicarDesconto(codigoCupom, total);  // Aplicar o desconto
            if (desconto > 0) {
                System.out.println("Desconto aplicado: R$" + desconto);
            } else {
                System.out.println("Cupom inválido ou sem desconto.");
            }
        }

        // Mostrar o valor final com o desconto
        double totalComDesconto = total - desconto;
        System.out.println("Total com desconto: R$" + totalComDesconto);

        // Perguntar se deseja finalizar a compra
        System.out.print("Deseja finalizar a compra? (sim/não): ");
        resposta = scanner.nextLine();
        if (resposta.equalsIgnoreCase("sim")) {
            System.out.println("Compra finalizada!");
            carrinho = new Carrinho(); // Limpar carrinho após a compra
        } else {
            System.out.println("Compra cancelada.");
        }
    }

    // Método que aplica o desconto com base no código do cupom
    private double aplicarDesconto(String codigo, double total) {
        double desconto = 0.0;

        // Exemplo de cupons
        if (codigo.equals("promo10")) {
            desconto = total * 0.10;  // 10% de desconto
        } else if (codigo.equals("promo20")) {
            desconto = total * 0.20;  // 20% de desconto
        } else if (codigo.equals("promo50")) {
            desconto = total * 0.50;  // 50% de desconto
        }

        return desconto;
    }

    // Método para mostrar cupons disponíveis
    private void mostrarCuponsDisponiveis() {
        System.out.println("Cupons disponíveis:");
        System.out.println("1. promo10 - 10% de desconto");
        System.out.println("2. promo20 - 20% de desconto");
        System.out.println("3. promo50 - 50% de desconto");
    }
}
