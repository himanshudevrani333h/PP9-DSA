import java.util.LinkedList;

public class stackusingqueuepop {

    public static class stack{
        LinkedList<Integer> qu = new LinkedList<>();
        LinkedList<Integer> temp = new LinkedList<>();
        
        private void transfer(LinkedList<Integer> q1, LinkedList<Integer> q2){
            while( q1.size() !=0) q2.addLast(q1.removeFirst());
        }
        public void push(int data){
         temp.addLast(data);
         transfer(qu, temp);

         LinkedList<Integer> temp2 = qu;
         qu = temp;
         temp = temp2;
        }

        public int pop(){
            return qu.removeFirst();
        }

        public int peek(){
            return qu.getFirst();
        }
    }
   
    public static void main(String[] args) {
        stack st = new stack();
        st.push(10);
        st.push(20);
        st.push(30);
        st.push(40);
        st.push(50);
        System.out.println(st.pop());
        System.out.println(st.peek());
    }

}
