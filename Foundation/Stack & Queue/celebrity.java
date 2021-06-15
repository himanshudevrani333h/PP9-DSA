public class celebrity {
    public static void findCelebrity(int[][] arr) {
       
        int celebrity =0;
        
        for( int i = 1; i<arr.length; i++)
          if(arr[celebrity][i] == 1)
           celebrity = i;
           
           
         for( int i =0; i<arr.length; i++){
             if( celebrity != i){
                 if( arr[celebrity][i] == 1 || arr[i][celebrity] ==0){
                  System.out.println("none");
                     return;
                 }
             }
         }  
         
         System.out.println(celebrity);
     }

     public static void main(String[] args) {
         int arr[][] = {{0,0,0,0},{1,0,1,1},{1,1,0,1},{1,1,1,0}};
         findCelebrity(arr);
     }
}
