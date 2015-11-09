package assignment_final;
import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;
public class UserRecord implements Serializable{
	private String date;
	private String MovieTitle;
	private double payment;
	private String transactionID;
	private double price;
	private String location;

	public UserRecord(String date, String movieTitle, String transactionID, double price, String location)
	{
		this.date = date;
		this.MovieTitle = movieTitle;
		this.transactionID = transactionID;
		this.price = price;
		this.location = location;
		
	}
	public String getLocation(){
		return location;
	}
	public String getDate()
	{
		return date;
	}
	public String getMovieTitle(){
		return MovieTitle;
	}
	public double getPayment(){
		return payment;
	}
	public double getPrice(){
		return price;
	}
	public String getTID(){
		return transactionID;
	}
}
