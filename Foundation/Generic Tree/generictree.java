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

    public boolean node_to_root(Node root, int data, ArrayList<Integer> res) {

        if (root.data == data) {
            res.add(root.data);
            return true;
        }

        boolean flag = false;
        for (Node child : root.childs)
            flag = flag || node_to_root(child, data, res);

        if (flag)
            res.add(root.data);

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

    public  int lca(Node node, int d1, int d2) {
        ArrayList<Integer> d1ntr = node_to_root_2(node, d1);
        ArrayList<Integer> d2ntr = node_to_root_2(node , d2);
         int potential_lca = -1;
         int i = d1ntr.size() -1;
         int j = d2ntr.size() -1;
         while(i >=0 || j>=0 ){
             
             if(i>=0 && j>=0  && d1ntr.get(i) == d2ntr.get(j)){
                potential_lca = d1ntr.get(i);
                i--;
                j--;
             }else{
                 return potential_lca;
             }
         }
         
         return potential_lca;
        
      }

}