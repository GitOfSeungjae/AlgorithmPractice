import java.util.Arrays;
import java.util.Scanner;


public class JumpGame {
    int[][] matrix;
    int[][] cache;
    int n;

    JumpGame(int[][] matrix){
        n = matrix.length;
        this.matrix = matrix;
        this.cache = new int[n][n];
        for(int[] arr : this.cache)
            Arrays.fill(arr, -1);
    }

    public boolean solve(int x, int y){

        // 게임판의 범위를 벗어남
        if(x >= n || y >= n) return false;

        // 목표지점에 도달
        if(x==n-1 && y==n-1) return true;

        // 메모이제이션
        if(cache[x][y] == 1) return false;
        else cache[x][y] = 1;

        int jump = matrix[x][y];
        return solve(x+jump, y) || solve(x, y+jump);
    }


    public static void main(String[] args) {

        int[][] matrix;
        int n;

        Scanner sc = new Scanner(System.in);
        int numTestCase = sc.nextInt();

        for(int i = 0; i<numTestCase; i++){
            n = sc.nextInt();
            matrix = new int[n][n];
            for(int x=0; x<n; x++){
                for(int y=0; y<n; y++){
                    matrix[x][y] = sc.nextInt();
                }
            }

            JumpGame jumpgame = new JumpGame(matrix);
            boolean res = jumpgame.solve(0,0);
            System.out.println(res? "YES":"NO");
        }
    }
}
