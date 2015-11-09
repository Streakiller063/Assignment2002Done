package assignment_final;

import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;

/*
 * 
 */

public class Movie implements Serializable {
	private String title;
	private String director;
	private String casts;
	private int showingTime;
	private Date releaseDate;
	private String showingStatus;
	private String synopsis;
	private double overallRating;
	private Date startPeriod;
	private Date endPeriod;
	private int totalTicketSales;
	private ArrayList<Review> array_review = new ArrayList<Review>();
	static Scanner sc = new Scanner(System.in);

	public static class Comparators{
		public static Comparator<Movie> ToTiSales = new Comparator<Movie>(){
			@Override
			public int compare(Movie m, Movie other){
				/*
				 * return (-), 0 , or (+).
				 */
				return -m.totalTicketSales + other.getTotalTicketSales();
			}
		};
		public static Comparator<Movie> OvAll = new Comparator<Movie>(){
			@Override
			public int compare(Movie m, Movie other){
				/*
				 * return (-), 0 , or (+).
				 */
				if(m.getOverallRating()>other.getOverallRating())
					return -1;
				else if(m.getOverallRating()==other.getOverallRating())
					return 0;
				else
					return 1;
			}
			
			
		};
	}
	
	public void addReview() {
		String review;
		int rating;
		System.out.println("Please type in your review about this movie(Do not press enter until finish)");
		review = sc.nextLine();
		System.out.println("Please rate this movie(1-5)");
		rating = sc.nextInt();
		sc.nextLine();
		Review rev = new Review(review, rating);
		array_review.add(rev);
		System.out.println("Thank you. Your review has been submitted");
	}

	// find overallRating
	public double getOverallRating() {
		double total = 0;

		for (int i = 0; i < array_review.size(); i++) {
			total += array_review.get(i).getRating();
		}
		return total / (array_review.size());
	}

	public void printReview() {
		int size = 0;
		if (array_review.size() < 5) {
			size = array_review.size();
		} else
			size = 5;
		System.out.println("Here are the some reviews: ");
		if (size == 0)
			System.out.println("There are no reviews yet");
		else {
			for (int i = 0; i < size; i++) {
				System.out.println((i + 1) + ". " + array_review.get(i).getReview());
			}
			System.out.println("The rating for this movie is currently: " + getOverallRating());
		}
	}

	public void setNewMovie() {
		this.setTitle();
		this.setDirector();
		this.setCasts();
		System.out.println("Enter release date");
		this.setReleaseDate();
		this.setShowingStatus();
		this.setShowingTime();
		this.setSynopsis();
		System.out.println("Enter start date");
		this.setstartPeriod();
		System.out.println("Enter end date");
		this.setendPeriod();
		System.out.println("Done");
	}

	public static Date dateFromString(String a) {
		String[] s = new String[3];
		s = a.split(" ");
		int year, month, day;
		year = Integer.parseInt(s[0]);
		month = Integer.parseInt(s[1]);
		day = Integer.parseInt(s[2]);
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.DATE, day);
		return cal.getTime();
	}

	public static Date dateFromInput() {

		int year, month, day;
		System.out.println("Enter year: ");
		year = sc.nextInt();
		System.out.println("Enter month: ");
		month = sc.nextInt();
		System.out.println("Enter day: ");
		day = sc.nextInt();
		sc.nextLine();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.DATE, day);
		return cal.getTime();
	}

	public void setTitle(String a) {
		this.title = a;
	}

	public void setDirector(String a) {
		this.director = a;
	}

	public void setCasts(String a) {
		this.casts = a;
	}

	public void setShowingTime(String a) {
		this.showingTime = Integer.parseInt(a);
	}

	public void setReleaseDate(String a) {
		this.releaseDate = dateFromInput();
	}

	public void setShowingStatus(int a) {
		switch (a) {
		case 1: {
			showingStatus = "ComingSoon";
			break;
		}
		case 2: {
			showingStatus = "Preview";
			break;
		}
		case 3: {
			showingStatus = "NowShowing";
			break;
		}
		case 4: {
			showingStatus = "EndOfShowing";
			break;
		}
		}
	}

	public void setSynopsis(String a) {
		this.synopsis = a;
	}

	public void setstartPeriod(String a) {
		this.startPeriod = dateFromString(a);
	}

	public void setendPeriod(String a) {
		this.endPeriod = dateFromString(a);
	}

	public void setOverallRating(String a) {
		this.overallRating = Double.parseDouble(a);
	}

	public void setTotalTicketSales(String a) {
		this.totalTicketSales = Integer.parseInt(a);
	}

	public void setTitle() {
		System.out.println("Enter title for the movie: ");
		title = sc.nextLine();
	}

	public void setDirector() {
		System.out.println("Enter director for the movie: ");
		director = sc.nextLine();
	}

	public void setCasts() {
		System.out.println("Enter casts for the movie: ");
		casts = sc.nextLine();
	}

	public void setShowingTime() {
		System.out.println("Enter Showing Time for the movie: ");
		showingTime = sc.nextInt();
		sc.nextLine();
	}

	public void setReleaseDate() {
		this.releaseDate = dateFromInput();
	}

	public void setShowingStatus() {
		System.out.println("1. Coming Soon");
		System.out.println("2. Preview");
		System.out.println("3. Now Showing");
		System.out.println("4. End of Showing");
		System.out.println("Choose status: ");
		switch (sc.nextInt()) {
		case 1: {
			showingStatus = "ComingSoon";
			break;
		}
		case 2: {
			showingStatus = "Preview";
			break;
		}
		case 3: {
			showingStatus = "NowShowing";
			break;
		}
		case 4: {
			showingStatus = "EndOfShowing";
			break;
		}
		}
		sc.nextLine();
	}

	public void setSynopsis() {
		System.out.println("Enter Synopsis for the movie: ");
		synopsis = sc.nextLine();
	}

	public void setstartPeriod() {
		this.startPeriod = dateFromInput();
	}

	public void setendPeriod() {
		this.endPeriod = dateFromInput();
	}

	public void setOverallRating(double a) {
		this.overallRating = a;
	}

	public void incTotalTicketSales() {
		this.totalTicketSales++;
	}

	public void writetoDatabase() {
		try {
			FileWriter fwStream = new FileWriter("movieDatabase.txt", true);
			BufferedWriter bwStream = new BufferedWriter(fwStream);
			PrintWriter pwStream = new PrintWriter(bwStream);
			SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy MM dd");
			pwStream.println(title);
			pwStream.println(director);
			pwStream.println(casts);
			pwStream.println(showingTime);
			pwStream.println(dateFormatter.format(releaseDate));
			pwStream.println(showingStatus);
			pwStream.println(synopsis);
			pwStream.println(dateFormatter.format(startPeriod));
			pwStream.println(dateFormatter.format(endPeriod));
			pwStream.println(overallRating);
			pwStream.println(totalTicketSales);
			pwStream.println("end");
			pwStream.close();

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public String getTitle() {
		return this.title;
	}

	public String getSynopsis() {
		return this.synopsis;
	}

	public String gettDirector() {
		return this.director;
	}

	public String getCasts() {
		return this.casts;
	}

	public int getShowingTime(int a) {
		return this.showingTime;
	}

	public Date getReleaseDate() {
		return this.releaseDate;
	}

	public String getShowingStatus() {
		return this.showingStatus;
	}

	public Date getstartPeriod() {
		return this.startPeriod;
	}

	public Date getendPeriod() {
		return this.endPeriod;
	}

	public double getOverallRating(double a) {
		return this.overallRating;
	}

	public int getTotalTicketSales() {
		return this.totalTicketSales;
	}

}
