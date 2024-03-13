import java.util.Scanner;

public class GenerateArray {
    static int[] generateArray(int n, int j, int k) {
        int[] A = new int[n];
        for (int i = 0; i < n; i++) {
            // Generating random numbers between j and k (inclusive)
            A[i] = (int) (Math.random() * (k - j + 1)) + j;
        }
        return A;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of elements (n):");
        int n = scanner.nextInt();
        System.out.println("Enter the value of j (1 < j < n):");
        int j = scanner.nextInt();
        System.out.println("Enter the value of k (j < k < n):");
        int k = scanner.nextInt();

        if (1 < j && j < k && k < n) {
            int[] A = generateArray(n, j, k);
            System.out.println("Generated array A:");
            for (int i = 0; i < n; i++) {
                System.out.print(A[i] + " ");
            }
        } else {
            System.out.println("Invalid input! Make sure 1 < j < k < n.");
        }
    }
}
