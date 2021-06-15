import java.util.LinkedList;


public class balancedbrackets {
    public static class Stack{
       
        LinkedList<Character> st = new LinkedList<>();
        
        public int size(){
            return this.st.size();
        }
        
        public void push(char data){
            this.st.addFirst(data);
        }
        
        public void pop(){
            this.st.removeFirst();
        }
        
        public char peek(){
            return this.st.getFirst();
        }
    }
    
    public static boolean balancedbrac(String str){
       Stack st = new Stack();
       
       for(int i =0; i<str.length(); i++){
           char ch = str.charAt(i);
           
           if(ch == '(' || ch =='[' || ch == '{'){
               st.push(ch);
             }else if(ch == ')'||ch == ']' || ch == '}'){
               if(st.size() ==0) return false;
               if(ch == ')' && st.peek() == '('){
                   st.pop();
               }
                if(ch == ']' && st.peek() == '['){
                   st.pop();
               }
                if(ch == '}' && st.peek() == '{'){
                   st.pop();
               }
             }
       }
       System.out.println(st.size());
       return (st.size() == 0);
       
    }
 
     public static void main(String[] args) throws Exception {
      
      String str = "[(a + b) + {(c + d) * (e / f)}]";
      System.out.println(balancedbrac(str));
      
     }
}
