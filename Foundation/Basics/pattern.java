import java.util.*;
public class pattern{
static Scanner sc = new Scanner(System.in);
public static void printpattern(int n)
{
    int nst =n;
    for( int r =0; r<n; r++)
    {
        for( int cstr =0; cstr<nst; cstr++)
        {
            System.out.print("* ");
        }
        System.out.println();
    }
}

public static void trianglepattern(int n)
{
    int nst =1;
    for( int r =0; r<n; r++)
    {
        for( int cstr =0; cstr<nst; cstr++)
        {
            System.out.print("* ");
        }
        nst++;
        System.out.println();
    }
}


public static void trianglspaceepattern(int n)
{
    int nst = 1;
    int nsp = n-1;
    for( int r =0; r<n; r++)
    {
        for( int csp = 1; csp<=nsp; csp++)System.out.print("\t");
        for(int cst =1; cst<= nst; cst++)System.out.print("*\t");
         nst++;
         nsp--;
        System.out.println();
    }
    
}

 public static void pattern5(int n)
   {
       int str = 1;
       int sp = n/2;
       
      for( int r = 1; r<=n; r++){
           for( int nsp = 1; nsp<=sp ; nsp++) System.out.print("\t");
           for( int nstr = 1; nstr <= str; nstr++) System.out.print("*\t");
       
         if( r <= n/2){
           sp--;
           str += 2;
            
        }else
        {
            str -=2;
            sp++;
        }
        System.out.println();
      }
       
   }

 public static void pattern6(int n)
   {
       int str = n/2 +1;
       int sp = n/2;
       
      for( int r = 1; r<=n; r++){
           for( int nstr = 1; nstr <= str; nstr++) System.out.print("*\t");
           for( int nsp = 1; nsp<=sp ; nsp++) System.out.print("\t");
           for( int nstr = 1; nstr <= str; nstr++) System.out.print("*\t");
       
         if( r <= n/2){
           sp += 2;
           str--;
            
        }else 
        {
            str++;
            sp -=2;
        }
        System.out.println();
      }
       
   }

