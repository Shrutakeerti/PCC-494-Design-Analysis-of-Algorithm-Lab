import java.util.Scanner;

public class FirstPositiveValue {
    // Function to find the first positive value of the given function
    static int firstPositiveValue(int[] coefficients) {
        for (int x = 1; ; x++) {
            int value = computeFunctionValue(coefficients, x);
            if (value > 0)
                return x;
        }
    }

    // Function to compute the value of the given function for a given value of x
    static int computeFunctionValue(int[] coefficients, int x) {
        int value = 0;
        int power = coefficients.length - 1;
        for (int coefficient : coefficients) {
            value += coefficient * Math.pow(x, power);
            power--;
        }
        return value;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the degree of the function:");
        int degree = scanner.nextInt();
        int[] coefficients = new int[degree + 1];
        System.out.println("Enter the coefficients of the function from highest degree to constant:");
        for (int i = degree; i >= 0; i--) {
            coefficients[i] = scanner.nextInt();
        }
        int n = firstPositiveValue(coefficients);
        System.out.println("The first positive value of the function occurs at n = " + n);
    }
}

