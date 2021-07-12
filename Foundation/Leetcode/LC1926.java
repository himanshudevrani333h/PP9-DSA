public class LC1926 {
    class Solution {
  
        public int nearestExit(char[][] maze, int[] entrance) {
            boolean visited[][]= new boolean[maze.length][maze[0].length];
            int dir[][] = {{1,0},{0,1},{-1,0},{0,-1}};
            // visited[entrance[0]][entrance[1]] = true;
            LinkedList<int[]> qu = new LinkedList<>();
            int sp =0;
            qu.addLast(entrance);
            boolean first = true;
            while(qu.size() >0){
                
                int sz= qu.size();
                
                while(sz-->0){
                    int[] rv = qu.removeFirst();
                    
                    if(visited[rv[0]][rv[1]]) continue;
                    
           if(rv[0] == maze.length -1 || rv[1] == maze[0].length -1 || rv[0]==0 || rv[1] == 0){
            if(!first)
               return sp;
           }                                                                                
                    
                    visited[rv[0]][rv[1]] = true;
                    if(first) first = false;
                    for(int d=0; d<4; d++){
                        int row = rv[0] + dir[d][0];
                        int col = rv[1] + dir[d][1];
                         // System.out.println(row+","+col);
                        if(row>=0 && col>=0  && row<maze.length && col<maze[0].length &&!visited[row][col] && maze[row][col] == '.'  ){
                            // int arr[] = {row,col};
    
                            qu.addLast(new int[]{row,col});
                        }
              
            }
            
            
            
        }
                  sp++;
    }
            return -1;}}
}
