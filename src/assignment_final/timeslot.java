package assignment_final;

import java.util.*;
import java.io.*;

public class timeslot implements Serializable {
	private Movie m; // movie class
	private String movieType;
	private char[][] seat;
	private theatre t;
	static Scanner sc = new Scanner(System.in);

	public timeslot(Movie m, theatre t, String movieformat) {
		this.m = m;
		movieType = movieformat;
		this.t = t;
		if (t.getTheatreType().compareTo("regular")==0){
			seat = new char[5][10];
			for (int i = 0; i < 5; i++){
				for (int j = 0; j < 10; j++) {
					seat[i][j] = 'O';
				}}
		} else if (t.getTheatreType().compareTo("gold")==0) {
			seat = new char[5][6];
			for (int i = 0; i < 5; i++)
				for (int j = 0; j < 6; j++) {
					seat[i][j] = 'O';
				}
		}

	}
	public String getMovieFormat(){
		return movieType;
	}

	public String getMovieName() {
		return m.getTitle();
	}
	public Movie getMovieObject(){
		return m;
	}

	public int getNumberofEmptySeats(theatre t) {
		int totalavailableseat = 0;
		if (t.getTheatreType() == "regular") {
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 10; j++) {
					if (seat[i][j] == 'O')
						totalavailableseat++;
				}
			}
		} else if (t.getTheatreType() == "gold") {
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 6; j++) {
					if (seat[i][j] == 'O')
						totalavailableseat++;
				}
			}
		}
		return totalavailableseat;
	}

	public int getNumberofOccupySeats() {
		int totaloccupiedseat = 0;
		if (t.getTheatreType() == "regular") {
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 10; j++) {
					if (seat[i][j] == 'O')
						totaloccupiedseat++;
				}
			}
		} else if (t.getTheatreType() == "gold") {
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 6; j++) {
					if (seat[i][j] == 'O')
						totaloccupiedseat++;
				}
			}
		}

		return totaloccupiedseat;
	}

	public void occupySeat() {
		int row_ascii;
		int col;

		switch (t.getTheatreType().charAt(0)) {
		case 'r':
		case 'R': {
			System.out.println("  1 2 3 4 5 6 7 8 9 10");
			for (int i = 0; i < 5; i++) {
				System.out.print((char)('A'+i)+" ");
				for (int j = 0; j < 10; j++) {
					System.out.print(seat[i][j] + " ");
				}
				System.out.println(" ");
			}
			System.out.println("        Screen\n");

			do {
				System.out.println("Please choose a seat : ");
				String input_seat = sc.nextLine().toLowerCase();
				char row = input_seat.charAt(0);
				col = (int)input_seat.charAt(1)-(int)'0';
				row_ascii = (int) row - (int) 'a';
				
				if(seat[row_ascii][col-1] == 'X'){
					System.out.println("Seat is occupied! Choose another seat!");
				}
				else
					System.out.println("Booking successful!");
				
			} while (row_ascii < 0 || row_ascii > 4 || col - 1 < 0 || col - 1 > 9 || seat[row_ascii][col - 1] == 'X');
			seat[row_ascii][col - 1] = 'X';

			break;
		}
		case 'G':
		case 'g':
			System.out.println("  1 2 3 4 5 6");
			for (int i = 0; i < 5; i++) {
				System.out.print((char)('A'+i)+" ");
				for (int j = 0; j < 6; j++) {
					
					System.out.print(seat[i][j]+" ");
				}
				System.out.println();
			}
			System.out.println("    Screen\n");

			do {
				System.out.println("Please choose a seat : ");
				String input_seat = sc.nextLine().toLowerCase();
				char row = input_seat.charAt(0);
				col = (int)input_seat.charAt(1)-(int)'0';
				row_ascii = (int) row - (int) 'a';
				
				if(seat[row_ascii][col-1] == 'X'){
					System.out.println("Seat is occupied! Choose another seat!");
				}
				else
					System.out.println("Booking successful!");

			} while (row_ascii < 0 || row_ascii > 4 || col - 1 < 0 || col - 1 > 9 || seat[row_ascii][col - 1] == 'X');
			seat[row_ascii][col - 1] = 'X';
			break;
		}

	}

}
