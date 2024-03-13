import java.util.Scanner;

public class SortArrayLinearTimeExpected {
    static void sortArray(int[] A, int j, int k) {
        // The array A is uniformly distributed between j and k.
        // So, we just need to print the numbers from j to k in order.
        for (int i = j; i <= k; i++) {
            System.out.print(i + " ");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the value of j:");
        int j = scanner.nextInt();
        System.out.println("Enter the value of k:");
        int k = scanner.nextInt();

        if (j < k) {
            System.out.println("Sorted array A:");
            sortArray(null, j, k);
        } else {
            System.out.println("Invalid input! Make sure j < k.");
        }
    }
}
