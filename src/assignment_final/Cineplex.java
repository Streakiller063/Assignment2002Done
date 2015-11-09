package assignment_final;
import java.util.*;
import java.io.*;

public class Cineplex implements Serializable{
	private String location;
	public ArrayList<Schedule> array_Schedule = new ArrayList<Schedule>();
	public Cineplex(String location)
	{
		this.location=location;
	}
	public void addArray_Schedule(Schedule a)
	{
		array_Schedule.add(a);
	}
	public String getLocation(){
		return location;
	}

}

