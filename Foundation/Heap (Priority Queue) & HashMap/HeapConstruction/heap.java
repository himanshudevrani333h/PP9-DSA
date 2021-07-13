import java.util.ArrayList;

class heap {

    private int noOfElem;
    private ArrayList<Integer> arr;
    private boolean isMaxHeap;

    private void initialize(boolean isMaxHeap) {
        this.noOfElem = 0;
        this.arr = new ArrayList<>();
        this.isMaxHeap = true;
    }

    private heap(boolean isMaxHeap) {
        initialize(isMaxHeap);
    }

    public heap() {
        this(true);
    }

    // O(n)
    public heap(int arr[], boolean isMaxHeap) {
        this(isMaxHeap);

        for (int el : arr)
            this.arr.add(el);

        this.noOfElem = this.arr.size();

        for (int i = this.noOfElem - 1; i >= 0; i--) { // Nlog(N) -> N
            downheapify(i);
        }

    }
    // Exception---------------

    public void undeflowException() throws Exception {
        if (this.noOfElem == 0)
            throw new Exception("Heap is Empty");
    }

    // basic fn-------------------------
    public int size() {
        return this.noOfElem;
    }

    public boolean isEmpty() {
        return this.noOfElem == 0;
    }

    // DS fn---------------------------------------
    public int compareTo(int t, int o) {
        if (isMaxHeap)
            return this.arr.get(t) - this.arr.get(o);
        return this.arr.get(o) - this.arr.get(t);
    }

    public void swap(int i, int j) {
        int data1 = this.arr.get(i);
        int data2 = this.arr.get(j);

        this.arr.set(i, data2);
        this.arr.set(j, data1);
    }

    // O(logN)
    private void downheapify(int pi) {
        int maxidx = pi, lci = 2 * pi + 1, rci = 2 * pi + 2;
        if (lci < this.noOfElem && compareTo(lci, maxidx) > 0) {
            maxidx = lci;
        }
        if (rci < this.noOfElem && compareTo(rci, maxidx) > 0) {
            maxidx = rci;
        }

        if (maxidx != pi) {
            swap(maxidx, pi);
            downheapify(maxidx);
        }
    }

    // O(logN)
    private void upheapify(int ci) {
        int pi = (ci - 1) / 2;

        if (compareTo(ci, pi) > 0) {
            swap(ci, pi);
            upheapify(pi);
        }
    }

    public int peek() throws Exception {
        undeflowException();
        return this.arr.get(0);
    }

    // O(Log(n))
    public int remove() throws Exception {
        undeflowException();
        int rEle = this.arr.get(0);

        swap(0, this.noOfElem - 1);
        this.arr.remove(this.noOfElem - 1);

        this.noOfElem--;

        downheapify(0);
        return rEle;
    }

    // O(LogN)
    public void add(int data) {
        this.arr.add(data);
        this.noOfElem++;
        upheapify(this.noOfElem - 1);
    }

}