package assignment_final;
import java.util.*;
import java.io.*;


public class theatre implements Serializable{
    public theatre(int theatreNum, String TheatreType)
    {
        theatreNo = theatreNum;
        this.TheatreType = TheatreType;
         
    }
    private int theatreNo;
    private int NumofSeat;
    private String TheatreType;
    timeslot[] slot = new timeslot[24];
    public void add_slot(int time, timeslot t)
    {
    	slot[time] = t;
    }
     
    public int getTheatreNo()
    {
        return theatreNo;
    }
    
    public int getNumofSeat()
    {
        return NumofSeat;
    }
    
    public String getTheatreType(){
    	return TheatreType;
    }
}
