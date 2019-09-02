package InfoNode;

import com.company.ConsoleColors;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

import java.util.LinkedList;
import java.util.Scanner;

public class Initialize {

    private boolean b = false;
    Node node [];
    LinkedList<Node> node_List = new LinkedList<Node>();

    Scanner input = new Scanner(System.in);

    String id;
    double x,y;

    public Initialize(){

    }



    public void getNode(int NumbreOfNOde){
        System.out.println("The number of nodes is"+NumbreOfNOde);
        node = new Node[NumbreOfNOde];
        for (int i = 0; i < NumbreOfNOde; i++){
            System.out.println("id : "+(i+1));//id = input.next();
            System.out.print("--- X :");x = input.nextDouble();
            System.out.print("--- Y :");y = input.nextDouble();System.out.println();
            node[i] = new Node(this.id,this.x,this.y);
        }
    }

    public void getNodeFromFile(int NumbreOfNOde) {
        BufferedReader reader;
        boolean a = true;
        String path = null;
        while(a){
            try {
                System.out.print("Enter the file path :");
                path= input.nextLine();
                if (new File(path).exists())
                {
                    System.out.println(ConsoleColors.GREEN+"File Exist"+ConsoleColors.RESET);
                    b = false;
                    break;
                }else{
                    System.out.println(ConsoleColors.RED+"File not Exist"+ConsoleColors.RESET);
                    b = true;
                }

            }catch (Exception e){
                b = true;
            }

        }

        System.out.println(path);
        int i = 0;
        String[] parts;
        node = new Node[NumbreOfNOde];
        try {
            reader = new BufferedReader(new FileReader(path));
            String line = reader.readLine();
            while (line != null) {
                parts = line.split(",");
                this.x = Double.parseDouble(parts[0]);
                this.y = Double.parseDouble(parts[1]);
                this.id = ""+(i+1);
                node[i] = new Node(this.id,this.x,this.y);
                // read next line
                i++;
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void affiche(){
        addtoListNode();
        for (Node b: this.node_List) {
            System.out.println("id :"+b.getID()+" ,x :"+b.getX()+" ,y :"+b.getY());
        }
    }

    private void addtoListNode(){
        for (Node n:this.node){
            node_List.add(n);
        }
    }

    public LinkedList<Node> Node_list(){
        return this.node_List;
    }
}
