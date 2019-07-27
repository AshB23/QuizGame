package pslayer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.Properties;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;





public class FileManager {
	public static Properties getProperty(String fileName) {
		File f = new File(fileName);  //Let properties file be referenced by File f
		try{
			FileInputStream fileInput = new FileInputStream(f); //Create input stream to get data
		
			Properties p = new Properties(); //Create blank properties object
			p.load(fileInput);               //Let input stream load data into propreties object
			fileInput.close();
			return p;                        //Return properties object with the requested data
		}
		catch(Exception e) {         //Error handling
			e.printStackTrace();
			return null;
		}
	}
	public static String makeQuizSet(String title, String[]questions,  String[]answers, String path) {
		if (title.equals("")) {
			return "Blank Title"; //JSON filename shouldn't be empty.
		}
		try{
			JSONObject j =  new JSONObject(); 
			j.put("name", title);
			JSONArray q = new JSONArray(); //Creating JSON Object with quizset name, quetion array, and solution array.
			JSONArray a = new JSONArray(); 
			if (questions!=null) {
				for (int i =0;i<questions.length;i++) {
					q.put(questions[i]);
					a.put(answers[i]);     //Inputting information into JSON object from the parameters.
				}
			}
			j.put("Chinese", q);
			j.put("English", a);   //Input arrays into JSON object
			System.out.println("JSON OBJECT SUCCESSFULLY CREATED!");
		    
			File f = new File(path + "/"+title+".json");
			System.out.println(f.getAbsolutePath());
			if  (f.createNewFile()) { //Try creating the file.
				System.out.println("FILE CREATED");
			}
			else {
				return "FILENAME ALREADY EXISTS"; 
			}
			FileWriter fw = new FileWriter(f);
			j.write(fw);   //Write the JSON object to the created file.
			fw.flush();
			fw.close();
			return "SUCCESS!";
		} catch(Exception e) { //Error Handling
			e.printStackTrace();
			return e.toString();
		} 
	}
	public static String delQuizSet(String file, String path) {
		try {
			File f = new File(path + "/"+file);
			if (f.exists()) {
				System.out.println("Deleting File");
				System.out.println(f.delete());
				return "SUCCESS!";
				
				
			}
			else {
				System.out.println("QuizGame "+file+" doesn't exist. Did you misspell it?");
				return "FILE NOT FOUND!";
			}
				
		}catch(Exception e) {
			e.printStackTrace();
			return e.toString();
		}
	}
	public static String[] getQuizSets(String path) {
		try {
			File jFolder = new File(path);
			
			String[]jFiles=new String[jFolder.list().length];
			for (int  i=0;i<jFiles.length;i++) {
				jFiles[i]=jFolder.list()[i];
			}
			return jFiles;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static File getQuizSet(String name, String path) {
		try {
			File f = new File(path+"/"+name);
			return f;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static String[] getArray(String name, String arrType, String path) {
		try {
			String[] arr;
			JSONArray j;
			File f = getQuizSet(name,path); //Refer to selected QuizSet with File f
			Scanner sc = new Scanner(f);
			String JSONString = sc.useDelimiter("\\A").next(); //Create JSONString with space Delimiter
			sc.close();                                        //reversing process to create JSON File
			System.out.println("jsonString =" + JSONString);
			JSONObject jObject = new  JSONObject(JSONString);  //Set jObject to the JSONObject from file
			if (arrType.equals("ch")) { //Check whether English or Chinese array is wanted by application.
				j = jObject.getJSONArray("Chinese");
			}
			else {
				 j= jObject.getJSONArray("English");
			}
			arr=new String[j.length()];
			for  (int i=0;i<j.length();i++) {
				arr[i]=j.getString(i);  //Turn JSONArray into regular array to return.
			}
			return arr; //return Chinese or English array.
		}catch(Exception e) {  //Error handling
			e.printStackTrace();
			return null;
		}
	}
}
