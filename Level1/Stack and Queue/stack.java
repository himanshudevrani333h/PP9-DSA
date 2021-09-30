
public class stack {

    private int[] arr;
    private int tos;
    private int NoOfElements;
    private int MaxCapacity;

    stack(int size) {
        initialize(size);
    }

    stack() {
        this(10);
    }

    protected void initialize(int size) {
        this.tos = -1;
        this.MaxCapacity = size;
        this.arr = new int[this.MaxCapacity];
        this.NoOfElements = 0;
    }

    public void display() {
        for (int i = this.tos; i >= 0; i--) {
            System.out.println(this.arr[i]);
            System.out.println();
        }
    }

    public int capacity() {
        return this.MaxCapacity;
    }

    public int size() {
        return this.NoOfElements;
    }

    public void overFlow() throws Exception {
        if (this.NoOfElements == this.MaxCapacity)
            throw new Exception("Stack is Full");
    }

    public void underFlow() throws Exception {
        if (this.NoOfElements == 0)
            throw new Exception("Stack is Empty");
    }

    public void push(int data) throws Exception {
        overFlow();
        this.arr[++this.tos] = data;
        this.NoOfElements++;
    }

    public int peek() throws Exception {
        underFlow();
        return this.arr[this.tos];
    }

    public int pop() throws Exception {
        underFlow();
        int el = this.arr[this.tos];
        this.arr[this.tos] = 0;
        this.tos--;
        this.NoOfElements--;
        return el;
    }

}