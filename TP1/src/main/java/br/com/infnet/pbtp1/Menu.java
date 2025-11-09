package br.com.infnet.pbtp1;

import br.com.infnet.pbtp1.entity.Gato;

import java.util.List;
import java.util.Scanner;

public class Menu {

    private GatoService gatoService;
    private Scanner scanner = new Scanner(System.in);
    int FIM = 0;

    public Menu(GatoService gatoService) {
        this.gatoService = gatoService;
    }

    public void menu(){
        System.out.println("-------Menu---------");
        System.out.println("[1] - Adcionar gato");
        System.out.println("[2] - Atualizar disponibilidade de adoção do gato");
        System.out.println("[3] - Excluir um gato");
        System.out.println("[4] - Consultar um gato");
        System.out.println("[5] - Listar todos os gatos");
        System.out.println("[0] - Sair");
        System.out.println("---------------------");
    }

    public void entrarOpcoes(){
       int opcao = -1;
        while(opcao != FIM){
            menu();
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao){
                case 1 -> cadastrarGato();
                case 2 -> atualizarDisponibilidade();
                case 3 -> deletarGato();
                case 4 -> consultarGato();
                case 5 -> listarGatos();
                case 0 -> System.out.println("Finalizando Aplicação");
                default -> System.out.println("Opção inválida.");
            }
        }

    }

    public void cadastrarGato(){
        System.out.println("Digite o nome do gato: ");
        String nome = scanner.nextLine();
        System.out.println("Escreva uma breve descrição para o gato: ");
        String descricao = scanner.nextLine();

        System.out.println("Digite F (fêmea) ou M (macho) para o gênero do gato: ");
        String generoInput = scanner.nextLine().trim().toUpperCase();
        if (!generoInput.equals("M") && !generoInput.equals("F")) {
            System.out.println("Entrada inválida. Use apenas 'M' ou 'F'.");
            return;
        }
        Character genero = generoInput.charAt(0);


        System.out.println("O gato está disponivel para adocão? [ S / N]");
        String resposta = scanner.nextLine();
        Boolean disponivelAdocao = true;
        if(resposta.equals("N")){
            disponivelAdocao = false;
        }

        Gato gato  = new Gato();
        gato.setNome(nome);
        gato.setDescricao(descricao);
        gato.setGenero(genero);
        gato.setDisponivelAdocao(disponivelAdocao);

        gatoService.criarGato(gato);
    }

    public void atualizarDisponibilidade(){
        System.out.println("Digite o ID do gato que deseja deletar: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        System.out.print("O gato está disponível para adoção? [S/N]: ");
        String resposta = scanner.nextLine().trim().toUpperCase();
        boolean disponivelAdocao = !resposta.equals("N");

        gatoService.atualizarDisponibilidadeAdocao(id, disponivelAdocao);
    }

    public void deletarGato(){
        System.out.println("Digite o ID do gato que deseja deletar: ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        gatoService.deletarGato(id);
    }

    public void consultarGato(){
        System.out.println("Digite o ID do gato que deseja consultar: ");
        Long id = scanner.nextLong();
        Gato gato = gatoService.buscarGatoPorId(id);
        System.out.println(gato);
    }

    public void listarGatos(){
        List<Gato> gatos = gatoService.listarGatos();
        for (Gato gato : gatos) {
            System.out.println("ID: " + gato.getId() +
                    " - Nome: " + gato.getNome() +
                    " - Descricao: " + gato.getDescricao() +
                    " - Adotado? " + gato.isDisponivelAdocao());
        }
    }
}
