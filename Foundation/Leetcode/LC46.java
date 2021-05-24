package Leetcode;

import java.util.*;
class LC46 {

    public static void main(String[] args) {
        int arr[] = {1,2,3};
        List<List<Integer>> res = permute(arr);
        System.out.println(res);

    }
    public static void permute( int [] nums , int count, List<List<Integer>> res, List<Integer> ans )
    {
        if( count == nums.length)
        {
            List<Integer> base = new ArrayList<>(ans);
            res.add(base);
            return;
        }
        
        for( int i =0; i<nums.length; i++)
        {
            if(nums[i] >= -10 && nums[i] <= 10)
            {
                int val = nums[i];
                nums[i] = -11;
                ans.add(val);
                permute(nums, count+1, res, ans);
                ans.remove(ans.size()-1);
                nums[i] = val;
            }
        }
    }
    static public List<List<Integer>> permute(int[] nums) {
      
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> ans = new ArrayList<>();
        permute(nums, 0, res, ans);
        return res;
    }
}