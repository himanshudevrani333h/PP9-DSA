import java.util.ArrayList;

public class binarytree {
    public static class Node {
        int data;
        Node left;
        Node right;

        Node(int data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        Node(int data) {
            this(data, null, null);
        }
    }

    public static void preOrder(Node root, ArrayList<Integer> ans) {
        if (root == null)
            return;

        ans.add(root.data);
        preOrder(root.left, ans);
        preOrder(root.right, ans);
    }

    public static void inOrder(Node root, ArrayList<Integer> ans) {
        if (root == null)
            return;

        inOrder(root.left, ans);
        ans.add(root.data);
        inOrder(root.right, ans);
    }

    public static void postOrder(Node root, ArrayList<Integer> ans) {
        if (root == null)
            return;

        postOrder(root.left, ans);
        postOrder(root.right, ans);
        ans.add(root.data);
    }

    public static int size(Node node) {
        return node == null ? 0 : size(node.left) + size(node.right) + 1;
    }

    public static int sum(Node node) {
        return node == null ? 0 : sum(node.left) + sum(node.right) + node.data;
    }

    public static int max(Node node) {
        return node == null ? -(int) 1e9 : Math.max(Math.max(max(node.left), max(node.right)), node.data);
    }

    public static int min(Node node) {
        return node == null ? (int) 1e9 : Math.min(Math.min(min(node.left), min(node.right)), node.data);
    }

    public static int height(Node node) {
        return node == null ? 0 : Math.max(height(node.left), height(node.right)) + 1;
    }

    public static int countleafnode(Node node) {
        if (node == null) {
            return 0;
        }
        if (node.left == null && node.right == null) {
            return 1;
        }

        int left = countleafnode(node.left);
        int right = countleafnode(node.right);
        int count = right + left;

        return count;

    }

    // Print the nodes having exactly one child in a Binary tree
    public static void exactlyOneChild(Node node, ArrayList<Integer> ans) {
        if (node == null || (node.left == null && node.right == null))
            return;

        if (node.left == null || node.right == null)
            ans.add(node.data);

        exactlyOneChild(node.left, ans);
        exactlyOneChild(node.right, ans);

    }

    public static int countExactlyOneChild(Node node) {
        if (node == null || (node.left == null && node.right == null))
            return 0;

        int left = countExactlyOneChild(node.left);
        int right = countExactlyOneChild(node.right);
        int sum = left + right;
        if (node.left == null || node.right == null)
            sum += 1;
        return sum;
    }

    public static boolean find(Node node, int data) {

        if (node == null)
            return false;

        if (node.data == data)
            return true;

        return (find(node.left, data) || find(node.right, data));

    }

    public static boolean nodetorootbyFind(Node root, int data, ArrayList<Node> ans) {

        if (root == null)
            return false;
        if (root.data == data) {
            ans.add(root);
            return true;
        }

        boolean res = nodetorootbyFind(root.left, data, ans) || nodetorootbyFind(root.right, data, ans);
        if (res)
            ans.add(root);

        return res;
    }

    public static ArrayList<Node> nodetorootbyfind_2(Node root, int data) {
        ArrayList<Node> res = new ArrayList<>();
        nodetorootbyFind(root, data, res);
        return res;
    }

    public static ArrayList<Integer> nodetorootpath(Node root, int data) {
        if (root == null)
            return null;

        if (root.data == data) {
            ArrayList<Integer> bres = new ArrayList<>();
            bres.add(root.data);
            return bres;
        }

        ArrayList<Integer> left = nodetorootpath(root.left, data);
        if (left != null) {
            left.add(root.data);
            return left;
        }
        ArrayList<Integer> right = nodetorootpath(root.right, data);

        if (right != null) {
            right.add(root.data);
            return right;
        }

        return new ArrayList<>();
    }

    public static void klevelDown(Node root, int k) {

        if (root == null || k < 0)
            return;

        if (k == 0) {
            System.out.print(root.data);
            return;
        }

        klevelDown(root.left, k - 1);
        klevelDown(root.right, k - 1);
    }

