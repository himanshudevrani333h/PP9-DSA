import java.util.ArrayList;
import java.util.LinkedList;

class hashmap {

    public class Node {
        Integer key = null;
        Integer value = null;

        Node(Integer key, Integer value) {
            this.key = key;
            this.value = value;
        }
    }

    private LinkedList<Node>[] Buckets;
    private int bucketlen = 0;
    private int TotalNoOfNodes = 0;

    // Constructor-----------------

    private void initialize(int size) {
        bucketlen = size;
        Buckets = new LinkedList[size];

        for (int i = 0; i < size; i++)
            Buckets[i] = new LinkedList<Node>();
        TotalNoOfNodes = 0;
    }

    public hashmap() {
        initialize(10);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        int tempSize = this.TotalNoOfNodes;
        for (int i = 0; i < this.bucketlen; i++) {
            LinkedList<Node> group = this.Buckets[i];
            int size = group.size();
            while (size-- > 0) {

                Node node = group.removeFirst();
                sb.append(node.key + "=" + node.value);
                group.addLast(node);

                if (--tempSize != 0)
                    sb.append(",");
            }
        }
        sb.append("}");
        return sb.toString();
    }
  

    // Normal functions

    public int size() {
        return bucketlen;
    }

    public boolean isEmpty() {
        return bucketlen == 0;
    }

    // DS functions--------------------------
    public ArrayList<Integer> keySet() {
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i < this.bucketlen; i++) {
            LinkedList<Node> group = this.Buckets[i];
            int size = group.size();
            while (size-- > 0) {
                Node node = group.removeFirst();
                ans.add(node.key);
                group.addLast(node);
            }
        }

        return ans;
    }

    private void rehash() {
        LinkedList<Node>[] temp = this.Buckets;
        initialize((int) (this.bucketlen * 2)); //  1 <= factor <= 2 

        for (int i = 0; i < temp.length; i++) {
            LinkedList<Node> group = temp[i];
            int size = group.size();
            while (size-- > 0) {
                Node node = group.removeFirst();
                put(node.key, node.value);
            }
        }
    }

    public void put(Integer key, Integer value) {
        // int hcOfKey = getHashCode(key);
        // Node newNode = new Node(key, value);

        // LinkedList<Node> getlist = Buckets[hcOfKey];
        // getlist.addLast(newNode);
        // TotalNoOfNodes++;
        // double lambda = getlist.size() / (1.0 * this.bucketlen);

        // if (lambda > 0.4)
        //     rehash();

        boolean res = containsKey(key);
        LinkedList<Node> group = getGroup(key);

        if (res) {
            group.getFirst().value = value;
        } else {
            Node node = new Node(key, value);
            group.addLast(node);
            this.TotalNoOfNodes++;
            double lambda = group.size() / (1.0 * this.bucketlen);

            if (lambda > 0.4)
                rehash();
        }
    }

    public void putIfAbsent(Integer key, Integer defaultValue) {
        boolean res = containsKey(key);
        LinkedList<Node> group = getGroup(key);
        if (!res) {
            Node node = new Node(key, defaultValue);
            group.addLast(node);
            this.TotalNoOfNodes++;
        }
    }

    public int getOrdefault(Integer key, int defaultValue) throws Exception {
       
        // LinkedList<Node> group = getGroup(key);
        // int gs = group.size();

        // while (gs-- > 0) {
        //     if (group.get(0).value != null)
        //         return group.get(0).value;

        //     group.addLast(group.removeFirst());
        // }

        // return defaultval;

        Integer val = get(key);
        return val != null ? val : defaultValue;

    }

    public Integer get(Integer key) throws Exception{
   
        // LinkedList<Node> group = getGroup(key);
        // int gs = group.size();

        // while (gs-- > 0) {
        //     if (group.get(0).key == key)
        //         return group.get(0).value;

        //     group.addLast(group.removeFirst());
        // }
        // return null;

        boolean res = containsKey(key);
        LinkedList<Node> group = getGroup(key);
        return res ? group.getFirst().value : null;
    }

    public Integer remove(Integer key) throws Exception {
        
        // LinkedList<Node> group = getGroup(key);
        // int gs = group.size();
        // Integer ans = null;
        // while (gs-- > 0) {
        //     if (group.get(0).key == key) {
        //          ans =group.get(0).value;
        //         group.removeFirst();

        //         TotalNoOfNodes--;
        //     }
        //     group.addLast(group.removeFirst());
        // }
        // return ans;

        boolean res = containsKey(key);
        LinkedList<Node> group = getGroup(key);

        if (res) {
            this.TotalNoOfNodes--;
            return group.removeFirst().key;
        }
        return null;
    }

    public boolean containsKey(Integer key) {
        LinkedList<Node> group = getGroup(key);
        int gs = group.size();

        while (gs-- > 0) {
            if (group.getFirst().key == key)
                return true;
            group.addLast(group.removeFirst());
        }

        return false;
    }

    private LinkedList<Node> getGroup(Integer key) {
        int hc = getHashCode(key);
        return Buckets[hc];
    }

    private int getHashCode(Integer key) {
        return Math.abs(key.hashCode()) % bucketlen;
    }
}