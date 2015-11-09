package assignment_final;
import java.util.*;
import java.io.*;

public class UserId implements Serializable {
	private String NRIC;
	private String name;
	private int phone;
	ArrayList<UserRecord> array_record = new ArrayList<UserRecord>();
	
	public UserId(String NRIC, String name, int phone)
	{
		this.NRIC = NRIC;
		this.name = name;
		this.phone = phone;
	}
	public String getName(){
		return name;
	}
	public String getNRIC(){
		return NRIC;
	}
	

}
