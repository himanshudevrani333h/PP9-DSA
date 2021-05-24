
import java.util.Scanner;
public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Bike[] instArray = new Bike[4];
        for(int i=0;i<4;i++){
            String bikeno = sc.nextLine();
            sc.nextLine();
            String bikemodel = sc.nextLine();
            String company = sc.nextLine();
            int milage = sc.nextInt();
            sc.nextLine();
            int price= sc.nextInt();
            instArray[i] = new Bike(bikeno,bikemodel,company,milage,price);
        }
        String company = sc.nextLine();
        String modellast = sc.nextLine();
        sc.close();
        int bikefun1 = findAverageMilagebycompany(instArray, company);
        if(bikefun1!=0) System.out.println(bikefun1);
        else System.out.println("No Bikes in the given company");
        
        Bike updated = searchbybikemodel(instArray, modellast);
        if(updated!=null) System.out.println(updated.getprice()+"::"+updated.getbikemodel());
        else System.out.println("No bike");
    }
    
    public static int findAverageMilagebycompany( Bike[] instArray, String company) 
    {
        int noOfClearance = 0;
        int count = 0;
        for(int i=0;i<instArray.length;i++){
            if(instArray[i].getcompany() == company){
                count++;
            int mil = instArray[i].getmilage();
            noOfClearance += mil;
            }

        }
        if(noOfClearance >0){
        int avg = noOfClearance/count;
        return avg;
          }else{

        return noOfClearance;
          }
    }

    public static Bike searchbybikemodel(Bike[] instArray, String modellast) 
    {   
        for(int i=0;i<instArray.length;i++){
            if(instArray[i].getbikemodel().contains(modellast)){
                return instArray[i]; // yahn sort karke bhjna hai bss
            }
        }
        return null;
  }
}

class Bike {
    
    String bikeno;
    String bikemodel;
    String company;
    int milage;
    int price;

    Bike(String bikeno,String bikemodel,String company,int milage,int price){
        this.bikeno = bikeno;
        this.bikemodel = bikemodel;
        this.company = company;
        this.milage = milage;
        this.price = price;
    }

   

    String getbikeno(){
        return bikeno;
    }

    String getbikemodel(){
        return bikemodel;
    }

    String getcompany(){
        return company;
    }
    
    int getmilage(){
        return milage;
    }
    int getprice(){
        return price;
    }
    


    void setbikeno(String bikeno){
        this.bikeno = bikeno;
    }

    void setbikemodel(String bikemodel){
        this.bikemodel = bikemodel;
    }

    void setcompany(String company){
        this.company = company;
    }

    void setmilage(int milage){
        this.milage = milage;
    }

    void setprice(int price){
        this.price = price;
    }

    
}