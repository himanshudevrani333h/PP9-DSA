import java.util.LinkedList;

public class duplicatebrackets {
    public static class stack{
        LinkedList<Character> st = new LinkedList<>();
        
        
        public void push(char data){
            st.addFirst(data);
        }
        public int size(){
            return st.size();
        }
        public char pop(){
            return st.removeFirst();
        }
        
        public char peek(){
            return  st.getFirst();
        }
    }
    
    public  static boolean duplicatebrac(String str){
        stack st = new stack();
        
        for( int i =0; i<str.length(); i++){
            char ch = str.charAt(i);
            
            boolean isLoop = false;
            
            while(st.size() !=0 && ch == ')' && st.peek() != '('){
                isLoop = true;
                st.pop();
            }
            
            if(!isLoop && ch == ')'){
                return true;
            }else if(isLoop){
                st.pop();
            }else{
                st.push(ch);
            }
        }
        return false;
    } 
    
    public static void main(String[] args) throws Exception {
    
    String str = "(a + b) + ((c + d))";
    System.out.println(duplicatebrac(str));
    
    }
}
