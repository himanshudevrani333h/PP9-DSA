import java.util.*;

public class string {
    public static Scanner sc = new Scanner(System.in);

    public static boolean ispalindrome(String str, int si, int li) {
        while (si < li) {
            if (str.charAt(si) != str.charAt(li))
                return false;
            si++;
            li--;
        }
        return true;
    }

    public static void printallpalindrom(String str) {
        for (int i = 0; i < str.length(); i++) {
            for (int j = i; j < str.length(); j++) {
                if (ispalindrome(str, i, j)) {
                    String res = str.substring(i, j + 1);
                    System.out.println(res);
                }
            }
        }
    }

    public static void stringcompression(String str) {
        String res = "";
        String res2 = "";
        int count = 1;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (count == 1) {
                res2 += Character.toString(ch);
            }
            if (i < str.length() - 1 && ch == str.charAt(i + 1)) {

                count++;
                continue;
            }

            if (count == 1)
                res += ch + "";
            else {
                // res+= Character.toString(ch) + count;
                res += ch + "";
                res += count;
            }
            count = 1;
        }
        System.out.println(res2);
        System.out.println(res);
    }

    public static void stringcompressionby_WhileLoop_pt1(String str) {
        int n = str.length();
        String ans = str.charAt(0) + "";
        int i = 1, count = 0;
        while (i < n) {
            count = 1;
            while (i < n && ans.charAt(ans.length() - 1) == str.charAt(i)) {
                count++;
                i++;
            }

            if (count > 1) {
                ans += count;
            }
            if (i < n) {
                ans += str.charAt(i);
            }
            i++;
        }
        System.out.println(ans);
    }

    public static void stringcompressionby_WhileLoop_pt2(String str) {
        int n = str.length();
        String ans = str.charAt(0) + "";
        int i = 1;
        while (i < n) {

            while (i < n && ans.charAt(ans.length() - 1) == str.charAt(i)) {
                i++;
            }

            if (i < n) {
                ans += str.charAt(i);
            }
            i++;
        }
        System.out.println(ans);
    }

    public static int countHi(String str) {
        int count = 0;
        int i = 0;
        while (i < str.length() - 1) {
            if (str.charAt(i) == 'h' && str.charAt(i + 1) == 'i') {
                count++;
                i += 2;
            } else {
                i++;
            }
        }
        return count;

    }

    public static int countHinothit(String str) {
        int count = 0;
        int i = 0;
        while (i < str.length() - 1) {
            if (str.charAt(i) == 'h' && str.charAt(i + 1) == 'i') {
                if (i < str.length() - 2 && str.charAt(i + 2) == 't') {
                    i += 3;
                } else {
                    count++;
                    i += 2;
                }

            } else {
                i++;
            }
        }
        return count;

    }

    public static void stringbuilderbasics() {
        StringBuilder sb = new StringBuilder();
        sb.append("abc");
        sb.append("def");
        System.out.println(sb.toString());

    }

    public static void toogle(String str) {
        String temp = str;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < temp.length(); i++) {
            char ch = temp.charAt(i);

            if (ch >= 'a' && ch <= 'z') {
                sb.append((char) (ch - 'a' + 'A'));
                // sb.append((char)(ch - 32));
            } else {
                // sb.append((char)(ch + 32));
                sb.append((char) (ch - 'A' + 'a'));
            }

        }
        System.out.println(sb.toString());
    }

    public static void diffofcharASCII(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            sb.append(ch);
            if (i < str.length() - 1) {
                int diff = str.charAt(i + 1) - str.charAt(i);
                sb.append(diff);
            }

        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        // String str = sc.next();
        // printallpalindrom(str);
        // int res = countHinothit(str);
        // System.out.println(res);
        // stringcompression(str);
        // stringcompressionby_WhileLoop_pt1(str);
        // stringcompressionby_WhileLoop_pt2(str);
        // stringbuilderbasics();
        // diffofcharASCII("pepCODinG");
        toogle("abcDEFgh");
    }

}
