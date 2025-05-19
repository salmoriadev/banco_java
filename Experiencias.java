import java.util.Scanner;

public class Experiencias {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int quantidadeRatos = 0;
        int quantidadeSapos = 0;
        int quantidadeCoelhos = 0;
        int total = 0;

        System.out.print("Digite o número de experimentos: ");
        int vezes = scanner.nextInt();

        for (int i = 0; i < vezes; i++) {
            System.out.print("Digite a quantidade e o tipo de animal (ex: 10 C): ");
            int numero = scanner.nextInt();
            char animal = scanner.next().toUpperCase().charAt(0);

            total += numero;

            switch (animal) {
                case 'R':
                    quantidadeRatos += numero;
                    break;
                case 'S':
                    quantidadeSapos += numero;
                    break;
                case 'C':
                    quantidadeCoelhos += numero;
                    break;
                default:
                    System.out.println("Animal desconhecido. Entrada ignorada.");
            }
        }

        System.out.println("\n--- Relatório Final ---");
        System.out.println("Total: " + total + " cobaias");
        System.out.println("Total de coelhos: " + quantidadeCoelhos);
        System.out.println("Total de ratos: " + quantidadeRatos);
        System.out.println("Total de sapos: " + quantidadeSapos);

        if (total > 0) {
            double percentualCoelhos = (double) quantidadeCoelhos / total * 100;
            double percentualRatos = (double) quantidadeRatos / total * 100;
            double percentualSapos = (double) quantidadeSapos / total * 100;

            System.out.printf("Percentual de coelhos: %.2f %%\n", percentualCoelhos);
            System.out.printf("Percentual de ratos: %.2f %%\n", percentualRatos);
            System.out.printf("Percentual de sapos: %.2f %%\n", percentualSapos);
        }

        scanner.close();
    }
}
