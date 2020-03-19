import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.Buffer;

public class Fence {
    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("C:/Users/user/IdeaProjects/AlgorithmPractice/resources/Fence/fence.in"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        final int cases = Integer.parseInt(br.readLine());
        for (int i = 1; i <= cases; i++){
            int fenceCnt = Integer.parseInt(br.readLine());
            String[] fenceHeightsStr = br.readLine().split(" ");
            int ret = getMaxFenceSize(fenceHeightsStr, 0, fenceCnt-1);
            System.out.println(ret);
        }

    }

    static int getMaxFenceSize(String[] fenceHeightStr, int start, int end){
        if(start >= end) return Integer.valueOf(fenceHeightStr[start]);
        int mid = (start+end)/2;
        int maxSize1 = Math.max(getMaxFenceSize(fenceHeightStr, start, mid), getMaxFenceSize(fenceHeightStr, mid+1, end));
        int tempStart = mid;
        int tempEnd = mid+1;
        int tempHeight = Math.min(Integer.valueOf(fenceHeightStr[tempStart]), Integer.valueOf(fenceHeightStr[tempEnd]));
        int maxSize2 = tempHeight*2;
        while(tempStart > start || tempEnd < end){
           if(tempEnd < end && (tempStart == start || Integer.valueOf(fenceHeightStr[tempStart-1]) < Integer.valueOf(fenceHeightStr[tempEnd+1]))){
              tempEnd++;
              tempHeight = Math.min(tempHeight, Integer.valueOf(fenceHeightStr[tempEnd]));
           }else{
              tempStart--;
              tempHeight = Math.min(tempHeight, Integer.valueOf(fenceHeightStr[tempStart]));
           }
           maxSize2 = Math.max(maxSize2, (tempEnd-tempStart+1)*tempHeight);
        }
        return Math.max(maxSize1, maxSize2);
    }
}
