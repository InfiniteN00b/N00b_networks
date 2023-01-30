import java.util.ArrayList;
import java.util.Scanner;

public class LeackyBuck {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> arr = new ArrayList<Integer>();
        int x;
        System.out.println("enter the time stamps to check: (enter 000 to finish)");
        while(true){
            x = sc.nextInt();
            if(x == 000) break;
            arr.add(x);
        }

        int X = 0, LCT = 1, I, L, d;
        System.out.println("ENtre the number of inter arrival packets I:");
        I = sc.nextInt();
        System.out.println("Enter traffic burstness: ");
        L = sc.nextInt();
        System.out.println("Enter drain rate:");
        d = sc.nextInt();

        for(int i = 0; i < arr.size(); i++){
            int xd = X - (arr.get(i) - LCT * d);
            if(xd > L) System.out.println("packets at time " + arr.get(i) + " is non confirming");
            else{
                X = xd + I;
                LCT = arr.get(i);
                System.out.println("packet at time " + arr.get(i) + " is confirming");
            }
        }
    }
}

