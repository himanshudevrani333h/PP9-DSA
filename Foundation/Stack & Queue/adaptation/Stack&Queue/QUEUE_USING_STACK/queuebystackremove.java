import java.util.LinkedList;

public class queuebystackremove {
    
    public static class queue{
        LinkedList<Integer> st = new LinkedList<>();
        LinkedList<Integer> temp = new LinkedList<>();
        

        private void transfer(LinkedList<Integer> st1, LinkedList<Integer>st2){
            while(st1.size() != 0) st2.addFirst(st1.removeFirst());
        }
        public void add(int data){
         transfer(st, temp);
         st.addFirst(data);
         transfer(temp, st);
        }

        public int remove(){
            return st.removeFirst();
        }

        public int peek(){
            return st.getFirst();
        }
    }

    public static void main(String[] args) {
        queue qu = new queue();

        qu.add(10);
        qu.add(20);
        qu.add(30);
        qu.add(40);

        System.out.println(qu.remove());
        System.out.println(qu.peek());
    }
}
