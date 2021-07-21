

class recursionWayup {

// ----------------Missed class Code from Rajneesh Sir Git  <start>--------------------//
       public static int subsequence(String ques, String ans) {
        if (ques.length() == 0) {
            System.out.println(ans);
            return 1;
        }

        char ch = ques.charAt(0);
        String roq = ques.substring(1);
        int count = 0;

        count += subsequence(roq, ans);
        count += subsequence(roq, ans + ch);

        return count;
    }

    public static int subsequence(String ques, String ans, ArrayList<String> res) {
        if (ques.length() == 0) {
            res.add(ans);
            return 1;
        }

        char ch = ques.charAt(0);
        String roq = ques.substring(1);
        int count = 0;

        count += subsequence(roq, ans, res);
        count += subsequence(roq, ans + ch, res);

        return count;
    }

    public static String[] nokiaKeys = { ".;", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tu", "vwx", "yz" };

    public static int nokiaKeyPad(String str, String ans) {
        if (str.length() == 0) {
            System.out.println(ans);
            return 1;
        }

        char ch = str.charAt(0);
        int idx = ch - '0';
        String word = nokiaKeys[idx];
        int count = 0;

        for (int i = 0; i < word.length(); i++)
            count += nokiaKeyPad(str.substring(1), ans + word.charAt(i));

        return count;
    }

    public static int stairPath(int n, String ans) {
        if (n == 0) {
            // System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int jump = 1; jump <= 3 && n - jump >= 0; jump++) {
            count += stairPath(n - jump, ans + jump);
        }

        return count;
    }

    public static int boardPath(int n, String ans) {
        if (n == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int dice = 1; dice <= 6 && n - dice >= 0; dice++) {
            count += boardPath(n - dice, ans + dice);
        }

        return count;
    }

    public static int boardPath(int sp, int ep, String ans) {
        if (sp == ep) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int dice = 1; dice <= 6 && sp + dice <= ep; dice++) {
            count += boardPath(sp + dice, ep, ans + dice);
        }

        return count;
    }

    public static int boardPath(int[] arr, int n, String ans) {
        if (n == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int jump : arr) {
            if (n - jump >= 0)
                count += boardPath(n - jump, n, ans + jump);
        }

        return count;
    }

    public static int mazePath_HVD(int sr, int sc, int er, int ec, String ans) {
        if (sr == er && sc == ec) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        if (sc + 1 <= ec && sr + 0 <= er)
            count += mazePath_HVD(sr + 0, sc + 1, er, ec, ans + "H");
        if (sc + 1 <= ec && sr + 1 <= er)
            count += mazePath_HVD(sr + 1, sc + 1, er, ec, ans + "D");
        if (sr + 1 <= er)
            count += mazePath_HVD(sr + 1, sc + 0, er, ec, ans + "V");

        return count;
    }

    public static int mazePath_MultiHVD(int sr, int sc, int er, int ec, String ans) {
        if (sr == er && sc == ec) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int jump = 1; sc + jump <= ec; jump++)
            count += mazePath_MultiHVD(sr, sc + jump, er, ec, ans + "H" + jump);
        for (int jump = 1; sc + jump <= ec && sr + jump <= er; jump++)
            count += mazePath_MultiHVD(sr + jump, sc + jump, er, ec, ans + "D" + jump);
        for (int jump = 1; sr + jump <= er; jump++)
            count += mazePath_MultiHVD(sr + jump, sc, er, ec, ans + "V" + jump);

