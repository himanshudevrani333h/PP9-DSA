import java.util.HashMap;

public class LRUcache {
    class LRUCache {

        private class Node {
            int key, value;
            Node prev, next;

            Node(int key, int value) {
                this.key = key;
                this.value = value;
                this.prev = null;
                this.next = null;
            }
        }

        private HashMap<Integer, Node> map = new HashMap<>();
        private int maxSize = 0;
        private int size = 0;
        private Node head = null, tail = null;

        public LRUCache(int capacity) {
            this.maxSize = capacity;
        }

        public void addLast(Node node) {
            if (this.head ==  null)
                this.head = this.tail = node;
            else{
                tail.next = node;
                node.prev = tail;
                tail = tail.next;
            }
            this.size++;
        }

        public void remove(Node node) {
            Node fwd = node.next, prev = node.prev;
            if(this.size == 1) this.head = this.tail = null;
            else if(node == this.tail) {
                prev.next = node.prev = null;
                this.tail = prev;
            }else if( node == this.head){
                node.next = fwd.prev = null;
                this.head = fwd;
            }else{
                prev.next = fwd;
                fwd.prev = prev;
                node.prev= node.next = null;
            }
            this.size--;
        }

        public void makeRecent(Node node) {
          if( this.tail == node) return;
          remove(node);
          addLast(node);
        }

        public int get(int appName) {
            if (!map.containsKey(appName))
                return -1;

            Node node = map.get(appName);
            makeRecent(node);
            return node.value;
        }

        public void put(int appName, int state) {
            if (map.containsKey(appName)) {
                Node node = map.get(appName);
                node.value = state;
                makeRecent(node);
            } else {
                Node node = new Node(appName, state);
                if (this.size == this.maxSize) {
                    map.remove(this.head.key);
                    remove(this.head);
                }
                addLast(node);
                map.put(appName,node);
            }

        }
    }
}