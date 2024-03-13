import java.util.Scanner;

public class SortArrayLinearTime {
    static void sortArray(int[] A, int j, int k) {
        int[] count = new int[k - j + 1];
        // Counting frequencies of elements between j and k
        for (int num : A) {
            count[num - j]++;
        }
        // Reconstructing the sorted array
        int index = 0;
        for (int i = 0; i < count.length; i++) {
            for (int c = 0; c < count[i]; c++) {
                A[index++] = i + j;
            }
        }
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
            int[] A = GenerateArray.generateArray(n, j, k);
            System.out.println("Generated array A before sorting:");
            for (int i = 0; i < n; i++) {
                System.out.print(A[i] + " ");
            }
            System.out.println(); // New line

            sortArray(A, j, k);
            System.out.println("Sorted array A:");
            for (int i = 0; i < n; i++) {
                System.out.print(A[i] + " ");
            }
        } else {
            System.out.println("Invalid input! Make sure 1 < j < k < n.");
        }
    }
}