    public static void KLevelsDown(Node root, int k, Node block, ArrayList<Integer> ans) { // by passing block element

        if (root == null || k < 0 || root == block)
            return;

        if (k == 0) {
            ans.add(root.data);
            return;
        }

        KLevelsDown(root.left, k - 1, block, ans);
        KLevelsDown(root.right, k - 1, block, ans);
    }

    public static ArrayList<Integer> KawayPath(Node root, int data, int k, ArrayList<Node> list) {
        ArrayList<Integer> ans = new ArrayList<>();
        Node block = null;
        for (int i = 0; i < list.size(); i++) {
            KLevelsDown(list.get(i), k - i, block, ans);
            block = list.get(i);
        }

        return ans;
    }

    public static void KawayPath(Node root, int data, int k) {
        ArrayList<Node> find = nodetorootbyfind_2(root, data);
        KawayPath(root, data, k, find);
    }

    public static int KawayPath2(Node node, int data, int k, ArrayList<Integer> ans) {
        if (node == null)
            return -1;

        if (node.data == data) {
            KLevelsDown(node, k, null, ans);
            return 1;
        }

        int ld = KawayPath2(node.left, data, k, ans);
        if (ld != -1) {
            KLevelsDown(node, k - ld, node.left, ans);
            return ld + 1;
        }

        int rd = KawayPath2(node.right, data, k, ans);
        if (rd != -1) {
            KLevelsDown(node, k - rd, node.right, ans);
            return rd + 1;
        }

        return -1;
    }

    public static Node removeLeafNode(Node root) {
        if (root == null)
            return null;
        if (root.left == null && root.right == null) {
            // root = null;
            return null;
        }

        root.left = removeLeafNode(root.left);
        root.right = removeLeafNode(root.right);
        return root;
    }

    static Node prev = null;

    public static boolean isBST(Node root) {

        if (root == null)
            return true;

        if (!isBST(root.left))
            return false;
        if (prev != null && prev.data > root.data)
            return false;
        prev = root;

        if (!isBST(root.right))
            return false;

        return true;
    }

    public static class isBSTpair {
        int minele = (int) 1e9;
        int maxele = -(int) 1e9;
        boolean isbst = true;
    }

    public static isBSTpair ISBST(Node root) {

        if (root == null) {
            return new isBSTpair();
        }

        isBSTpair left = ISBST(root.left);
        if (!left.isbst)
            return left;
        isBSTpair right = ISBST(root.right);
        if (!right.isbst)
            return right;

        isBSTpair self = new isBSTpair();
        self.isbst = false;
        if (left.minele < root.data && right.maxele > root.data) {
            self.minele = Math.min(left.minele, root.data);
            self.maxele = Math.max(right.maxele, root.data);
            self.isbst = true;
        }

        return self;
    }

    public static int height_2(Node root) {
        return root == null ? -1 : Math.max(height_2(root.left), height_2(root.right)) + 1;
    }

    public static boolean isBalanceTree(Node root) { // height of left - height of right node <= 1 && also >=-1;

        if (root == null)
            return true;

        if (!isBalanceTree(root.left))
            return false;
        int left_height = height_2(root);

        if (!isBalanceTree(root.right))
            return false;
        int right_height = height_2(root);

        if (Math.abs(left_height - right_height) <= 1)
            return true;

        return false;
    }

    public static class balnced_tree_pair {
        boolean isbalance = true;
        int height = -1;
    }

    public static balnced_tree_pair isBalanceTree_2(Node root) {

        if (root == null)
            return new balnced_tree_pair();

        balnced_tree_pair leftpair = isBalanceTree_2(root.left);
        if (!leftpair.isbalance)
            return leftpair;

        balnced_tree_pair rightpair = isBalanceTree_2(root.right);
        if (!rightpair.isbalance)
            return rightpair;

        balnced_tree_pair res = new balnced_tree_pair();
        if (Math.abs(leftpair.height - rightpair.height) > 1) {
            res.isbalance = false;
            return res;
        }

        res.height = Math.max(leftpair.height, rightpair.height);

        return res;
    }

