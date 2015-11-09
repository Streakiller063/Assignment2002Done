package ioHandler;

import java.util.Scanner;


public class InputHandler {
	
	
	/*
	 * for getting input within particular range
	 */
	public static int getIntInput(int low, int high) {
		Scanner sc = new Scanner(System.in);
		int input = sc.nextInt();
		while(input<low || input>high) {
			System.out.format("Invalid input! enter input between (%d-%d):",low,high);
			input = sc.nextInt();
		}
		sc.close();
		return input;
	}
	
}
