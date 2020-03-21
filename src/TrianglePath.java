import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class TrianglePath {
    int[][] triangle;
    int[][] maxSumTriangle;

    TrianglePath(int triangleSize, Scanner sc){
        this.triangle = new int[triangleSize][triangleSize];
        for (int i=0; i<triangleSize; i++){
            Arrays.fill(triangle[i], -1);
            for (int j=0; j<=i; j++){
                this.triangle[i][j] = sc.nextInt();
            }
        }
        this.maxSumTriangle = new int[triangleSize][triangleSize];
    }

    void fillMaxSumTriangle(int x, int y, int tempSum){
        if(x == triangle.length) return;
        if(maxSumTriangle[x][y] == 0) maxSumTriangle[x][y] = triangle[x][y];
        if(maxSumTriangle[x][y] <= tempSum + triangle[x][y]) {
            maxSumTriangle[x][y] = tempSum + triangle[x][y];
            fillMaxSumTriangle(x+1, y, maxSumTriangle[x][y]);
            fillMaxSumTriangle(x+1, y+1, maxSumTriangle[x][y]);
        }
    }

    int getMaxSum(){
        fillMaxSumTriangle(0, 0, 0);
        int maxNum = 0;
        for (int sumNums : maxSumTriangle[maxSumTriangle.length-1])
            maxNum = Math.max(maxNum, sumNums);
        return maxNum;
    }

    public static void main(String[] args) {
        try{
            System.setIn(new FileInputStream("C:/Users/user/IdeaProjects/AlgorithmPractice/resources/TrianglePath/trianglepath.in"));
        }catch(IOException ie){
            System.out.println("FileInput Exception Occured.");
        }
        Scanner sc = new Scanner(System.in);
        int caseCnt = sc.nextInt();
        for(int i=0; i<caseCnt; i++){
            int triangelSize = sc.nextInt();
            TrianglePath trianglePath = new TrianglePath(triangelSize, sc);
            System.out.println(trianglePath.getMaxSum());
        }
    }
}
