import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class KNN {
    private ArrayList<ArrayList<Double>> trainingData;
    private ArrayList<Integer> trainingLabels;
    private int k;

    // Constructeur de la classe KNN
    public KNN(ArrayList<ArrayList<Double>> trainingData, ArrayList<Integer> trainingLabels, int k) {
        this.trainingData = trainingData;
        this.trainingLabels = trainingLabels;
        this.k = k;
    }

    // Méthode pour calculer la distance Euclidienne entre deux points
    public static double euclideanDistance(ArrayList<Double> point1, ArrayList<Double> point2) {
        validateEqualDimensions(point1, point2);

        double sum = 0.0;
        for (int i = 0; i < point1.size(); i++) {
            sum += Math.pow(point1.get(i) - point2.get(i), 2);
        }
        return Math.sqrt(sum);
    }

    // Méthode pour calculer la distance de Manhattan
    public static double manhattanDistance(ArrayList<Double> point1, ArrayList<Double> point2) {
        validateEqualDimensions(point1, point2);

        double sum = 0.0;
        for (int i = 0; i < point1.size(); i++) {
            sum += Math.abs(point1.get(i) - point2.get(i));
        }
        return sum;
    }

    // Méthode pour calculer la distance Minkowski (p-norm)
    public static double minkowskiDistance(ArrayList<Double> point1, ArrayList<Double> point2, int p) {
        validateEqualDimensions(point1, point2);

        double sum = 0.0;
        for (int i = 0; i < point1.size(); i++) {
            sum += Math.pow(Math.abs(point1.get(i) - point2.get(i)), p);
        }
        return Math.pow(sum, 1.0 / p);
    }

    // Méthode pour effectuer la classification avec l'algorithme des k-plus-proches voisins
    public int classify(ArrayList<Double> testPoint, String distanceType, int p) {
        validateTrainingData();

        ArrayList<DistanceLabel> distances = new ArrayList<>();

        // Calcul des distances entre le point de test et chaque point d'entraînement
        for (int i = 0; i < trainingData.size(); i++) {
            double distance;
            switch (distanceType.toLowerCase()) {
                case "euclidean":
                    distance = euclideanDistance(testPoint, trainingData.get(i));
                    break;
                case "manhattan":
                    distance = manhattanDistance(testPoint, trainingData.get(i));
                    break;
                case "minkowski":
                    distance = minkowskiDistance(testPoint, trainingData.get(i), p);
                    break;
                default:
                    throw new IllegalArgumentException("Distance type not supported: " + distanceType);
            }
            distances.add(new DistanceLabel(distance, trainingLabels.get(i)));
        }

        // Trier les distances par ordre croissant
        Collections.sort(distances, Comparator.comparingDouble(DistanceLabel::getDistance));

        // Sélectionner les k plus proches voisins
        ArrayList<Integer> nearestLabels = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            nearestLabels.add(distances.get(i).getLabel());
        }

        // Retourner la classe la plus fréquente parmi les k voisins
        return majorityVote(nearestLabels);
    }

    // Méthode pour voter la classe majoritaire
    private static int majorityVote(ArrayList<Integer> labels) {
        // Compter les occurrences de chaque label
        int maxCount = 0;
        int majorityLabel = -1;

        for (int label : labels) {
            int count = Collections.frequency(labels, label);
            if (count > maxCount) {
                maxCount = count;
                majorityLabel = label;
            }
        }

        return majorityLabel;
    }

    // Validation des dimensions des points
    private static void validateEqualDimensions(ArrayList<Double> point1, ArrayList<Double> point2) {
        if (point1.size() != point2.size()) {
            throw new IllegalArgumentException("Points must have the same dimensions.");
        }
    }

    // Validation des données d'entraînement
    private void validateTrainingData() {
        if (trainingData.size() != trainingLabels.size()) {
            throw new IllegalArgumentException("Training data and labels must have the same size.");
        }
        if (trainingData.isEmpty()) {
            throw new IllegalArgumentException("Training data cannot be empty.");
        }
        if (k > trainingData.size()) {
            throw new IllegalArgumentException("k cannot be greater than the number of training samples.");
        }
    }

    // Classe interne pour stocker une distance et un label
    private static class DistanceLabel {
        private final double distance;
        private final int label;

        public DistanceLabel(double distance, int label) {
            this.distance = distance;
            this.label = label;
        }

        public double getDistance() {
            return distance;
        }

        public int getLabel() {
            return label;
        }
    }

    // Méthode pour exécuter l'algorithme KNN sur les données d'entraînement et afficher le résultat
    public void runKNN() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the distance metric (euclidean, manhattan, minkowski): ");
        String distanceType = sc.nextLine().trim().toLowerCase();

        int p = 2; // Default for Euclidean and can be adjusted for Minkowski
        if (distanceType.equals("minkowski")) {
            System.out.print("Enter the p value for Minkowski distance: ");
            p = sc.nextInt();
        }

        // Exemple de test avec un point fictif (remplissez-le avec vos propres données)
        ArrayList<Double> testPoint = new ArrayList<>();
        System.out.println("Enter the test point coordinates: ");
        for (int i = 0; i < trainingData.get(0).size(); i++) {
            System.out.print("Coordinate " + (i + 1) + ": ");
            testPoint.add(sc.nextDouble());
        }

        int result = classify(testPoint, distanceType, p);
        System.out.println("Predicted label for the test point: " + result);
    }
}
