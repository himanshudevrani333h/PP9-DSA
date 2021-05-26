public class linkedlist {

    public class Node {
        int data = 0;
        Node next = null;

        Node(int data) {
            this.data = data;
        }
    }

    private Node head = null;
    private Node tail = null;
    private int size = 0;

    public boolean isEmpty() {
        if (this.size > 0)
            return false;
        else
            return true;
    }

    public int size() {
        return this.size;
    }

    private Node getFirstNode() {
        return this.head;
    }

    public int getFirst() {
        if (this.size == 0)
            return -1;
        else
            return getFirstNode().data;
    }

    private Node getLastNode() {
        return this.tail;
    }

    public int getLast() {
        if (this.size == 0)
            return -1;
        else
            return getLastNode().data;
    }

    public void display() {
        Node curr = this.head;

        if (this.size == 0) {
            System.out.println("No element found");
            return;
        }
        while (curr != null) {
            System.out.print(curr.data + ", ");
            curr = curr.next;
        }
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append("[");
        Node curr = this.head;
        while (curr != null) {
            sb.append(curr.data);
            if (curr.next != null) {
                sb.append(", ");
            }

            curr = curr.next;
        }
        sb.append("]");
        return sb.toString();
    }

    private Node getAtNode(int idx) {
        Node curr = this.head;
        while (idx-- > 0) {
            curr = curr.next;
        }
        return curr;
    }

    public int getAt(int idx) {
        if (idx < 0 || idx >= this.size)
            return -1;
        else
            return getAtNode(idx).data;
    }

    // -----------Remove------------//
    private Node removeFirstNode() {
        Node node = this.head;
        if (this.size == 1)
            this.head = this.tail = null;
        else {
            this.head = this.head.next;
            node.next = null;
        }

        return node;
    }

    public int removefirst() {
        if (this.size == 0)
            return -1;
        else
            return removeFirstNode().data;
    }

    private Node removeLast() {
        Node node = this.tail;
        if (this.size == 1)
            this.head = this.tail = null;
        else {
            Node secondlast = getAtNode(this.size - 2);
            secondlast.next = null;
            this.tail = secondlast;
        }

        return node;
    }

    public int removelast() {
        if (this.size == 0)
            return -1;
        else
            return removeLast().data;
    }

    private Node removeAtNode(int idx) {
        if (idx == 0)
            return removeFirstNode();
        else if (idx == this.size - 1)
            return removeLast();
        else {
            Node prevNode = getAtNode(idx - 1);
            Node node = prevNode.next;
            Node forwNode = node.next;

            node.next = null;
            prevNode.next = forwNode;
            this.size--;

            return node;
        }
    }

    public void removeAt(int idx) {

        if (idx < 0 || idx >= this.size)
            return;
        else
            removeAtNode(idx);
    }

    // -------------------ADD----------------------//

    private void addfirstNode(Node node) {
        if (this.size == 0) {
            this.head = this.tail = node;
        } else {
            node.next = this.head;
            this.head = node;
        }
        this.size++;
    }

    public void addFirst(int data) {
        Node node = new Node(data);
        addfirstNode(node);
    }

    private void addLastNode(Node node) {
        if (this.size == 0) {
            this.head = this.tail = node;
        } else {
            this.tail.next = node;
            this.tail = node;
        }
        this.size++;
    }

    public void addLast(int data) {
        Node node = new Node(data);
        addLastNode(node);
    }

    private void addAtnode(int idx, int data) {
        Node datanode = new Node(data);
        if (this.size == 0)
            addFirst(data);
        else if (idx == this.size)
            addLast(data);
        else {
            Node prevousnode = getAtNode(idx - 1);
            Node forward = prevousnode.next;
            prevousnode.next = datanode;
            datanode.next = forward;
            this.size++;
        }
    }

    public void addAt(int idx, int data) {
        if (idx < 0 || idx > this.size)
            return;
        else
            addAtnode(idx, data);
    }
    
    // ------------------------------------------------------------------------//

    public void oddEven(){
        Node even = new Node(-1);
        Node ep = even;
        Node odd = new Node(-1);
        Node op = odd;
        Node curr = head;
        while( curr != null)
        {
            if( curr.data %2 == 0)
            {
                ep.next = curr;
                ep = ep.next;
            }else{
                op.next = curr;
                op= op.next;
            }
            
            curr = curr.next;
        }
        
        op.next= even.next;
        ep.next = null;
        this.head = odd.next;
        if( even.next != null) this.tail = ep;
        else
          this.tail = op;
      }

}
