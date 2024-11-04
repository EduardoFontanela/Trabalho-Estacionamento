import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Estacionamento estacionamento = new Estacionamento();

        while (true) {
            System.out.println("1. Cadastrar vaga");
            System.out.println("2. Registrar entrada");
            System.out.println("3. Registrar saída");
            System.out.println("4. Gerar relatório de vagas ocupadas");
            System.out.println("5. Histórico de permanência");
            System.out.println("6. Ver vagas disponíveis");
            System.out.println("7. Sair");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Número da vaga: ");
                    int numero = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Tamanho da vaga (pequeno, médio, grande): ");
                    String tamanho = scanner.nextLine();
                    estacionamento.cadastrarVaga(numero, tamanho);
                    break;
                case 2:
                    System.out.print("Placa do veículo: ");
                    String placa = scanner.nextLine();
                    System.out.print("Modelo do veículo: ");
                    String modelo = scanner.nextLine();
                    System.out.print("Tamanho do veículo (pequeno, médio, grande): ");
                    tamanho = scanner.nextLine();
                    estacionamento.registrarEntrada(placa, modelo, tamanho);
                    break;
                case 3:
                    System.out.print("Placa do veículo: ");
                    placa = scanner.nextLine();
                    System.out.print("Quantas horas o veículo permaneceu no estacionamento? ");
                    long horasPermanencia = scanner.nextLong();
                    scanner.nextLine();
                    long horaEntradaCalculada = System.currentTimeMillis() - (horasPermanencia * 3600000);
                    long horaSaidaCalculada = System.currentTimeMillis();
                    estacionamento.registrarSaidaComHoras(placa, horaEntradaCalculada, horaSaidaCalculada, horasPermanencia);
                    break;
                case 4:
                    estacionamento.gerarRelatorio();
                    break;
                case 5:
                    estacionamento.gerarHistorico();
                    break;
                case 6:
                    estacionamento.listarVagasDisponiveis();
                    break;
                case 7:
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}
