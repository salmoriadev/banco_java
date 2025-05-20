import java.util.Scanner;

public class Imposto {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double salario = scanner.nextDouble();
        if (salario <= 2000) {
            System.out.println("Isento");
        } else {
            double imposto = calcularImposto(salario);
            System.out.printf("R$ %.2f\n", imposto);
        }

        scanner.close();
    }

    public static double calcularImposto(double salario) {
        if (salario <= 3000) {
            return (salario - 2000) * 0.08;
        } else if (salario <= 4500) {
            return (salario - 3000) * 0.18 + 1000 * 0.08;
        } else {
            return (salario - 4500) * 0.28 + 1500 * 0.18 + 1000 * 0.08;
        }
    }
}

