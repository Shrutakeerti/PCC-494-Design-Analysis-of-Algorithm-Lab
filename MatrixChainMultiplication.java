import java.util.Scanner;

public class MatrixChainMultiplication {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input the dimensions of matrices
        System.out.print("Enter the number of matrices: ");
        int n = scanner.nextInt();
        int[] dimensions = new int[n + 1];
        System.out.println("Enter the dimensions of matrices:");
        for (int i = 0; i <= n; i++) {
            System.out.print("Dimension " + i + ": ");
            dimensions[i] = scanner.nextInt();
        }

        // Calculate the minimum number of multiplications
        int minimumMultiplications = matrixChainOrder(dimensions);

        // Output the result
        System.out.println("Minimum number of multiplications: " + minimumMultiplications);
    }

    public static int matrixChainOrder(int[] dimensions) {
        int n = dimensions.length - 1;
        int[][] dp = new int[n][n];

        for (int len = 2; len < n; len++) {
            for (int i = 1; i < n - len + 1; i++) {
                int j = i + len - 1;
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i; k <= j - 1; k++) {
                    int cost = dp[i][k] + dp[k + 1][j] + dimensions[i - 1] * dimensions[k] * dimensions[j];
                    if (cost < dp[i][j]) {
                        dp[i][j] = cost;
                    }
                }
            }
        }
        return dp[1][n - 1];
    }
}
