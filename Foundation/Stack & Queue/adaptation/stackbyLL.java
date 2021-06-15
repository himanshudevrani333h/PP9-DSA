
import java.util.LinkedList;
public class stackbyLL {
    
    public static class stack{
        LinkedList<Integer> st = new LinkedList<>();

        public int size(){
            return this.st.size();
        }

        public boolean isempty(){
         return this.st.isEmpty();
        }

        public void push(int data){
            this.st.addFirst(data);
        }

        public int top(){
            return this.st.getFirst();
        }

        public int pop(){
            return this.st.removeFirst();
        }
        
       
    }

    public static void main(String[] args) {
      stack st = new stack();
      st.push(10);
      st.push(20);
      st.push(30);
      st.push(40);
    System.out.println(st.top());
    System.out.println(st.pop());
        
    }
}
