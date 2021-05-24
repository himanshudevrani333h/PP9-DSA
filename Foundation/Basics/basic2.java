import java.util.*;
class basic2{

 static Scanner sc = new Scanner(System.in);
  public static void isprime( int m, int n )
  { 
    //  for only finding prime or not
    //  while(div*div <= m)
    //  {
    //     if( m% div ==0)
    //     {
    //         break;
    //     }
    //     div++;
    //  }
    // if( div * div > m)
    // {
    //     return true;
    // }else{
    //     return false;
    // }
    // for printing only prime no existing bw low and high no
    for( int i = m; i<=n; i++)
    {
        int div = 2;
       while(div*div <= i)
      {
        if( i% div ==0)
        {
            break;
        }
        div++;
     }
    if( div * div > i)
    {
        System.out.println(i);
    
    }
    
  }
  }


    public static void main(String[] args)
    {
    
        int low = sc.nextInt();
        int high =  sc.nextInt();
        isprime(low , high);
        // System.out.println(res);
   
    }
}
