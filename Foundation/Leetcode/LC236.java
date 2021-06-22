// LCA in Binary tree
public class LC236 {
   
    public ArrayList<TreeNode> nodetoroot(TreeNode root, TreeNode node){
        if( node == null || root == null) return new ArrayList<>();
        
        if( root == node){
            ArrayList<TreeNode> base = new ArrayList<>();
            base.add(root);
            return base;
        }
        
        ArrayList<TreeNode> recAns = new ArrayList<>();
        ArrayList<TreeNode> left = nodetoroot(root.left, node);
        if( left.size() > 0){
            left.add(root);
            return left;
        }
        ArrayList<TreeNode> right = nodetoroot(root.right, node);
         if( right.size() > 0){
             right.add(root);
             return right;
        }
        
        return recAns;
    }
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
       
         ArrayList<TreeNode> list1 = nodetoroot( root, p);
         ArrayList<TreeNode> list2 = nodetoroot( root, q);
         
        int i = list1.size() -1;
        int j = list2.size() -1;
        TreeNode res = null;
        while( i>=0 || j>=0){
            if( i>=0 && j>=0 && list1.get(i) == list2.get(j)){
                res = list1.get(i);
                i--;
                j--;
            }else{
                return res;
            }
        }
        
        return res;
        
    }
}
