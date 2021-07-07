import java.util.Arrays;
import java.util.PriorityQueue;

public class heap {
    public static void Int_minPQ() {
        PriorityQueue<Integer> pq = new PriorityQueue<>(); // by default min Priority Queue
        for (int i = 10; i >= 1; i--)
            pq.add(i * 10);

        while (pq.size() != 0)
            System.out.println(pq.remove());
    }

    public static void Int_maxPQ() {
        // this - other, default behaviour.
        // other - this, reverse of default behaviour.
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            return b - a;
        });

        for (int i = 10; i >= 1; i--)
            pq.add(i * 10);

        while (pq.size() != 0)
            System.out.println(pq.remove());
    }

    public static void matrixPQ(int[][] arr) {

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return a[1] - b[1];
        });

        for (int[] a : arr) {
            pq.add(a);
        }
        // pq.add(arr);

        while (pq.size() > 0) {
            int res[] = pq.remove();

            for (int b : res)
                System.out.print(b + " ");

            System.out.println();

        }

    }

    public static class mobilePhone {
        String Company = "";
        String Model = "";
        int Ram = 0;
        int Storage = 0;
        int BatteryBackup = 0;

        mobilePhone(String Company, String Model, int Ram, int Storage, int BatteryBackup) {
            this.Company = Company;
            this.Model = Model;
            this.Ram = Ram;
            this.Storage = Storage;
            this.BatteryBackup = BatteryBackup;
        }

        mobilePhone() {

        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();

            sb.append("Company: " + this.Company + "\n");
            sb.append("Model: " + this.Model + "\n");
            sb.append("Ram: " + this.Ram + "GB\n");
            sb.append("Storage: " + this.Storage + "GB\n");
            sb.append("BatteryBackup: " + this.BatteryBackup + "mAH\n");

            return sb.toString();
        }
    }

    public static void sortMobilePhones(){
        PriorityQueue<mobilePhone> pq = new PriorityQueue<>((a,b)->{
            if( b.Ram - a.Ram != 0){
                return   b.Ram - a.Ram;
            }else if( b.BatteryBackup - a.BatteryBackup  != 0){
                  return  b.BatteryBackup - a.BatteryBackup ;
            }else{
                return  b.Ram - a.Ram;
            }
        });
        
        pq.add(new mobilePhone("one","1000",4,4,1000));
        pq.add(new mobilePhone("two","2000",4,4,10000));
        pq.add(new mobilePhone("three","3000",6,40,100000));
        pq.add( new mobilePhone("four","4000",8,400,100000));


        while(pq.size() >0){
            mobilePhone rev = pq.remove();
            // String ans = rev.toString();
            System.out.println(rev);
        }
    }

    public static void main(String[] args) {
        // Int_minPQ();
        // Int_maxPQ();
    

        // int arr[][] = { { 1, 2, 3, 4 }, { 13, 14, 15, 16 }, { 9, 10, 11, 12 }, { 5, 6, 7, 8 } };

        // matrixPQ(arr);
         
        sortMobilePhones();
        

    }
}