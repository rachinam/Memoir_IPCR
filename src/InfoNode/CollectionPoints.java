package InfoNode;

import com.company.ConsoleColors;

import java.io.File;
import java.io.FileWriter;
import java.util.LinkedList;

class isCP{
    String id;
    Node n;
    boolean iscp;
    public isCP(String id ,Node n ,boolean is){
        this.id = id;
        this.n = n;
        this.iscp = is;
    }
}

public class CollectionPoints {
    private LinkedList<Node> node_List = new LinkedList<Node>();
    private LinkedList<Node> node_List_final = new LinkedList<Node>();
    private LinkedList<Double> cp_list = new LinkedList<Double>();
    private Calcule calcule = new Calcule();

    Node node[] ;

    private LinkedList<CP> result = new LinkedList<CP>();

    public CollectionPoints(LinkedList<Node> n){
        this.node_List = n;
        node = new Node[this.node_List.size()];
        copyToTable();
        //this.node_List_final = n;
    }

    public void showNei(){
        for(Node n: this.node_List){
            System.out.println(ConsoleColors.BLUE+"node id : "+n.getID()+" Neighbor is "+ConsoleColors.RESET);
            n.showNeighbor();
            System.out.println(ConsoleColors.BLUE_BACKGROUND+"*************************************************"+ConsoleColors.RESET);
        }
    }
    public void showCP(){
        for (CP c:this.result){
            c.showCP();
        }
    }
    // -1 node isolate LinkedList<CP>

    private void removeByIndex(int i){
        if(this.node_List_final.size() > 0)
            this.node_List_final.remove(i);
    }
    private void Add(Node n){
        this.node_List_final.add(n);
    }




    private void copyToTable(){
        int i = 0;
        for(Node n :this.node_List){
            node[i] = n;
            i++;
        }
    }

    private void copyToList(){
        for(Node n :node){
            if(n != null){
                this.node_List_final.add(n);
            }
        }
    }
    public void removeDeplicate(){
        for(Node n:this.node_List){
            if(n.getNeighbor().size() > 0)
            for(Neighbor_Node nei :n.getNeighbor()){
                removeById(n.getID(),nei.node.getID());
            }
        }
        copyToList();
    }

    private void removeById(String idp ,String id){
        boolean is = false;
        for(int i = 0 ;i <node.length ;i++){
            if(node[i] != null)
                if(node[i].getID() == idp){
                    is = true;
                }
        }
        if(is){
            for(int i = 0 ;i <node.length ;i++){
                if(node[i] != null)
                    if(node[i].getID() == id){
                        node[i] = null;
                    }
            }
        }
    }




    public void showfinal(){
        System.out.println("************************* nei final ");
        System.out.println("size : "+this.node_List_final.size());
        for(Node n: this.node_List_final){
            System.out.println(ConsoleColors.BLUE + "node id : " + n.getID() + " Neighbor is " + ConsoleColors.RESET);
            n.showNeighbor();
            System.out.println(ConsoleColors.BLUE_BACKGROUND + "*************************************************" + ConsoleColors.RESET);

        }
        System.out.println("************************* nei final -- ");
    }
    public void calcule_cp(){
        this.node_List = this.node_List_final;
        System.out.println("Size :"+this.node_List.size());
        int size;
        CP p;
        for(Node n: this.node_List){
            size = n.getNeighbor().size();
            switch (size){
                case 0:
                    System.out.println("id:"+n.getID()+"- x="+n.getX()+"y="+n.getY());
                    p = new CP(n.getX() ,n.getY() ,size);
                    this.result.add(p);
                    break;
                case 1:
                    System.out.println("1");
                    twoNode(n ,n.getNeighbor().get(0).node);
                    p = new CP(n.getX(),n.getY(),size);
                    this.result.add(p);
                    break;
                case 2:
                    System.out.println("2");
                    threeNode(n ,n.getNeighbor().get(0).node ,n.getNeighbor().get(1).node);
                    break;
                case 3:
                    System.out.println("3");
                    threeNode(n ,n.getNeighbor().get(0).node ,n.getNeighbor().get(1).node);
                    break;
                default:
                    if(size > 3)
                        threeNode(n ,n.getNeighbor().get(0).node ,n.getNeighbor().get(1).node);
                    break;

            }
        }
        //return this.result;
    }

    public LinkedList<CP> twoNode(Node n1 ,Node n2){
        double p1[] = {n1.getX(),n1.getY()};
        double p2[] = {n2.getX(),n2.getY()};

        double re[] = calcule.middleLine(p1,p2);
        if(re != null){
            this.result.add(new CP(re[0],re[1],2));
        }
        return this.result;
    }

    public LinkedList<CP>  threeNode(Node n1 ,Node n2 ,Node n3){
        double d1[] = {n1.getX() ,n1.getY() ,n1.getPort()};
        double d2[] = {n2.getX() ,n2.getY() ,n2.getPort()};
        double d3[] = {n3.getX() ,n3.getY() ,n3.getPort()};

        double re1[] = calcule.cercle(d1,d2);
        double re2[] = calcule.cercle(d1,d3);

        // line 1
        double p1[] = {re1[0],re1[1]};
        double p2[] = {re1[2],re1[3]};

        // line 2
        double p3[] = {re2[0],re2[1]};
        double p4[] = {re2[2],re2[3]};

        double re[] = calcule.getIntersection(p1,p2,p3,p4);

        if(re != null){
            this.result.add(new CP(re[0],re[1],3));
        }
        return this.result;
    }


    public void copyToFile(String path ,String idNodeMobile){
        path += "/positions.dat";
        String source;
        double i = 1;
        try {
            File file = new File(path);
            if(!file.exists()){
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file,false);
            source = idNodeMobile+" 0 X Y \n #node time(s) x y  The first position of the node mobile ";
            System.out.println(source);
            fw.write(source);
            for (CP cp :this.result){
                source = idNodeMobile+" "+i+" "+cp.getX()+" "+cp.getY()+" \n";
                System.out.println(source);
                fw.write(source);
                i++;
            }
            fw.close();
            System.out.println("successful ...");

        }catch(Exception e){
            System.out.println("ERROR "+e.getMessage());
        }
    }
}
