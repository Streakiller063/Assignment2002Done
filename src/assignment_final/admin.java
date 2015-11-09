package assignment_final;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

import ioHandler.SerializeHandler;

public class admin {
	Scanner sc = new Scanner(System.in);
	public ArrayList<LoginItem> array_login = new ArrayList<LoginItem>();
	public ArrayList<ArrayList> array_movie = new ArrayList<ArrayList>();
	public ArrayList<Movie> ComingSoon = new ArrayList<Movie>();
	public ArrayList<Movie> Preview = new ArrayList<Movie>();
	public ArrayList<Movie> NowShowing = new ArrayList<Movie>();
	public ArrayList<Movie> EndOfShowing = new ArrayList<Movie>();
	//public ArrayList<Schedule> array_Schedule = new ArrayList<Schedule>();
	public Cineplex JP = new Cineplex("Jurong Point");
	public Cineplex Clementi = new Cineplex("Clementi");
	public Cineplex Changi = new Cineplex("Changi");
	public Cineplex cin;
	public void printMenu() {
		System.out.println("Please choose from this menu: ");
		System.out.println("1. Create movie listing");
		System.out.println("2. Update movie listing");
		System.out.println("3. Remove movie listing");
		System.out.println("4. Create movie showtimes");
		System.out.println("5. Update movie showtimes");
		System.out.println("6. Remove movie showtimes");
		System.out.println("7. Create new admin id");
		System.out.println("8. Delete existing admin id");
		System.out.println("9. Set Price");
		System.out.println("10. Exit");
	}

