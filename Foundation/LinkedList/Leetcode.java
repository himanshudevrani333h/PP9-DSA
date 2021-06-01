public class Leetcode {
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    // 876
    public ListNode middleNode(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    // 19
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || head.next == null)
            return null;

        ListNode slow = head, fast = head;
        while (n-- > 0)
            fast = fast.next;

        if (fast == null)
            return head = head.next;

        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }

        slow.next = slow.next.next;

        return head;
    }

    // GFG Segregate even and odd nodes in a Link List//

    ListNode divide(int N, ListNode head){
        ListNode even = new ListNode(-1);
        ListNode ep = even;
        ListNode odd = new ListNode(-1);
        ListNode op = odd;
        ListNode curr = head;
        
        while( curr != null)
        {
            if( curr.val % 2 ==0)
            {
                ep.next = curr;
                ep = ep.next;
            }else{
                op.next = curr;
                op = op.next;
            }
            
            curr = curr.next;
        }
        
        ep.next = odd.next;
        op.next = null;
        
        return even.next;
     }


    //  83

    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(-101);
        ListNode dp = dummy;
        ListNode curr = head;
        int size =0;
        while( curr != null)
        {
            while( curr != null && curr.val == dp.val)
            {
                ListNode fwd = curr.next;
                curr.next = null;
                curr = fwd;
            }
            dp.next = curr;
            if( curr != null){
                dp = dp.next;
                curr = curr.next;
                size++;
            }
        }
         
        return dummy.next;
    }

    // 206
    public ListNode reverseList(ListNode head) {
        if( head == null || head.next == null) return head;
        ListNode prev= null;
        ListNode curr = head;
        
        while( curr != null){
            ListNode fwd = curr.next;
            curr.next = prev;
            
            prev = curr;
            curr = fwd;
        }
        
        return prev;
    }
    
   //   21
    public static ListNode mergetwolist(ListNode head, ListNode tail){
        if(head ==null || tail == null) return head != null?head:tail;
        
        ListNode l1 = head;
        ListNode l2 = tail;
        ListNode dummy = new ListNode(-1);
        ListNode prev= dummy;
        while( l1 != null && l2 != null){
            if( l1.val <= l2.val){
                prev.next = l1;
                l1 = l1.next;
            }else{
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }
        
        prev.next = l1 != null?l1:l2;
        return dummy.next;
    }

    // 148
    public static ListNode merge_sort(ListNode node){
         if( node.next == null) return node;
        
          ListNode mid = mid(node);
          ListNode left = node;
          ListNode right = mid.next;
          mid.next = null;

          ListNode srt1 = merge_sort(left);
          ListNode srt2= merge_sort(right);
          ListNode res = mergetwolist(srt1, srt2);
          return res;
    }

}
