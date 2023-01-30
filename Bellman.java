import java.util.Scanner;

public class Bellman {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of vertices: ");
        int n = sc.nextInt();
        System.out.println("enter cost matrix: ");
        int cost[][] = new int[n][n];
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                cost[i][j] = sc.nextInt();
        System.out.println("Enter source vertex: ");
        int src = sc.nextInt();
        int dist[] = new int[n];
        for(int i = 0; i < n; i++){
            if(i == src) dist[i] = 0;
            else dist[i] = 999;
        }
        for(int k = 0; k < n - 1; k++)
            for(int i = 0; i < n; i++)
                for(int j = 0; j < n; j++)
                    if(cost[i][j] != 999 && dist[i] + cost[i][j] < dist[j])
                        dist[j] = dist[i] + cost[i][j];

        for(int i:dist)
            System.out.print(i + " ");
    }

}
