import java.util.Scanner;

public class Banco {
    String menu = """
    =================================
    Bem-vindo ao Banco Dio!
    Escolha uma operação:
    [d] Depósito
    [s] Saque
    [e] Extrato
    [q] Sair
    =================================
    """;

    public static void main(String[] args) {
        Banco app = new Banco();
        System.out.println(app.menu);

        // Variáveis de estado
        double saldo = 0;
        final double LIMITE = 500;
        int numeroSaques = 0;
        final int MAX_SAQUES = 3;
        String extrato = "";

        // Scanner para leitura
        Scanner scanner = new Scanner(System.in);

        // Loop principal, agora corretamente dentro de main
        while (true) {
            System.out.print("Digite a opção desejada: ");
            String opcao = scanner.nextLine();

            switch (opcao) {
                case "d":
                    System.out.print("Quanto depositar? R$ ");
                    double deposito = Double.parseDouble(scanner.nextLine());
                    if (deposito > 0) {
                        saldo += deposito;
                        System.out.printf("Depósito de R$ %.2f realizado. Saldo: R$ %.2f%n",
                                          deposito, saldo);
                        extrato += String.format("Depósito: R$ %.2f%n", valor);
                    } else {
                        System.out.println("Valor deve ser positivo.");
                    }
                    
                    break;
                case "s":
                    System.out.print("Quanto sacar? R$ ");
                    double saque = Double.parseDouble(scanner.nextLine());
                    if (numeroSaques >= MAX_SAQUES) {
                        System.out.println("Limite de saques atingido.");
                    } else if (saque > saldo) {
                        System.out.println("Saldo insuficiente.");
                    } else if (saque > LIMITE) {
                        System.out.println("Excede limite de R$500.");
                    } else {
                        saldo -= saque;
                        numeroSaques++;
                        extrato += String.format("Saque: R$ %.2f%n", valor)
                        System.out.printf("Saque de R$ %.2f realizado. Saldo: R$ %.2f%n",
                                          saque, saldo);
                    }
                    break;
                case "e":
                    System.out.println("================ EXTRATO ================");
                    if (extrato.isEmpty()) {
                        System.out.println("Nenhum movimento realizado.");
                    } else {
                        System.out.println(extrato);
                    }
                    break;
                case "q":
                    System.out.println("Encerrando. Até logo!");
                    scanner.close();  // fecha recurso
                    return;          // sai de main
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
