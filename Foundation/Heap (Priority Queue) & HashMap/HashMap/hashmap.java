import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Arrays;

public class hashmap {

    public static void Hashmap() {
        HashMap<String, Integer> map = new HashMap<>();

        map.put("India", 100);
        System.out.println(map.get("India"));
    }

    public static void frequencyCount(String str) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            if (map.containsKey(str.charAt(i))) {
                int oldValue = map.get(str.charAt(i));
                oldValue++;
                map.put(str.charAt(i), oldValue);
            } else {
                map.put(str.charAt(i), 1);
            }
        }

        // for (int i = 0; i < str.length(); i++) {
        // char ch = str.charAt(i);
        // if (map.containsKey(ch))
        // map.put(ch, map.get(ch) + 1);
        // else
        // map.put(ch, 0 + 1);
        // }

        // for (int i = 0; i < str.length(); i++) {
        // char ch = str.charAt(i);
        // map.put(ch, map.getOrDefault(ch, 0) + 1);
        // }

        // for (int i = 0; i < str.length(); i++) {
        // char ch = str.charAt(i);
        // map.putIfAbsent(ch,0);
        // map.put(ch, map.get(ch) + 1);
        // }

        for (Character ch : map.keySet()) {
            System.out.println(ch + "->" + map.get(ch));
        }
    }

    public static void frequencyWithPathInArrayList(String str) {
        HashMap<Character, ArrayList<Integer>> map = new HashMap<>();

        for (int i = 0; i < str.length(); i++) {
            if (map.containsKey(str.charAt(i))) {
                map.get(str.charAt(i)).add(i);
            } else {
                map.put(str.charAt(i), new ArrayList<Integer>(Arrays.asList(i)));
            }
        }

        // for (int i = 0; i < str.length(); i++) {
        // char ch = str.charAt(i);
        // if (!map.containsKey(ch))
        // map.put(ch, new ArrayList<>());

        // map.get(ch).add(i);
        // }

        // for (int i = 0; i < str.length(); i++) {
        // char ch = str.charAt(i);
        // map.putIfAbsent(ch,new ArrayList<>());
        // map.get(ch).add(i);
        // }

        for (Character ch : map.keySet()) {
            System.out.println(ch + "->" + map.get(ch));
        }
    }

    public static void getElementsWithoutDuplicacy(int[] arr1, int[] arr2) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr1.length; i++) {
            map.put(arr1[i], arr1[i]);
        }

        for (int i = 0; i < arr2.length; i++) {
            if (map.containsKey(arr2[i])) {
                System.out.println(arr2[i]);
                map.remove(arr2[i]);
            }
        }
    }

    public static void getElementsWithDuplicacy(int[] arr1, int[] arr2) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr1.length; i++) {
            map.put(arr1[i], map.getOrDefault(arr1[i], 0) + 1); // mila to arr1[i] ki key pe value get kardo then+1
                                                                // warna arr1[i] ko key
        } // banakar uspe 1 put kardo

        for (int i = 0; i < arr2.length; i++) {
            if (map.containsKey(arr2[i])) {
                System.out.println(arr2[i]);
                if (map.get(arr2[i]) == 1) {
                    map.remove(arr2[i]);
                } else {
                    map.put(arr2[i], map.get(arr2[i]) - 1);
                }
            }
        }
    }

    public static void printMaxFreqCharacter(String str) {
        char ans = '\u0000';
        int max = -(int) 1e9;
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            if (map.containsKey(str.charAt(i))) {
                int oldValue = map.get(str.charAt(i));
                oldValue++;
                map.put(str.charAt(i), oldValue);
            } else {
                map.put(str.charAt(i), 1);
            }
        }

        for (Character ch : map.keySet()) {
            if (map.get(ch) > max) {
                ans = ch;
                max = map.get(ch);
            }
        }

        System.out.println(ans);
    }

    // public static void LongestConsecutiveSequenceOfElements(int arr[]) {
    //     Arrays.sort(arr);
    //     HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
    //     int prev = -(int) 1e9;
    //     for (int i = 0; i < arr.length; i++) {
            
    //         if (!map.containsKey(arr[i])) {
    //             if(arr[i] == prev){
    //                 break;
    //             } 
    //             if (arr[i] == prev + 1) {
    //                 ArrayList<Integer> res = map.get(prev);
    //                 res.add(arr[i]);
    //                 map.put(prev, res);
    //                 prev = arr[i];
    //             } else {
    //                 map.put(arr[i], new ArrayList<Integer>(Arrays.asList(arr[i])));
    //                 prev = arr[i];
    //             }
    //         }
    //     }

    //     System.out.println(map);
    // }

    public static void LongestConsecutiveSequenceOfElements(int arr[]) {
      HashSet<Integer> map = new HashSet<>();
      for(int i =0; i<arr.length; i++) map.add(arr[i]);
      int len =0, head =0;
      for(int el:arr){
          if(!map.contains(el)) continue;
          
          int left = el -1 , right = el+1;
          map.remove(el);

          while(map.contains(left)){
             map.remove( left--);
          }

          
          while(map.contains(right)){
            map.remove(right++);
         }

         if(right - left -1 > len){
             len = right - left - 1;
             head = left +1;
         }
      }

      for( int i =0; i<len; i++) System.out.println(head+i);
    }

    public static void main(String[] args) {
        // Hashmap();
        // frequencyWithPathInArrayList("aaaaabbbbcccdfaa");
        // frequencyCount("aaaaabbbbcccdfaa");
        int arr1[] = { 5, 5, 9, 8, 5, 5, 8, 0, 3 };
        int arr2[] = { 9, 7, 1, 0, 3, 6, 5, 9, 1, 1, 8, 0, 2, 4, 2, 9, 1, 5 };
        int arr3[] = { 12, 5, 1, 2, 10, 2, 13, 7, 11, 8, 9, 11, 8, 9, 5, 6, 11 };
        LongestConsecutiveSequenceOfElements(arr3);
    }

}