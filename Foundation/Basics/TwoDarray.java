import java.util.*;
public class TwoDarray {

public static Scanner sc = new Scanner(System.in);
public static void input(int a[][] )
    {
        for( int i =0; i<a.length; i++)
        {
            for(int j =0; j<a[0].length; j++)
            {
                a[i][j] = sc.nextInt();
            }
        }
    }
public static void display(int a[][])
    {
      for( int i =0; i<a.length; i++)
      {
          for( int j =0; j<a[0].length; j++)
          {
              System.out.println(a[i][j]);
          }
      }
    }
    
public static void display2(int a[][])
    {
      for( int []arr :a)
      {
          for( int e:arr)
          {
              System.out.println(e);
          }
          System.out.println();
      }
      
    }

public static int max( int a[][])
    {
        int max = -(int)1e9;
        for( int i =0; i<a.length; i++)
        {
            for( int j =0; j<a[0].length; j++)
            {
                if(a[i][j] > max){
                    max = a[i][j];
                }
            }
        }
        return max;
    }

public static int min( int a[][])
    {
        int min = (int)1e9;
        for( int i =0; i<a.length; i++)
        {
            for( int j =0; j<a[0].length; j++)
            {
                if(a[i][j] < min){
                    min = a[i][j];
                }
            }
        }
        return min;
    }

public static boolean find(int a[][], int data)
    {
        for(int i=0; i<a.length; i++)
        {
            for( int j =0; j<a[0].length; j++)
            {
                if(a[i][j] == data) return true;
            }
        }
        return false;
    }

public static void wavelefttoright(int a[][])
  {
      
      for( int j =0; j<a[0].length; j++)
      {
          if(j %2 == 0)
          {
             for( int i =0; i<a.length; i++)
             {
                 System.out.println(a[i][j]);
             }
          }else{
            for( int i =a.length-1; i>=0; i--)
            {
                System.out.println(a[i][j]);
            }
          }
      } 
  }

public static void stateOfWakanda(int[][] arr) {
    int n = arr.length, m = arr[0].length;
    for (int j = 0; j < m; j++) {
        if (j % 2 == 0) {
            for (int i = 0; i < n; i++)
                System.out.println(arr[i][j]);
        } else {
            for (int i = n - 1; i >= 0; i--)
                System.out.println(arr[i][j]);
        }
    }
}

public static void stateOfWakanda_FB(int[][] arr) {
    int n = arr.length, m = arr[0].length;
    for (int i = 0; i < n; i++) {
        if (i % 2 == 0) {
            for (int j = 0; j < m; j++)
                System.out.println(arr[i][j]);
        } else {
            for (int j = m - 1; j >= 0; j--)
                System.out.println(arr[i][j]);
        }
    }
}

//stateOfWakanda2
public static void diagonalPrint(int[][] arr) {
    int n = arr.length, m = arr[0].length;
    for (int gap = 0; gap < m; gap++) {
        for (int i = 0, j = gap; i < n && j < m; i++, j++) {
            System.out.println(arr[i][j]);
        }
    }
}

public static void FulldiagonalPrint(int[][] arr) {
    int n = arr.length, m = arr[0].length;
    for (int gap = n-1; gap >= 1; gap--) {
        for (int i = gap, j = 0; i < n && j < m; i++, j++) {
            System.out.println(arr[i][j]);
        }
    }
    
    for (int gap = 0; gap < m; gap++) {
        for (int i = 0, j = gap; i < n && j < m; i++, j++) {
            System.out.println(arr[i][j]);
        }
    }
}

public static void spiral(int a[][])
{
    int n = a.length;
    int m = a[0].length;
    int rmin =0;
    int cmin =0;
    int rmax = n-1;
    int cmax = m-1;
    int tc = n*m;
    int c =0;
    while( c<tc)
    {
        for( int i =rmin, j = cmin; i<=rmax && c<tc; i++)
        {
            System.out.println(a[i][j]);
            c++;
        }
        cmin++;
        for( int i =rmax, j = cmin; j<=cmax && c<tc; j++)
        {
            System.out.println(a[i][j]);
            c++;
        }
        rmax--;
        for( int i =rmax, j = cmax; i>=rmin && c<tc; i--)
        {
            System.out.println(a[i][j]);
            c++;
        }
        cmax--;
        for( int i = rmin, j = cmax; j>=cmin && c<tc; j--)
        {
            System.out.println(a[i][j]);
            c++;
        }
        rmin++;
    }
 
}

// public static void exitpoint(int a[][])
// {
//     int n= a.length;
//     int m = a[0].length;
//     int i =0;
//     int j =0;
//     int tr=0;
//     int tc =0;
//     while(i!=n && i >0 ||j!=m && j>0)
//     {   
//         for( int r =0, c = 0; c<m && (i!=n && i>0 && j!=m && j>0); c++)
//         {   i =r;
//             j =c;
//             tr =r;
//             tc= c;
//             if(a[r][c] != 1)
//             {
//                 continue;
//             }else
//              tr +=1;
//              i+=1;
//              break;
//         }
        
//         for( int r =tr,c = tc; r<n && (i!=n && i>0 && j!=m && j>0); r++)
//         {
//             i =r;
//             j =c;
//             tr =r;
//             tc =c;
//             if(a[r][c] != 1)
//             {
//                 continue;
//             }else
//             tc -=1;
//             j -=1;
//              break;
//         }

//         for( int r = tr,c = tc; c>m && (i!=n && i>0 && j!=m && j>0); c--)
//         {
//             i =r;
//             j =c;
//             tr =r;
//             tc =c;
//             if(a[r][c] != 1)
//             {
//                 continue;
//             }else
//             tr -=1;
//             i -=1;
//              break;
//         }
//         for( int r = tr, c = tc; r<n && (i!=n && i>0 && j!=m && j>0); r-- )
//         {
//             i =r;
//             j =c;
//             tr =r;
//             tc =c;
//             if(a[r][c] != 1)
//             {
//                 continue;
//             }else
//             tr -=1;
//             i -=1;
//              break;
//         }
 
//     }
//   if( i == n) i = i-1;
//   if(j == m) j = j-1;
//   if(i <0) i = i+1;
//   if( j <0) j = j+1;
//   System.out.println(i+","+j);
// }

public static void exitpoint(int a[][])
{
    int n = a.length,m = a[0].length;
    int i =0,j=0,dir =0;
    
    while(true)
    {
        dir =(dir +a[i][j])%4;
        if( dir ==0)
        {
           j++;
           if( j == m)
           {
               System.out.println(i+"\n"+(j-1));
               break;
           }
        } else if( dir ==1)
        {
          i++;
          if( i == n)
           {
               System.out.println((i-1)+"\n"+(j));
               break;
           }
        }else if(dir == 2)
        {
           j--;
           if( j < 0)
           {
               System.out.println(i+"\n"+(j+1));
               break;
           }
        }else{
          i--;
          if( i < 0)
           {
               System.out.println((i+1)+"\n"+(j));
               break;
           }
        }
    }
}

public static int[][] rotateby90(int a[][])  //time complexity is higher ie.n3
{   
    int n = a.length;
    int m = a[0].length;
    int res[][] = new int[n][m];
    int nr =0,nc =0;
    while(nr !=n-1 && nc != m-1)
    {
        for( int i = n-1; i>=0; i--)
        {
            for( int j = 0; j<m; j++)
            {
                res[nr][nc] = a[i][j];
                nr++;
            }
            nr =0;
            if(nc < m-1) nc++;
            
        }
    }
    return res;
}

public static void swap(int[][]a, int i1,int j1, int i2, int j2)
{
    int temp = a[i1][j1];
    a[i1][j1] = a[i2][j2];
    a[i2][j2] = temp;

}
public static void rotatearray2(int a[][])
 {  
    int n = a.length,m = a[0].length;
    // transpose
    for( int i =0; i<n; i++)
    {
        for( int j =i; j<m; j++)
        {
            swap(a,i,j,j,i);
        }
    }

    // column shift
    int si =0, li=m-1;
    while(si <li)
    {
      for( int i =0; i<n; i++)
      {
          swap(a,i,si,i,li);
      }
      si++;
      li++;
    }
 
}

public static int checkmax(int a[][], int c)
{   int max = -(int)1e9;
    int r = -1;
    for( int i=0; i<a.length; i++)
    {
        if(a[i][c] > max){
         max = a[i][c];
         r = i;   
        }
    }
    return r;
}
public static void saddlepoint(int a[][])
{
    int n= a.length,m = a[0].length;
    int rmin = (int)1e9,c =-1;
    boolean flag = false;
    for( int i =0; i<n; i++)
    {
        for( int j = 0; j<m; j++)
        {
           if(a[i][j]<rmin)
           {
               rmin = a[i][j];
               c = j;
           }
        }

        int ridx = checkmax(a,c);
        if(ridx == i)
        { 
            System.out.println(a[ridx][c]);
            flag = true;
          }
    }
    if(!flag)
    System.out.println("Invalid Number");
    

}
public static void searchinasorted2darray(int a[][], int data)
{
    int n = a.length, m = a[0].length;
    int i =0, j = m-1;
    boolean flag = false;
    while(i<n && j>=0)
    {
        if(a[i][j] < data){
            i++;
        }else if(a[i][j]>data)
        {
            j --;
        }else
        {
            System.out.println(i);
            System.out.println(j);
            flag = true;
            break;
        }

    }
    if(!flag)
    System.out.println("Not Found");
}
public static void main(String[] args) {
    int arr[][] = new int[sc.nextInt()][sc.nextInt()];
    // int data = sc.nextInt();
    input(arr);
    // wavelefttoright(arr);
    int res[][]=rotateby90(arr);
    display(res);
    // int min =  min(arr);
    // int max=  max(arr);
    // boolean find= find(arr, data);
    // System.out.println("min -> "+min+"  "+"max-> "+max+"  "+find);
    }

}
