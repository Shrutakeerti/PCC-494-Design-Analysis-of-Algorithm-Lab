import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DriverChange {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input the number of coins of each denomination
        System.out.println("Enter the number of coins for each denomination:");
        int[] coins = new int[5];
        int[] denominations = {1, 2, 5, 10, 20}; // Added denominations array
        String[] denominationNames = {"1-rupee", "2-rupee", "5-rupee", "10-rupee", "20-rupee"}; // Added denominationNames array
        for (int i = 0; i < 5; i++) {
            System.out.print("Number of " + denominationNames[i] + " coins: ");
            coins[i] = scanner.nextInt();
        }

        // Input the amount paid by the passenger
        System.out.print("Enter the amount paid by the passenger: ");
        int amountPaid = scanner.nextInt();

        // Calculate the change to be given
        int change = amountPaid - 17;

        // Calculate combinations
        List<List<Integer>> combinations = new ArrayList<>();
        calculateCombinations(coins, denominations, change, 0, new ArrayList<>(), combinations); // Passed denominations array

        // Filter out combinations meeting the requirements
        List<List<Integer>> validCombinations = new ArrayList<>();
        for (List<Integer> combination : combinations) {
            if (isValid(combination)) {
                validCombinations.add(combination);
            }
        }

        // Display results
        System.out.println("Possible combinations to give change: ");
        for (List<Integer> combination : validCombinations) {
            System.out.println(combination);
        }

        // Find combination with the least number of coins
        List<Integer> minCoinsCombination = findMinCoinsCombination(validCombinations);
        System.out.println("Combination with the least number of coins: " + minCoinsCombination);

        // Find combination with the maximum number of coins
        List<Integer> maxCoinsCombination = findMaxCoinsCombination(validCombinations);
        System.out.println("Combination with the maximum number of coins: " + maxCoinsCombination);
    }

    // Function to calculate all possible combinations of coins
    private static void calculateCombinations(int[] coins, int[] denominations, int target, int index, List<Integer> current, List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(current));
            return;
        }

        if (target < 0 || index >= coins.length) {
            return;
        }

        // Include the coin at the current index if available
        for (int count = 0; count <= coins[index] && count * denominations[index] <= target; count++) {
            for (int i = 0; i < count; i++) {
                current.add(denominations[index]);
            }
            calculateCombinations(coins, denominations, target - count * denominations[index], index + 1, current, result);
            for (int i = 0; i < count; i++) {
                current.remove(current.size() - 1);
            }
        }

        // Skip the coin at the current index
        calculateCombinations(coins, denominations, target, index + 1, current, result);
    }

    // Function to check if a combination meets the requirements
    private static boolean isValid(List<Integer> combination) {
        int count1 = 0;
        int count20 = 0;
        for (int coin : combination) {
            if (coin == 1) {
                count1++;
            }
            if (coin == 20) {
                count20++;
            }
        }
        return count1 <= 3 && count20 <= 0;
    }

    // Function to find the combination with the least number of coins
    private static List<Integer> findMinCoinsCombination(List<List<Integer>> combinations) {
        List<Integer> minCoinsCombination = null;
        int minCoins = Integer.MAX_VALUE;
        for (List<Integer> combination : combinations) {
            if (combination.size() < minCoins) {
                minCoins = combination.size();
                minCoinsCombination = combination;
            }
        }
        return minCoinsCombination;
    }

    // Function to find the combination with the maximum number of coins
    private static List<Integer> findMaxCoinsCombination(List<List<Integer>> combinations) {
        List<Integer> maxCoinsCombination = null;
        int maxCoins = Integer.MIN_VALUE;
        for (List<Integer> combination : combinations) {
            if (combination.size() > maxCoins) {
                maxCoins = combination.size();
                maxCoinsCombination = combination;
            }
        }
        return maxCoinsCombination;
    }
}
