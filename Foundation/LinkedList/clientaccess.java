import java.util.LinkedList;

public class clientaccess{
    
    public static void main(String[] args) {
        linkedlist ll = new linkedlist();
        // System.out.println(ll.size());
        for( int i =1; i<=11; i++) ll.addLast(i);
        // System.out.println( ll.removelast());
        // ll.removeAt(4);
        ll.addAt(4, -2);
        ll.removeAt(4);
        System.out.print(ll.toString());
    }
}
