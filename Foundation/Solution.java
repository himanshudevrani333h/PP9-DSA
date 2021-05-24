
// public class Solution {

//     public static int fact(int n)
//     {
//         int fact = 1;
//         for( int i =n; i>0; i--)
//         {
//             fact *= i;
//         }

//         return fact;
//     }
//     public static int ncr(int n, int r)
//     {
//         int factn = fact(n);
//         int factr = fact(n-r);

//         return factn/factr;

//     }

//     public static Float tempconv(int tf)
//     { 
//         return  (Float)((tf - 32) * (float)5/9);

//     }

//     public static void removeduplicate(String str)
//     {
//         StringBuilder sb = new StringBuilder();
//        int i =0, j =1;

//        while(i<str.length() && j< str.length())
//        {
//            char ch = str.charAt(i);
//            char ch2 = str.charAt(j);
//            if( ch != ch2)
//            {
//                sb.append(ch);
//            }

//            i++;
//            j++;
//        }
//        sb.append(str.charAt(str.length()-1));
//        System.out.println(sb.toString());
//     }
//     public static void main(String[] args) {
//         // Scanner sc = new Scanner(System.in);
//         // int n =sc.nextInt();
//         // // int r = sc.nextInt();
//         System.out.println(tempconv(-40));
//         // removeduplicate("aaabbbcccccdda");
//         // int ns = n-1;
//         // int nstr = 1;

//         // for( int i = 1; i<=n; i++)
//         // {
//         //     int strno =i;
//         //     for( int sp =1; sp<=ns; sp++)
//         //     {
//         //         System.out.print("  ");
//         //     }
//         //     for( int str = 1; str<=nstr; str++)
//         //     {
//         //         System.out.print(strno+" ");
//         //         if( str <= (nstr/2))
//         //         {  
//         //             strno++;
//         //         }else
//         //         { strno--;

//         //         }
//         //     }

//         //     ns -= 1;
//         //     nstr += 2;
//         //     System.out.println();
//         // }
//     }
// }

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        Customer[] customers = new Customer[n];
        for (int i = 0; i < customers.length; i++) {
            int id = scan.nextInt();
            scan.nextLine();
            String name = scan.nextLine();
            double salary = scan.nextDouble();
            int age = scan.nextInt();
            customers[i] = new Customer(id, name, salary, age);
        }
        double salary1 = scan.nextDouble();
        double avg = findAverageSalaryOfCustomer(customers);
        if (avg > 0) {
            System.out.println("Average of salary " + avg);
        }
        Customer c = searchCustomerBySalary(customers, salary1);
        if (c != null) {
            System.out.println("id-" + c.getId());
            System.out.println("name-" + c.getName());
            System.out.println("salary-" + c.getSalary());
            System.out.println("age-" + c.getAge());
        } else {
            System.out.println("No Customer found with mentioned attribute");
        }
    }

    public static double findAverageSalaryOfCustomer(Customer[] customers) {
        double sum = 0;
        int count = 0;
        for (int i = 0; i < customers.length; i++) {
            sum += customers[i].getSalary();
            count++;
        }
        if (sum == 0) {
            return 0;
        } else {
            return sum / count;
        }
    }

    public static Customer searchCustomerBySalary(Customer[] customers, double salary) {
        for (int i = 0; i < customers.length; i++) {
            if (salary == customers[i].getSalary()) {
                return customers[i];
            }
        }
        return null;
    }
}

class Customer {
    int id;
    String name;
    double salary;
    int age;

    public Customer(int id, String name, double salary, int age) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public int getAge() {
        return age;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setAge(int age) {
        this.age = age;
    }
}