import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class KNN {

    // Méthode pour calculer la distance Euclidienne entre deux points
    public static double euclideanDistance(ArrayList<Double> point1, ArrayList<Double> point2) {
        double sum = 0.0;
        for (int i = 0; i < point1.size(); i++) {
            sum += Math.pow(point1.get(i) - point2.get(i), 2);
        }
        return Math.sqrt(sum);
    }

    // Méthode pour calculer la distance de Manhattan
    public static double manhattanDistance(ArrayList<Double> point1, ArrayList<Double> point2) {
        double sum = 0.0;
        for (int i = 0; i < point1.size(); i++) {
            sum += Math.abs(point1.get(i) - point2.get(i));
        }
        return sum;
    }

    // Méthode pour calculer la distance Minkowski (p-norm)
    public static double minkowskiDistance(ArrayList<Double> point1, ArrayList<Double> point2, int p) {
        double sum = 0.0;
        for (int i = 0; i < point1.size(); i++) {
            sum += Math.pow(Math.abs(point1.get(i) - point2.get(i)), p);
        }
        return Math.pow(sum, 1.0 / p);
    }

    // Méthode pour effectuer la classification avec l'algorithme des k-plus-proches voisins
    public static int classify(ArrayList<ArrayList<Double>> trainingData, ArrayList<Integer> trainingLabels,
                               ArrayList<Double> testPoint, int k, String distanceType, int p) {
        ArrayList<DistanceLabel> distances = new ArrayList<>();

        // Calcul des distances entre le point de test et chaque point d'entraînement
        for (int i = 0; i < trainingData.size(); i++) {
            double distance = 0.0;
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
                    throw new IllegalArgumentException("Distance type not supported");
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

    // Classe interne pour stocker une distance et un label
    private static class DistanceLabel {
        private double distance;
        private int label;

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
}
