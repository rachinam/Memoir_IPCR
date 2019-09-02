package InfoNode;

import java.util.ArrayList;
import java.lang.Math.*;
import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;
import java.util.concurrent.*;

public class Calcule {

    private int PORT = 50;
    private LinkedList<Node> node = new LinkedList<Node>();

    public Calcule(){

    }
    public Calcule(LinkedList<Node> n) {
        this.node = n;
    }

    private double sqr(double a) {
        return a * a;
    }

    private double distance(int i, int j) {
        return Math.sqrt(sqr(this.node.get(j).getY() - this.node.get(i).getY()) + sqr(this.node.get(j).getX() - this.node.get(i).getX()));
    }

    public boolean calculNeighbor() {
        for (int i = 0; i < this.node.size(); i++) {
            for (int j = 0; j < this.node.size(); j++) {
                if (i != j) {
                    double d = distance(i, j);
                    if (d <= this.PORT) {
                        this.node.get(i).addNeighbor(this.node.get(j).getID(), this.node.get(j).getX(), this.node.get(j).getY(), d);
                    }
                }
            }
        }
        return true;
    }


    public void Remove_frequent_neighbors() {
        int result[];
        for(Node n1 : node){
            for(Node n2 : node){
                if(n1.getID() != n2.getID()){
                   result = compare(n1,n2);
                   switch (result[0]){

                       case 1:
                           n1.removeNeighbor(result[1]);
                           break;
                       case 2:
                           n2.removeNeighbor(result[1]);
                           break;

                   }
                }
            }
        }
    }
    private int[] compare(Node n1, Node n2){
        int res[] = new int[2];
        for(int i = 0; i < n1.getNeighbor().size(); i++){
            for(int j = 0; j < n2.getNeighbor().size(); j++){
                if(n1.getNeighbor().get(i).node.getID() == n2.getNeighbor().get(j).node.getID()){
                    if(n1.getNeighbor().size() > n2.getNeighbor().size()){
                        res[0] = 2;
                        res[1] = j;
                        return res;
                    }else{
                        res[0] = 1;
                        res[1] = i;
                        return res;
                    }
                }
            }
        }
        res[0] = 0;
        res[1] = 0;
        return res;
    }



    // *********************************************************************** LINE
    /*
     *  x + y = p
     *  double  [] = {a,p,y};
     * */
    public double[] middleLine(double p1[] ,double p2[]){
        if(p1.length == 2 && p2.length == 2){
            double   middle[] = new double  [2];
            middle[0] = ((p2[0] + p1[0]) / 2) ;
            middle[1] = ((p2[1] + p1[1]) / 2);
            return middle;
        }else{
            return null;
        }
    }
    public double[] getIntersection(double d1[],double d2[],double d3[],double d4[]){
        double d = (d1[0]-d2[0])*(d3[1]-d4[1]) - (d1[1]-d2[1])*(d3[0]-d4[0]);
        if (d == 0) return null;

        double xi = ((d3[0]-d4[0])*(d1[0]*d2[1]-d1[1]*d2[0])-(d1[0]-d2[0])*(d3[0]*d4[1]-d3[1]*d4[0]))/d;
        double yi = ((d3[1]-d4[1])*(d1[0]*d2[1]-d1[1]*d2[0])-(d1[1]-d2[1])*(d3[0]*d4[1]-d3[1]*d4[0]))/d;

        double re[] = new double[2];
        re[0] = xi;
        re[1] = yi;
        return  re;
    }

    // *********************************************************************** CERCLE
    private double power(double x){
        return x*x;
    }
    // c1[] = {x ,y ,r}
    public double[] cercle(double c1[] ,double c2[]){
        double re[] = new double[4];
        double dx = c2[0] - c1[0];
        double dy = c2[1] - c1[1];
        double d = (Math.sqrt(power(dx) + power(dy))) * 1000000;
        d = Math.round(d);d = d/1000000;
        // d Distance between circle centres
        /*if(d > (c1[2]+c2[2])){
            System.out.println("The circles do not intersect");
        }else if(d < Math.abs(c2[2] - c1[1])){
            System.out.println("No Intersect - One circle is contained within the other");
        }else if(d ==0 && c2[2] == c1[2]){
            System.out.println("No Intersect - The circles are equal and coincident");
        }else if(d == c2[2] + c1[2] || d == c2[2] - c1[2]){
            System.out.println("The circles intersect at a single point");
        }else{*/
            System.out.println("The circles intersect at two points");
            double chorddistance = (power(c1[2]) - power(c2[2]) + power(d)) / (2*d);
            double halfchordlength = Math.sqrt(power(c1[2]) - power(chorddistance));
            double chordmidpointx = (c1[0] + ((chorddistance*dx)/d));
            double chordmidpointy = (c1[1] + ((chorddistance*dy)/d));
            re[0] = (chordmidpointx + ((halfchordlength*dy)/d)) * 1000000;
            re[0] = Math.round(re[0]); re[0] = re[0] /1000000;

            re[1] = (chordmidpointy - ((halfchordlength*dx)/d)) * 1000000;
            re[1] = Math.round(re[1]); re[1] = re[1] /1000000;

            re[2] = (chordmidpointx - ((halfchordlength*dy)/d)) * 100000;
            re[2] = Math.round(re[2]); re[2] = re[2] /100000;

            re[3] = (chordmidpointy + ((halfchordlength*dx)/d)) * 100000;
            re[3] = Math.round(re[3]); re[3] = re[3] /100000;
            return re;

        //}

        //return null;
    }

}
