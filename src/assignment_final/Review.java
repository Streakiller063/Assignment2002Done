package assignment_final;
import java.util.*;
import java.io.*;

public class Review implements Serializable {
	private String review;
	private int rating;
	static Scanner sc = new Scanner(System.in);
	public Review(String review, int rating)
	{
		this.review = review;
		this.rating = rating;
	}


	public String getReview(){
		return review;
	}
	public int getRating(){
		return rating;
	}

}
