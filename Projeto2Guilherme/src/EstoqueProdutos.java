package com.company;
import java.util.Scanner; //importando Scanner

//Classe que representa um produto
class Produto {
    String nome; //nome do produto
    double[] dados = new double[2]; //dados[0] = quantidade em estoque, dados[1] = preço
}

public class EstoqueProdutos {

    //Vetor de até 100 produtos (como um "estoque")
    static Produto[] estoque = new Produto[100];

    //Variável que controla quantos produtos já foram cadastrados
    static int qtd = 0;

    public static void main(String[] args) {
        //Criando um objeto Scanner para ler dados do teclado
        Scanner leia = new Scanner(System.in);

        //Variável que armazenará a opção do menu escolhida pelo usuário
        int opcao;

        //Mensagem inicial de boas-vindas ao sistema
        mensagem();

        //Laço principal do programa: exibe o menu até que o usuário escolha sair (opção 5)
        do {
            //Mostra as opções disponíveis no menu
            System.out.println("\nQual operação deseja seguir:");
            System.out.println("1. Cadastrar Produto");
            System.out.println("2. Consultar Produto");
            System.out.println("3. Atualizar Quantidade");
            System.out.println("4. Relatório Geral");
            System.out.println("5. Sair");

            //Lê a opção digitada
            opcao = leia.nextInt();

            //Escolhe a ação de acordo com a opção informada
            switch (opcao) {
                case 1:
                    cadastrarProduto(leia); //chama subrotina para cadastrar produto
                    break;
                case 2:
                    consultarProduto(leia); //chama subrotina para consultar produto pelo nome
                    break;
                case 3:
                    atualizarQuantidade(leia); //chama subrotina para atualizar quantidade de um produto
                    break;
                case 4:
                    relatorioGeral(); //chama subrotina para mostrar todos os produtos
                    break;
                case 5:
                    System.out.println("Encerrando o programa...");
                    break;
                default:
                    //Caso o usuário digite um número inválido
                    System.out.println("Opção inválida. Tente novamente.");
            }

        } while (opcao != 5); //continua rodando até a opção 5 (sair) ser escolhida
    }

    //Subrotina para exibir a mensagem de boas-vindas ao usuário
    public static void mensagem() {
        System.out.println("-------------------------------------------");
        System.out.println("BEM-VINDO AO SISTEMA DE ESTOQUE DE PRODUTOS");
        System.out.println("-------------------------------------------");
    }

    //Subrotina para cadastrar um novo produto
    public static void cadastrarProduto(Scanner leia) {
        //Verifica se o limite do vetor foi atingido
        if (qtd >= estoque.length) {
            System.out.println("Limite de produtos atingido!");
            return;
        }

        //Limpa o buffer antes de ler uma string
        leia.nextLine();

        //Lê o nome do produto
        System.out.print("Nome do produto: ");
        String nome = leia.nextLine();

        //Lê a quantidade disponível no estoque
        System.out.print("Quantidade disponível: ");
        double quantidade = leia.nextDouble();

        //Lê o preço do produto
        System.out.print("Preço do produto: R$ ");
        double preco = leia.nextDouble();

        //Cria um novo objeto Produto e armazena os dados informados
        Produto produto = new Produto();
        produto.nome = nome;
        produto.dados[0] = quantidade; //armazenando quantidade
        produto.dados[1] = preco;      //armazenando preço

        //Adiciona o produto no vetor e atualiza a quantidade de produtos
        estoque[qtd] = produto;
        qtd++; //incrementa a quantidade de produtos cadastrados

        //Mensagem de sucesso
        System.out.println("Produto cadastrado com sucesso!");
    }

    //Subrotina para consultar as informações de um produto pelo nome
    public static void consultarProduto(Scanner leia) {
        //Verifica se há produtos cadastrados
        if (qtd == 0) {
            System.out.println("Nenhum produto cadastrado ainda.");
            return;
        }

        //Limpa o buffer antes de ler texto
        leia.nextLine();

        //Solicita o nome do produto a ser buscado
        System.out.print("Digite o nome do produto para consulta: ");
        String busca = leia.nextLine();

        //Variável de controle para saber se o produto foi encontrado
        boolean encontrado = false;

        //Laço para percorrer o vetor de produtos
        for (int i = 0; i < qtd; i++) {
            //Compara ignorando maiúsculas/minúsculas
            if (estoque[i].nome.equalsIgnoreCase(busca)) {
                Produto p = estoque[i];

                //Mostra os dados do produto encontrado
                System.out.println("Produto encontrado!");
                System.out.printf("Nome: %s\n", p.nome);
                System.out.printf("Quantidade em estoque: %.0f\n", p.dados[0]);
                System.out.printf("Preço: R$ %.2f\n", p.dados[1]);

                encontrado = true; //marca como encontrado
                break;
            }
        }

        //Caso o produto não seja encontrado
        if (!encontrado) {
            System.out.println("Produto não encontrado!");
        }
    }

    //Subrotina para atualizar a quantidade em estoque de um produto
    public static void atualizarQuantidade(Scanner leia) {
        //Verifica se há produtos cadastrados
        if (qtd == 0) {
            System.out.println("Nenhum produto cadastrado ainda.");
            return;
        }

        leia.nextLine(); //Limpa buffer
        System.out.print("Digite o nome do produto que deseja atualizar: ");
        String busca = leia.nextLine();

        boolean encontrado = false;

        //Percorre o vetor procurando o produto pelo nome
        for (int i = 0; i < qtd; i++) {
            if (estoque[i].nome.equalsIgnoreCase(busca)) {
                System.out.print("Nova quantidade em estoque: ");
                double novaQtd = leia.nextDouble();

                estoque[i].dados[0] = novaQtd; //atualiza a quantidade
                System.out.println("Quantidade atualizada com sucesso!");

                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("Produto não encontrado!");
        }
    }

    //Subrotina para exibir o relatório completo com todos os produtos
    public static void relatorioGeral() {
        //Verifica se há produtos cadastrados
        if (qtd == 0) {
            System.out.println("Nenhum produto cadastrado ainda.");
            return;
        }

        //Mensagem inicial do relatório
        System.out.println("\n========= RELATÓRIO GERAL DE PRODUTOS =========");

        //Percorre todos os produtos e exibe suas informações
        for (int i = 0; i < qtd; i++) {
            Produto p = estoque[i];
            System.out.println("Nome: " + p.nome);
            System.out.printf("Quantidade: %.0f\n", p.dados[0]);
            System.out.printf("Preço: R$ %.2f\n", p.dados[1]);
            System.out.println("-----------------------------------------------");
        }
    }
}