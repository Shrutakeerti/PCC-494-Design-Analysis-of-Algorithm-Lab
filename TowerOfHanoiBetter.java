import java.util.Scanner;

public class TowerOfHanoiBetter {
    // Function to solve Tower of Hanoi with 4 pegs
    static void towerOfHanoi(int n, int source, int destination, int auxiliary1, int auxiliary2) {
        if (n == 0)
            return;
        
        if (n == 1) {
            System.out.println("Move disk 1 from Peg " + source + " to Peg " + destination);
            return;
        }
        
        if (n == 2) {
            System.out.println("Move disk 1 from Peg " + source + " to Peg " + auxiliary1);
            System.out.println("Move disk 2 from Peg " + source + " to Peg " + destination);
            System.out.println("Move disk 1 from Peg " + auxiliary1 + " to Peg " + destination);
            return;
        }

        towerOfHanoi(n - 2, source, auxiliary1, auxiliary2, destination); // Move n-2 disks to auxiliary1
        System.out.println("Move disk " + (n - 1) + " from Peg " + source + " to Peg " + auxiliary2);
        System.out.println("Move disk " + n + " from Peg " + source + " to Peg " + destination);
        System.out.println("Move disk " + (n - 1) + " from Peg " + auxiliary2 + " to Peg " + destination);
        towerOfHanoi(n - 2, auxiliary1, destination, source, auxiliary2); // Move n-2 disks from auxiliary1 to destination
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of disks:");
        int n = scanner.nextInt();
        towerOfHanoi(n, 1, 2, 3, 4);
    }
}

