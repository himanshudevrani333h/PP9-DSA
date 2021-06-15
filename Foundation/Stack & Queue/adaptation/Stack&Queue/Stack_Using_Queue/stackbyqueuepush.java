import java.util.LinkedList;

public class stackbyqueuepush{

    public static class stack{
        LinkedList<Integer> qu1 = new LinkedList<>();
        LinkedList<Integer> temp = new LinkedList<>();
        int peek = 0;
        public void push( int data){
            peek = data;
            qu1.addLast(data);
        }
        
        private void transfer(LinkedList<Integer> q1 , LinkedList<Integer> q2){
            while(q1.size() >1) q2.addLast(q1.removeFirst());
        }
        public int pop(){
             transfer(qu1, temp);
             int rem = qu1.removeFirst();

             while( temp.size() != 0) push(temp.removeFirst());
             return rem;
        }

        public int size(){
            return qu1.size();
        }

        public int peek(){
            return peek;
        }
    }

    public static void main(String[] args) {
        stack st = new stack();

        st.push(10);
        st.push(20);
        st.push(30);
        st.push(40);

        System.out.println(st.pop());
        System.out.println(st.peek());
    }

}