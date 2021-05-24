import java.util.*;
class basic{

static Scanner sc = new Scanner(System.in);
    public static void main(String[] args)
    {
      int n = sc.nextInt();
      multimultiplication(n);
    //   multiplication(n);
    //   for(int i  =0; i<10; i++)
    //   {
    //       int n = sc.nextInt();
    //   printoddeven(n);
    //   }
      
  }
  public static void multimultiplication(int n)
  {
      for( int i =1; i<=n;  i++)
      {
          multiplication(i);
          System.out.println();
      }
    //   for( int i =1; i<=n; i++)
    //   {
    //       int mul = i;
    //       for( int j = 1; j<=10; j++)
    //       {
    //           System.out.println(mul+" * "+j+" = "+ mul*j);
    //       }
    //       System.out.println("***---***");
    //   }
  }
  public static void multiplication(int n)
  {
      for( int i =1; i<=10; i++)
      {
          System.out.println(n+" * "+i+" = "+ n*i);
      }
  }

  public static void printoddeven( int n )
  {
      if(n%2==0)
      {
         System.out.println("even");
      }else
      {
        System.out.println("odd");
      }
  }
}