import java.util.Scanner;

public class MonotonicFunction {
    // Function to find the point where the monotonic function changes direction
    static int findPointOfChange(int startPoint) {
        for (int x = startPoint; ; x++) {
            double value = Math.exp(-x * x);
            double nextValue = Math.exp(-(x + 1) * (x + 1));
            if (value > nextValue)
                return x;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the starting point to search for the point of change:");
        int startPoint = scanner.nextInt();
        int n = findPointOfChange(startPoint);
        System.out.println("The point of change in the function occurs at n = " + n);
    }
}
