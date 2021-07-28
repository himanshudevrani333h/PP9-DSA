public class NQueens {

    public static boolean isQueenSafe(boolean[][] box, int sr, int sc) {
        int n = box.length, m = box[0].length;
        // int[][] dir = { { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 } };
        int[][] dir = { { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 } };

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

    public static int Nqueens_Permutations01(boolean box[][], int tnq, String ans) {
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
                count += Nqueens_Permutations01(box, tnq - 1, ans + "(" + r + "," + c + ") ");
                box[r][c] = false;
            }

        }

        return count;
    }

    // optimizing isSafe function
    static boolean[] row, col, diag, antidaig; // branch and bound (shadow clone)

    public static int Nqueens_Combination02(int n, int m, int bno, int tnq, String ans) {
        if (tnq == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int b = bno; b < n * m; b++) {

            int r = b / m;
            int c = b % m;

            if (!row[r] && !col[c] && !diag[r + c] && !antidaig[r - c + m - 1]) {

                row[r] = col[c] = diag[r + c] = antidaig[r - c + m - 1] = true;
                count += Nqueens_Combination02(n, m, b + 1, tnq - 1, ans + "(" + r + "," + c + ") ");
                row[r] = col[c] = diag[r + c] = antidaig[r - c + m - 1] = false;

            }
        }

        return count;
    }

    public static int Nqueens_Permutation02(int n, int m, int tnq, String ans) {
        if (tnq == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;

        for (int b = 0; b < n * m; b++) {

            int r = b / m;
            int c = b % m;

            if (!row[r] && !col[c] && !diag[r + c] && !antidaig[r - c + m - 1]) {

                row[r] = col[c] = diag[r + c] = antidaig[r - c + m - 1] = true;
                count += Nqueens_Permutation02(n, m, tnq - 1, ans + "(" + r + "," + c + ") ");
                row[r] = col[c] = diag[r + c] = antidaig[r - c + m - 1] = false;

            }
        }
        return count;
    }

    public static int Nqueens_Combination02_BreakAtone(int n, int m, int bno, int tnq, String ans) {
        if (tnq == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int b = bno; b < n * m; b++) {

            int r = b / m;
            int c = b % m;

            if (!row[r] && !col[c] && !diag[r + c] && !antidaig[r - c + m - 1]) {

                row[r] = col[c] = diag[r + c] = antidaig[r - c + m - 1] = true;
                if (count < 1) {
                    count += Nqueens_Combination02_BreakAtone(n, m, b + 1, tnq - 1, ans + "(" + r + "," + c + ") ");
                    row[r] = col[c] = diag[r + c] = antidaig[r - c + m - 1] = false;
                }

            }
        }

        return count;
    }

    // Floor And Rooms method for less calls

    public static int Nqueens_Combination03(int n, int m, int floor, int tnq, String ans) {
        if (tnq == 0) {

            System.out.println(ans);
            return 1;

        }

        int count = 0;
        for (int rooms = 0; rooms < m; rooms++) {
            int r = floor;
            int c = rooms;

            if (!col[c] && !diag[r + c] && !antidaig[r - c + m - 1]) {
                col[c] = diag[r + c] = antidaig[r - c + m - 1] = true;
                count += Nqueens_Combination03(n, m, floor + 1, tnq - 1, ans + "(" + r + "," + c + ") ");
                col[c] = diag[r + c] = antidaig[r - c + m - 1] = false;

            }
        }

        return count;
    }

    public static int Nqueens_Permutaions03(int n, int m, int floor, int tnq, String ans) {
        if (tnq == 0 || floor >= m) {
            if (tnq == 0) {
                System.out.println(ans);
                return 1;
            }
            return 0;

        }

        int count = 0;
        for (int rooms = 0; rooms < m; rooms++) {
            int r = floor;
            int c = rooms;

            if (!row[r] && !col[c] && !diag[r + c] && !antidaig[r - c + m - 1]) {
                row[r] = col[c] = diag[r + c] = antidaig[r - c + m - 1] = true;
                count += Nqueens_Permutaions03(n, m, 0, tnq - 1, ans + "(" + r + "," + c + ") ");
                row[r] = col[c] = diag[r + c] = antidaig[r - c + m - 1] = false;

            }
        }
        count += Nqueens_Permutaions03(n, m, floor + 1, tnq, ans);
        return count;
    }

    public static void nqueen() {
        int n = 4, tnq = 4;
        int m = 4;
        boolean[][] box = new boolean[n][n];
        row = new boolean[n];
        col = new boolean[m];
        diag = new boolean[n + m - 1];
        antidaig = new boolean[n + m - 1];
        // System.out.println(Nqueens_Combination01(box, 0, tnq, ""));
        // System.out.println(Nqueens_Permutations01(box, tnq, ""));
        // System.out.println(Nqueens_Permutation02(n, m, tnq, ""));
        // System.out.println(Nqueens_Combination02_BreakAtone(n, m, 0, tnq, ""));
        System.out.println(Nqueens_Permutaions03(n, m, 0, 4, ""));
    }

    public static void main(String[] args) {
        nqueen();

    }
}
