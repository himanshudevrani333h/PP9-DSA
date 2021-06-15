public class dynamicqueue extends Queue{

    public dynamicqueue(){
        super();
    }

    public dynamicqueue(int sz){
        super.initialize(sz);
    }

    @Override
    public void add(int data) throws Exception{
        if( super.size() == maaxsize()){
            int temp[] = new int[super.size()];
            int idx =0;
            while(super.size() != 0) temp[idx++] = super.remove();
            
            super.initialize(super.maaxsize()*2);
            for( int e:temp) super.add(e);
        }
       super.add(data);
    }
}
