import java.util.ArrayList;
import java.util.Collections;

public class BST {

    public class Node {
        Node left = null;
        Node right = null;
        int data = 0;

        Node(int data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

    }

    public int size(Node root) {
        return root == null ? 0 : size(root.left) + size(root.right) + 1;
    }

    public int maximun(Node root) {
        if (root == null)
            return 0;

        while (root.right != null) {
            root = root.right;
        }

        return root.data;
    }

    public int minimum(Node root) {
        if (root == null)
            return 0;

        while (root.left != null) {
            root = root.left;
        }

        return root.data;
    }

    public int sum(Node root) {
        return root == null ? 0 : sum(root.left) + sum(root.right) + root.data;
    }

    public boolean find(Node root, int data) {
        if (root == null)
            return false;
        while (root != null) {
            if (root.data == data) {
                return true;
            } else if (root.data < data) {
                root = root.right;
            } else {
                root = root.left;
            }
        }
        return false;
    }

    public ArrayList<Node> Node_To_root_path(Node root, int data) {
        if (root == null)
            return new ArrayList<>();

        ArrayList<Node> res = new ArrayList<>();

        while (root.data != data || root != null) {
            res.add(root);
            if (root.data < data) {
                root = root.right;
            } else {
                root = root.left;
            }
        }
        res.add(root);
        if (root == null) {
            res.clear();
        }
        Collections.reverse(res);
        return res;

    }

    public int LCA(Node node, int d1, int d2) {
        if (node == null)
            return 0;

        while (node != null) {

            if (d1 > node.data && d2 > node.data) {
                node = node.right;
            } else if (node.data > d1 && node.data > d2) {
                node = node.left;
            } else {
                return node.data;
            }
        }

        return -1;
    }

    public ArrayList<Integer> pir(Node root, int d1, int d2) {

        if (root == null)
            return new ArrayList<>();

        ArrayList<Integer> res = new ArrayList<>();
        if (d1 > root.data && d2 > root.data) {
            pir(root.right, d1, d2);
        } else if (d1 < root.data && d2 < root.data) {
            pir(root.left, d1, d2);
        } else {
            pir(root.left, d1, d2);
            res.add(root.data);
            pir(root.right, d1, d2);
        }
        return res;
    }

    public ArrayList<Integer> pir_2(Node root, int d1, int d2) {

        if (root == null)
            return new ArrayList<>();

        ArrayList<Integer> res = new ArrayList<>();

        pir(root.left, d1, d2);
        if (root.data >= d1 && root.data <= d2)
            res.add(root.data);
        pir(root.right, d1, d2);

        return res;
    }

    public Node add(Node node, int data) {
        if (node == null)
            return new Node(data, null, null);

        if (node.data > data) {
            node.left = add(node.left, data);
        } else if (node.data <= data) {
            node.right = add(node.right, data);
        }

        return node;
    }

    public Node deleteNode(Node root, int key) {

        if (root == null)
            return null;

        if (root.data > key) {
            root.left = deleteNode(root.left, key);
        } else if (root.data < key) {
            root.right = deleteNode(root.right, key);
        } else {
            if (root.left == null || root.right == null)
                return root.left == null ? root.right : root.left;

            int minElement = minimum(root.right);
            root.data = minElement;

            root.right = deleteNode(root.right, minElement);

        }

        return root;
    }
    // Add all greater values to every node in a BST ~ GFG

    public void modify(Node root, int arr[]) {
        if (root == null)
            return;

        modify(root.right, arr);
        root.data += arr[0];
        arr[0] = root.data;

        modify(root.left, arr);
    }

    public Node modify(Node root) {
        int arr[] = new int[1];
        modify(root, arr);
        return root;

    }

    public static void rwsol(Node node, int arr[]) {
        if (node == null)
            return;

        rwsol(node.right, arr);

        int temp = node.data;
        node.data = arr[0];
        arr[0] += temp;

        rwsol(node.left, arr);

    }

    public static void rwsol(Node node) {
        int arr[] = new int[1];
        rwsol(node, arr);
        // display(node);
    }

}