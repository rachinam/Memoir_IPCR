package InfoNode;



import java.util.LinkedList;
import Jama.Matrix;
import com.company.ConsoleColors;

import java.lang.Math.*;

public class Node{

    private String ID;
    private double   X;
    private double   Y;
    private LinkedList<Neighbor_Node> neighbor = new LinkedList<Neighbor_Node>();
    private int PORT = 50;

    public Node(){

    }
    public Node(String id, double   x, double   y){
        this.ID = id;
        this.X = x;
        this.Y = y;
    }
    public void showNeighbor(){
        for(Neighbor_Node n: neighbor){
            System.out.println("--------------- Neighbor id :"+n.node.ID + ", X :"+n.node.X+", Y :"+n.node.Y+", distance :"+n.distance+"\n");
        }
    }
    public void addNeighbor(String ID ,double   X ,double   Y ,double   dist){
        neighbor.add(new Neighbor_Node(new Node(ID,X,Y),dist));
    }
    public void removeNeighbor(int index){
        this.getNeighbor().remove(index);
    }
    public double   getX(){
        return this.X;
    }
    public double   getY(){
        return this.Y;
    }
    public String getID(){
        return this.ID;
    }
    public LinkedList<Neighbor_Node> getNeighbor(){
        return this.neighbor;
    }
    public int getPort(){
        return this.PORT;
    }

}