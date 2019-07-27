package pslayer;
import java.sql.*;
import java.util.Properties;

import pslayer.FileManager;

public class DBManager {
	private static String PROPERTIES_LOCATION="C:\\Users\\kumon\\eclipse-workspace\\QuizGame\\src\\QuizGame.properties";
	private static Connection conn=connection();  //get connection on servlet startup and remain connected.
	private static Statement stmt;
	private static Connection connection() {
		try {
			Class.forName("org.postgresql.Driver"); //Activate JDBC driver
			Properties p =FileManager.getProperty(PROPERTIES_LOCATION);   //Retrieve QuizGame.properties file
			Connection con=DriverManager.getConnection(p.getProperty("DB_URL"),p.getProperty("DB_USER"), p.getProperty("DB_PASS")); 
			return con;  //Create connection using information in created properties file
		}catch(SQLException e)   //Error Handling
		{
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) { 
			e.printStackTrace();
			return null;
		}
	}
	private static boolean exec(String command) {
		
		try {
			stmt = conn.createStatement();
			
			stmt.execute(command);
			return true;
			
			
		}catch(SQLException s) {
			System.out.println(s.toString());
			return false;
		}finally {
		}
	}
	private static ResultSet execQuery(String command) {
		try {
			stmt = conn.createStatement();
			return stmt.executeQuery(command); //Perform command called upon by parameter 
		}catch(SQLException s) {               //and return the ResultSet generated
			System.out.println(s.toString());
			return null;                      //Error handling
		}
	}
	public static void reset() {
		exec("DELETE FROM PLAYERS;");		
	}
	public static int size() {
		int counter=0;
		ResultSet rs = execQuery("SELECT * FROM PLAYERS;");
		try {
			while (rs.next()) {
				counter++;
			}
			return counter;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
		
	}
	public static boolean containsIP(String ip) {
		ResultSet rs = execQuery("SELECT ip FROM PLAYERS;"); //call execQuery() function to open connection and statement to collect all player IPs.
		try {
			
			while (rs.next()) { //loop through ResultSet to check each IP to see if there is an IP match.
				
				if (rs.getString(1).equals(ip)) {  //if IP match occurs..
					System.out.println(rs.getString(1));
					System.out.println("ip match!");
					return true;       //returns true for IP match
				}
			}
		} catch (SQLException e) {      //Error Handling
			e.printStackTrace();
		}
		return false;    //returns false if no IP match.
	}
	public static boolean addPlayer(String player, String ip) {
		
		if (!exec("INSERT INTO PLAYERS VALUES(DEFAULT,'"+player+"',0,'"+ip+"');")) {
			System.out.println("Failed Insert: DEFAULT,"+player+",0,"+ip); //perform if insert unsuccessful
			return false;
		}
		else {
			System.out.println("Successful Insert:DEFAULT,"+player+",0,"+ip); //perform if insert successful
			return true;
		}
	}
	public static int[] topScores() {
		int[] scores=new int[size()];
		ResultSet rs = execQuery("SELECT SCORE FROM PLAYERS;");
		for (int i=0;i<size();i++) {
			try {
				rs.next();
				scores[i]=rs.getInt(1);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e.toString());
				
			} 
			
		}
		

		for (int i = 0; i < scores.length-1; i++)  {       
	       for (int j = 0; j < scores.length-i-1; j++)  {
	          if (scores[j] < scores[j+1]) {  //sort from greatest to least
	        	 int temp = scores[j];
	        	 scores[j]=scores[j+1];
	        	 scores[j+1]=temp;
	          }
	       }
		}     
		return scores;
		
	}
	public static String[] topPlayers() {
		if (size()<3) {
			String[]players=new String[3];
			ResultSet rs = execQuery("SELECT name, score FROM PLAYERS;");
			try {
				for (int i=0;i<size();i++) {
					
					rs.next();
					if (rs.getInt(2)==topScores()[0]) {
						players[0]=rs.getString(1);
					}
					players[1]=null;
					players[2]=null;
				}
			}catch(SQLException e){
				e.printStackTrace();
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			return players;
		}
		String[]players=new String[3];
		ResultSet rs = execQuery("SELECT name, score FROM PLAYERS;");
		try {
			for (int i=0;i<size();i++) {
				
				rs.next();
				if (rs.getInt(2)==topScores()[2]) {
					players[2]=rs.getString(1);
				}
				else if(rs.getInt(2)==topScores()[1]) {
					players[1]=rs.getString(1);
				}
				else if(rs.getInt(2)==topScores()[0]) {
					players[0]=rs.getString(1);
				}
			}
		}catch(SQLException e){
			e.printStackTrace();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return players;
	}
	public static void addScore(String ip) {
		
		ResultSet current = execQuery ("SELECT SCORE FROM PLAYERS WHERE ip='"+ip+"';"); //Get correct player row by ID
		int currentScore;
		try{current.next();
			currentScore=current.getInt(1); //Sest currentScore to be the listed score of the player in DB
		}catch(Exception e){  //Error handling
			e.printStackTrace();
			currentScore=-99;  
		}
		currentScore++;  //Increment player's score
		exec("UPDATE PLAYERS SET SCORE=("+currentScore+")WHERE ip='"+ip+"';"); //Update player's score in DB
	}
	public static String getNameByIP(String ip) {
		ResultSet nameHolder = execQuery("SELECT NAME FROM PLAYERS WHERE ip='"+ip+"';");
		try {
			nameHolder.next();
			String name = nameHolder.getString(1);
			return name;
		}
		catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}
	public static String getScore(String ip) {
		String score;
		int numerator;
		int denominator;
		ResultSet rs = execQuery("SELECT SCORE FROM PLAYERS WHERE ip='"+ip+"';");
		try {
			rs.next();
			numerator = rs.getInt(1);
		}
		catch(SQLException e){
			e.printStackTrace();
			return "0/10";
		}
		denominator = GameManager.getSize();
		score=numerator+"/"+denominator;
		return score;
		
	}
}
