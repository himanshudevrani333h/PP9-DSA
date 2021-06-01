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
    public void deletegivennode(Node node){
        Node temp = node.next;
        node.data = temp.data;
        node.next = temp.next;
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
    

    public  Node addTwoLinkedList(Node l1, Node l2){
     
        linkedlist res = new linkedlist();
        Node dummy = new Node(-1);
        Node ll1 = reversell(l1);
        Node ll2 = reversell(l2);
        int carry = 0;
        Node prev = dummy;
        while( ll1 != null || ll2 != null || carry !=0 ){
           
            int sum = carry + (ll1 != null? ll1.data:0) +(ll2 != null? ll2.data:0);
            carry = sum /10;
            sum = sum %10;

            prev.next = new Node(sum);
            prev = dummy.next;
            
            if(ll1 != null) ll1 = ll1.next;
            if(ll2 != null) ll2 = ll2.next;
        }

        Node res2 = dummy.next;
        res2 = reversell(res2);
        // res.head = res2;
        return res2;
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

      public Node reversell(Node head){
          if(head == null || head.next == null) return head;

          Node prev = null;
          Node curr = head;

          while( curr != null){
              Node fwd = curr.next;
              curr.next = prev;
              prev = curr;

              curr = fwd;
          }

          return prev;
      }

      public void reversePI() {
        if (this.head == null || this.head.next == null)
            return;

        Node prev = null;
        Node curr = this.head;
        while (curr != null) {
            Node forw = curr.next; // backup

            curr.next = prev; // link

            prev = curr; // move
            curr = forw;
        }

        this.tail = this.head;
        this.head = prev;
    }

    private Node reversePRHelperbynode(Node node) {
        if (node.next == null)
            return node;

        Node reverseNode = reversePRHelperbynode(node.next);
        reverseNode.next = node;

        return node;
    }

    public void reversePR1() {

        Node reverseNode = reversePRHelperbynode(head);
        reverseNode.next = null;
        head = tail;
        tail = reverseNode;
    }

    private void reversePRHelper(Node node) {
        if (node.next == null)
            return;

        reversePRHelper(node.next);
        Node forw = node.next;
        forw.next = node;
    }

    public void reversePR() {

        reversePRHelper(head);
        head.next = null;
        Node temp = head;
        head = tail;
        tail = temp;
    }

    private void displayReverseHelper(Node node) {
        if (node == null)
            return;

        displayReverseHelper(node.next);
        System.out.print(node.data + " ");
    }

    public void displayReverse() {
        displayReverseHelper(head);
        System.out.println();
    }

    public Node midNode(Node node) {
        if (node == null || node.next == null)
            return node;
        Node slow = node, fast = node;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    public Node reverse(Node node) {
        if (node == null || node.next == null)
            return node;

        Node curr = node, prev = null;
        while (curr != null) {
            Node forw = curr.next;

            curr.next = prev;

            prev = curr;
            curr = forw;
        }

        return prev;
    }

    public void fold() {
        Node mid = midNode(head);
        Node nhead = mid.next;
        mid.next = null;

        nhead = reverse(nhead);

        Node c1 = head, c2 = nhead;
        while (c2 != null) {
            Node f1 = c1.next, f2 = c2.next;

            c1.next = c2;
            c2.next = f1;

            c1 = f1;
            c2 = f2;
        }

        if (size() % 2 != 0)
            tail = mid;
        else
            tail = mid.next;
    }

}
