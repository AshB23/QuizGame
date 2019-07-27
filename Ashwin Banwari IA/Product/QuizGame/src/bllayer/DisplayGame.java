package bllayer;



import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pslayer.DBManager;
import pslayer.*;


/**
 * Servlet implementation class DisplayGame
 */
@WebServlet("/DisplayGame")
public class DisplayGame extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static Date lastDate;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayGame() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		lastDate = new Date();
		 
		System.out.println("DisplayGame.java:"+GameManager.getState());
		if (request.getParameterMap().containsKey("selectedQuizSet")) {
			GameManager.startGame(request.getParameter("selectedQuizSet"),getServletContext().getRealPath("/WEB-INF/JSONFiles"));
			dateFormat.format(lastDate);
			request.getRequestDispatcher("/NewGame.jsp").forward(request,response);
		}
		else if(pslayer.GameManager.getState()==1||GameManager.getState()==2){
			if (GameManager.next()) {
				GameManager.updOptions();
				GameManager.setState(3);
				
			}
			else {
				GameManager.setState(4);
				request.getRequestDispatcher("DisplayGame").forward(request, response);
				return;
			}
			request.setAttribute("question", GameManager.getCurrent()[0]);
			dateFormat.format(lastDate);
			request.getRequestDispatcher("/CountDown.jsp").forward(request, response);			
		}
		else if(GameManager.getState()==3||GameManager.getState()==4) { //Student answers has just been submitted
			if (GameManager.getState()==3) {
				GameManager.setState(2);
			}
			dateFormat.format(lastDate);   //Get current time at time of servlet GET/POST request with lastDate.
			Date tempDate = new Date();    
			dateFormat.format(tempDate);   //Create another Date, tempDate, at same time of servlet GET/POST request.
			while (tempDate.getTime()-lastDate.getTime()<3000){
				tempDate = new Date();
				dateFormat.format(tempDate);  //Continuously update the tempDate until it is 2 seconds past lastDate.
			}                                 //This allows time for all results from the last question to be processed to show updated rankings.
			String[]names=DBManager.topPlayers();
			int[]scores = DBManager.topScores();  //Collect information of top three players through DBManager.
			try{
				request.setAttribute("name1", names[0]);
				request.setAttribute("score1", scores[0]);
				request.setAttribute("name2", names[1]);  //Store all information as attributes to be used by front-end JSP.
				request.setAttribute("score2", scores[1]);
				request.setAttribute("name3", names[2]);
				request.setAttribute("score3", scores[2]);
			}catch(Exception e) {
				System.out.println("Too few people");
				System.out.println(e);
			}   //Error handling
			if (GameManager.getState()==4) {
				GameManager.setState(0);
				request.getRequestDispatcher("/Final.jsp").forward(request, response); //Send to final ranking board.
			}
			else {
				request.getRequestDispatcher("/Ranking.jsp").forward(request, response); //Send to ranking in-between questions.
			}
		}
		else {
			request.setAttribute("result", "Wrong Path!");
			dateFormat.format(lastDate);
			request.getRequestDispatcher("/ActionResult.jsp").forward(request,response);
		}	
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
