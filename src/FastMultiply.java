import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FastMultiply {

    // list num의 자릿수 올림을 처리한다.
    public ArrayList<Integer> normalize(ArrayList<Integer> num) {

        num.add(0);

        for (int i = 0; i < num.size() - 1; i++) {
            if (num.get(i) < 0) {
                int borrow = (Math.abs(num.get(i)) + 9) / 10;
                num.set(i + 1, num.get(i + 1) - borrow);
                num.set(i, num.get(i) + borrow * 10);

            } else {
                num.set(i + 1, num.get(i + 1) + num.get(i) / 10);
                num.set(i, num.get(i) % 10);
            }
        }
        if (num.get(num.size() - 1) == 0)
            num.remove(num.size() - 1);

        return num;
    }

    // 두 짧 정수의 곱을 반환한다. 각 배열에는 각 수의 자리수가 1의 자리에서부터 시작해 저장되어 있다.
    // 예: multiply("123","456") = 123*456 = 56088

    ArrayList<Integer> multiply(List<Integer> a, List<Integer> b) {
        ArrayList<Integer> c = new ArrayList<Integer>();
        c = ensureSize(c, a.size() + b.size() + 1);
        for (int i = 0; i < a.size(); i++)
            for (int j = 0; j < b.size(); j++) {
                c.set(i + j, c.get(i + j) + a.get(i) * b.get(j));
            }

        c = normalize(c);
        return c;
    }

    // a += b *(10^k)를 구현
    ArrayList<Integer> addTo(ArrayList<Integer> a, ArrayList<Integer> b, int k) {
        a = ensureSize(a, Math.max(a.size(), b.size() + k));
        for (int i = 0; i < b.size(); i++)
            a.set(i + k, a.get(i + k) + b.get(i));
        a = normalize(a);
        return a;
    }

    // a -= b;를 구현 (a>=b를 가정)
    ArrayList<Integer> subFrom(ArrayList<Integer> a, ArrayList<Integer> b) {
        a = ensureSize(a, Math.max(a.size(), b.size()) + 1);
        for (int i = 0; i < b.size(); i++)
            a.set(i, a.get(i) - b.get(i));
        a = normalize(a);
        return a;
    }

    // 카라츠바의 빠른 정수 곱셈 알고리즘
    // 두 긴 정수의 곱을 반환한다.
    ArrayList<Integer> karatsuba(ArrayList<Integer> a, ArrayList<Integer> b) {
        int a_size = a.size();
        int b_size = b.size();

        // a<b일 경우 둘을 바꾼다.
        if (a_size < b_size)
            return karatsuba(b, a);
        // 기저 사례 : a나 b가 비어있을 경우
        if (a_size == 0 || b_size == 0)
            return null;
        // 기저 사례 : a 가 비교적 짧은 경우 0(n^2) 곱셈으로 변경한다.
        if (a_size <= 50)
            return multiply(a, b);

        int half = a_size / 2;

        // a 와 b를 밑에서 half 자리와 나머지로 분리한다.
        ArrayList<Integer> a0 = new ArrayList<Integer>(a.subList(0, half));
        ArrayList<Integer> a1 = new ArrayList<Integer>(a.subList(half, a.size()));
        ArrayList<Integer> b0 = new ArrayList<Integer>(b.subList(0, Math.min(b.size(), half)));
        ArrayList<Integer> b1 = new ArrayList<Integer>(b.subList(Math.min(b.size(), half), b.size()));

        // z2 = a1 * b1
        ArrayList<Integer> z2 = karatsuba(a1, b1);
        // z0 = a0 * b0
        ArrayList<Integer> z0 = karatsuba(a0, b0);
        // a0 = a0 + a1; b0 = b0 + b1
        z0 = addTo(a0, a1, 0);
        z0 = addTo(b0, b1, 0);

        // z1 = (a0 * b0) - z0 - z2;
        ArrayList<Integer> z1 = karatsuba(a0, b0);
        z1 = subFrom(z1, z0);
        z1 = subFrom(z1, z2);

        // ret = z0 + z1 * 10^half + z2 * 10^(half*2)
        ArrayList<Integer> ret = new ArrayList<Integer>();
        ret = addTo(ret, z0, 0);
        ret = addTo(ret, z1, half);
        ret = addTo(ret, z2, half * 2);

        return ret;
    }

    // 출력 : ArrayList를 입력받아서 출력을 위한 String으로 만들어준다.
    String toString(List<Integer> a) {
        String ret = "";
        // 앞의 0 없애기
        while (a.size() > 1 && a.get(a.size() - 1) == 0)
            a.remove(a.size() - 1);

        for (int i = 0; i < a.size(); i++)
            ret += (char) ('0' + a.get(a.size() - 1 - i));

        return ret;
    }

    // 입력 : String을 입력받아서 계산을 위한 ArrayList를 만들어준다.
    ArrayList<Integer> fromString(String s) {
        ArrayList<Integer> ret = new ArrayList<Integer>();
        for (int i = 0; i < s.length(); i++) {
            ret.add(s.charAt(i) - '0');

        }

        Collections.reverse(ret);

        return ret;
    }

    // 자바에는 없는 C++의 resize를 구현해줌
    public ArrayList<Integer> ensureSize(ArrayList<Integer> list, int size) {
        // Prevent excessive copying while we're adding
        list.ensureCapacity(size);
        while (list.size() < size) {
            list.add(0);
        }

        return list;
    }

}
