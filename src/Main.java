import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

    // Specify the number of clusters (k)
    static int k = 10;

    //By default p=2 is the norm for the Euclidean distance
    static int p;

    //Choose random norm for the Minkowski's distance
    static Random rand = new Random();
    static int[] p_norme = {1, 2, (3 + rand.nextInt(10))};



       static String[] methode = {"E34", "GFD", "ART", "Yang", "Zernike7"};
       static String[] distance = {"Manhatten", "Euclidean", "Minkowski with norm_"+p_norme[2]};


        public static void main(String[] args) {

        Criteres c1 = new Criteres();
        Shape[][] t_bdshape = c1.BdShape;
        Scanner sc = new Scanner(System.in);


            System.out.print("\n Specify the number of clusters 'k' in K-Means Algorithm: k=");
            // Specify the number of clusters (k)
            k=sc.nextInt();

            System.out.print("\n Specify the number of iterations for the K-Means Algorithm: ");
            // Specify the number of iterations
            int n_it=sc.nextInt();


            //Array for every feature's execution time
             double[] t_ex_e34 = new double[n_it];
             double[] t_ex_gfd = new double[n_it];
            // pour yand et zernike et ART 
             double[] t_ex_art  = new double[n_it];
            double[] t_ex_yang  = new double[n_it];
            double[] t_ex_zernike7  = new double[n_it];

            //Array for every feature's global precision
             double[] p_ex_e34 = new double[n_it];
             double[] p_ex_gfd = new double[n_it];
            double[] p_ex_art  = new double[n_it];
            double[] p_ex_yang  = new double[n_it];
            double[] p_ex_zernike7  = new double[n_it];

            //Array for every feature's repeal
            double[] r_ex_e34 = new double[n_it];
            double[] r_ex_gfd = new double[n_it];
            double[] r_ex_art  = new double[n_it];
            double[] r_ex_yang  = new double[n_it];
            double[] r_ex_zernike7  = new double[n_it];

            //Array for every feature's true positives
            double[] po_ex_e34 = new double[n_it];
            double[] po_ex_gfd = new double[n_it];
            double[] po_ex_art  = new double[n_it];
            double[] po_ex_yang  = new double[n_it];
            double[] po_ex_zernike7  = new double[n_it];




            for (int p=1; p<4; p++) {

            //Test for the 5 features
            for (int m = 0; m < 5; m++) {

                String s = methode[m];

                for (int it = 0; it < n_it; it++) {

                    // Create KMeansClustering instance and perform clustering
                    KMeans kMeans = new KMeans(t_bdshape, k);


                    //Calculate the execution time
                    // start time
                    long startTime = System.nanoTime();

                    // Call the method you want to measure the execution time of
                    kMeans.cluster(s, p);

                    // end time
                    long endTime = System.nanoTime();

                    // Calculate the execution time in milliseconds
                    long executionTime = (endTime - startTime) / 1000000;

                    // execution time
                    System.out.println("The execution time of the feature " + s + " is: " + executionTime + " ms. || " + (endTime - startTime) + " ns");

                    switch (m) {
                        case 0:
                            t_ex_e34[it] = (double) (endTime - startTime) / 1000000;
                            break;
                        case 1:
                            t_ex_gfd[it] = (double) (endTime - startTime) / 1000000;
                            break;
                        case 2:
                          t_ex_art[it] = (double) (endTime - startTime) / 1000000;
                            break;
                        case 3:
                            t_ex_yang[it] = (double) (endTime - startTime) / 1000000;
                            break;
                        case 4:
                            t_ex_zernike7[it] = (double) (endTime - startTime) / 1000000;
                            break;
                    }

                    //Displaying the new Clusters
                    for (int c = 0; c < k; c++) {
                        int nb_c = 0;
                        System.out.println("Cluster " + c + ":");

                        //Browse into BDShape array
                        for (int i = 0; i < t_bdshape.length; i++) {
                            for (int j = 0; j < t_bdshape[0].length; j++) {

                                //Display the all shapes of a Cluster c
                                if (t_bdshape[i][j].getCluster() == c) {

                                    System.out.println("(" + t_bdshape[i][j].getE34() + "\n\n, " + t_bdshape[i][j].getGFD() + "\n\n, "
                                            + t_bdshape[i][j].getART() + "\n, " + t_bdshape[i][j].getYang() +"\n, " + t_bdshape[i][j].getZernike7() + ")");
                                    nb_c++;
                                }
                            }
                        }
                        System.out.println(); // Empty line for separation
                        System.out.println("Cluster " + c + " Contains " + nb_c + " Elements.");
                        System.out.println("_____________________________________________________________________________________________________________________");
                    }

                    System.out.println(kMeans.centroids);


                    //Precision
                    Double[] prec = new Double[k];

                    //Recall
                    Double[] rapp = new Double[k];

                    //Display confusion matrix
                    Double[][] mat = c1.Mat_confus(t_bdshape);
                    int vp = 0;
                    int vn = 0;
                    int nb = 0;
                    double nb_c;
                    double pt = 0, rt = 0;

                    System.out.println("Confusion Matrix: ");
                    for (int i = 0; i < 10; i++) {
                        for (int j = 0; j < 10; j++) {

                            //line by line Display
                            System.out.print("   " + mat[i][j]);

                            //True negatives
                            if (j != i) vn += mat[i][j];

                            //Number of elements by column
                            nb += mat[j][i];


                        }
                        //Number of correct predictions
                        nb_c = mat[i][i];

                        //Precision (Nb_Correct/Nb_total)
                        prec[i] = nb_c / nb;

                        //Repeal
                        rapp[i] = nb_c / 12;

                        nb = 0;

                        //True positives
                        vp += mat[i][i];
                        System.out.println();
                    }
                    System.out.println("\n True positives: " + vp + "\n True Negatives: " + vn);

                    for (int i = 0; i < 10; i++) {
                        System.out.print("\n\n Precision of the class " + i + " is: ");
                        System.out.printf("%.3f", prec[i]);

                        System.out.print("\n Repeal of the class " + i + " is: ");
                        System.out.printf("%.3f", rapp[i]);
                        pt += prec[i];
                        rt += rapp[i];
                    }
                    System.out.printf("\n\n %s%.3f", "Total Precison: ", (pt / 10));
                    System.out.printf("\n %s%.3f", "Total Repeal : ", (rt / 10));

                    //Collect the precision data of every feature
                    switch (m) {
                        case 0:
                            p_ex_e34[it] = (double) (pt / 10) * 100;
                            r_ex_e34[it] = (double) (rt / 10) * 100;
                            po_ex_e34[it] = vp;

                            break;
                        case 1:
                            p_ex_gfd[it] = (double) (pt / 10) * 100;
                            r_ex_gfd[it] = (double) (rt / 10) * 100;
                            po_ex_gfd[it] = vp;

                            break;
                        case 2:
                            p_ex_art[it] = (double) (pt / 10) * 100;
                            r_ex_art[it] = (double) (rt / 10) * 100;
                            po_ex_art[it] = vp;

                            break;
                        case 3:
                            p_ex_yang[it] = (double) (pt / 10) * 100;
                            r_ex_yang[it] = (double) (rt / 10) * 100;
                            po_ex_yang[it] = vp;

                            break;
                        case 4:
                            p_ex_zernike7[it] = (double) (pt / 10) * 100;
                            r_ex_zernike7[it] = (double) (rt / 10) * 100;
                            po_ex_zernike7[it] = vp;

                            break;
                    }

                }
            }

          //Graphics representation
          Hist.Histo_car(distance[p-1], "Execution Time (ms)", t_ex_e34, t_ex_gfd, t_ex_art, t_ex_yang, t_ex_zernike7);
          Hist.Histo_prec(distance[p-1],"Precision (%)", p_ex_e34, p_ex_gfd, p_ex_art, p_ex_yang, p_ex_zernike7);
          Hist.Histo_rapp(distance[p-1],"Repeal (%)", r_ex_e34, r_ex_gfd, r_ex_art, r_ex_yang, r_ex_zernike7);
          Hist.courb_pos(distance[p-1],"True Positives", po_ex_e34, po_ex_gfd, po_ex_art, po_ex_yang, po_ex_zernike7);

         // Exemple de données d'entraînement
        ArrayList<ArrayList<Double>> trainingData = new ArrayList<>();
        ArrayList<Integer> trainingLabels = new ArrayList<>();

        // Exemple de points de données 2D avec des étiquettes (label)
        ArrayList<Double> point1 = new ArrayList<>();
        point1.add(1.0);
        point1.add(2.0);
        trainingData.add(point1);
        trainingLabels.add(0); // Label pour le premier point

        ArrayList<Double> point2 = new ArrayList<>();
        point2.add(2.0);
        point2.add(3.0);
        trainingData.add(point2);
        trainingLabels.add(0); // Label pour le deuxième point

        ArrayList<Double> point3 = new ArrayList<>();
        point3.add(5.0);
        point3.add(4.0);
        trainingData.add(point3);
        trainingLabels.add(1); // Label pour le troisième point

        ArrayList<Double> point4 = new ArrayList<>();
        point4.add(6.0);
        point4.add(7.0);
        trainingData.add(point4);
        trainingLabels.add(1); // Label pour le quatrième point

        // Instanciation de KNN
        int k = 3;  // Choisir la valeur de k
        KNN knn = new KNN(trainingData, trainingLabels, k);

        // Exécution de la méthode KNN pour tester la classification
        knn.runKNN();
         // Exemple de données de précisions pour différentes classes KNN
         double[] precisionE34 = {0.85, 0.90, 0.92, 0.88, 0.80};  // Précision pour E34
         double[] precisionGFD = {0.88, 0.91, 0.89, 0.87, 0.82};  // Précision pour GFD
         double[] precisionArt = {0.78, 0.82, 0.84, 0.79, 0.77};  // Précision pour ART
         double[] precisionYang = {0.92, 0.95, 0.93, 0.89, 0.91}; // Précision pour Yang
         double[] precisionZernike7 = {0.87, 0.85, 0.88, 0.86, 0.84}; // Précision pour Zernike7
 
         // Afficher un histogramme des précisions des différentes classes pour KNN
         Hist.Histo_prec("KNN", "Précision", precisionE34, precisionGFD, precisionArt, precisionYang, precisionZernike7);

        }
    }

}
