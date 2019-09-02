package other;

import Jama.Matrix;

public class other {
    private double Mile(double p1[], double p2[]){
        p1[0] = p1[0] * 10000; p1[0] = Math.round(p1[0]);p1[0] = p1[0] / 10000;
        p1[1] = p1[1] * 10000; p1[1] = Math.round(p1[1]);p1[1] = p1[1] / 10000;

        p2[0] = p2[0] * 10000; p2[0] = Math.round(p2[0]);p2[0] = p2[0] / 10000;
        p2[1] = p2[1] * 10000; p2[1] = Math.round(p2[1]);p2[1] = p2[1] / 10000;
        double p;
        if((p1[0] != p2[0])){
            p = ((p2[1] - p1[1]) / (p2[0] - p1[0]));
            //System.out.println("1:"+(p2[1] - p1[1])+", 2:"+(p2[0] - p1[0]));
        }else{
            p = 0;
        }
        p = p * 10000; p = Math.round(p); p = p / 10000;
        //System.out.println("Mile-0-:"+p);
        return p;

    }
    private double[] Equ_Line(double p1[] ,double p2[]){
        p1[0] = p1[0] * 10000; p1[0] = Math.round(p1[0]);p1[0] = p1[0] / 10000;
        p1[1] = p1[1] * 10000; p1[1] = Math.round(p1[1]);p1[1] = p1[1] / 10000;

        p2[0] = p2[0] * 10000; p2[0] = Math.round(p2[0]);p2[0] = p2[0] / 10000;
        p2[1] = p2[1] * 10000; p2[1] = Math.round(p2[1]);p2[1] = p2[1] / 10000;
        /*
         *   x - y = -3   Eqn(1)
         *   double  [] = {x,y,p};
         * */
        double p    = (p1[1] - (p1[0] * Mile(p1,p2)));
        double re[] = {Mile(p1,p2),(p1[0]) ,p};
        System.out.println(Mile(p1,p2)+"x + "+(p1[0])+"y "+" = "+p);
        return re;
    }

    private double [][] ret_equ1(double eq1[],double eq2[]){
        double  re[][] = {{eq1[0],eq1[1]},{eq2[0],eq2[1]}};
        return re;
    }
    private double [] ret_equ2(double   eq1[],double   eq2[]){
        eq1[2] = (eq1[2]);
        double  re[] = {eq1[2],eq2[2]};
        return re;
    }
    /*
     * x + y =  p ---&gt; Eqn(1)
     * x + y =  p ---&gt; Eqn(2)
     * lhsArray[]  = {{x,y},{x,y}}
     * rhsArray[] = {p,p}
     * */
    private double[] Line_IntersectionPoint(double  lhsArray[][] ,double  rhsArray[]){

/*
        System.out.println(ConsoleColors.BLUE+"*********************************************"+ConsoleColors.RESET);
        for(int i = 0; i < lhsArray.length; i++){
            for(int j = 0; j < lhsArray[i].length; j++){
                System.out.print(""+lhsArray[i][j]+",");
            }
            System.out.println();
        }
        System.out.println(rhsArray[0]);
        System.out.println(rhsArray[1]);
        System.out.println(ConsoleColors.BLUE+"*********************************************"+ConsoleColors.RESET);
*/

        double re[] = new double [2];
        //Creating Matrix Objects with arrays
        Matrix lhs = new Matrix(lhsArray);
        Matrix rhs = new Matrix(rhsArray, 2);

        //Calculate Solved Matrix
        Matrix ans = lhs.solve(rhs);
        re[0] = (ans.get(0, 0));
        re[1] = (ans.get(1, 0));
        return re;
    }

}
