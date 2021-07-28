import java.util.ArrayList;
import java.util.Collections;

public class coins {

    public static int infiPermutations(int coins[], int targ, String asf) {

        if (targ == 0) {
            System.out.println(asf);
            return 1;
        }

        int count = 0;
        for (int i = 0; i < coins.length; i++) {

            if (targ - coins[i] >= 0) {
                count += infiPermutations(coins, targ - coins[i], asf + coins[i]);
            }
        }

        return count;

    }

    public static int infiCombination(int coins[], int targ, String asf, int idx) {
        if (targ == 0) {
            System.out.println(asf);
            return 1;
        }
        int count = 0;
        for (int i = idx; i < coins.length; i++) {
            if (targ - coins[i] >= 0) {
                count += infiCombination(coins, targ - coins[i], asf + coins[i], i);
            }
        }

        return count;
    }

    public static int finiteCombination(int coins[], int targ, String asf, int idx) {
        if (targ == 0) {
            System.out.println(asf);
            return 1;
        }
        int count = 0;
        for (int i = idx; i < coins.length; i++) {
            if (targ - coins[i] >= 0) {
                count += finiteCombination(coins, targ - coins[i], asf + coins[i], i + 1);
            }
        }

        return count;
    }

    public static int finitePermutations(int coins[], int targ, String asf, boolean[] visi) {
        if (targ == 0) {
            System.out.println(asf);
            return 1;
        }
        int count = 0;
        for (int i = 0; i < coins.length; i++) {

            if (!visi[i] && targ - coins[i] >= 0) {
                visi[i] = true;
                count += finitePermutations(coins, targ - coins[i], asf + coins[i], visi);
                visi[i] = false;
            }
        }

        return count;

    }

    // All 4fns by Subseq

    public static int finiteCombination_subseq(int coins[], int targ, String asf, int idx) {
        if (targ == 0 || idx >= coins.length - 1) {
            if (targ == 0) {
                System.out.println(asf);
                return 1;
            }

            return 0;
        }
        int count = 0;
        if (targ - coins[idx] >= 0)
            count += finiteCombination_subseq(coins, targ - coins[idx], asf + coins[idx], idx + 1);

        count += finiteCombination_subseq(coins, targ, asf, idx);

        return count;
    }

    public static int InfiniteCombination_subseq(int coins[], int targ, String asf, int idx) {
        if (targ == 0 || idx >= coins.length - 1) {
            if (targ == 0) {
                System.out.println(asf);
                return 1;
            }

            return 0;
        }
        int count = 0;
        if (targ - coins[idx] >= 0)
            count += finiteCombination_subseq(coins, targ - coins[idx], asf + coins[idx], 0);

        count += finiteCombination_subseq(coins, targ, asf, idx + 1);

        return count;
    }

    public static int finitePermutation_subseq(int coins[], int targ, String asf, int idx) {
        if (targ == 0 || idx >= coins.length) {
            if (targ == 0) {
                System.out.println(asf);
                return 1;
            }

            return 0;
        }

        int count = 0;

        if (coins[idx] > 0 && targ - coins[idx] >= 0) {
            int currCoin = coins[idx];
            coins[idx] = -coins[idx];
            count += finitePermutation_subseq(coins, targ - currCoin, asf + currCoin, 0);
            coins[idx] = -coins[idx];
        }
        count += finitePermutation_subseq(coins, targ, asf, idx + 1);

        return count;
    }

    public static int InfinitePermutation_subseq(int coins[], int targ, String asf, int idx) {
        if (targ == 0 || idx >= coins.length) {
            if (targ == 0) {
                System.out.println(asf);
                return 1;
            }

            return 0;
        }

        int count = 0;

        if (targ - coins[idx] >= 0)
            count += InfinitePermutation_subseq(coins, targ - coins[idx], asf + coins[idx], 0);

        count += InfinitePermutation_subseq(coins, targ, asf, idx + 1);

        return count;
    }

    // interview bit subset q

    public static void subsetmaker(ArrayList<Integer> al, ArrayList<Integer> smallAns,
            ArrayList<ArrayList<Integer>> ans, int idx) {
        ArrayList<Integer> base = new ArrayList<>(smallAns);
        ans.add(base);
        if (idx >= al.size()) {

            return;
        }

        for (int i = idx; i < al.size(); i++) {
            smallAns.add(al.get(i));
            subsetmaker(al, smallAns, ans, i + 1);
            smallAns.remove(smallAns.size() - 1);
        }

    }

    public ArrayList<ArrayList<Integer>> subsets(ArrayList<Integer> A) {
        ArrayList<Integer> list = A;
        ArrayList<Integer> smallAns = new ArrayList<>();
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();

        Collections.sort(list);
        subsetmaker(list, smallAns, ans, 0);

        return ans;
    }

    // ------------------------------------IB________

    public static void main(String[] args) {
        int coins[] = { 2, 3, 5, 7 };
        // boolean visi[] = new boolean[coins.length];
        // System.out.println(finitePermutation_subseq(coins, 12, "", 0));
        // System.out.println(finitePermutations(coins,12,"",visi));
        // System.out.println(InfinitePermutation_subseq(coins, 12, "", 0));
        System.out.println(finiteCombination(coins, 10, "",0) );
    }

}