import java.util.ArrayList;
import java.util.Random;


public class KMeans {

    //Array of shapes BDShape
    public Shape[][] t_bdshape;
    // Number of clusters
    public int k;
    //List of shapes (Cluster's center)
    public ArrayList<Shape> centroids;

    //Constructor of the Class KMeans
    public KMeans(Shape[][] t_bdshape, int k) {
        this.t_bdshape = t_bdshape;
        this.k = k;
        centroids = initialiser_Centroids();
    }

    // 2- Initialization of random centroids
    public ArrayList<Shape> initialiser_Centroids() {
        Random rand = new Random();
        ArrayList<Shape> centroids = new ArrayList<>();

        //Select a random Shape for every Class
        for (int i = 0; i < k; i++) {
            // Random int 0 - 11 (It represents a random sample of the class i)
            int randomIndex = rand.nextInt(11);    //(t_dataPoints1[0].length);
            //Add the choosen shape to the initial list of centroids
            centroids.add(t_bdshape[i][randomIndex]);
        }
        //List of choosen centroid's centers
        //this.centroids=centroids;
        return centroids;
    }


    // 3- Assigne every shape to its close centroid's center
    public void assigner_Clusters(String s, int p) {
        switch (s) {
            case "E34":
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 12; j++) {

                        double minDistance = Double.MAX_VALUE;
                        double distance;
                        int clusterIndex = -1;
                        //for (int t = 0; t < k; t++) {
                        for (int t = 0; t < k; t++) {

                            //Calculate the distance between the actual shape and the other centers of the centroid
                            distance = calculateDistance(t_bdshape[i][j].E34, centroids.get(t).getE34(), p);


                            if (distance < minDistance) {
                                minDistance = distance;
                                clusterIndex = t;

                            }
                        }                       
                        //Assign the actual shape to the closest center (according to the distance)
                        t_bdshape[i][j].setCluster(clusterIndex);
                    }
                }
                break;
            case "GFD":
                for (int i = 0; i < 10; i++){
                    for (int j = 0; j < 12; j++){

                        double minDistance = Double.MAX_VALUE;
                        double distance;
                        int clusterIndex = -1;
                        //for (int t = 0; t < k; t++) {
                        for (int t = 0; t < k; t++) {

                           //Calculate the distance between the actual shape and the other centers of the centroid
                            distance = calculateDistance(t_bdshape[i][j].GFD, centroids.get(t).getGFD(), p);


                            if (distance < minDistance) {
                                minDistance = distance;
                                clusterIndex = t;
                            }
                        }
                        //Assign the actual shape to the closest center (according to the distance)
                        t_bdshape[i][j].setCluster(clusterIndex);
                    }
                }
                break;
            case "ART":
                for (int i = 0; i < 10; i++){
                    for (int j = 0; j < 12; j++){

                        double minDistance = Double.MAX_VALUE;
                        double distance;
                        int clusterIndex = -1;
                        //for (int t = 0; t < k; t++) {
                        for (int t = 0; t < k; t++) {

                            //Calculate the distance between the actual shape and the other centers of the centroid
                            distance = calculateDistance(t_bdshape[i][j].ART, centroids.get(t).getART(), p);


                            if (distance < minDistance) {
                                minDistance = distance;
                                clusterIndex = t;
                            }
                        }
                        //Assign the actual shape to the closest center (according to the distance)
                        t_bdshape[i][j].setCluster(clusterIndex);
                    }
                }
                break;
            case "Yang":
                for (int i = 0; i < 10; i++){
                    for (int j = 0; j < 12; j++){

                        double minDistance = Double.MAX_VALUE;
                        double distance;
                        int clusterIndex = -1;
                        //for (int t = 0; t < k; t++) {
                        for (int t = 0; t < k; t++) {

                             //Calculate the distance between the actual shape and the other centers of the centroid
                            distance = calculateDistance(t_bdshape[i][j].Yang, centroids.get(t).getYang(), p);


                            if (distance < minDistance) {
                                minDistance = distance;
                                clusterIndex = t;
                            }
                        }
                        //Assign the actual shape to the closest center (according to the distance)
                        t_bdshape[i][j].setCluster(clusterIndex);
                    }
                }
                break;
                case "Zernike7":
                for (int i = 0; i < 10; i++){
                    for (int j = 0; j < 12; j++){

                        double minDistance = Double.MAX_VALUE;
                        double distance;
                        int clusterIndex = -1;
                        //for (int t = 0; t < k; t++) {
                        for (int t = 0; t < k; t++) {

                             //Calculate the distance between the actual shape and the other centers of the centroid
                            distance = calculateDistance(t_bdshape[i][j].Zernike7, centroids.get(t).getZernike7(), p);


                            if (distance < minDistance) {
                                minDistance = distance;
                                clusterIndex = t;
                            }
                        }
                        //Assign the actual shape to the closest center (according to the distance)
                        t_bdshape[i][j].setCluster(clusterIndex);
                    }
                }
                break;
        }
    }

    //4- Calculate the new Centroid by testing the mean of every Cluster
    private void ajuster_Centroids(String s) {

        //List of new centers
        ArrayList<Shape> newCentroids = new ArrayList<>();

        switch (s) {
            case "E34":
                //List to calculate every features mean
                ArrayList<Double> totalE34 = new ArrayList<>();

                //initialize the List with zeros
                for (int i = 0; i < t_bdshape[0][0].E34.size(); i++) {
                    totalE34.add(0.0);
                }

                //Browse all centroid's elements
                for (int z = 0; z < k; z++) {

                    //Browse all E34 feature's elements (16 elements)
                    int count = 0;
                    //Browse shape's array
                    for (int i = 0; i < t_bdshape.length; i++) {
                        for (int j = 0; j < t_bdshape[0].length; j++) {

                            //if the actual shape is in the same cluster
                            if (t_bdshape[i][j].getCluster() == z) {

                                //TotalE34[j] +=Forme[i][j].E34[j]
                                for (int it = 0; it < totalE34.size(); it++) {
                                    totalE34.set(it, totalE34.get(it) + t_bdshape[i][j].E34.get(it));
                                    count++;
                                }
                            }
                        }
                    }
                    if (count > 0) {
                        for (int i = 0; i < totalE34.size(); i++) {
                            //Assign the mean to the same position ( Sum(total[i])/ nb_total)
                            totalE34.set(i, totalE34.get(i) / count);
                        }
                        newCentroids.add(new Shape(totalE34, null, null, null, null));
                    }

                }
                //The centroid receive the new centers
                this.centroids = newCentroids;
                totalE34.clear();
                break;
            case "GFD":
                //List to calculate the GFD feature's mean
                ArrayList<Double>  totalGFD= new ArrayList<>();

                //initialize the List with zeros
                for(int i = 0; i< t_bdshape[0][0].GFD.size(); i++){ totalGFD.add(0.0);}

                //Browse all the centroid's elements
                for (int z = 0; z < k; z++) {

                    //Browse all GFD feature's elements (100 elements)
                    int count = 0;
                    //Browse shape's array
                    for (int i = 0; i < t_bdshape.length; i++) {
                        for(int j = 0; j < t_bdshape[0].length; j++) {

                            //if the actual shape is in the same cluster
                            if (t_bdshape[i][j].getCluster() == z) {


                                //TotalE34[j] +=Forme[i][j].E34[j]
                                for(int it=0; it< totalGFD.size(); it++){
                                    totalGFD.set(it, totalGFD.get(it) + t_bdshape[i][j].GFD.get(it));
                                    count++; }}
                        } }
                    if(count>0){
                        for(int i=0;i<totalGFD.size();i++) {
                            //Assign the mean of the values in the same position ( Sum(total[i])/ nb_total)
                            totalGFD.set(i, totalGFD.get(i)/count);}
                        newCentroids.add(new Shape(null, totalGFD,null,null,null)); }
                }
                //The centroid receive the new centers
                this.centroids = newCentroids;
                totalGFD.clear();
                break;

            case "SA":
                //List to calculate the ART feature's mean
                ArrayList<Double>  totalART = new ArrayList<>();

                //initialize the List with zeros
                for(int i = 0; i< t_bdshape[0][0].ART.size(); i++){ totalART.add(0.0);}

                //Browse all the centroid's elements
                for (int z = 0; z < k; z++) {

                    //Browse all ART feature's elements (90 elements)
                    int count = 0;
                    //Browse shape's array
                    for (int i = 0; i < t_bdshape.length; i++) {
                        for(int j = 0; j < t_bdshape[0].length; j++) {

                            //if the actual shape is in the same cluster
                            if (t_bdshape[i][j].getCluster() == z) {


                                //TotalE34[j] +=Forme[i][j].E34[j]
                                for(int it=0; it< totalART.size(); it++){
                                    totalART.set(it, totalART.get(it) + t_bdshape[i][j].ART.get(it));
                                    count++; }}
                        } }
                    if(count>0){
                        for(int i=0;i<totalART.size();i++) {
                            //Assign the mean to the same position ( Sum(total[i])/ nb_total)
                            totalART.set(i, totalART.get(i)/count);}
                        newCentroids.add(new Shape(null, null,totalART,null,null)); }
                }
                //the centroid receive the new centers
                this.centroids = newCentroids;
                totalART.clear();

                break;

            case "Yang":
                //List to calculate the Yang feature's mean
                ArrayList<Double>  totalYang = new ArrayList<>();

                //initialize the List with zeros
                for(int i = 0; i< t_bdshape[0][0].Yang.size(); i++){ totalYang.add(0.0);}

                //Browse all the centroid's elements
                for (int z = 0; z < k; z++) {

                    //Browse all Yang feature's elements (128 elements)
                    int count = 0;
                    //Browse shape's array
                    for (int i = 0; i < t_bdshape.length; i++) {
                        for(int j = 0; j < t_bdshape[0].length; j++) {

                            //if the actual shape is in the same cluster
                            if (t_bdshape[i][j].getCluster() == z) {


                                //TotalE34[j] +=Forme[i][j].E34[j]
                                for(int it=0; it< totalYang.size(); it++){
                                    totalYang.set(it, totalYang.get(it) + t_bdshape[i][j].Yang.get(it));
                                    count++; }}
                        } }
                    if(count>0){
                        for(int i=0;i<totalYang.size();i++) {
                            //Assign the mean to the same position ( Sum(total[i])/ nb_total)
                            totalYang.set(i, totalYang.get(i)/count);}
                        newCentroids.add(new Shape(null, null,null,totalYang,null)); }
                }
                //The centroid receive the new centers
                this.centroids = newCentroids;
                totalYang.clear();
                break;

                case "Zernike7":
                //List to calculate the Zernike7 feature's mean
                ArrayList<Double>  totalZernike7 = new ArrayList<>();

                //initialize the List with zeros
                for(int i = 0; i< t_bdshape[0][0].Zernike7.size(); i++){ totalZernike7.add(0.0);}

                //Browse all the centroid's elements
                for (int z = 0; z < k; z++) {

                    //Browse all Zernike7 feature's elements (128 elements)
                    int count = 0;
                    //Browse shape's array
                    for (int i = 0; i < t_bdshape.length; i++) {
                        for(int j = 0; j < t_bdshape[0].length; j++) {

                            //if the actual shape is in the same cluster
                            if (t_bdshape[i][j].getCluster() == z) {


                                //TotalE34[j] +=Forme[i][j].E34[j]
                                for(int it=0; it< totalZernike7.size(); it++){
                                    totalZernike7.set(it, totalZernike7.get(it) + t_bdshape[i][j].Zernike7.get(it));
                                    count++; }}
                        } }
                    if(count>0){
                        for(int i=0;i<totalZernike7.size();i++) {
                            //Assign the mean to the same position ( Sum(total[i])/ nb_total)
                            totalZernike7.set(i, totalZernike7.get(i)/count);}
                        newCentroids.add(new Shape(null, null,null,totalZernike7,null)); }
                }
                //The centroid receive the new centers
                this.centroids = newCentroids;
                totalZernike7.clear();
                break;

        }
    }

    // Calculate the p-distance of two features (E34, GFD, ART, Yang, Zernike7)
    public static double calculateDistance(ArrayList<Double> a, ArrayList<Double> b, int p) {
        double s=0.0;
        for(int i=0; i<b.size(); i++){
            s+= Math.pow(Math.abs(a.get(i)- b.get(i)), p);
        }
        return Math.pow(s, (double)(1.0/p) );
    }

    //K-means clustering
    public void cluster(String s, int p) {
         try{
            assigner_Clusters(s, p);
            ajuster_Centroids(s);
        }catch(Exception e){ System.out.print(e.getCause());}
        }
    }