    public static class tilt_pair {
        int sum = 0;
        int tiltSF = 0;
    }

    public static tilt_pair tiltOftree(Node root) {

        if (root == null)
            return new tilt_pair();

        tilt_pair left = tiltOftree(root.left);
        tilt_pair right = tiltOftree(root.right);

        tilt_pair res = new tilt_pair();
        res.tiltSF = left.tiltSF + right.tiltSF + Math.abs(left.sum - right.sum);
        res.sum = left.sum + right.sum;

        return res;
    }

    public static int[] tiltOftree_2(Node root) {

        if (root == null)
            return new int[2]; // idx 0 for tiltSF & idx 1 for sum

        int[] left = tiltOftree_2(root.left);
        int[] right = tiltOftree_2(root.right);

        int res[] = new int[2];

        res[0] = left[0] + right[0] + Math.abs(left[1] - right[1]);
        res[1] = left[1] + right[1] + root.data;

        return res;
    }

    public static class diaClass {
        int highest_length = -1;
        int diameter = 0;
    }

    public static diaClass diameterOftree(Node root) {
        if (root == null)
            return new diaClass();

        diaClass left = diameterOftree(root.left);
        diaClass right = diameterOftree(root.right);

        diaClass res = new diaClass();
        res.diameter = Math.max(Math.max(left.diameter, right.diameter),
                left.highest_length + right.highest_length + 2);
        res.highest_length = Math.max(left.highest_length, right.highest_length) + 1;
        return res;

    }

    public static int diameterOftree_2(Node root) {
        if (root == null)
            return 0;

        int leftdia = diameterOftree_2(root.left);
        int rightdia = diameterOftree_2(root.right);

        int left_height = height(root.left);
        int right_height = height(root.right);

        return Math.max(Math.max(leftdia, rightdia), left_height + right_height + 2);
    }

    public int[] diameterOfBinaryTree_02(Node root) {
        if (root == null)
            return new int[] { 0, -1 }; // {diameter,height}

        int[] ld = diameterOfBinaryTree_02(root.left);
        int[] rd = diameterOfBinaryTree_02(root.right);

        int[] myAns = new int[2];
        myAns[0] = Math.max(Math.max(ld[0], rd[0]), ld[1] + rd[1] + 2);
        myAns[1] = Math.max(ld[1], rd[1]) + 1;

        return myAns;
    }

    int diameter = 0;

    public int diameterOfBinaryTree_03(Node root) {
        if (root == null)
            return -1;

        int ld = diameterOfBinaryTree_03(root.left);
        int rd = diameterOfBinaryTree_03(root.right);

        diameter = Math.max(diameter, ld + rd + 2);
        return Math.max(ld, rd) + 1;
    }

    public static class isbstPair {
        boolean isbst = true;
        int minElement = (int) 1e9;
        int maxElement = -(int) 1e9;

        Node maxbstNode = null;
        int maxSize = 0;
    }

    public static isbstPair LargestBST(Node root) {

        if(root== null) return new isbstPair();

        isbstPair leftpair = LargestBST(root.left);
        isbstPair rightpair = LargestBST(root.right);

        isbstPair res = new isbstPair();
        if (leftpair.isbst && rightpair.isbst && leftpair.maxElement < root.data && rightpair.minElement > root.data) {
            res.isbst = true;
            res.minElement = Math.min(leftpair.minElement, root.data);
            res.maxElement = Math.max(rightpair.maxElement, root.data);

            res.maxSize = leftpair.maxSize + rightpair.maxSize + 1;
            res.maxbstNode = root;

        }else{
            res.isbst = false;
            res.maxSize= Math.max(leftpair.maxSize, rightpair.maxSize);
            res.maxbstNode = leftpair.maxSize >rightpair.maxSize ? leftpair.maxbstNode : rightpair.maxbstNode;
        }

        return res;
    }

}