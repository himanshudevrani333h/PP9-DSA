
public class client {
    
   public static void main(String[] args) throws Exception {
    //    stack st = new stack(6);
    //    for(int i=1; i<=6; i++){
    //        st.push(i);
    //    }
    //    System.out.println(st.size());
    //    System.out.println("*******");
    //    System.out.println(st.toString());
    //    System.out.println("*******");
    //    System.out.println(st.toString());

    Queue qu = new Queue(3);
    for( int i =1; i<=3; i++) qu.add(i);
    
    System.out.println(qu.size());
    System.out.println(qu.toString());
    // System.out.println( qu.remove());
    // System.out.println(qu.toString());

   } 
}
