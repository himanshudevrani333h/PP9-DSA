import java.util.LinkedList;

public class queuebyLL {
    public static class Queue{
        LinkedList<Integer> qu = new LinkedList<>();

        public int size(){
            return this.qu.size();
        }

        public boolean isEmpty(){
            return this.qu.isEmpty();
        }

        public void add(int data){
         this.qu.addLast(data);
        }

        public int remove(){
           return this.qu.removeFirst();
        }

        public int peek(){
            return this.qu.getFirst();
        }
    }

    public static void main(String[] args) {
        Queue qu = new Queue();
        qu.add(10);
        qu.add(20);
        qu.add(30);
        qu.add(40);
        qu.add(50);
        System.out.println(qu.remove());
    }
}
