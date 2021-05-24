import java.util.*;

public class RecursionAL {

    public static ArrayList<String> subseq(String str) {
        if (str.length() == 0) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }
        char ch = str.charAt(0);
        ArrayList<String> recurion = subseq(str.substring(1));
        ArrayList<String> ans = new ArrayList<>(recurion);
        for (String s : recurion) {
            ans.add(ch + s);
        }

        return ans;

    }

    public static ArrayList<String> getKPC(String str) {
        String key[] = { ".;", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tu", "vwx", "yz" };

        if (str.length() == 0) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }

        char ch = str.charAt(0);
        String temp = key[ch - '0'];
        ArrayList<String> recursion = getKPC(str.substring(1));

        ArrayList<String> res = new ArrayList<>();

        for (int i = 0; i < temp.length(); i++) {
            char chr = temp.charAt(i);
            for (String val : recursion) {
                res.add(chr + val);
            }
        }

        return res;

    }

    // public static ArrayList<String> decode(String str) {
    // char key[] = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
    // 'm', 'n', 'o', 'p', 'q', 'r', 's',
    // 't', 'u', 'v', 'w', 'x', 'y', 'z' };
    // if (str.length() == 0) {
    // ArrayList<String> base = new ArrayList<>();
    // base.add("");
    // return base;
    // }
    // if (str.charAt(0) == '0') {
    // return new ArrayList<>();
    // }
    // char ch1 = str.charAt(0);
    // // char ch2=str.charAt(1);
    // char temp1 = key[(ch1 - '0') - 1];
    // // char temp2 = key[(ch2 -'0') -1];
    // ArrayList<String> recur = decode(str.substring(1));
    // ArrayList<String> res = new ArrayList<>();
    // for (String s : recur) {
    // res.add(temp1 + s);

    // }

    // if (str.length() > 1) {
    // if (((ch1 - '0') * 10 + (str.charAt(1) - '0') <= 26)) {
    // ArrayList<String> recur2 = decode(str.substring(2));
    // for (String s : recur2) {
    // res.add(key[(((ch1 - '0') * 10 + (str.charAt(1) - '0')) - 1)] + s);
    // }
    // }
    // }
    // return res;
    // }

    // 11283, 11023, 0 , 113410111
    // public static ArrayList<String> decodeWays(String str) {
    // if (str.length() == 0) {
    // ArrayList<String> base = new ArrayList<>();
    // base.add("");
    // return base;
    // }

    // if (str.charAt(0) == '0')
    // return new ArrayList<>();

    // char ch1 = str.charAt(0);
    // ArrayList<String> myAns = new ArrayList<>();
    // ArrayList<String> recAnsFor1Len = decodeWays(str.substring(1));
    // for (String s : recAnsFor1Len) {
    // myAns.add((char) ('a' + ch1 - '1') + s);
    // }

    // if (str.length() > 1) {
    // int num = (ch1 - '0') * 10 + (str.charAt(1) - '0');
    // if (num <= 26) {
    // ArrayList<String> recAnsFor2Len = decodeWays(str.substring(2));
    // for (String s : recAnsFor2Len) {
    // myAns.add((char) ('a' + num - 1) + s);
    // }
    // }
    // }

    // return myAns;
    // }

    public static ArrayList<String> getstairpath(int n) {
        if (n == 0) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        } else if (n < 0) {
            return new ArrayList<>();
        }

        ArrayList<String> step1 = getstairpath(n - 1);
        ArrayList<String> step2 = getstairpath(n - 2);
        ArrayList<String> step3 = getstairpath(n - 3);
        ArrayList<String> res = new ArrayList<>();
        for (String s : step1) {
            res.add("1" + s);
        }
        for (String s : step2) {
            res.add("2" + s);
        }
        for (String s : step3) {
            res.add("3" + s);
        }

        return res;
    }

    public static ArrayList<String> mazepath(int sr, int sc, int dr, int dc) {
        if (sr > dr || sc > dc) {
            return new ArrayList<>();
        }

        if (sr == dr && sc == dc) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }

        ArrayList<String> hz = mazepath(sr, sc + 1, dr, dc);
        ArrayList<String> vc = mazepath(sr + 1, sc, dr, dc);
        ArrayList<String> res = new ArrayList<>();
        for (String s : hz) {
            res.add("H" + s);
        }
        for (String s : vc) {
            res.add("V" + s);
        }

        return res;
    }

    public static ArrayList<String> mazepathwitdiagonal(int sr, int sc, int dr, int dc) {
        if (sr > dr || sc > dc) {
            return new ArrayList<>();
        }

        if (sr == dr && sc == dc) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }

        ArrayList<String> hz = mazepathwitdiagonal(sr, sc + 1, dr, dc);
        ArrayList<String> diag = mazepathwitdiagonal(sr + 1, sc + 1, dr, dc);
        ArrayList<String> vc = mazepathwitdiagonal(sr + 1, sc, dr, dc);
        ArrayList<String> res = new ArrayList<>();

        for (String s : hz) {
            res.add("h" + s);
        }
        for (String s : diag) {
            res.add("d" + s);
        }
        for (String s : vc) {
            res.add("v" + s);
        }

        return res;
    }

    public static ArrayList<String> mazepathwithinfinitejumps(int sr, int sc, int dr, int dc) {
        if (sr == dr && sc == dc) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }

        ArrayList<String> res = new ArrayList<>();
        for (int jmp = 1; sc + jmp <= dc; jmp++) {

            ArrayList<String> hz = mazepathwithinfinitejumps(sr, sc + jmp, dr, dc);
            
            for (String s : hz) {

                res.add("h" + jmp + s);
            }
        }

        for (int jmp = 1; sr + jmp <= dr && sc + jmp <= dc; jmp++) {

            ArrayList<String> diag = mazepathwithinfinitejumps(sr + jmp, sc+jmp, dr, dc);
            
            for (String s : diag) {

                res.add("d" + jmp + s);
            }
        }

        for (int jmp = 1; sr + jmp <= dr; jmp++) {

            ArrayList<String> vc = mazepathwithinfinitejumps(sr + jmp, sc, dr, dc);
            
            for (String s : vc) {

                res.add("v" + jmp + s);
            }
        }

        return res;
    }

    public static void main(String[] args) {
        // System.out.println(subseq("abc"));
        // System.out.println(getKPC("78"));
        // System.out.println(decode("11283"));
        // System.out.println(getstairpath(3));
        // System.out.println(mazepathwitdiagonal(0,0,2,2));
        System.out.println(mazepathwithinfinitejumps(1, 1, 2, 2));
    }
}
