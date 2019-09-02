package InfoNode;

public class CP {
    private String id;
    private double X,Y;
    private int numbreOfNeighbor;

    public CP(double x ,double y, int n){
        this.X = x; this.Y = y; this.numbreOfNeighbor = n;
    }

    public void showCP(){
        System.out.println("CP ----- ("+this.X+","+this.Y+")"+"numbre of neighbor:"+this.numbreOfNeighbor);
    }

    public double getX(){
        return this.X;
    }
    public double getY(){
        return this.Y;
    }
}
