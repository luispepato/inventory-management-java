import java.util.Scanner;

public class ControleEstoque {

    static String[] produtos    = new String[10];
    static int[]    quantidades = new int[10];
    static int      contador    = 0;

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;

        do {
            exibirMenu();
            opcao = sc.nextInt();
            sc.nextLine(); // limpa o buffer

            switch (opcao) {
                case 1 -> cadastrarProduto();
                case 2 -> listarProdutos();
                case 3 -> entradaEstoque();
                case 4 -> saidaEstoque();
                case 5 -> estoqueBaixo();
                case 6 -> System.out.println("Encerrando o sistema. Até logo!");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }

        } while (opcao != 6);
    }

    // ─── MENU ────────────────────────────────────────────────────────────────

    static void exibirMenu() {
        System.out.println("\n====== CONTROLE DE ESTOQUE ======");
        System.out.println("1 - Cadastrar produto");
        System.out.println("2 - Listar produtos");
        System.out.println("3 - Entrada de estoque");
        System.out.println("4 - Saída de estoque");
        System.out.println("5 - Mostrar produtos com estoque baixo");
        System.out.println("6 - Sair");
        System.out.print("Escolha uma opção: ");
    }

    // ─── 1: CADASTRAR ────────────────────────────────────────────────────────

    static void cadastrarProduto() {
        if (contador >= produtos.length) {
            System.out.println("Estoque cheio! Limite de " + produtos.length + " produtos.");
            return;
        }

        System.out.print("Nome do produto: ");
        String nome = sc.nextLine();

        System.out.print("Quantidade inicial: ");
        int qtd = sc.nextInt();
        sc.nextLine();

        produtos[contador]    = nome;
        quantidades[contador] = qtd;
        contador++;

        System.out.println("Produto \"" + nome + "\" cadastrado com sucesso!");
    }

    // ─── 2: LISTAR ───────────────────────────────────────────────────────────

    static void listarProdutos() {
        if (contador == 0) {
            System.out.println("Nenhum produto cadastrado ainda.");
            return;
        }

        System.out.println("\n--- Lista de Produtos ---");
        System.out.printf("%-4s %-20s %s%n", "#", "Produto", "Quantidade");
        System.out.println("-".repeat(36));

        for (int i = 0; i < contador; i++) {
            System.out.printf("%-4d %-20s %d%n", (i + 1), produtos[i], quantidades[i]);
        }
    }

    // ─── 3: ENTRADA ──────────────────────────────────────────────────────────

    static void entradaEstoque() {
        listarProdutos();
        if (contador == 0) return;

        System.out.print("Número do produto para entrada: ");
        int indice = sc.nextInt() - 1;
        sc.nextLine();

        if (indice < 0 || indice >= contador) {
            System.out.println("Produto não encontrado.");
            return;
        }

        System.out.print("Quantidade a adicionar: ");
        int qtd = sc.nextInt();
        sc.nextLine();

        quantidades[indice] += qtd;
        System.out.println("Estoque atualizado! " + produtos[indice] + " agora tem " + quantidades[indice] + " unidades.");
    }

    // ─── 4: SAÍDA ────────────────────────────────────────────────────────────

    static void saidaEstoque() {
        listarProdutos();
        if (contador == 0) return;

        System.out.print("Número do produto para saída: ");
        int indice = sc.nextInt() - 1;
        sc.nextLine();

        if (indice < 0 || indice >= contador) {
            System.out.println("Produto não encontrado.");
            return;
        }

        System.out.print("Quantidade a retirar: ");
        int qtd = sc.nextInt();
        sc.nextLine();

        if (qtd > quantidades[indice]) {
            System.out.println("Quantidade insuficiente em estoque! Disponível: " + quantidades[indice]);
            return;
        }

        quantidades[indice] -= qtd;
        System.out.println("Saída registrada! " + produtos[indice] + " agora tem " + quantidades[indice] + " unidades.");
    }

    // ─── 5: ESTOQUE BAIXO ────────────────────────────────────────────────────

    static void estoqueBaixo() {
        int minimo = 5; // limite configurável
        boolean achou = false;

        System.out.println("\n--- Produtos com estoque baixo (< " + minimo + " unidades) ---");

        for (int i = 0; i < contador; i++) {
            if (quantidades[i] < minimo) {
                System.out.println("⚠  " + produtos[i] + " — " + quantidades[i] + " unidades");
                achou = true;
            }
        }

        if (!achou) {
            System.out.println("Todos os produtos estão com estoque adequado.");
        }
    }
}
