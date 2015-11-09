package assignment_final;
import java.util.*;
import java.io.*;
import java.util.*;

public class Schedule implements Serializable{
	theatre theatre1 = new theatre(1, "regular");
	theatre theatre2 = new theatre(2, "gold");
	theatre theatre3 = new theatre(3, "regular");
	static Scanner sc = new Scanner(System.in);
	theatre theatre_sel;
	String date;

	public Schedule(String date) {
		this.date = date;
	}

	public void addTimeSlot() {
		System.out.println("Pick a Theatre(1-3)");
		int theatreno = sc.nextInt();
		if (theatreno == 1)
			theatre_sel = theatre1;
		else if (theatreno == 2)
			theatre_sel = theatre2;
		else
			theatre_sel = theatre3;
		
		showMovieList();
		int movieSelection = sc.nextInt();
		Movie m = new Movie();
		m = getMovieObject(m, movieSelection);
		System.out.println("\nPlease enter what time: ");
		int time_for_slot = sc.nextInt();
		sc.nextLine();
		System.out.println("\n3D or 2D movie(3D / 2D)?");
		String format = sc.nextLine();
		timeslot t = new timeslot(m, theatre_sel, format);
		theatre_sel.add_slot(time_for_slot, t);
	

	}		

	

	public void removeTimeSlot(String time) {

	}

	public ArrayList<ArrayList> array_movie = new ArrayList<ArrayList>();
	public ArrayList<Movie> NowShowing = new ArrayList<Movie>();

	public void showMovieList() {
		try {
			FileInputStream fis = new FileInputStream("movieDatabase.txt");
			ObjectInputStream in = new ObjectInputStream(fis);
			array_movie = (ArrayList) in.readObject();
			NowShowing = array_movie.get(2);
			in.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		
		System.out.println("\nChoose movie you want to add to this slot");
		for (int i = 0; i < NowShowing.size(); i++) {
			System.out.println((i + 1) + ". " + NowShowing.get(i).getTitle());
		}
	}

	public Movie getMovieObject(Movie m, int choice) {
		return NowShowing.get(choice-1);
	}

	public void printSchedule() {
		System.out.println("Theatre 1 ("+theatre1.getTheatreType()+ ") for " + date);
		for (int i = 0; i < theatre1.slot.length; i++) {
			if (theatre1.slot[i] != null)
				System.out.println("time: " + i + " O Clock: " + theatre1.slot[i].getMovieName());
		}
		System.out.println("Theatre 2 ("+theatre2.getTheatreType()+ ") for "+ date);
		for (int i = 0; i < theatre2.slot.length; i++) {
			if (theatre2.slot[i] != null)
				System.out.println("time: " + i + " O Clock: " + theatre2.slot[i].getMovieName());
		}
		System.out.println("Theatre 3 ("+theatre3.getTheatreType()+ ") for "+ date);
		for (int i = 0; i < theatre3.slot.length; i++) {
			if (theatre3.slot[i] != null)
				System.out.println("time: " + i + " O Clock: " + theatre3.slot[i].getMovieName());
		}
		

}
}
