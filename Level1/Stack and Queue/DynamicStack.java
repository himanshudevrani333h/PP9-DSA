public class DynamicStack extends stack {

    DynamicStack(int size) {
        super(size);
    }

    DynamicStack() {
        super();
    }

    @Override
    public void push(int data) throws Exception {
        if (super.capacity() == super.size()) {
            int temparr[] = new int[super.size()];
            while (super.size() != 0) {
                temparr[super.size() - 1] = super.pop();
            }
            super.initialize(temparr.length * 2);
            for (int i = 0; i < temparr.length; i++)
                super.push(temparr[i]);
        }
        super.push(data);
    }
}
