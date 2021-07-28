public class NQueens {

    public static boolean isQueenSafe(boolean[][] box, int sr, int sc) {
        int n = box.length, m = box[0].length;
        // int[][] dir = { { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 } };
        int[][] dir = { { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 },  { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 } };

        for (int d = 0; d < dir.length; d++) {
            for (int rad = 1; rad <= n; rad++) {
                int row = sr + rad * dir[d][0];
                int col = sc + rad * dir[d][1];

                if (row >= 0 && col >= 0 && row < n && col < m) {
                    if (box[row][col])
                        return false;
                } else
                    break;
            }
        }

        return true;
    }

    public static int Nqueens_Combination01(boolean box[][], int bno, int tnq, String ans) {
        if (tnq == 0) {
            System.out.println(ans);
            return 1;
        }
        int count = 0;
        int n = box.length, m = box[0].length;
        for (int b = bno; b < n * m; b++) {
            int r = b / m;
            int c = b % m;
            if (isQueenSafe(box, r, c)) {
                box[r][c] = true;
                count += Nqueens_Combination01(box, b + 1, tnq - 1, ans + "(" + r + "," + c + ") ");
                box[r][c] = false;
            }

        }

        return count;
    }

    public static int Nqueens_Permutations01(boolean box[][], int bno, int tnq, String ans) {
        if (tnq == 0) {
            System.out.println(ans);
            return 1;
        }
        int count = 0;
        int n = box.length, m = box[0].length;
        for (int b = 0; b < n * m; b++) {
            int r = b / m;
            int c = b % m;
            if (!box[r][c] && isQueenSafe(box, r, c)) {
                box[r][c] = true;
                count += Nqueens_Permutations01(box, b + 1, tnq - 1, ans + "(" + r + "," + c + ") ");
                box[r][c] = false;
            }

        }

        return count;
    }


    public static void nqueen() {
        int n = 4, tnq = 4;
        boolean[][] box = new boolean[n][n];
        System.out.println(Nqueens_Permutations01(box, 0, tnq, ""));
    }

    public static void main(String[] args) {
        nqueen();

    }
}
