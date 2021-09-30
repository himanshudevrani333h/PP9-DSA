
public class stackClient {
    

    public static void main(String[] args) throws Exception {
        // stack st = new stack();
        // st.push(10);
        // st.push(20);
        // st.push(30);
        // st.push(10);
        // st.push(20);
        // st.push(30);
        // st.push(100);
        // st.push(1000);
        // st.push(30);
    

        // System.out.println(st.peek());
        // System.out.println(st.pop());
        // st.push(80);
        // System.out.println(st.peek());
        // System.out.println(st.peek());

        DynamicStack ds = new DynamicStack();
        ds.push(1);
        ds.push(10);
        ds.push(100);
        ds.push(1000);
        ds.push(10000);
        ds.push(100000);
        ds.push(1000000);
        ds.push(10000000);
        ds.push(100000000);
        ds.push(1000000000);
        ds.push(1000000009);
        ds.push(1000000099);
        ds.push(1000000099);
        ds.push(1000000099);
        ds.display();
    //    System.out.println(ds.pop());
    //    System.out.println(ds.pop());
    //    System.out.println(ds.pop());
    //    System.out.println(ds.pop());
    //    System.out.println(ds.pop());
    }
}
