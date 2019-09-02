package InfoNode;


public class Neighbor_Node {

    Node node;
    double distance;
    private double CP_X,CP_Y;

    public Neighbor_Node(Node node, double dist){
        this.node = node;
        this.distance = dist;
    }

    public void setCP(double x, double y){
        this.CP_X = x; this.CP_Y = y;
    }

    public double[] getCP(){
        double re[] = {this.CP_X,this.CP_Y};
        return re;
    }

}
