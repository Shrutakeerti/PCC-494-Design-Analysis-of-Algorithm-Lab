import java.util.Scanner;

public class TowerOfHanoi {
    // Function to move n disks from source to destination using auxiliary and extra pegs
    static void towerOfHanoi(int n, String source, String destination, String auxiliary1, String auxiliary2) {
        if (n == 0)
            return;
        if (n == 1) {
            System.out.println("Move disk 1 from " + source + " to " + destination);
            return;
        }

        int k = (int) Math.ceil(n / 2.0);
        
        towerOfHanoi(n - k, source, auxiliary1, auxiliary2, destination); // Move k disks from source to auxiliary1 using auxiliary2
        towerOfHanoi(k, source, destination, auxiliary1, auxiliary2); // Move remaining (n-k) disks from source to destination using auxiliary1
        System.out.println("Move disk " + n + " from " + source + " to " + destination);
        towerOfHanoi(n - k, auxiliary2, destination, source, auxiliary1); // Move remaining (n-k) disks from auxiliary2 to destination using source and auxiliary1
        towerOfHanoi(n - k, auxiliary1, destination, source, auxiliary2); // Move remaining (n-k) disks from auxiliary1 to destination using source and auxiliary2
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of disks:");
        int n = scanner.nextInt();
        towerOfHanoi(n, "Peg 1", "Peg 2", "Peg 3", "Peg 4");
    }
}
