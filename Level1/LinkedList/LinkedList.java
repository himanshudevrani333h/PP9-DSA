
public class LinkedList {

    public static class ListNode {
        int val = 0;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static ListNode midNode(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    public static ListNode reverse(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode prev = null, curr = head;
        while (curr != null) {
            ListNode forw = curr.next; // backup

            curr.next = prev; // link

            prev = curr;
            curr = forw;
        }

        return prev;
    }

    public static boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null)
            return true;

        ListNode mid = midNode(head);
        ListNode nhead = mid.next;
        mid.next = null;

        nhead = reverse(nhead);
        ListNode c1 = head, c2 = nhead;

        boolean flag = true;
        while (c1 != null && c2 != null) {
            if (c1.val != c2.val) {
                flag = false;
                break;
            }

            c1 = c1.next;
            c2 = c2.next;
        }

        nhead = reverse(nhead);
        mid.next = nhead;

        return flag;
    }

    public static void fold(ListNode head) {
        if (head == null || head.next == null)
            return;

        ListNode mid = midNode(head);
        ListNode nhead = mid.next;
        mid.next = null;

        nhead = reverse(nhead);
        ListNode c1 = head, c2 = nhead;
        while (c2 != null) {
            // backup
            ListNode f1 = c1.next, f2 = c2.next;

            // links
            c1.next = c2;
            c2.next = f1;

            // move
            c1 = f1;
            c2 = f2;
        }
    }

    public static void unfold(ListNode head) {
        if (head == null || head.next == null)
            return;

        ListNode d1 = new ListNode(-1), d2 = new ListNode(-1), c1 = d1, c2 = d2, c = head;

        while (c != null) {
            c1.next = c;
            c2.next = c.next;

            c1 = c1.next;
            c2 = c2.next;

            c = c.next;
            if (c != null)
                c = c.next;
        }

        c1.next = null;
        ListNode rhead = reverse(d2.next);
        c1.next = rhead;
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null)
            return head;

        ListNode slow = head, fast = head;
        while (n-- > 0)
            fast = fast.next;

