
public class queens {

    // Queen_Set.=========================================================================================

    // tnb : total no boxes, bno : box no, tnq : total No queen, qpsf : queen placed
    // so far
    public static int queenCombination1D(int tnb, int bno, int tnq, int qpsf, String asf) {
        if (qpsf > tnq) {
            System.out.println(asf);
            return 1;
        }

        int count = 0;
        for (int b = bno; b <= tnb; b++) {
            count += queenCombination1D(tnb, b + 1, tnq, qpsf + 1, asf + "b" + b + "q" + qpsf + " ");
        }
        return count;
    }

    public static int queenCombination1D_sub(int tnb, int bno, int tnq, int qpsf, String asf) {
        if (qpsf > tnq || bno > tnb) {
            if (qpsf > tnq) {
                System.out.println(asf);
                return 1;
            }
            return 0;
        }

        int count = 0;
        count += queenCombination1D_sub(tnb, bno + 1, tnq, qpsf + 1, asf + "b" + bno + "q" + qpsf + " ");
        count += queenCombination1D_sub(tnb, bno + 1, tnq, qpsf, asf);

        return count;
    }

    public static int queenPermutation1D(int tnb, int tnq, int qpsf, boolean[] vis, String asf) {
        if (qpsf > tnq) {
            System.out.println(asf);
            return 1;
        }

        int count = 0;
        for (int b = 1; b <= tnb; b++) {
            if (!vis[b]) {
                vis[b] = true;
                count += queenPermutation1D(tnb, tnq, qpsf + 1, vis, asf + "b" + b + "q" + qpsf + " ");
                vis[b] = false;
            }
        }
        return count;
    }

    public static int queenPermutation1D_sub(int tnb, int bno, int tnq, int qpsf, boolean[] vis, String asf) {
        if (qpsf > tnq || bno > tnb) {
            if (qpsf > tnq) {
                System.out.println(asf);
                return 1;
            }
            return 0;
        }

        int count = 0;

        if (!vis[bno]) {
            vis[bno] = true;
            count += queenPermutation1D_sub(tnb, 1, tnq, qpsf + 1, vis, asf + "b" + bno + "q" + qpsf + " ");
            vis[bno] = false;
        }
        count += queenPermutation1D_sub(tnb, bno + 1, tnq, qpsf, vis, asf);
        return count;
    }

    // 2QueenSet.=============================================================================

    // Queen_Set.=========================================================================================

    // tnb : total no boxes, bno : box no, tnq : total No queen, qpsf : queen placed
    // so far
    public static int queenCombination2D(boolean[][] box, int bno, int tnq, String asf) {
        if (tnq == 0) {
            System.out.println(asf);
            return 1;
        }

        int count = 0;
        int n = box.length, m = box[0].length;
        for (int b = bno; b < n * m; b++) {
            int r = b / m;
            int c = b % m;
            count += queenCombination2D(box, b + 1, tnq - 1, asf + "(" + r + "," + c + ") ");
        }
        return count;
    }

    public static int queenCombination2D_sub(boolean[][] box, int bno, int tnq, String asf) {
        if (tnq == 0 || bno >= box.length * box[0].length) {
            if (tnq == 0) {
                System.out.println(asf);
                return 1;
            }
            return 0;
        }

        int count = 0;
        int m = box[0].length;

        int r = bno / m;
        int c = bno % m;
        count += queenCombination2D_sub(box, bno + 1, tnq - 1, asf + "(" + r + "," + c + ") ");
        count += queenCombination2D_sub(box, bno + 1, tnq, asf);

        return count;
    }

    public static int queenPermutation2D(boolean[][] box, int tnq, String asf) {
        if (tnq == 0) {
            System.out.println(asf);
            return 1;
        }

        int count = 0;
        int n = box.length, m = box[0].length;
        for (int b = 0; b < n * m; b++) {
            int r = b / m;
            int c = b % m;
            if (!box[r][c]) {
                box[r][c] = true;
                count += queenPermutation2D(box, tnq - 1, asf + "(" + r + "," + c + ") ");
                box[r][c] = false;
            }
        }
        return count;
    }

    public static int queenPermutation2D_sub(boolean[][] box, int bno, int tnq, String asf) {
        if (tnq == 0 || bno >= box.length * box[0].length) {
            if (tnq == 0) {
                System.out.println(asf);
                return 1;
            }
            return 0;
        }

        int count = 0;
        int m = box[0].length;

        int r = bno / m;
        int c = bno % m;
        if (!box[r][c]) {
            box[r][c] = true;
            count += queenPermutation2D_sub(box, 0, tnq - 1, asf + "(" + r + "," + c + ") ");
            box[r][c] = false;
        }
        count += queenPermutation2D_sub(box, bno + 1, tnq, asf);

        return count;
    }

    public static void queen() {
        int tnb = 5, tnq = 3;
        boolean[] vis = new boolean[tnb + 1];
        // System.out.println(queenCombination1D(tnb, 1, tnq, 1, ""));
        // System.out.println(queenPermutation1D(tnb, tnq, 1,vis, ""));
    }

    public static void queen2D() {
        int tnq = 4;
        boolean[][] box = new boolean[4][4];
        // System.out.println(queenCombination2D_sub(box, 0, tnq, ""));
        // System.out.println(queenPermutation2D_sub(box, 0, tnq, ""));
        System.out.println(queenPermutation2D(box, tnq, ""));

        // System.out.println(queenPermutation1D_sub(tnb, 1, tnq, 1, vis, ""));
    }

    public static void main(String[] args) {
        // queen();
        queen2D();
    }
}
