import java.util.LinkedList;

public class queuebystackadd {

    public static class queue {
        LinkedList<Integer> st = new LinkedList<>();
        LinkedList<Integer> temp = new LinkedList<>();
        int peek = 0;

        public void add(int data) {
            if (this.st.size() == 0)
                peek = data;
            this.st.addFirst(data);
        }

        private void transfer(LinkedList<Integer> st1, LinkedList<Integer> st2) {
            while (st1.size() != 0)
                st2.addFirst(st1.removeFirst());
        }

        public int remove() {
            transfer(st, temp);
            int rem = temp.removeFirst();
            while (temp.size() != 0)
                add(temp.removeFirst());

            return rem;
        }

        public int size() {
            return this.st.size();
        }

        public int peek() {
            return peek;
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