package assignment_final;
import java.util.*;

public class Mobilima {
	static Scanner sc = new Scanner(System.in);
	static char input;

	public static void main(String[] args) {
		do{
		System.out.println("Do you want to log in as an admin?(y/n/e)");
		input = sc.nextLine().charAt(0);
		System.out.println();
		
		if(input == 'y')
		{
			admin ad = new admin();
			ad.adminApp();
		}
		else if(input == 'n')
		{
			user usr = new user();
			usr.userApp();
		}
		else if (input != 'e'){
			System.out.println("Input Error! Try again!\n");
		}
		
		}while(input != 'e');
		System.out.println("Program terminating..");
		// TODO Auto-generated method stub

	}

}
