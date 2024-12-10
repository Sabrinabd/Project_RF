import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;





public class Criteres {
    public static int k = 18;

        // BDShape's Array [Class][Sample].
        Shape[][] BdShape = new Shape[k][12];

      //List for every feature
        List<Double> E34 = new ArrayList<>();
        List<Double> GFD = new ArrayList<>();
        List<Double> ART = new ArrayList<>();
        List<Double> Yang = new ArrayList<>();
        List<Double> Zernike7 = new ArrayList<>();




        public Criteres() {
            
            //Read every feature's file and convert it into a feature's List for every shape[i][j]
            for (int i = 1; i <= k; i++) {
                for (int j = 1; j <= 12; j++) {
                    String fileName = "src//Signatures//E34//s"+String.format("%02d", i)+"n0"+String.format("%02d", j)+".e34";
                    File file = new File(fileName);
                    ArrayList<Double> lign=new ArrayList<>();
                    try {
                        Scanner scanner = new Scanner(file);
                        while (scanner.hasNextLine()) {
                            //Read the file line by line
                            String line = scanner.nextLine();
                            try {
                            Double value = Double.parseDouble(line.trim());
                                this.E34.add(value);
                                lign.add(value);
                                //this.BdShape[i-1][j-1]=new Forme(E34,null,null,null);

                            } catch (NumberFormatException e) {
                                System.err.println("Ligne non valide trouvée et ignorée: " + line);
                            }
                        }
                        this.BdShape[i-1][j-1]=new Shape(lign,null,null,null,null);

                        scanner.close();
                    } catch (FileNotFoundException e) {
                        System.err.println("File not Found: " + fileName);
                    }

                }
            }
            for (int i = 1; i <= k; i++) {
                for (int j = 1; j <= 12; j++) {
                    String fileName = "src//Signatures//GFD//s"+String.format("%02d", i)+"n0"+String.format("%02d", j)+".gfd";
                    File file = new File(fileName);

                    ArrayList<Double> lign=new ArrayList();

                    try {
                        Scanner scanner = new Scanner(file);
                        while (scanner.hasNextLine()) {
                            //read the file line by line
                            String line = scanner.nextLine();
                            try {
                                Double value = Double.parseDouble(line.trim());
                                this.GFD.add(value);
                                lign.add(value);

                            } catch (NumberFormatException e) {
                                System.err.println("Line not found : " + line);
                            }
                        } this.BdShape[i-1][j-1].GFD=lign;
                        scanner.close();
                    } catch (FileNotFoundException e) {
                        System.err.println("File not Found: " + fileName);
                    }

                }
            }
            for (int i = 1; i <= k; i++) {
                for (int j = 1; j <= 12; j++) {
                    String fileName = "src//Signatures//ART//s"+String.format("%02d", i)+"n0"+String.format("%02d", j)+".art";
                    File file = new File(fileName);
                    ArrayList lign=new ArrayList();
                    try {
                        Scanner scanner = new Scanner(file);
                        while (scanner.hasNextLine()) {
                            //read the file line by line
                            String line = scanner.nextLine();
                            try {
                                Double value = Double.parseDouble(line.trim());
                                this.ART.add(value);
                                lign.add(value);
                            } catch (NumberFormatException e) {
                                System.err.println("Line not found: " + line);
                            }
                        } this.BdShape[i-1][j-1].ART=lign;
                        scanner.close();
                    } catch (FileNotFoundException e) {
                        System.err.println("File not found: " + fileName);
                    }

                }
            }
            for (int i = 1; i <= k; i++) {
                for (int j = 1; j <= 12; j++) {
                    String fileName = "src/Signatures/Yang/s"+String.format("%02d", i)+"n0"+String.format("%02d", j)+".yng";
                    File file = new File(fileName);
                    ArrayList lign=new ArrayList();
                    try {
                        Scanner scanner = new Scanner(file);
                        while (scanner.hasNextLine()) {
                            //read the file line by line
                            String line = scanner.nextLine();
                            try {
                                Double value = Double.parseDouble(line.trim());
                                this.Yang.add(value);
                                lign.add(value);
                            } catch (NumberFormatException e) {
                                System.err.println("Line not found: " + line);
                            }
                        }this.BdShape[i-1][j-1].Yang=lign;
                        scanner.close();
                    } catch (FileNotFoundException e) {
                        System.err.println("File not found: " + fileName);
                    }

                }
            }
            for (int i = 1; i <= k; i++) {
                for (int j = 1; j <= 12; j++) {
                    String fileName = "src//Signatures//Zernike7//s"+String.format("%02d", i)+"n0"+String.format("%02d", j)+".zrk.txt";
                    File file = new File(fileName);
                    ArrayList lign=new ArrayList();
                    try {
                        Scanner scanner = new Scanner(file);
                        while (scanner.hasNextLine()) {
                            //read the file line by line
                            String line = scanner.nextLine();
                            try {
                                Double value = Double.parseDouble(line.trim());
                                this.Zernike7.add(value);
                                lign.add(value);
                            } catch (NumberFormatException e) {
                                System.err.println("Line not found: " + line);
                            }
                        } this.BdShape[i-1][j-1].Zernike7=lign;
                        scanner.close();
                    } catch (FileNotFoundException e) {
                        System.err.println("File not found: " + fileName);
                    }

                }
            }


        }

        //Calculate the confusion matrix
        public Double[][] Mat_confus(Shape[][] bdshape){
            Double[][] mat_con = new Double[k][k];
            //initialization of (9*9) zeros Matrix
            for(int i=0; i<k;i++){
                for(int j=0; j<k;j++) {
                    mat_con[i][j]=0.0;}}

            //compare
            for(int i=0; i<k;i++){
                for(int j=0; j<12;j++){

                    if(bdshape[i][j].getCluster()==i){ mat_con[i][i]+=1;}

                    else{ mat_con[i][bdshape[i][j].getCluster()]+=1;}
                }
            }

            return mat_con;
        }


    }