	public void adminApp() {
		login();
		printMenu();
		int option;
		do {
			option = sc.nextInt();
			sc.nextLine();
			System.out.println();
			switch (option) {
			case 1: {
				addMovieEntry();
				break;
			}
			case 2: {
				updateMovieListing();
				break;
			}
			case 3: {
				deleteMovieEntry();
				break;
			}
			case 4: {
				createMovieShowTimes();
				break;
			}
			case 5:
				break;
			case 6: {
				removeMovieShowTime();
				break;
			}

			case 8: {
				deleteId();
				break;
			}
			case 7: {
				createId();
				break;
			}
			case 9: {setPrice();
				break;
			}
			case 10: {
				return;
			}
			default:
				System.out.println("Input Error! Try again!");
			}

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
			try {
				FileOutputStream fos = new FileOutputStream("movieDatabase.txt");
				ObjectOutputStream out = new ObjectOutputStream(fos);
				out.writeObject(array_movie);
				out.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			System.out.println();
			printMenu();
		} while (option != 9);

	}
//new function!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	public void setPrice()
	{
		Pricetype PriceObjectRegular = new Pricetype();
		Pricetype PriceObjectGold = new Pricetype();
		try {
			FileInputStream fis = new FileInputStream("pricelist_regular.txt");
			ObjectInputStream in = new ObjectInputStream(fis);
			PriceObjectRegular = (Pricetype) in.readObject();
			in.close();
			fis = new FileInputStream("pricelist_gold.txt");
			in = new ObjectInputStream(fis);
			PriceObjectGold = (Pricetype) in.readObject();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		int choice = 0;
		System.out.println("Please choose: 1. Regular 2. Gold");
		choice = sc.nextInt();
		sc.nextLine();
		Pricetype PriceObject;
		if(choice==1) 
			PriceObject = PriceObjectRegular;
		else
			PriceObject = PriceObjectGold;
		System.out.println("Current Price: ");
		System.out.println("Adult weekday Price: "+PriceObject.getAdultPrice());
		System.out.println("Adult public holiday Price: "+PriceObject.getPHPrice());
		System.out.println("Student Price: "+PriceObject.getStudentPrice());
		System.out.println("Children Price: "+PriceObject.getChildrenPrice());
		System.out.println("Senior Price: "+PriceObject.getSeniorPrice());
		System.out.println("3D price: plus "+PriceObject.getThreeDPrice());

		System.out.println("Please enter the price for adult at weekday: ");
		PriceObject.setAdultPrice(sc.nextDouble());
		System.out.println("Please enter the price for adult at public holiday: ");
		PriceObject.setPHPrice(sc.nextDouble());
		System.out.println("Please enter the price for student: ");
		PriceObject.setStudentPrice(sc.nextDouble());
		System.out.println("Please enter the price for children: ");
		PriceObject.setChildrenPrice(sc.nextDouble());
		System.out.println("Please enter the price for senior: ");
		PriceObject.setSeniorPrice(sc.nextDouble());
		System.out.println("Please enter 3D increment price: ");
		PriceObject.setThreeDPrice(sc.nextDouble());
		System.out.println("Price are set. Thank you!");
		
		try {
			FileOutputStream fos;
			if(choice==1)
				fos = new FileOutputStream("pricelist_regular.txt"); 
			else
				fos = new FileOutputStream("pricelist_gold.txt"); 
			ObjectOutputStream out = new ObjectOutputStream(fos);
			out.writeObject(PriceObject);
			out.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
	}
	public void login() {
		String id, password;
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = new FileInputStream("login.txt");
			in = new ObjectInputStream(fis);
			array_login = (ArrayList) in.readObject();
			in.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		//load cineplex
		try {
			fis = new FileInputStream("JurongPoint.txt");
			in = new ObjectInputStream(fis);
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
		
			array_movie = SerializeHandler.loadArray("movieDatabase.txt");
			ComingSoon = array_movie.get(0);
			Preview = array_movie.get(1);
			NowShowing = array_movie.get(2);
			EndOfShowing = array_movie.get(3);

		//array_Schedule = SerializeHandler.loadArray("scheduleDatabase.txt");
		/*try {
			fis = new FileInputStream("scheduleDatabase.txt");
			in = new ObjectInputStream(fis);
			array_Schedule = (ArrayList) in.readObject();
			in.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}*/
		System.out.println("Please enter your id");
		id = sc.nextLine();
		System.out.println("Please entert your password");
		password = sc.nextLine();
		System.out.println();

		boolean LoginStatus = false;
		for (int i = 0; i < array_login.size(); i++) {
			if (array_login.get(i).getId().compareTo(id) == 0
					&& array_login.get(i).getPassword().compareTo(password) == 0) {
				LoginStatus = true;
				break;
			}
		}
		if (LoginStatus == false) {
			System.out.println("Wrong id/password. Shutting down apps for security");
			System.exit(0);
		}
	}

	public void createId() {
		System.out.println("Enter new id :");
		String id = sc.nextLine();
		System.out.println("Enter new password :");
		String password = sc.nextLine();
		LoginItem newID = new LoginItem();
		newID.setId(id);
		newID.setPassword(password);
		array_login.add(newID);
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream("login.txt");
			out = new ObjectOutputStream(fos);
			out.writeObject(array_login);
			out.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		System.out.println("Admin id created!");

	}

	public void deleteId() {
		System.out.println("Choose the admin id you want to delete");
		for (int i = 0; i < array_login.size(); i++) {
			System.out.println((i + 1) + ". " + array_login.get(i).getId());
		}
		int choice = sc.nextInt();
		sc.nextLine();
		array_login.remove(choice - 1);
		System.out.println("This id has been removed\n");
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream("login.txt");
			out = new ObjectOutputStream(fos);
			out.writeObject(array_login);
			out.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void addMovieEntry() {
		Movie mov = new Movie();
		mov.setNewMovie();
		if (mov.getShowingStatus().compareTo("ComingSoon") == 0)
			ComingSoon.add(mov);
		else if (mov.getShowingStatus().compareTo("Preview") == 0)
			Preview.add(mov);
		else if (mov.getShowingStatus().compareTo("NowShowing") == 0)
			NowShowing.add(mov);
		else
			EndOfShowing.add(mov);
		array_movie.add(ComingSoon);
		array_movie.add(Preview);
		array_movie.add(NowShowing);
		array_movie.add(EndOfShowing);

	}

	public void deleteMovieEntry() {
		System.out.println("Choose movie category where you want to delete");
		System.out.println("1. Coming Soon");
		System.out.println("2. Preview");
		System.out.println("3. Now Showing");
		int cat = sc.nextInt();
		System.out.println();
		switch (cat) {
		case 1: {
			System.out.println("Coming soon movie:");
			for (int i = 0; i < ComingSoon.size(); i++) {
				System.out.println((i + 1) + ". " + ComingSoon.get(i).getTitle());
			}
			break;
		}
		case 2: {
			System.out.println("Preview Movie");
			for (int i = 0; i < Preview.size(); i++) {
				System.out.println((i + 1) + ". " + Preview.get(i).getTitle());
			}
			break;
		}
		case 3: {
			System.out.println("Now Showing Movie");
			for (int i = 0; i < NowShowing.size(); i++) {
				System.out.println((i + 1) + ". " + NowShowing.get(i).getTitle());
			}
			break;
		}
		}

		System.out.println("Choose the movie you want to remove : ");
		int choice = sc.nextInt();
		sc.nextLine();
		EndOfShowing.add((Movie) (array_movie.get(cat - 1)).get(choice - 1));
		array_movie.get(cat - 1).remove(choice - 1);
		System.out.println("This movie has been removed.");
		FileOutputStream fos = null;
	}

	public void createMovieShowTimes() {
		System.out.println("Choose the cineplex you want to add showtimes to: ");
		System.out.println("1. Jurong Point");
		System.out.println("2. Clementi");
		System.out.println("3. Changi");
		int choose = sc.nextInt();
		sc.nextLine();
		if(choose == 1)
			cin= JP;
		else if(choose==2)
			cin = Clementi;
		else
			cin = Changi;
		Date dnow = new Date();
		Calendar cal = Calendar.getInstance();
		System.out.println("What date do you want to add schedule?");
		SimpleDateFormat ft = new SimpleDateFormat("E dd MM yyyy");
		System.out.println("1. " + ft.format(dnow));
		cal.setTime(dnow);
		for (int i = 2; i < 8; i++) {
			cal.add(Calendar.DAY_OF_YEAR, 1);
			dnow = cal.getTime();
			System.out.println(i + ". " + ft.format(dnow));
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy MM dd");
		int date_selection = sc.nextInt();
		System.out.println();
		dnow = new Date();
		cal.setTime(dnow);
		cal.add(Calendar.DAY_OF_YEAR, date_selection - 1);
		dnow = cal.getTime();
		Schedule a;
		boolean scheduleCheck = false;
		/*for(int i = 0;i<cin.array_Schedule.size();i++)
		{
			if((cin.array_Schedule.get(i).date).compareTo(ft.format(dnow))==0)
			{
				scheduleCheck = true;
				a = cin.array_Schedule.get(i);
				a.addTimeSlot();
				break;
				
			}
		}*/
		if(scheduleCheck == false)
		{
			a = new Schedule(ft.format(dnow));
			a.addTimeSlot();
			cin.array_Schedule.add(a);
		}

	}

	public void removeMovieShowTime() {
		System.out.println("Choose the cineplex you want to add showtimes to: ");
		System.out.println("1. Jurong Point");
		System.out.println("2. Clementi");
		System.out.println("3. Changi");
		int choose = sc.nextInt();
		sc.nextLine();
		if(choose == 1)
			cin= JP;
		else if(choose==2)
			cin = Clementi;
		else
			cin = Changi;
		System.out.println("Please select the date: ");
		for (int i = 0; i < cin.array_Schedule.size(); i++) {
			System.out.println((i + 1) + ". " + cin.array_Schedule.get(i).date);
		}
		int date_sel = sc.nextInt();
		sc.nextLine();
		boolean deleteall = false;
		System.out.println("\nDo you want to delete all the schedules for that date? (y/n)");
		if (sc.nextLine().compareTo("y") == 0) {
			deleteall = true;
			cin.array_Schedule.remove(date_sel - 1);
		} else {
			System.out.println("\nSchedule for theatre 1");
			for (int i = 0; i < 24; i++) {
				if (cin.array_Schedule.get(date_sel - 1).theatre1.slot[i] != null)
					System.out.println(
							i + " O'clock " +cin. array_Schedule.get(date_sel - 1).theatre1.slot[i].getMovieName());
			}
			System.out.println("Schedule for theatre 2");
			for (int i = 0; i < 24; i++) {
				if (cin.array_Schedule.get(date_sel - 1).theatre2.slot[i] != null)
					System.out.println(
							i + " O'clock " + cin.array_Schedule.get(date_sel - 1).theatre2.slot[i].getMovieName());
			}
			System.out.println("Schedule for theatre 3");
			for (int i = 0; i < 24; i++) {
				if (cin.array_Schedule.get(date_sel - 1).theatre3.slot[i] != null)
					System.out.println(
							i + " O'clock " + cin.array_Schedule.get(date_sel - 1).theatre3.slot[i].getMovieName());
			}
		}
		if (deleteall == false) {
			System.out.println("Please enter the theatre no: ");
			int tno = sc.nextInt();
			System.out.println("Please enter the time: (i.e. 12, 1, 3)");
			int time = sc.nextInt();
			if (tno == 1) {
				cin.array_Schedule.get(date_sel - 1).theatre1.slot[time] = null;
				System.out.println("Show time removed!");

			} else if (tno == 2) {
				cin.array_Schedule.get(date_sel - 1).theatre2.slot[time] = null;
				System.out.println("Show time removed!");
			} else {
				cin.array_Schedule.get(date_sel - 1).theatre3.slot[time] = null;
				System.out.println("Show time removed!");
			}
		}

	}

	public void updateMovieListing() {
		System.out.println("1. Change Showing Status");
		System.out.println("Choose option: ");
		int option = sc.nextInt();
		switch (option) {
		case 1: {
			System.out.println("\nWhich category to change?");
			System.out.println("1. Coming Soon");
			System.out.println("2. Preview");
			System.out.println("3. Now Showing");
			int cat = sc.nextInt();
			System.out.println();
			switch (cat) {
			case 1: {
				System.out.println("Coming soon movie:");
				for (int i = 0; i < ComingSoon.size(); i++) {
					System.out.println((i + 1) + ". " + ComingSoon.get(i).getTitle());
				}
				break;
			}
			case 2: {
				System.out.println("Preview Movie");
				for (int i = 0; i < Preview.size(); i++) {
					System.out.println((i + 1) + ". " + Preview.get(i).getTitle());
				}
				break;
			}
			case 3: {
				System.out.println("Now Showing Movie");
				for (int i = 0; i < NowShowing.size(); i++) {
					System.out.println((i + 1) + ". " + NowShowing.get(i).getTitle());
				}
				break;
			}
			}
			System.out.println("Choose the movie you want to update :");
			int choice = sc.nextInt();
			sc.nextLine();
			System.out.println("\nChange status to: ");
			System.out.println("1. Coming Soon");
			System.out.println("2. Preview");
			System.out.println("3. Now Showing");
			System.out.println("4. End of Showing");
			int status = sc.nextInt();
			Movie m = (Movie) array_movie.get(cat - 1).get(choice - 1);
			m.setShowingStatus(status);
			array_movie.get(cat - 1).remove(choice - 1);
			array_movie.get(status - 1).add(m);

		}
		}
	}

}