        return count;
    }

    public static int permutation(String str, String ans) {
        if (str.length() == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            String ros = str.substring(0, i) + str.substring(i + 1);
            count += permutation(ros, ans + str.charAt(i));
        }

        return count;
    }

    public static int permutationWithoutDuplicate(String str, String ans) {
        if (str.length() == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        char prev = '$';
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != prev) {
                String ros = str.substring(0, i) + str.substring(i + 1);
                count += permutationWithoutDuplicate(ros, ans + str.charAt(i));
            }
            prev = str.charAt(i);
        }

        return count;

    }

    public static void permutationWithoutDuplicate(String str) {
        StringBuilder sb = new StringBuilder();
        int[] freq = new int[26];
        for (int i = 0; i < str.length(); i++)
            freq[str.charAt(i) - 'a']++;

        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < freq[i]; j++) {
                sb.append((char) (i + 'a'));
            }
        }

        // System.out.println(sb);
        System.out.println(permutationWithoutDuplicate(sb.toString(), ""));
    }

    public static int decodeWays(String str, String ans) {
        if (str.length() == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        char ch = str.charAt(0);
        if (ch == '0')
            return 0;

        count += decodeWays(str.substring(1), ans + (char) ('a' + ch - '1'));
        if (str.length() > 1) {
            int num = (ch - '0') * 10 + (str.charAt(1) - '0');
            if (num <= 26)
                count += decodeWays(str.substring(2), ans + (char) ('a' + num - 1));
        }

        return count;
    }

    public void permute(int[] nums,int count,List<List<Integer>> res,List<Integer> ans) {
        if(count == nums.length){
            List<Integer> base = new ArrayList<>();
            for(int ele : ans) base.add(ele);
            res.add(base);
            return; 
        }
        
        for(int i=0;i<nums.length;i++){
            
            if(nums[i]>= -10 && nums[i] <= 10){
                int val = nums[i];
                
                nums[i] = -11;
                ans.add(val);
                
                permute(nums,count + 1,res,ans);
                
                ans.remove(ans.size() - 1);
                nums[i] = val;
            }
        }
        
    }

    
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> ans = new ArrayList<>();
        
        permute(nums,0,res,ans);
        
        return res;
    }

    // ----------------Missed class Code from Rajneesh Sir Git <end>--------------------//
    

    public static int subsequense(String str, String ans) {
        if (str.length() == 0) {
            System.out.println(ans);
            return 1;
        }
        char ch = str.charAt(0);
        int count = 0;
        count += subsequense(str.substring(1), ans);
        count += subsequense(str.substring(1), ch + ans);
        return count;
    }

    public static String key[] = { ".;", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tu", "vwx", "yz" };

    public static int nokiakey(String str, String ans) {
        if (str.length() == 0) {
            System.out.println(ans);
            return 1;
        }
        char ch = str.charAt(0);
        int idx = ch - '0';
        String word = key[idx];
        int count = 0;
        for (int i = 0; i < word.length(); i++)
            count += nokiakey(str.substring(1), ans + word.charAt(i));

        return count;

    }

    public static int staircase(int num, String ans) {
        if (num == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int i = 1; i <= 3 && num - i >= 0; i++)
            count += staircase(num - i, ans + i);

        return count;
    }

    public static int board(int num, String ans) {
        if (num == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int i = 1; i <= 6 && num - i >= 0; i++)
            count += staircase(num - i, ans + i);

        return count;
    }

    public static int mazepath2(int sr, int sc, int dr, int dc, String ans) {
        if (sr == dr && sc == dc) {
            System.out.println(ans);
            return 1;
        }
        int count = 0;
        if (sc + 1 <= dc) {
            count += mazepath2(sr, sc + 1, dr, dc, ans + "h");
        }

        // if( sc+1 <= dc && sr+1 <= dr)
        // {
        // count+= mazepath2(sr+1, sc+1, dr, dc, ans+"d");
        // }
        if (sr + 1 <= dr) {
            count += mazepath2(sr + 1, sc, dr, dc, ans + "v");
        }

        return count;
    }

    public static int mazepathwithjumps(int sr, int sc, int dr, int dc, String ans) {
        if (sc == dc && sr == dr) {
            System.out.println(ans);
            return 1;
        }
        int count = 0;
        for (int i = 1; sc + i <= dc; i++) {
            count += mazepathwithjumps(sr, sc + i, dr, dc, ans + "h" + i);
        }
        for (int i = 1; sr + i <= dr; i++) {
            count += mazepathwithjumps(sr + i, sc, dr, dc, ans + "v" + i);
        }
        for (int i = 1; sc + i <= dc && sr + i <= dr; i++) {
            count += mazepathwithjumps(sr + i, sc + i, dr, dc, ans + "d" + i);
        }
        return count;
    }

    public static int permutation(String str, String ans) {
        if (str.length() == 0) {
            System.out.println(ans);
            return 1;
        }
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            String restofstring = str.substring(0, i) + str.substring(i + 1);
            count += permutation(restofstring, ans + ch);
        }
        return count;
    }

    public static int permutionwithoutrecurrence(String str, String ans) {
        if (str.length() == 0) {
            System.out.println(ans);
            return 1;
        }
        int count = 0;
        char prev = '#';
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != prev) {
                count += permutionwithoutrecurrence(str.substring(0, i) + str.substring(i + 1), ans + str.charAt(i));
            }

            prev = str.charAt(i);
        }

        return count;

    }

    public static void permutionwithoutrecurrence(String str) {
        StringBuilder sb = new StringBuilder();
        int frq[] = new int[26];
        for (int i = 0; i < str.length(); i++)
            frq[str.charAt(i) - 'a']++;

        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < frq[i]; j++) {
                sb.append((char) (i + 'a'));
            }
        }
        System.out.println(permutionwithoutrecurrence(sb.toString(), ""));

    }

    public static int encoding(String str, String ans) {
        if (str.length() == 0) {
            System.out.println(ans);
            return 1;
        }
        int count = 0;
        char ch = str.charAt(0);
        if (ch == '0') {
            return 0;
        }
        count += encoding(str.substring(1), ans + (char) ('a' + ch - '1'));
        if (str.length() > 1) {
            int num = (ch - '0') * 10 + (str.charAt(1) - '0');
            if (num <= 26)
                count += encoding(str.substring(2), ans + (char) ('a' + num - 1));
        }
        return count;
    }

    public static void floodfillbasic(int sr, int sc, int dr, int dc, String ans) {
        
        if (sr == dr && sc == dc) {
            System.out.println(ans);
            return;
        }
        boolean bv[]= new boolean[dr*dc];

        if (sr - 1 >= sr && bv[sr]== false) {
            bv[sr] = true;
            floodfillbasic(sr - 1, sc, dr, dc, ans + "t"); // for top
            bv[sr] = false;
        }

        if (sr + 1 <= dr&&  bv[sr]== false) {
            bv[sr] = true;
            floodfillbasic(sr + 1, sc, dr, dc, ans + "d"); // for down
            bv[sr] = false;
        }
        if (sc + 1 <= dc&&  bv[sc] == false) {
            bv[sc] = true;
            floodfillbasic(sr, sc + 1, dr, dc, ans + "r"); // for right
            bv[sc] = false;
        }
        if (sc - 1 >= sc&&  bv[sc] == false) {
            bv[sc] = true;
            floodfillbasic(sr, sc - 1, dr, dc, ans + "l"); // for left
            bv[sc] = false;
        }
    }

    public static void main(String[] args) {

        // int res= subsequense("abc", "");
        // System.out.println(res);
        // int res = nokiakey("78", "");
        // System.out.println(res);
        // int res = board(3,"");
        // System.out.println(res);
        // int res = mazepathwithjumps(1, 1, 3, 3, "");
        // System.out.println(res);
        // int res = permutation("ab", "");
        // System.out.println(res);
        // permutionwithoutrecurrence("aba");
        // int res = encoding("655196", "");
        // System.out.println(res);
        floodfillbasic(0,0,2,2,"");

    }
}
