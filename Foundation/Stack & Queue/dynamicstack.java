
public class dynamicstack extends stack{
  
  public dynamicstack(){
      super();
  }
  public dynamicstack(int size){
      super(size);
  }
  @Override

  public void push(int data) throws Exception{
      
    if( super.size() == super.maxsize()){
      int temp[] = new int[super.size()];
      int idx = super.size()-1;
      while(super.size() != 0) temp[idx--] = super.pop();

      super.initialize(super.maxsize() *2);
      for( int el:temp) super.push(el);

    }

    super.push(data);
  }


}
