public class linkedlist {
        
        public class Node{
            int data = 0;
            Node next = null;

               Node(int data )
            {
                this.data = data;
            }
        }
            private Node head = null;
            private Node tail = null;
            private int size = 0;

            public boolean isEmpty()
            {
                if(this.size >0) return false;
                else
                return true;
            }
    
            public int size()
            {
                return this.size;
            }
    
            private void addfirstNode(Node node)
            {
                if(this.size == 0)
                {
                    this.head= this.tail = node;
                }else{
                    node.next = this.head;
                    this.head = node;
                }
                this.size++;
            }

            public void addFirst(int data)
            {
                Node node = new Node(data);
                addfirstNode(node);
            }

            private void addLastNode(Node node)
            {
                if(this.size ==0)
                {
                    this.head = this.tail = node;
                }else{
                    this.tail.next = node;
                    this.tail = node;
                }
                this.size++;
            }

            public void addLast(int data)
            {
                Node node = new Node(data);
                addLastNode(node);
            }

            private Node getFirstNode()
            {  
                return this.head;
            }

            public int getFirst()
            {
                if(this.size == 0) return -1;
                else
                  return getFirstNode().data;
            }

            private Node getLastNode()
            {
                return this.tail;
            }

            public int getLast()
            {
                if( this.size ==0) return -1;
                else
                 return getLastNode().data;
            }
            // private Node getAtNode(){
            //     Node node = this.head;
            //     int i =0;
            //     while(i<idx)
            //     {
            //         node = node.next; 
            //         i++;
            //     }
            //   return node;
            // }

            // public int getAt(int idx)
            // {   if( this.size == 1)return getFirst();
            //     if( this.size == idx) return getLast();
            //     if( this.size == 0) return -1;
            //     else
            //        return getAtNode().data;
            // }
    }

