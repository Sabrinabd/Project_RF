import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class KNN {
    private ArrayList<Shape> trainingData;
    private ArrayList<Integer> trainingLabels;
    private int neighbors;// Nombre de voisins à considérer
    private boolean isValidated = false;

    // Constructeur de la classe KNN
    public KNN(ArrayList<Shape> trainingData, ArrayList<Integer> trainingLabels, int neighbors) {
        this.trainingData = trainingData;
        this.trainingLabels = trainingLabels;

        this.neighbors = neighbors;


    }

 

   

    

    // Méthode pour effectuer la classification avec l'algorithme des k-plus-proches voisins
    public int classify(ArrayList<Double> testPoint,String feature, int p) {
        if (!isValidated) {
            validateTrainingData();
            isValidated = true;
        }
        ArrayList<DistanceLabel> distances = new ArrayList<>();

        // Calcul des distances entre le point de test et chaque point d'entraînement
        for (int i = 0; i < trainingData.size(); i++) {
            double distance = 0;
            switch (feature) {
                case "ART":
                     distance=KMeans.calculateDistance(testPoint, trainingData.get(i).getART(), p);
                    break;
                case "E34":
                    distance=KMeans.calculateDistance(testPoint, trainingData.get(i).getE34(), p);
                    break;
                case "GFD":
                    distance=KMeans.calculateDistance(testPoint, trainingData.get(i).getGFD(), p);
                    break;
                case"Zernike7":
                    distance=KMeans.calculateDistance(testPoint, trainingData.get(i).getZernike7(), p);
                    break;
                case "Yang":
                    distance=KMeans.calculateDistance(testPoint, trainingData.get(i).getYang(), p);
                    break;
            }
            
            distances.add(new DistanceLabel(distance, trainingLabels.get(i)));
        }

        // Trier les distances par ordre croissant
        Collections.sort(distances, Comparator.comparingDouble(DistanceLabel::getDistance));

        // Sélectionner les k plus proches voisins
        ArrayList<Integer> nearestLabels = new ArrayList<>();
        for (int i = 0; i < neighbors; i++) {
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

    // Validation des données d'entraînement
    private void validateTrainingData() {
        if (trainingData.size() != trainingLabels.size()) {
            throw new IllegalArgumentException("Training data and labels must have the same size.");
        }
        if (trainingData.isEmpty()) {
            throw new IllegalArgumentException("Training data cannot be empty.");
        }
        if (neighbors > trainingData.size()) {
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

    
}