        if (fast == null) {
            ListNode rnode = slow;
            head = head.next;
            rnode.next = null;

            return head;
        }

        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }

        ListNode rnode = slow.next;
        slow.next = rnode.next;
        rnode.next = null;
        return head;
    }
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null || l2 == null ) return l1 != null ? l1 :l2;
        ListNode dummyNode = new ListNode(-1);
        ListNode res = dummyNode;
        while( l1 != null && l2 != null){
            
            if(  l1.val < l2.val){
                res.next = l1;
                l1 = l1.next;
            }else {
                res.next = l2;
                l2 = l2.next;
            }
            
            res = res.next;
        }
        
       res.next = l1 != null? l1 : l2;
        
        return dummyNode.next;
    }

    public static ListNode segregateEvenOdd(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode even = new ListNode(-1), odd = new ListNode(-1), ep = even, op = odd, curr = head;

        while (curr != null) {
            if ((curr.val & 1) == 0) {
                ep.next = curr;
                ep = ep.next;
            } else {
                op.next = curr;
                op = op.next;
            }

            curr = curr.next;
        }

        ep.next = op.next = null;
        ep.next = odd.next;

        return even.next;
    }

    public static ListNode segregate01(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode onces = new ListNode(-1), onceP = onces;
        ListNode zeros = new ListNode(-1), zeroP = zeros;
        ListNode curr = head;

        while (curr != null) {
            if (curr.val == 0) {
                zeroP.next = curr;
                zeroP = zeroP.next;
            } else {
                onceP.next = curr;
                onceP = onceP.next;
            }

            curr = curr.next;
        }

        onceP.next = zeroP.next = null;
        zeroP.next = onces.next;

        return zeros.next;
    }

    public static ListNode segregate012(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode once = new ListNode(-1), op = once;
        ListNode zero = new ListNode(-1), zp = zero;
        ListNode two = new ListNode(-1), tp = two;
        ListNode curr = head;

        while (curr != null) {
            if (curr.val == 0) {
                zp.next = curr;
                zp = zp.next;
            } else if (curr.val == 1) {
                op.next = curr;
                op = op.next;
            } else {
                tp.next = curr;
                tp = tp.next;
            }

            curr = curr.next;
        }

        op.next = zp.next = tp.next = null;
        op.next = two.next;
        zp.next = once.next;

        return zero.next;
    }

    public static ListNode mergeSort(ListNode head) {
        
        if( head == null){
         return head;
        }

         ListNode temp = head;
         ListNode midNode = midNode(temp);
         ListNode righthalf = midNode.next;
         midNode.next = null;
         ListNode leftHalf = mergeSort(temp);
         ListNode rightHalf = mergeSort(righthalf);

         ListNode res = mergeTwoLists(leftHalf, rightHalf);

         return res;
    }

    public static ListNode mergeKLists(ListNode[] lists, int si, int ei) {
        
        if( si == ei)  return lists[si];
        
        int mid = (si+ei)/2;
        
        ListNode leftlist = mergeKLists(lists,si,mid);
        ListNode rightlist = mergeKLists(lists,mid+1,ei);
        
        return (mergeTwoLists(leftlist,rightlist));
        
    }
    
    private static ListNode th = null, tt = null;
    
    public static void addFirst(ListNode node){
        if(th == null){
            th = tt = node;
        }else{
            node.next = th;
            th = node;
        }
    }

    public static int length(ListNode node){
        ListNode curr = node;
        int len =0;
        while( curr != null){
            curr = curr.next;
            len++;
        }
        return len;
    }

    public static ListNode reverseInKGroup(ListNode head, int k) {
       if( head == null || head.next == null || k<=1) return head;
       
       int len = length(head);
       ListNode curr = head, oh = null, ot = null;

       while( len >= k){
          int tempK = k;

          while( tempK -->0){
              ListNode fwd = curr.next;
              curr.next = null;
              addFirst(curr);
              curr = fwd;
          }

          if(oh == null){
              oh = th;
              ot = tt;
          }else{
              ot.next = th;
              ot = tt;
          }

          th = tt = null;
          len -= k;
       }

       ot.next = curr;
       return oh;
    }


    public static ListNode reverseInRange(ListNode head, int n, int m) {
        if( head == null || head.next == null || n == m) return head;
        ListNode dummy = new ListNode(-1), prev = dummy, curr = head;
        prev.next = head;
        
        int i =1;
        while( i<=n){
            
            while( i>= n && i<=m){
                ListNode fwd = curr.next;
                curr.next = null;
                addFirst(curr);
                curr = fwd;
                
                i++;
            }
            
            if( i>m){
                prev.next = th;
                tt.next = curr;
                break;
            }
            
            prev = curr;
            curr = curr.next;
            i++;
        }
        
        return dummy.next;
    }

    public static ListNode removeAllDuplicates(ListNode head) {
        if( head == null || head.next == null) return head;
        
        ListNode dummy = new ListNode(-1), prev = dummy, curr = head;
        
        while( curr != null){
            if(curr.val == prev.val){
               curr = curr.next;
            }else{
                ListNode fwd = curr.next;
                 prev.next = curr;
                 curr.next = null;
                 curr = fwd;
                 prev = prev.next;
            }
        }
        
        return dummy.next;
     }

     public static ListNode removeDuplicates(ListNode head) {
        if( head == null || head.next == null) return head;
        
        ListNode dummy = new ListNode(-1), prev = dummy, curr = head.next;
         prev.next = head;
        while( curr != null){
            boolean flag = false;
           while(curr != null && prev.next.val == curr.val){
            ListNode fw = curr.next;
            curr.next = null;
            curr = fw;
           flag = true;
           }
           if(flag){
           prev.next = curr;
          
           }else{
               prev = prev.next;
              
           }
           
           if(curr != null){
                curr =curr.next;
           }
        }
        
        return dummy.next;
    }

    public static void main(String[] args) {

    }
}