 public static void pattern7(int n)
   {
       int str = 1;
       int sp = n/2;
       
       for( int r = 1; r<=n; r++){
          int val = r;
          for( int nsp = 1; nsp<=sp ; nsp++) System.out.print("\t");

           if( r > n/2 +1) val = n- r +1;

           for( int nstr = 1; nstr <= str; nstr++)
           { 
               System.out.print(val+"\t");  
               if( nstr <= str/2 )
               {
                   val++;
               }else{
                   val--;
               }

             }
         
          if( r <= n/2){
           sp --;
           str +=2;
            
        }else 
        {
            str -=2;
            sp ++;
        }
        System.out.println();
      }
       
   }

public static void diagonal( int n)
{
    int colstr = n;
    for( int r =1; r<=n; r++)
    {
        for( int c = 1; c<=colstr; c++)
        {  
            if( r== c || r+c == n+1)
            {
               System.out.print("*\t");
            }else
            System.out.print("\t");
        }
        System.out.println();
    }

}

public static void singlediagonal( int n)
{
    for( int r =1; r<=n; r++)
    {
        for( int j = 1; j<=r; j++)
        {
            if( j == r)
            {
                System.out.print("*\t");
            }else
            System.out.print("\t");
        }
        System.out.println();
    }
}

public static void singleoppositediagonal(int n)
{
    for( int r =1; r<=n; r++)
    {
        for( int c = 1; c<=n; c++)
        {
            if( r+c == n+1)
            {
                System.out.print("*\t");
            }else
            System.out.print("\t");
        }
        System.out.println();
    }
}

public static void outerdiamond( int n )
{
   int str =1;
   int isp = -1;
   int osp = n/2 +1 ;

   for(int r= 1; r<=n; r++)
   {
       for(int cosp = 1; cosp <= osp; cosp++) System.out.print("\t");
       for(int nstr =1; nstr<=str; nstr++) System.out.print("*\t");
       for(int cisp = 1; cisp <= isp; cisp++) System.out.print("\t");
       if( r > 1 && r< n) for(int nstr =1; nstr<=str; nstr++) System.out.print("*\t");
       if( r<= n/2)
       {
         isp +=2;
         osp -=1;
       }else
       {
        isp -=2;
        osp +=1;
       }

       System.out.println();

   }
   
}

public static void numberspattern(int n)
{  
    int no =1;
    for( int r =  1; r<=n; r++)
    {
        for( int c = 1; c<=r; c++) 
        {System.out.print(no+"\t");
        no++;
        }
        System.out.println();
    }
}

public static void fabnumberspattern(int n)
{  
    int no =0;
    int b =1;
    for( int r =  1; r<=n; r++)
    {
        for( int c = 1; c<=r; c++) 
        { 
            System.out.print(no+"\t");
            int cr = b + no;
            b= no;
            no =cr;
        }
        System.out.println();
    }
}
public static void ncRpattern( int n)
{   
    
        for( int row = 0; row<n; row++)
        { 
         int ncr = 1;
        for( int col =0; col<=row; col++)
        {  
            System.out.print(ncr +"\t");
            int ncr1 = (ncr*(row - col))/ (col +1);
            ncr = ncr1;
        }
        System.out.println();
        }


}

public static void pattern16( int n)
{   
    int cs = 2*n -3;
    for( int row = 1; row<=n; row++)
    {  int val = 1;
        for( int cnos = 1; cnos<= row; cnos++) System.out.print(val++ +"\t");
        for(int csp = 1; csp<= cs; csp++) System.out.print("\t");
        for( int cnos = 1; cnos<= row; cnos++) 
        {  if( row == n && cnos ==1)
            {
                val--;
                continue;
            }
            System.out.print(--val +"\t");
        }
        cs -= 2;
        System.out.println();
    }
}
public static void arrow( int n)
{
    int str = 1;
    int sp = n/2;
    for( int i =1; i<=n; i++)
    {   if( i <= n/2 || i> n/2+1){
        for( int j =1; j<=sp; j++)
        System.out.print("\t");
          }
        if(i != n/2 +1){
         for( int k =1; k<=str; k++)
          System.out.print("*\t");
        }else{
            for( int k =1; k<=n; k++)
            System.out.print("*\t");
        }
         if( i <= n/2) str++;
         else
         str--;

         System.out.println();
        

    }
}

public static void hollowandfulltriangle(int n)
{   
  int str = n;
  int sp =0;
  for( int r = 1; r<=n; r++)
  {
      for( int nsp =1; nsp<=sp; nsp++) System.out.print("\t");
      for( int nstr = 1; nstr<=str; nstr++)
      {
          if( r >1 && nstr>1 && nstr < str && r<= n/2)
          {
              System.out.print("\t");
          }else
          System.out.print("*\t");
      }

      if( r<=n/2)
      {
       sp++;
       str -=2;
      }else
      { 
        sp--;
        str +=2;
      }
      System.out.println();
  }
    
}

public static void swastikpattern( int n)
{
    for( int r = 1; r<=n; r++)
    {
        for( int j =1; j<=n; j++)
        {
            if( r == 1)
            {
                if( j <= n/2 +1 || j == n)
                {
                    System.out.print("*\t");
                }else{
                    System.out.print("\t");
                }
            }else if( r >1 && r<= n/2  )
            {
                if( j == n || j == n/2+1)
                {
                    System.out.print("*\t");
                }else{
                    System.out.print("\t");
                }
            }else if( r == n/2+1)
            { if( j >=1)
                System.out.print("*\t");
            }else if( r > n/2 +1 && r<n)
            {
                if( j == n/2 +1 || j == 1)
                {
                    System.out.print("*\t");
                }else
                System.out.print("\t");
            }else if( j >= n/2 +1 || j == 1)
            {
                System.out.print("*\t");
            }else 
            {
                System.out.print("\t");
            }
        }
        System.out.println();
    }

}

public static void Wpattern( int n)
{
    for( int i = 1; i<=n; i++)
    {
        for( int j =1; j<=n; j++)
        {
             if( j == 1 || j == n)
                {
                    System.out.print("*\t");
                }else if( i > n/2 && ( j == i || j + i == n+1) )
               {  System.out.print("*\t");
                
            }else System.out.print("\t");
        }
        System.out.println();
    }
}
public static void main(String[] args)
{ int n = sc.nextInt();
// printpattern(n);
// trianglepattern(n);
// trianglspaceepattern(n);
// pattern5(n);
// pattern6(n);
// pattern7(n);
// singleoppositediagonal(n);
// singlediagonal(n);
// diagonal(n);
// outerdiamond(n);
// numberspattern(n);
// fabnumberspattern(n);
// ncRpattern(n);
// pattern16(n);
// arrow(n);
// hollowandfulltriangle(n);
// swastikpattern(n);
Wpattern(n);
}
}