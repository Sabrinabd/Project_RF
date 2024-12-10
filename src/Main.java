import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

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
        }
         //lire le nombre de clusters
         System.out.print("=============== Algorithme K-NN: =====================");
        
         System.out.print("\n Specify the number of clusters 'k' in K-NN Algorithm: k=");
         int k=sc.nextInt();
         // lire le nombre de shape par cluster
         System.out.print("\n Specify the number of shapes per cluster: ");
         int n_shape=sc.nextInt();
 
 
         // Création des données d'entraînement
         ArrayList<Shape> trainingData = new ArrayList<>();
         ArrayList<Integer> trainingLabels = new ArrayList<>();
         for (int i = 0; i < k; i++) {
             for (int j = 0; j < n_shape; j++) {
                 trainingData.add(t_bdshape[i][j]);
                 trainingLabels.add(i);
             }
         }
         //Creation des données de test
         ArrayList<Shape> testData = new ArrayList<>();
         ArrayList<Integer> testLabels = new ArrayList<>();
         for (int i = 0; i < k; i++) {
             for (int j = n_shape; j < 12; j++) {
                 testData.add(t_bdshape[i][j]);
                 testLabels.add(i);
             }
         }
 
         // Instanciation de KNN
        
        n_it=k-1;
        
         //Array for every feature's execution time
          t_ex_e34 = new double[n_it];
          t_ex_gfd = new double[n_it];
          t_ex_art  = new double[n_it];
        t_ex_yang  = new double[n_it];
        t_ex_zernike7  = new double[n_it];

        //Array for every feature's global precision
         p_ex_e34 = new double[n_it];
         p_ex_gfd = new double[n_it];
        p_ex_art  = new double[n_it];
        p_ex_yang  = new double[n_it];
        p_ex_zernike7  = new double[n_it];

        //Array for every feature's repeal
        r_ex_e34 = new double[n_it];
         r_ex_gfd = new double[n_it];
        r_ex_art  = new double[n_it];
        r_ex_yang  = new double[n_it];
        r_ex_zernike7  = new double[n_it];

        //Array for every feature's true positives
        po_ex_e34 = new double[n_it];
        po_ex_gfd = new double[n_it];
        po_ex_art  = new double[n_it];
        po_ex_yang  = new double[n_it];
        po_ex_zernike7  = new double[n_it];




        for ( p=1; p<4; p++) {

        //Test for the 5 features
        for (int m = 0; m < 5; m++) {

            String s = methode[m];

            for (int it = 1; it < k; it++) {// Nombre de voisins à considérer au max sera le nombre de clusters

                // Create KNN instance and perform classification
                int neighbors = it;  // Choisir la valeur de k
                KNN knn = new KNN(trainingData, trainingLabels, neighbors);


                //Calculate the execution time
                // start time
                long startTime = System.nanoTime();

                // Call the method you want to measure the execution time of
                ArrayList<Integer> predictions = new ArrayList<>();
                for (int i = 0; i < testData.size(); i++) {
                    int predictionlabel = 0;
                    switch (s) {
                        case "ART":
                            predictionlabel=knn.classify(testData.get(i).getART(), s, p);
                            break;
                        case "E34":
                            predictionlabel= knn.classify(testData.get(i).getE34(), s, p);
                            break;
                        case "GFD":
                            predictionlabel= knn.classify(testData.get(i).getGFD(), s, p);
                            break;
                        case "Zernike7":
                            predictionlabel = knn.classify(testData.get(i).getZernike7(), s, p);
                            break;
                        case "Yang":
                            predictionlabel = knn.classify(testData.get(i).getYang(), s, p);
                            break;
                    }
                    predictions.add(predictionlabel);
                    

                    
                }

                // end time
                long endTime = System.nanoTime();

                // Calculate the execution time in milliseconds
                long executionTime = (endTime - startTime) / 1000000;

                // execution time
                System.out.println("The execution time of the feature " + s + " is: " + executionTime + " ms. || " + (endTime - startTime) + " ns");

                switch (m) {
                    case 0:
                        t_ex_e34[it-1] = (double) (endTime - startTime) / 1000000;
                        break;
                    case 1:
                        t_ex_gfd[it-1] = (double) (endTime - startTime) / 1000000;
                        break;
                    case 2:
                      t_ex_art[it-1] = (double) (endTime - startTime) / 1000000;
                        break;
                    case 3:
                        t_ex_yang[it-1] = (double) (endTime - startTime) / 1000000;
                        break;
                    case 4:
                        t_ex_zernike7[it-1] = (double) (endTime - startTime) / 1000000;
                        break;
                }

               

              


                //Precision
                Double[] prec = new Double[k-1];

                //Recall
                Double[] rapp = new Double[k-1];

               // KPI calculation
                int vp = 0;
                int vn = 0;
                int nb = testLabels.size();
                double nb_c;
                double pt = 0, rt = 0;

              
                   
                    //Number of correct predictions
                    
                   
                    nb_c =  0;
                    for (int i =0 ; i<testLabels.size(); i++){
                        if (testLabels.get(i)==predictions.get(i)){
                            nb_c++;
                        }
                    }

                    //Precision (Nb_Correct/Nb_total)
                    prec[it-1] = nb_c / nb;

                    //Repeal
                    rapp[it-1] = nb_c / 12;
                    //True positives
                    vp +=  nb_c;
                    System.out.println();
                
                System.out.println("\n True positives: " + vp + "\n True Negatives: " + vn);

                
                    System.out.print("\n\n Precision of the number neighbors choice of  " + it + " is: ");
                    System.out.printf("%.3f", prec[it-1]);

                    System.out.print("\n Repeal of the number neighbors choice of   " + it + " is: ");
                    System.out.printf("%.3f", rapp[it-1]);
                    pt += prec[it-1];
                    rt += rapp[it-1];
                
                System.out.printf("\n\n %s%.3f", "Total Precison: ", (pt / 10));
                System.out.printf("\n %s%.3f", "Total Repeal : ", (rt / 10));

                //Collect the precision data of every feature
                switch (m) {
                    case 0:
                        p_ex_e34[it-1] = (double) (pt / 10) * 100;
                        r_ex_e34[it-1] = (double) (rt / 10) * 100;
                        po_ex_e34[it-1] = vp;

                        break;
                    case 1:
                        p_ex_gfd[it-1] = (double) (pt / 10) * 100;
                        r_ex_gfd[it-1] = (double) (rt / 10) * 100;
                        po_ex_gfd[it-1] = vp;

                        break;
                    case 2:
                        p_ex_art[it-1] = (double) (pt / 10) * 100;
                        r_ex_art[it-1] = (double) (rt / 10) * 100;
                        po_ex_art[it-1] = vp;

                        break;
                    case 3:
                        p_ex_yang[it-1] = (double) (pt / 10) * 100;
                        r_ex_yang[it-1] = (double) (rt / 10) * 100;
                        po_ex_yang[it-1] = vp;

                        break;
                    case 4:
                        p_ex_zernike7[it-1] = (double) (pt / 10) * 100;
                        r_ex_zernike7[it-1] = (double) (rt / 10) * 100;
                        po_ex_zernike7[it-1] = vp;

                        break;
                }

            }
        }

      //Graphics representation
    Hist.Histo_car(distance[p-1], "Execution Time (ms) For KNN", t_ex_e34, t_ex_gfd, t_ex_art, t_ex_yang, t_ex_zernike7);
    Hist.Histo_prec(distance[p-1],"Precision (%) For KNN", p_ex_e34, p_ex_gfd, p_ex_art, p_ex_yang, p_ex_zernike7);
    Hist.Histo_rapp(distance[p-1],"Repeal (%) For KNN", r_ex_e34, r_ex_gfd, r_ex_art, r_ex_yang, r_ex_zernike7);
    Hist.courb_pos(distance[p-1],"True Positives For KNN", po_ex_e34, po_ex_gfd, po_ex_art, po_ex_yang, po_ex_zernike7);


        }
    }
}
