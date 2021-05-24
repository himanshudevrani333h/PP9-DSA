import java.util.Scanner;
class Bike {
private String bikeNo;
public String getBikeNo() {
	return bikeNo;
}
public void setBikeNo(String bikeNo) {
	this.bikeNo = bikeNo;
}
public String getBikeModel() {
	return bikeModel;
}
public void setBikeModel(String bikeModel) {
	this.bikeModel = bikeModel;
}
public String getCompany() {
	return company;
}
public void setCompany(String company) {
	this.company = company;
}
public int getMileage() {
	return mileage;
}
public void setMileage(int mileage) {
	this.mileage = mileage;
}
public int getPrice() {
	return price;
}
public void setPrice(int price) {
	this.price = price;
}
private String bikeModel;
private String company;
private int mileage;
private int price;

}
public class tcs 
{
	
	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);	
		Bike arr [] = new Bike[4];
		for(int i =0;i<4;i++)
			{
			Bike t = new Bike();
			String bikeNo = sc.nextLine();
			String bikeModel = sc.nextLine();
			String company = sc.nextLine();
			int mileage = sc.nextInt();
			int price = sc.nextInt();
			t.setBikeModel(bikeModel);
			t.setBikeNo(bikeNo);
			t.setCompany(company);
			t.setMileage(mileage);
			t.setPrice(price);
			sc.nextLine();
			arr[i]=t;
			}
		
		String company = sc.nextLine();
		String end = sc.nextLine();
//		System.out.println(company+end);
		int average = findAverageMileageByCompany(arr,company);
		if(average == 0)
		{
			System.out.println("No Bikes in the given Company");
		}
		else {
			System.out.println(average);
		}
		Bike []result = searchByBikeModel(arr,end);
		if (result== null)
		{
			System.out.println("No such bikes with the given model");
		}
		else
		{
			for(int i =0;i<result.length;i++)
			{
				System.out.println(result[i].getCompany());
				System.out.println(result[i].getBikeModel());
			}
		}
	}
	
public static int findAverageMileageByCompany(Bike [] arr,String company)
{
	int average=0;
	int count =0;
	int len = arr.length;
	for(int i=0;i<len;i++)
	{
		Bike temp = arr[i];
		if(temp.getCompany().equals(company))
		{
			average= average+temp.getMileage();
//			System.out.println(average);
			count++;
		}
	}
	if(count>0)
	{
		average = average/count;
	}
//	System.out.println(average);
	return average;
	}
public static Bike[] searchByBikeModel(Bike [] arr,String end)

{
	
	int len = arr.length;
	Bike [] new_arr = new Bike[len];
	int count=0;
	for(int i=0;i<len;i++)
	{
		Bike temp = arr[i];
		if(temp.getBikeModel().endsWith(end))
		{
			new_arr[count]=temp;
			count++;
		}
		
	}
	if(count ==0)
		return null;
	for(int i =0;i<count;i++)
	{
		for(int j=i+1;j<count-1;j++)
		{
			if(new_arr[i].getPrice()>new_arr[j].getPrice())
			{
				Bike t = new Bike();
				t = new_arr[i];
				new_arr[i]=new_arr[i+1];
				new_arr[i+1]=t;
			}
		}
	}
	Bike main_return[] = new Bike[count];
	for(int i =0;i<count;i++)
	{
		main_return[i]=new_arr[i];
	}
	return main_return;
	}
}