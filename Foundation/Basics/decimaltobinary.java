import java.util.*;

public class decimaltobinary {
    public static Scanner sc = new Scanner(System.in);

    public static int dtb(int n)
    {   int pow = 1;
       int res = 0;
      while( n != 0)
      {
          int rem = n%2;
          n = n/2;
         
          res += rem*pow;
          pow *=10;
      }
      
      return res;
    }

    public static int btodec( int n)
    {
        int pow = 1;
        int res = 0;
        while( n !=0)
        {
            int rem = n%10;
            res += rem*pow;
            pow = pow*2;
            n = n/10;
        }
        return res;
    }

    public static int anybaseaddition(int base, int no1, int no2)
    {
        int res = 0,carry =0, pow =1, sum =0;

        while( no1!=0 || no2 != 0 || carry !=0)
        {
            sum = carry + no1%10 + no2%10;
            no1 /=10;
            no2 /=10;
            carry = sum/base;
            res += (sum%base)*pow;
            pow *=10;
        }
    //     while( no1 != 0 && no2 != 0)
    //     {
    //         int no1rem = no1%10;
    //         int no2rem = no2%10;
    //         int temp = (carry + no1rem +no2rem);
    //         carry = temp /base;
    //         res += (temp% base)*pow;
    //         pow *=10;
    //         no1 /=10;
    //         no2 /=10;
            
    //     }

    //     while(no1 != 0)
    //     {
    //         if(carry >0)
    //         {   int rem = no1%10;
    //             int temp = rem + carry;
    //             res += (temp%base) * pow;
    //             carry = temp/base;
    //             pow *=10;
    //             no1 /= 10;
    //         }else{
    //            int rem = no1%10; 
    //            res += rem * pow;
    //            no1 /=10;
    //            pow *=10;
    //         }
            
    //     }

    //     while(no2 != 0)
    //     {
    //         if(carry >0)
    //         {   int rem = no2%10;
    //             int temp = rem + carry;
    //             res += (temp%base) * pow;
    //             carry = temp/base;
    //             pow *=10;
    //             no2 /= 10;
    //         }else{
    //           int rem = no2%10;  
    //           res += rem * pow;
    //           no2 /=10;
    //           pow *=10;                  
    //         }
    //     }

    //     if( carry >0)
    //     {
    //         res += carry*pow;
    //     }


        return res;

    }

    public static int anybasesubtraction(int base, int a, int b)
    {
        int res =0, borrow = 0,pow =1;
        while( a!=0 || b !=0 )
        {
            int diff = (borrow + b%10) - a%10;
            if( diff <0)
            {
                borrow = -1;
                diff += base;
            }else{
                borrow =0;
            }
            a /=10;
            b /=10;
            int digit = diff;

           res += digit*pow;
           pow *=10;


        }
        return res;
    }
    public static int normalmulti( int base,int a, int b)
    {
        int res =0, pow =1, carry = 0;
        while( a != 0 || carry !=0)
        {
            int mult = (a%10 *b) + carry;
            a /=10;
            int dig = mult%base;
            carry = mult /base;
            res += dig*pow;
            pow *=10;
        }
        return res;
    }

    public static int anybasemutiplication( int base, int a, int b)
    {
        int ans =0, pow =1;
        while(b !=0)
        {
            int d = b%10;
            b /=10;
            int temp = normalmulti(base, a, d) *pow;
            ans = anybaseaddition(base, ans, temp);
            pow *=10;
        }
        return ans;
    }
    public static void  main(String[] args) {
        // int n = sc.nextInt();
        int base = sc.nextInt();
        int no1 = sc.nextInt();
        int no2= sc.nextInt();
        // int res = dtb(n);
        // System.out.println(res);
        int res = anybasemutiplication(base,no1,no2);
        // int res = btodec(n);
        System.out.println(res);
    }
}
