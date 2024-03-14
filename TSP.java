import java.util.*;

class TSP {
    private int n;
    private int[][] distanceMatrix;
    private List<Integer> bestTour;
    private int bestDistance;

    public TSP(int n, int[][] distanceMatrix) {
        this.n = n;
        this.distanceMatrix = distanceMatrix;
        this.bestTour = new ArrayList<>();
        this.bestDistance = Integer.MAX_VALUE;
    }

    public int calculateDistance(List<Integer> tour) {
        int totalDistance = 0;
        for (int i = 0; i < n - 1; i++) {
            totalDistance += distanceMatrix[tour.get(i)][tour.get(i + 1)];
        }
        totalDistance += distanceMatrix[tour.get(n - 1)][tour.get(0)]; // Return to starting city
        return totalDistance;
    }

    public void branchAndBound() {
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        priorityQueue.offer(new Node(0, new ArrayList<>(Collections.singletonList(0)), new HashSet<>(Collections.singletonList(0))));

        while (!priorityQueue.isEmpty()) {
            Node node = priorityQueue.poll();
            int lowerBound = node.lowerBound;
            List<Integer> tour = node.tour;
            Set<Integer> visited = node.visited;

            if (visited.size() == n) {
                int distance = calculateDistance(tour);
                if (distance < bestDistance) {
                    bestDistance = distance;
                    bestTour = new ArrayList<>(tour);
                }
                continue;
            }

            for (int city = 0; city < n; city++) {
                if (!visited.contains(city)) {
                    List<Integer> newTour = new ArrayList<>(tour);
                    newTour.add(city);
                    Set<Integer> newVisited = new HashSet<>(visited);
                    newVisited.add(city);
                    int newLowerBound = lowerBound + distanceMatrix[tour.get(tour.size() - 1)][city];

                    // Calculate a lower bound on the total distance
                    Set<Integer> remainingCities = new HashSet<>();
                    for (int i = 0; i < n; i++) {
                        if (!newVisited.contains(i)) {
                            remainingCities.add(i);
                        }
                    }
                    final int finalCity = city; // Store city in final variable
                    OptionalInt closestCityOptional = remainingCities.stream()
                            .mapToInt(c -> distanceMatrix[finalCity][c])
                            .min();
                    if (closestCityOptional.isPresent()) {
                        int closestCity = closestCityOptional.getAsInt();
                        remainingCities.remove(closestCity);
                        OptionalInt secondClosestCityOptional = remainingCities.stream()
                                .mapToInt(c -> distanceMatrix[finalCity][c])
                                .min();
                        if (secondClosestCityOptional.isPresent()) {
                            int secondClosestCity = secondClosestCityOptional.getAsInt();
                            newLowerBound += closestCity + secondClosestCity;

                            if (newLowerBound < bestDistance) {
                                priorityQueue.offer(new Node(newLowerBound, newTour, newVisited));
                            }
                        }
                    }
                }
            }
        }
    }

    public List<Integer> getBestTour() {
        return bestTour;
    }

    public int getBestDistance() {
        return bestDistance;
    }

    static class Node implements Comparable<Node> {
        int lowerBound;
        List<Integer> tour;
        Set<Integer> visited;

        Node(int lowerBound, List<Integer> tour, Set<Integer> visited) {
            this.lowerBound = lowerBound;
            this.tour = tour;
            this.visited = visited;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.lowerBound, other.lowerBound);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of cities: ");
        int n = scanner.nextInt();
        System.out.println("Enter the distance matrix:");
        int[][] distanceMatrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                distanceMatrix[i][j] = scanner.nextInt();
            }
        }

        TSP tspSolver = new TSP(n, distanceMatrix);
        tspSolver.branchAndBound();
        List<Integer> bestTour = tspSolver.getBestTour();
        int bestDistance = tspSolver.getBestDistance();

        System.out.println("Best tour: " + bestTour);
        System.out.println("Best distance: " + bestDistance);
    }
}
