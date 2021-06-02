public class stack {

    private int arr[];
    private int size;
    private int tos;
    private int maxsize;

    private void initialize(int len) {
        this.arr = new int[len];
        this.size = 0;
        this.tos = -1;
        this.maxsize = len;
    }

    public stack() {
        initialize(5);
    }

    public stack(int length) {
        initialize(length);
    }

    @Override
    public String toString() {
        int temp = tos;
        StringBuilder sb = new StringBuilder();
        sb.append(arr[temp]+"\n");
        while (temp-- > 0) {
            if (temp != 0)
                sb.append(arr[temp] + "\n");
            else
                sb.append(arr[temp]);
        }

        return sb.toString();
    }

    public void stackunderflow() throws Exception {
        if (this.size == 0)
            throw new Exception("stackunderflow: lol");
    }

    public void stackoverflow() throws Exception {
        if (this.size == this.maxsize)
            throw new Exception("stackoverflow: lol");
    }

    public int size() {
        return size;
    }

    public boolean isempty() {
        return this.size == 0;
    }

    public int top() throws Exception {
        stackunderflow();
        return arr[this.tos];
    }

    private void push_(int data) {
        arr[++this.tos] = data;
        this.size++;
    }

    public void push(int data) throws Exception {
        stackoverflow();
        push_(data);
    }

    private int pop_() {
        int rem = arr[this.tos];
        arr[this.tos--] = 0;
        this.size--;
        return rem;
    }

    public int pop() throws Exception {
        stackunderflow();
        return pop_();
    }

}
