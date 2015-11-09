package assignment_final;

import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;

public class user {
	Scanner sc = new Scanner(System.in);
	ArrayList<ArrayList> array_movie = new ArrayList<ArrayList>();
	ArrayList<Movie> NowShowing = new ArrayList<Movie>();
	ArrayList<Movie> Preview = new ArrayList<Movie>();
	ArrayList<Movie> ComingSoon = new ArrayList<Movie>();
	//ArrayList<Schedule> array_Schedule = new ArrayList<Schedule>();
	ArrayList<UserId> array_user = new ArrayList<UserId>();
	public Cineplex JP = new Cineplex("Jurong Point");
	public Cineplex Clementi = new Cineplex("Clementi");
	public Cineplex Changi = new Cineplex("Changi");
	public Cineplex cin;

	public void userApp() {
		//load cineplex
		try {
			FileInputStream fis = new FileInputStream("JurongPoint.txt");
			ObjectInputStream in = new ObjectInputStream(fis);
			JP = (Cineplex) in.readObject();
			in.close();
			fis = new FileInputStream("Clementi.txt");
			in = new ObjectInputStream(fis);
			Clementi = (Cineplex) in.readObject();
			in.close();
			fis = new FileInputStream("Changi.txt");
			in = new ObjectInputStream(fis);
			Changi = (Cineplex) in.readObject();
			in.close();
			
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		/*try {
			FileInputStream fis = new FileInputStream("scheduleDatabase.txt");
			ObjectInputStream in = new ObjectInputStream(fis);
			array_Schedule = (ArrayList) in.readObject();
			in.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}*/
		try {
			FileInputStream fis = new FileInputStream("userRecord.txt");
			ObjectInputStream in = new ObjectInputStream(fis);
			array_user = (ArrayList) in.readObject();
			in.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		try {
			FileInputStream fis = new FileInputStream("movieDatabase.txt");
			ObjectInputStream in = new ObjectInputStream(fis);
			array_movie = (ArrayList) in.readObject();
			NowShowing = array_movie.get(2);
			ComingSoon = array_movie.get(0);
			Preview = array_movie.get(1);
			in.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		printMenu();
		int option;
		do {
			option = sc.nextInt();
			sc.nextLine();
			System.out.println();
			switch (option) {
			case 1: {

				System.out.println("Now Showing: ");
				for (int i = 0; i < NowShowing.size(); i++) {
					System.out.println((i + 1) + ". " + NowShowing.get(i).getTitle());
				}
				System.out.println("Preview: ");
				for (int i = 0; i < Preview.size(); i++) {
					System.out.println((i + 1) + ". " + Preview.get(i).getTitle());
				}
				System.out.println("Coming Soon: ");
				for (int i = 0; i < ComingSoon.size(); i++) {
					System.out.println((i + 1) + ". " + ComingSoon.get(i).getTitle());
				}
				System.out.println();
				System.out.println("Do you want to write a review?(y/n) ");
				if (sc.nextLine().compareTo("y") == 0) {

					writeReview();

				}

				break;
			}
			case 2: {
				booking();
				userApp();
				break;
			}
			case 3: {
				viewBookingHistory();

				break;
			}
			case 4:
				topRankedMovie();
				break;
			case 5: {
				return;
			}
			default:
				System.out.println("Input Error! Try again!\n");

			}
			printMenu();
		} while (option != 5);
	}

	public void viewBookingHistory() {
		System.out.println("Please enter your NRIC: ");
		String NRIC = sc.nextLine();
		boolean found = false;
		for (int i = 0; i < array_user.size(); i++) {
			if (NRIC.compareTo(array_user.get(i).getNRIC()) == 0) {
				System.out.println("Record for: " + array_user.get(i).getName());
				found = true;
				for (int j = 0; j < array_user.get(i).array_record.size(); j++) {
					System.out.print(array_user.get(i).array_record.get(j).getTID());
					System.out.print(" "+array_user.get(i).array_record.get(j).getLocation());
					System.out.print(" "+array_user.get(i).array_record.get(j).getDate());
					System.out.print(" " + array_user.get(i).array_record.get(j).getMovieTitle());
					System.out.println(" $"+array_user.get(i).array_record.get(j).getPrice());
				}
				break;
			}
		}
		if (found == false) {
			System.out.println("No record is found");
		}
	}

	public void addToUserRecord(String bookingDate, String movieTitle, String transactionID, double price, String location) {
		String name;
		String NRIC;
		int phone;
		System.out.println("Enter your name: ");
		name = sc.nextLine();
		System.out.println("Enter your NRIC: ");
		NRIC = sc.nextLine();
		System.out.println("Enter your phone number");
		phone = sc.nextInt();
		boolean found = false;
		UserId newuser;
		for (int i = 0; i < array_user.size(); i++) {
			if (array_user.get(i).getNRIC().compareTo(NRIC) == 0) {
				found = true;
				newuser = array_user.get(i);
				UserRecord newrecord = new UserRecord(bookingDate, movieTitle, transactionID, price, location);
				newuser.array_record.add(newrecord);
				break;
			}
		}
		if (found == false) {
			newuser = new UserId(NRIC, name, phone);
			UserRecord newrecord = new UserRecord(bookingDate, movieTitle, transactionID, price, location);
			newuser.array_record.add(newrecord);
			array_user.add(newuser);
		}
		try {
			FileOutputStream fos = new FileOutputStream("userRecord.txt");
			ObjectOutputStream out = new ObjectOutputStream(fos);
			out.writeObject(array_user);
			out.close();
			System.out.println();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		System.out.println("record added");

	}

	public void topRankedMovie() {
		ArrayList<Movie> mov = new ArrayList<Movie>();
		for (int i = 0; i < NowShowing.size(); i++) {
			mov.add(NowShowing.get(i));
		}
		System.out.println("Please choose the method of Rank: ");
		System.out.println("1. Based on Total TIcket Sales");
		System.out.println("2. Based on Overall rating");
		if(sc.nextInt()==1){
			Collections.sort(mov, Movie.Comparators.ToTiSales);
			System.out.println("Top movies right now: ");
			for(int i=0;i<mov.size();i++)
			{
				if(i>4)
					break;
				else{
					System.out.println((i+1)+". "+mov.get(i).getTitle()+ "(Tickets Sold: "+mov.get(i).getTotalTicketSales()+")");
				}
				
			}}
		else{
			Collections.sort(mov, Movie.Comparators.OvAll);
			System.out.println("Top movies right now: ");
			for(int i=0;i<mov.size();i++)
			{
				if(i>4)
					break;
				else{
					System.out.println((i+1)+". "+mov.get(i).getTitle()+" (Rating: "+mov.get(i).getOverallRating()+")");
				}
				
			}}

	}

	public void booking() {
		Date dnow = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(dnow);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int date = cal.get(Calendar.DATE);
		System.out.println("Choose Cineplex");
		System.out.println("1. Jurong Point");
		System.out.println("2. Clementi");
		System.out.println("3. Changi");
		int choose = sc.nextInt();
		if(choose==1)
			cin = JP;
		else if(choose==2)
			cin=Clementi;
		else
			cin=Changi;

		for (int i = 0; i < cin.array_Schedule.size(); i++) {
			String[] a = new String[4];
			a = cin.array_Schedule.get(i).date.split(" ");
			int y = Integer.parseInt(a[3]);
			int m = Integer.parseInt(a[2]);
			int d = Integer.parseInt(a[1]);
			if (y >= year) {
				if (m >= month) {
					if (d >= date)
						System.out.println(i + 1 + ". " + cin.array_Schedule.get(i).date);
				}
			}

		}
		System.out.println("Enter a choice(-1 to return): ");
		int choice = sc.nextInt();
		System.out.println();
		if (choice == -1) {
			return;
		}
		String bookingDate = cin.array_Schedule.get(choice - 1).date;
		cin.array_Schedule.get(choice - 1).printSchedule();
		System.out.println("Choose theatre :");
		int tno = sc.nextInt();
		System.out.println("Enter the time :");
		int time = sc.nextInt();
		System.out.println();
		String movieTitle;
		Movie m;
		String class_type;
		String format;
		if (tno == 1) {
			cin.array_Schedule.get(choice - 1).theatre1.slot[time].occupySeat();
			movieTitle = cin.array_Schedule.get(choice - 1).theatre1.slot[time].getMovieName();
			m = cin.array_Schedule.get(choice - 1).theatre1.slot[time].getMovieObject();
			class_type = cin.array_Schedule.get(choice-1).theatre1.getTheatreType();
			format = cin.array_Schedule.get(choice-1).theatre1.slot[time].getMovieFormat();

		} else if (tno == 2) {
			cin.array_Schedule.get(choice - 1).theatre2.slot[time].occupySeat();
			movieTitle = cin.array_Schedule.get(choice - 1).theatre2.slot[time].getMovieName();
			class_type = cin.array_Schedule.get(choice-1).theatre2.getTheatreType();
			m = cin.array_Schedule.get(choice - 1).theatre2.slot[time].getMovieObject();
			format = cin.array_Schedule.get(choice-1).theatre2.slot[time].getMovieFormat();
		} else {
			cin.array_Schedule.get(choice - 1).theatre3.slot[time].occupySeat();
			movieTitle = cin.array_Schedule.get(choice - 1).theatre3.slot[time].getMovieName();
			class_type = cin.array_Schedule.get(choice-1).theatre2.getTheatreType();
			m = cin.array_Schedule.get(choice - 1).theatre3.slot[time].getMovieObject();
			format = cin.array_Schedule.get(choice-1).theatre3.slot[time].getMovieFormat();
		}

		
		sc.nextLine();
		payment(bookingDate, movieTitle, class_type, format);

		/*try {
			FileOutputStream fos = new FileOutputStream("scheduleDatabase.txt");
			ObjectOutputStream out = new ObjectOutputStream(fos);
			out.writeObject(array_Schedule);
			out.close();
			System.out.println();
		} catch (IOException ex) {
			ex.printStackTrace();
		}*/
		try {
			FileOutputStream fos;
			if(cin.getLocation().compareTo("Jurong Point")==0)
				fos = new FileOutputStream("JurongPoint.txt");
			else if(cin.getLocation().compareTo("Clementi")==0)
				fos = new FileOutputStream("Clementi.txt");
			else
				fos = new FileOutputStream("Changi.txt");
			ObjectOutputStream out = new ObjectOutputStream(fos);
			out.writeObject(cin);
			out.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		// update movie total ticket sales
		
		for(int i = 0;i<NowShowing.size();i++)
		{
			if(NowShowing.get(i).getTitle().compareTo(m.getTitle())==0)
			{
				NowShowing.get(i).incTotalTicketSales();
				break;
			}
				
		}
		array_movie.set(2, NowShowing);
		array_movie.set(0, ComingSoon);
		array_movie.set(1, Preview);
		try {
			FileOutputStream fos = new FileOutputStream("movieDatabase.txt");
			ObjectOutputStream out = new ObjectOutputStream(fos);
			out.writeObject(array_movie);
			out.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	public void printMenu() {
		System.out.println("What do you want to do?");
		System.out.println("1. Search/List movie");
		System.out.println("2. Book and purchase ticket");
		System.out.println("3. View booking history");
		System.out.println("4. Top 5 ranking by ticket sales");
		System.out.println("5. Exit");
	}
//NEW PAYMENT FUNCTION
	public void payment(String bookingDate, String movieTitle, String class_type, String format){
		Pricetype PriceObject = null;
		String transactionID;
		try {
			FileInputStream fis;
			if(class_type.compareTo("regular")==0)
				fis=new FileInputStream("pricelist_regular.txt");
			else
				fis=new FileInputStream("pricelist_gold.txt");
			ObjectInputStream in = new ObjectInputStream(fis);
			PriceObject = (Pricetype) in.readObject();
			in.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		String bookingDay = bookingDate.split(" ")[0];
		boolean weekend  = false;
		if(bookingDay.compareTo("Sun")==0||bookingDay.compareTo("Sat")==0)
		{
			weekend = true;
		}
		System.out.println("Theatre Type: "+class_type);
		System.out.println("Format: "+format);
		System.out.println("Please choose your category: ");
		System.out.println("1. adult");
		System.out.println("2. Student");
		System.out.println("3. Child");
		System.out.println("4. Senior");
		double price = 0;
		switch(sc.nextInt())
		{
		case 1:
		{
			if(weekend==true)
			{
				price = PriceObject.getPHPrice();
			}
			else{
				price = PriceObject.getAdultPrice();
			}
			break;
		}
		case 2:
		{
			if(weekend == true)
			{
				System.out.println("Student price is not available at weekend. Adult weekend price is used. ");
				price = PriceObject.getPHPrice();
			}
			else{
				price = PriceObject.getStudentPrice();
			}
			break;
		}
		case 3:{
			price = PriceObject.getChildrenPrice();
			break;
		}
		case 4:{
			price = PriceObject.getSeniorPrice();
			break;
		}}
		if(format.compareTo("3D")==0)
			price += PriceObject.getThreeDPrice();
		System.out.println("Ticket price is $"+ price);
		System.out.println("Payment is successful. Thank you");
		sc.nextLine();
		Calendar dnow = Calendar.getInstance();
		transactionID = "MOB"+dnow.get(Calendar.YEAR)+(dnow.get(Calendar.MONTH)+1)+dnow.get(Calendar.DATE)+dnow.get(Calendar.HOUR_OF_DAY)+dnow.get(Calendar.MINUTE);
		System.out.println("Your transaction id is: "+transactionID);
		addToUserRecord(bookingDate, movieTitle, transactionID, price, cin.getLocation());

	}

	public void writeReview() {
		System.out.println("Please enter the movie number: ");
		System.out.println("Now Showing: ");
		for (int i = 0; i < NowShowing.size(); i++) {
			System.out.println((i + 1) + ". " + NowShowing.get(i).getTitle());
		}
		int choice = sc.nextInt();
		NowShowing.get(choice - 1).printReview();
		NowShowing.get(choice - 1).addReview();
		try {
			FileOutputStream fos = new FileOutputStream("movieDatabase.txt");
			ObjectOutputStream out = new ObjectOutputStream(fos);
			out.writeObject(array_movie);
			out.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

}
