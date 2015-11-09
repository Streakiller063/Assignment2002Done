package assignment_final;
import java.io.*;
import java.util.*;
public class Pricetype implements Serializable {
	private double SeniorPrice;
	private double StudentPrice;
	private double ChildrenPrice;
	private double AdultPrice;
	private double PHPrice;
	private double threedprice;
	
	Pricetype(){	
	}
	
	public void setSeniorPrice(double a){
		this.SeniorPrice = a;
	}
	public void setAdultPrice(double a){
		this.AdultPrice = a;
	}
	public void setStudentPrice(double a){
		this.StudentPrice = a;
	}	
	public void setChildrenPrice(double a){
		this.ChildrenPrice = a;
	}
	public void setPHPrice(double a){
		this.PHPrice = a;
	}
	public void setThreeDPrice(double a)
	{
		this.threedprice = a;
	}
	
	public double getThreeDPrice()
	{
		return threedprice;
	}
	
	public double getStudentPrice(){
		return this.StudentPrice;
	}
	public double getSeniorPrice(){
		return this.SeniorPrice;
	}
	public double getChildrenPrice(){
		return this.ChildrenPrice;
	}
	public double getAdultPrice(){
		return  this.AdultPrice;
	}
	public double getPHPrice(){
		return this.PHPrice;
	}
	
	public double getPHAdultPrice(){
		return getPHPrice();
	}	
	public double getPHStudentPrice(){
		return  getStudentPrice() + getPHPrice();
	}	
	public double getPHSeniorPrice(){
		return  getSeniorPrice() + getPHPrice();
	}	
	public double getPHChildrenPrice(){
		return  getChildrenPrice() + getPHPrice();
	}
	
	
	
	/*public void printPriceList(){
		System.out.println("         | Normal Price |   3D Price   | Golden Price |");
		System.out.println("-------------------------------------------------------");
		System.out.println("Senior   | " + String.format("% 14.1f", SeniorPrice)   + "|" + String.format("% 14.1f", ticketPrice("3d","normal","senior",1))  + "|" + String.format("% 14.1f", ticketPrice("3d","gold","senior",1))  + "|");
		System.out.println("Student  | " + String.format("% 14.1f", StudentPrice)  + "|" + String.format("% 14.1f", ticketPrice("3d","normal","student",1)) + "|" + String.format("% 14.1f", ticketPrice("3d","gold","student",1)) + "|");
		System.out.println("Children | " + String.format("% 14.1f", ChildrenPrice) + "|" + String.format("% 14.1f", ticketPrice("3d","normal","children",1))+ "|" + String.format("% 14.1f", ticketPrice("3d","gold","children",1))+ "|");
		System.out.println("-------------------------------------------------------");
		System.out.println("Children below 5 don't need a ticket.");
	}*/
	
	/*public double ticketPrice(String movie_format, String audience, int quantity){
		double price = 0;
		if (audience.toLowerCase() == "student"){
			price = StudentPrice;
		}
		else if (audience.toLowerCase() == "senior"){
			price = SeniorPrice;
		}
		else{
			price = ChildrenPrice;
		}

		
		if (movie.toLowerCase() == "3d"){
			if (theatre.toLowerCase() == "gold"){
				price = price + ThreeDPrice + GoldPrice;	
			}
			else
				price += ThreeDPrice;
		}
		
		return quantity * price;
		
	}*/
	
}
