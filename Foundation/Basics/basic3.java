import java.util.*;
public class basic3{

static Scanner sc = new Scanner(System.in);

public static void rotatenumber(int n, int k)
 {
    int count =0;
    int ov = n;
    while( ov!=0)
    {
     ov = ov/10;
    count++;
    }

   k = k%count;
  
   if( k<0)
   {
     k = count % k;
   }

 int mul = 1;
 int div =1;
 for( int i =1; i<=count; i++)
 {
    if(i <=k)
    {
    div = div *10;

    }else
    {
        mul = mul *10;
    }
 }

 int quo = n / div;
 int rem = n % div;

 System.out.println(rem * mul + quo); 


}

public static void printlinebyline(int n )
{  int pow = powofdigit(n);
    
    while(pow != 0)
    {
       int quo = n/pow;
       n = n%pow;
       pow /=10;
       System.out.println(quo);   
    }

  
}
public static void gcdLcm(int n, int m)
{
   int temp1 =n;
   int temp2 = m;

   while(n%m != 0)
   {
      int rem = n%m;
      n = m;
      m = rem;
   }
   int gcd = m;
   int lcm = (temp1 * temp2)/ gcd;
   System.out.println(gcd);
   System.out.println(lcm);
}
public static int powofdigit( int n )
{
    int pow = 1;
    n = n/10;
    while( n != 0)
    {
       pow = pow*10;
       n= n/10;
    }
    return pow;
}

public static void main(String[] args)
{
  int n = sc.nextInt();
  int k = sc.nextInt();
//   printlinebyline(n);
   // rotatenumber(n, k);  
   gcdLcm(n,k);
}

}