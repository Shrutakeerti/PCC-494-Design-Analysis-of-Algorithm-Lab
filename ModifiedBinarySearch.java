import java.util.Scanner;

public class ModifiedBinarySearch {
    // Function to find the pivot element in the rotated sorted array
    static int findPivot(int[] arr, int low, int high) {
        if (high < low) return -1;
        if (high == low) return low;

        int mid = (low + high) / 2;
        if (mid < high && arr[mid] > arr[mid + 1])
            return mid;
        if (mid > low && arr[mid] < arr[mid - 1])
            return mid - 1;
        if (arr[low] >= arr[mid])
            return findPivot(arr, low, mid - 1);
        return findPivot(arr, mid + 1, high);
    }

    // Function to perform binary search
    static int binarySearch(int[] arr, int low, int high, int key) {
        if (high < low) return -1;

        int mid = (low + high) / 2;
        if (key == arr[mid])
            return mid;
        if (key > arr[mid])
            return binarySearch(arr, (mid + 1), high, key);
        return binarySearch(arr, low, (mid - 1), key);
    }

    // Function to search for a key in rotated sorted array
    static int search(int[] arr, int key) {
        int n = arr.length;
        int pivot = findPivot(arr, 0, n - 1);

        if (pivot == -1)
            return binarySearch(arr, 0, n - 1, key);

        if (arr[pivot] == key)
            return pivot;

        if (arr[0] <= key)
            return binarySearch(arr, 0, pivot - 1, key);
        return binarySearch(arr, pivot + 1, n - 1, key);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the size of the rotated sorted array:");
        int size = scanner.nextInt();
        int[] arr = new int[size];
        System.out.println("Enter the elements of the rotated sorted array:");
        for (int i = 0; i < size; i++) {
            arr[i] = scanner.nextInt();
        }
        System.out.println("Enter the key to search:");
        int key = scanner.nextInt();
        int index = search(arr, key);
        if (index != -1)
            System.out.println("Element found at index " + index);
        else
            System.out.println("Element not found");
    }
}
