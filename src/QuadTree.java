import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class QuadTree {
    static int QTINDEX;
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("C:/Users/user/IdeaProjects/AlgorithmPractice/resources/QuadTree/quadtree.in"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int caseCnt = Integer.parseInt(br.readLine());
        for (int caseNo=1;caseNo<=caseCnt;caseNo++){
            String quadTree = br.readLine();
            QTINDEX = 0;
            String reversed = reverse(quadTree);
            System.out.println(reversed);
        }
    }

    static String reverse(String quadTree){
        char head = quadTree.charAt(QTINDEX);
        QTINDEX++;
        if(head == 'b' || head == 'w') return String.valueOf(head);
        String ul = reverse(quadTree);
        String ur = reverse(quadTree);
        String ll = reverse(quadTree);
        String lr = reverse(quadTree);
        return "x" + ll + lr + ul + ur;

    }
}
