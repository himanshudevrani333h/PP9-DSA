import java.util.ArrayList;

public class generictree {

    public class Node {
        int data;
        ArrayList<Node> childs;

        Node(int data) {
            this.data = data;
            this.childs = new ArrayList<>();
        }

    }

    public int size(Node root) {

        int sz = 0;
        for (Node child : root.childs) {
            int size = size(child);
            sz += size;
        }

        return sz + 1;
    }

    public int maximum(Node root) {

        int max = -(int) 1e9;
        for (Node child : root.childs) {
            int mx = maximum(child);
            if (mx > max)
                max = mx;
        }

        return root.data > max ? root.data : max;
    }

    public int minimum(Node root) {
        int min = (int) 1e9;
        for (Node child : root.childs) {
            int mn = minimum(child);
            if (mn < min)
                min = mn;
        }

        return root.data < min ? root.data : min;
    }

    public int height(Node root) {
        int height = -1;
        for (Node child : root.childs) {
            int hg = height(child);
            if (hg > height)
                height = hg;
        }

        return height + 1;
    }

    public int sum(Node root) {
        int sum = 0;
        for (Node child : root.childs) {
            sum += sum(child);
        }

        return sum + root.data;
    }

    public boolean find(Node root, int data) {
        if (root.data == data)
            return true;
        for (Node child : root.childs) {
            if (find(child, data))
                return true;
        }

        return false;
    }

    public boolean find_2(Node root, int data) {
        if (root.data == data)
            return true;

        boolean flag = false;
        for (Node child : root.childs) {
            flag = flag || find_2(child, data);
        }

        return flag;
    }

    public static int countLeaves(Node root) {
        if (root.childs == null)
            return 1;

        int count = 0;
        for (Node child : root.childs) {

            count += countLeaves(child);

        }

        return count;

    }

    public boolean node_to_root(Node root, int data, ArrayList<Node> res) {

        if (root.data == data) {
            res.add(root);
            return true;
        }

        boolean flag = false;
        for (Node child : root.childs)
            flag = flag || node_to_root(child, data, res);

        if (flag)
            res.add(root);

        return flag;

    }

    public ArrayList<Integer> node_to_root_2(Node root, int data) {

        if (root.data == data) {
            ArrayList<Integer> base = new ArrayList<>();
            base.add(root.data);
            return base;
        }

        ArrayList<Integer> recAns = new ArrayList<>();
        for (Node child : root.childs) {
            recAns = node_to_root_2(child, data);
            if (recAns.size() != 0)
                break;
        }

        if (recAns.size() != 0) {
            recAns.add(root.data);
        }

        return recAns;
    }

    public int lca(Node node, int d1, int d2) {
        ArrayList<Integer> d1ntr = node_to_root_2(node, d1);
        ArrayList<Integer> d2ntr = node_to_root_2(node, d2);
        int potential_lca = -1;
        int i = d1ntr.size() - 1;
        int j = d2ntr.size() - 1;
        while (i >= 0 || j >= 0) {

            if (i >= 0 && j >= 0 && d1ntr.get(i) == d2ntr.get(j)) {
                potential_lca = d1ntr.get(i);
                i--;
                j--;
            } else {
                return potential_lca;
            }
        }

        return potential_lca;

    }

    public Node lca2(Node node, int d1, int d2) {
        ArrayList<Node> d1ntr = new ArrayList<>();
        node_to_root(node, d1, d1ntr);
        ArrayList<Node> d2ntr = new ArrayList<>();
        node_to_root(node, d2, d2ntr);
        Node potential_lca = null;
        int i = d1ntr.size() - 1;
        int j = d2ntr.size() - 1;
        while (i >= 0 || j >= 0) {

            if (i >= 0 && j >= 0 && d1ntr.get(i) == d2ntr.get(j)) {
                potential_lca = d1ntr.get(i);
                i--;
                j--;
            } else {
                return potential_lca;
            }
        }

        return potential_lca;

    }

    public int distanceBetweenNodes_1(Node node, int d1, int d2) {
        Node lca = lca2(node, d1, d2);
        ArrayList<Node> d1path = new ArrayList<>();
        node_to_root(lca, d1, d1path);
        ArrayList<Node> d2path = new ArrayList<>();
        node_to_root(lca, d2, d2path);

        int res = (d1path.size() + d2path.size()) - 2;
        return res;
    }

    public int distanceBetweenNodes_2(Node node, int d1, int d2) {
        ArrayList<Integer> d1ntr = node_to_root_2(node, d1);
        ArrayList<Integer> d2ntr = node_to_root_2(node, d2);

        int LCA = 0;
        int i = d1ntr.size() - 1;
        int j = d2ntr.size() - 1;

        while (i >= 0 && j >= 0 && d1ntr.get(i) == d2ntr.get(j)) {
            i--;
            j--;
            LCA++;
        }

        int res = d1ntr.size() + d2ntr.size() - (2 * LCA); // +1 answer anusaar
        return res;
    }

    public boolean areTreeSimilarinShape(Node root, Node root2) {
        if (root.childs.size() != root2.childs.size())
            return false;

        boolean res = true;

        for (int i = 0; i < root.childs.size(); i++)
            res = res && areTreeSimilarinShape(root.childs.get(i), root2.childs.get(i));
        return res;
    }

    public boolean isMirrorImage(Node root, Node root2) {

        if (root.childs.size() != root2.childs.size())
            return false;

        boolean res = true;

        for (int i = 0; i < root.childs.size(); i++) {
            int j = root2.childs.size() - i - 1;
            res = res && isMirrorImage(root.childs.get(i), root2.childs.get(j));

        }
        return res;
    }

    public boolean isSymmetric(Node root) {
        return isMirrorImage(root, root);
    }

    static int ceil;
    static int floor;

    public static void ceilAndFloor(Node node, int data) {

        // if( node.data < data){
        // floor = Math.max(node.data, floor);
        // }
        // if( node.data > data){
        // ceil = Math.min(node.data, ceil);
        // }

        if (node.data > data) {
            if (node.data < ceil) {
                ceil = node.data;
            }
        }

        if (node.data < data) {
            if (node.data > floor) {
                floor = node.data;
            }
        }
        for (Node child : node.childs) {
            ceilAndFloor(child, data);
        }
    }

    public int floor(Node node, int ub) {

        int mx = -(int) 1e9;

        for (Node child : node.childs) {
            int reAns = floor(child, ub);
            mx = Math.max(mx, reAns);
        }

        return node.data < ub ? Math.max(node.data, mx) : mx;

    }

    public int kthLargest(Node node, int k) {

        int max = (int) 1e9;

        while (k-- > 0) {
            max = floor(node, max);
        }

        return max;

    }
    
    public Node tail_Of_tree(Node node){
        while(node.childs.size() != 0){
            node = node.childs.get(0);
        }
        return node;
    }

    public void LinearizeGenTree(Node root){

        for( Node child: root.childs) LinearizeGenTree(child);

        for(int i = root.childs.size() - 1; i>0; i--){
            Node tail = tail_Of_tree(root.childs.get(i-1));
            tail.childs.add(root.childs.get(i)); 

            root.childs.remove(i);
        }
    }

}