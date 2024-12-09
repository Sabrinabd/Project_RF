import java.util.ArrayList;


public class Shape {

  //Create an ArrayList for every Shape's feature
        public ArrayList<Double> E34;
        public ArrayList<Double> GFD;
        public ArrayList<Double> ART;
        public ArrayList<Double> Yang;
        public ArrayList<Double> Zernike7;
  
  //To tell in which cluster it has been assigned
        private int Cluster;

//Default constructor
        public Shape() {
            this.E34 =null;
            this.GFD =null;
            this.ART =null;
            this.Yang =null;
            this.Zernike7 =null;
        }

  //Constructor
        public Shape(ArrayList<Double> E34, ArrayList<Double> GFD, ArrayList<Double> ART, ArrayList<Double> Yang, ArrayList<Double> Zernike7) {
            this.E34 = E34;
            this.GFD = GFD;
            this.ART = ART;
            this.Yang = Yang;
            this.Zernike7 = Zernike7;

        }

  //Getters and Setters
        public ArrayList<Double> getE34() {
            return E34;
        }
        public void setE34(ArrayList<Double> E34) {
            this.E34 = E34;
        }

        public ArrayList<Double> getART () {
            return ART;
        }
        public void setART(ArrayList<Double> ART) {
            this.ART = ART;
        }
       

        public ArrayList<Double> getGFD() {
            return GFD;
        }
        public void setGFD(ArrayList<Double> GFD) {
            this.GFD = GFD;
        }

        public ArrayList<Double> getYang () {
            return Yang;
        }
        public void setYang(ArrayList<Double> Yang) {
            this.Yang = Yang;
        }

        public ArrayList<Double> getZernike7 () {
            return Zernike7;
        }
        public void setZernike7(ArrayList<Double> Zernike7) {
            this.Zernike7 = Zernike7;
        }

        public void setCluster(int x){
            this.Cluster=x;
        }

        public int getCluster(){
            return this.Cluster;
        }

    }


