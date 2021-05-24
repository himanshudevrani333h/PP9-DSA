import java.util.*;
import java.util.ArrayList;

public class ArrayListbasic {
    static Scanner sc = new Scanner(System.in);

    public static void basicArrayList() {
        ArrayList<Integer> al = new ArrayList<>();
        al.add(10);
        al.add(20);
        al.add(30);
        System.out.println(al);
        for (Integer e : al)
            System.out.println(e);
        System.out.println(al.size());
        System.out.println(al.get(1));
        System.out.println(al.remove(1));
    }

    public static void swap(ArrayList<Integer> al, int i, int li) {
        int temp = al.get(li);
        al.set(li, al.get(i));
        al.set(i, temp);
    }

    public static boolean isprime(int n) {
        int div = 2;
        while (div * div <= n) {
            if (n % div == 0) {
                return false;
            } else {
                div++;
            }
        }

        return true;
    }

    public static void removeprime() {
        int n = sc.nextInt();
        ArrayList<Integer> al = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int m = sc.nextInt();
            al.add(m);
        }
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < al.size(); i++) {
            if (!isprime(al.get(i))) {
                res.add(al.get(i));
            }
        }
        al.clear();
        for(Integer e:res)
        {
            al.add(e);
        }
        System.out.println(al);
    }

    public static void removedata() {
        ArrayList<Integer> al = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            int n = sc.nextInt();
            al.add(n);
        }
        int data = sc.nextInt();

        // for (int i = 0; i < al.size(); i++) {
        // if (al.get(i) == data) {
        // al.remove(i);
        // }
        // }

        int i = al.size() - 1;
        while (i >= 0) {
            if (al.get(i) == data) {
                swap(al, i, al.size() - 1);
                al.remove(al.size() - 1);
            }
            i--;
        }

        System.out.println(al);
    }

    public static void main(String[] args) {
        // basicArrayList();
        // removedata();
        removeprime();
    }
}
