package com.company;


import InfoNode.Calcule;
import InfoNode.CollectionPoints;
import InfoNode.Initialize;
import InfoNode.Node;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Scanner;


public class Main {
    private static int NumbreOfNOde;
    static Scanner input = new Scanner(System.in);

    public static boolean InuptNumbreNode(){
        System.out.print(ConsoleColors.BLUE_BOLD+"Enter the number of nodes:"+ConsoleColors.RESET);
        try{
            NumbreOfNOde =  input.nextInt();
            return false;
        }catch (Exception e){
            System.out.println(ConsoleColors.RED+"input is not an int value"+ConsoleColors.RESET);
            return true;
        }
    }

    public static String[] getPath_NodeMobile(){
        System.out.println("Path to create new file 'positions.dat' and id Node mobile 'default is 10' :");

        String gets[] = new String[2];
        for(int i = 0; i < 2; i++)
        {
            if(i == 0){
                System.out.print("Path: ");
                gets[i] = input.next();
            }else if(i == 1){
                System.out.print("Node mobile : ");
                gets[i] = input.next();
            }
        }
        return gets;
    }


    public static void main(String[] args) throws IOException, ScriptException, NoSuchMethodException {
        System.out.print(ConsoleColors.RED_BACKGROUND+"***********************************************************************"+ConsoleColors.RESET+"\n");
        System.out.print(ConsoleColors.RED_BACKGROUND+"********************"+ConsoleColors.RESET+ConsoleColors.GREEN+"TECHNIQUE DE COLLECTE DE DONNEES"+ConsoleColors.RESET+ConsoleColors.RED_BACKGROUND+"*******************"+ConsoleColors.RESET+"\n");
        System.out.print(ConsoleColors.RED_BACKGROUND+"***********************************************************************"+ConsoleColors.RESET+"\n");


        boolean b = true;

        Initialize i = new Initialize();
        InuptNumbreNode();

        System.out.println("How to enter the node!");
        System.out.println("1....... From a file");
        System.out.println("2....... Manual");
        switch (input.nextInt()){
            case 1:
                System.out.println("Each line of the file must contain the location of the node: X,Y ... ");
                i.getNodeFromFile(NumbreOfNOde);
                break;
            case 2:
                i.getNode(NumbreOfNOde);
                break;
            default:
                System.out.println("Not in the list!");
        }

        i.affiche();
        Calcule c = new Calcule(i.Node_list());
        c.calculNeighbor();

        c.Remove_frequent_neighbors();

        // ok


        CollectionPoints cp = new CollectionPoints(i.Node_list());

        cp.removeDeplicate();

        cp.showfinal();
        cp.calcule_cp();
        cp.showCP();


        String t[] = getPath_NodeMobile();

        cp.copyToFile(t[0] ,t[1]);
    }
}