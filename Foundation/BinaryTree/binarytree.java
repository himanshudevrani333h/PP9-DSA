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


    public static class isBSTpair{
        int minele = (int)1e9;
        int maxele = -(int)1e9;
        boolean isbst = true;
    }
    

    public static isBSTpair ISBST(Node root){
       
        if(root == null){
            return new isBSTpair();
        }
       
       isBSTpair left = ISBST(root.left);
       if(!left.isbst) return left;
       isBSTpair right = ISBST(root.right);
       if(!right.isbst) return right;

       isBSTpair self = new isBSTpair();
       self.isbst = false;
       if(left.minele < root.data && right.maxele > root.data){
           self.minele = Math.min(left.minele, root.data);
           self.maxele = Math.max(right.maxele, root.data);
           self.isbst = true;
       }

       return self;
    }


}