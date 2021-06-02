public class Queue {

    private int arr[];
    private int front;
    private int rear;
    private int size;
    private int maxsize;

    private void initialize(int len) {
        this.arr = new int[len];
        this.front = 0;
        this.rear = 0;
        this.size = 0;
        this.maxsize = len;
    }

    public Queue() {
        initialize(5);
    }

    public Queue(int len) {
        initialize(len);
    }
    
    public int size(){
        return this.size;
    }

    public boolean isempty(){
        return this.size==0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < this.size; i++) {
            int idx = (i + this.front) % this.maxsize;
            sb.append(this.arr[idx]);
            if (i != this.size - 1)
                sb.append(", ");
        }
         sb.append("]");
        return sb.toString();
    }

    public void queueunderflow() throws Exception {
        if (this.size == 0)
            throw new Exception("queueunderflow: lol");
    }

    public void queueoverflow() throws Exception {
        if (this.size == this.maxsize)
            throw new Exception("queueoverflow: lol");
    }

    private void add_(int data) {
        arr[this.rear] = data;
        this.rear = (this.rear + 1) % this.maxsize;
        this.size++;
    }

    public void add(int data) throws Exception {
        queueoverflow();
        add_(data);
    }

    private int remove_() {
        int rm = this.arr[this.front];
        this.arr[this.front] = 0;
        this.front = (this.front + 1) % this.maxsize;
        this.size--;
        return rm;
    }

    public int remove() throws Exception {
        queueunderflow();
        return remove_();
    }
}
