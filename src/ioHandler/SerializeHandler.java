package ioHandler;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class SerializeHandler {

	public static ArrayList loadArray(String filename) {
		//function for deserializing file and loading into arraylist
		FileInputStream fis = null;
		ObjectInputStream in = null;
		ArrayList temp = new ArrayList();
		try {
			fis = new FileInputStream(filename);
			in = new ObjectInputStream(fis);
			temp = (ArrayList) in.readObject();
			in.close();
			fis.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return temp;
	}
	
	public static void saveArray(Object obj, String filename) {
		//function for serializing file and saving into .txt
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream(filename);
			out = new ObjectOutputStream(fos);
			out.writeObject(obj);
			out.close();
			fos.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
