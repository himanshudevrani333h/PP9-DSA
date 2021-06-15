import java.util.Arrays;
import java.util.LinkedList;

public class mergeTimeIntervals {
    public static void mergeOverlappingIntervals(int[][] arr) {
        Arrays.sort(arr,(a,b) ->{
           return a[0] - b[0]; 
        });
        LinkedList<int[]> st = new LinkedList<>();
        for(int a[]: arr){
            int minStartTime = a[0];
            int minEndTime = a[1];
            
            while( st.size() != 0 &&  a[0] <= st.getFirst()[1]){
                minStartTime = st.getFirst()[0];
                minEndTime = Math.max(minEndTime, st.getFirst()[1]);
                st.removeFirst();
            }
            
            st.push(new int[]{minStartTime,minEndTime});
        }
        
        while( st.size() !=0){
            int a[] = st.removeLast();
            System.out.println(a[0]+" "+a[1]);
        }
     }

     public static void main(String[] args) {
         int a[][] = {{1,8},{5, 12},{14, 19},{22, 28},{25, 27},{27, 30} };
         mergeOverlappingIntervals(a);
     }
}
