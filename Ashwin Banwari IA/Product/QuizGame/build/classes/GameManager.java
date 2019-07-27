package pslayer;

import java.util.Random;

import pslayer.DBManager;
import pslayer.FileManager;

import java.util.Collections;
import java.util.ArrayList;


public class GameManager {
	private static int state;  //0=off, 1=starting, 2=rank 3=game 4=final game
	private static String name;
	private static String[] en;
	private static String[] ch;
	private static int counter=-1;
	private static String[] options;
	static public int getState() {
		return state;
	}
	static public void setState(int state) {
		GameManager.state=state;
	}
	
	public static void startGame(String quizSet, String path) {
		GameManager.state=1;
		GameManager.name=quizSet;
		GameManager.ch=FileManager.getArray(name,"ch", path);
		GameManager.en=FileManager.getArray(name,"en",path);
		counter=-1;
		DBManager.reset();
	}
	public static boolean next() {
		counter++;	
		if (counter<en.length) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static void updOptions() {
		/* function to update the available 
		 * options to the player will see
		 * on answering the question.
		 */
		Random r = new Random();
		int j; 
		options=new String[4];
		ArrayList<Integer> k=new ArrayList<Integer>();
		options[0]=ch[counter];
		k.add(counter);
		System.out.println(counter);
		for  (int  i=1; i<4;i++) {
			do{
				j = r.nextInt(ch.length);
			}
			while(k.contains(j));
			options[i]=ch[j];
			k.add(j);
			System.out.println(j);
		}
		
	}
			
	public static String[]getCurrent(){
		String[]info = new String[5];
		info[0]=en[counter];
		ArrayList<Integer> b=new ArrayList<Integer>();
		for ( int i=0;i<4;i++) {
			b.add(i);
		}
		Collections.shuffle(b);
		for (int i=0;i<4;i++) {
			info[i+1]=options[b.get(i)];
			System.out.println(info[i+1]);
		}
		return info;
		
	}
	public static String getCorrect() {
		return ch[counter];
	}
	public static int getSize() {
		return en.length;
	}
}
