import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class WildCard {

    char[] wc;    // 와일드카드 문자열
    char[] fn;    // 파일이름 문자열
    ArrayList<String> fileNameArray;

    /* 동적프로그래밍 저장 배열
     * -1 : 계산되지 않음
     *  1 : 일치
     *  0 : 불일치 */
    int[][] cache;


    WildCard(String wildCard, ArrayList<String> fileNameArray){
        this.wc = wildCard.toCharArray();
        this.fileNameArray = fileNameArray;
        this.cache = new int[101][101];
    }

    public int match(int wIdx, int fnIdx){

        // 동적 프로그래밍
        if(cache[wIdx][fnIdx] != -1)
            return cache[wIdx][fnIdx];

        // 1:1 대응, 일치하면 인덱스 증가 후 재귀호출
        if(wIdx < wc.length && fnIdx < fn.length)
            if(wc[wIdx] == '?' || wc[wIdx] == fn[fnIdx])
                return cache[wIdx][fnIdx] = match(wIdx+1, fnIdx+1);

        // 와일드카드와 파일명 모두 끝에 도달, 파일명도 끝에 도달했어야 일치
        if(wIdx == wc.length)
            return cache[wIdx][fnIdx] = (fnIdx == fn.length)? 1 : 0;

        // * 문자를 만난 경우
        if (wc[wIdx] == '*') {
            if (match(wIdx + 1, fnIdx) == 1 ||
                    (fnIdx < fn.length && match(wIdx, fnIdx + 1) == 1))
                return cache[wIdx][fnIdx] = 1;
        }

        return cache[wIdx][fnIdx] = 0;
    }

    public void solve(){

        ArrayList<String> resFileName = new ArrayList<String>();
        for(int i=0; i<fileNameArray.size(); i++){

            // 캐쉬 초기화
            for(int[] arr : this.cache)
                Arrays.fill(arr, -1);

            String temp = fileNameArray.get(i);
            fn = temp.toCharArray();
            if(match(0,0) == 1)
                resFileName.add(temp);
        }

        // 정렬 후 출력
        Collections.sort(resFileName);
        for(String str : resFileName)
            System.out.println(str);
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int numTextCase = sc.nextInt();

        for(int i =0; i<numTextCase; i++){

            String wildcardStr = sc.next();
            int numFileName = sc.nextInt();

            ArrayList<String> fileNameArray = new ArrayList<String>();
            for(int j=0; j<numFileName; j++)
                fileNameArray.add(sc.next());

            WildCard wildcard = new WildCard(wildcardStr, fileNameArray);
            wildcard.solve();
        }
    }
}
