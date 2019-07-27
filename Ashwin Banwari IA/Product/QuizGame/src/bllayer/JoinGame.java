package bllayer;


import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bllayer.DisplayGame;
import pslayer.DBManager;
import pslayer.GameManager;

/**
 * Servlet implementation class JoinGame
 */
@WebServlet("/JoinGame")
public class JoinGame extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JoinGame() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date newDate = new Date();
		dateFormat.format(newDate);
		
		String ip=request.getRemoteAddr();
		try{request.setAttribute("timer", 6000-newDate.getTime()+DisplayGame.lastDate.getTime());
		}catch(Exception e) {}
		
		if (GameManager.getState()==0) {	
			request.setAttribute("destination","/QuizGame");
			request.setAttribute("result", "No ongoing QuizGame. Wait for teacher to start game. ");
			request.getRequestDispatcher("/ActionResult.jsp").forward(request, response);
		
		}
		else if(GameManager.getState()==1) {  //Check if the game is in state 1 (Accepting players)
			if (DBManager.containsIP(ip)) {   //Check if user's ip is already under another name.
				request.setAttribute("timer", 15000-newDate.getTime()+DisplayGame.lastDate.getTime());
				request.getRequestDispatcher("/JoinGame.jsp").forward(request,response);  //Don't create new account, 
			}                                                                             //user is sent under profile of his first account.
			else if(DBManager.addPlayer(request.getParameter("playerName"),ip)) {
				request.setAttribute("timer", 15000-(newDate.getTime()-DisplayGame.lastDate.getTime()));  
				System.out.println(request.getAttribute("timer"));
				request.getRequestDispatcher("/JoinGame.jsp").forward(request,response); //Create new account, send user JSP page.
				
			}
			else {
				request.setAttribute("destination","/");
				request.setAttribute("result", "Sorry, your name is too long :(");       //Error handling
				request.getRequestDispatcher("/ActionResult.jsp").forward(request, response);
			}
		}
		else if (GameManager.getState()==4) {
			request.setAttribute("studentScore", DBManager.getScore(ip));
			request.getRequestDispatcher("/StudentScore.jsp").forward(request, response);
		}
		else if(GameManager.getState()==3) {
			if (DBManager.containsIP(ip)) {  
				String options[]=GameManager.getCurrent();
				for (int i=1; i<5;i++) {
					request.setAttribute("Answer"+i, options[i]);
				}
				request.getRequestDispatcher("/SelectAnswer.jsp").forward(request,response);
			}
			else {
				request.setAttribute("destination","/");
				request.setAttribute("result", "Game is already ongoing :(");
				request.getRequestDispatcher("/ActionResult.jsp");
			}
		}
		else if(request.getParameter("selected")!=null) {
			if (DBManager.containsIP(ip)) {
				
				try {
					request.setAttribute("name", DBManager.getNameByIP(ip));
					if (request.getParameter("selected").equals(GameManager.getCorrect())) {
						request.setAttribute("result", ", you are.. correct!");
						request.setAttribute("color", "green");
						DBManager.addScore(ip);
					}
					else {   
						request.setAttribute("result", ", you are.. wrong!");
						request.setAttribute("color", "red");
					}
				}
				catch(Exception e) {
					System.out.println(e.toString());
					request.setAttribute("result", ", no answer receieved!");
					request.setAttribute("color", "red");
				}
				
				request.getRequestDispatcher("/Break.jsp").forward(request, response);
			}
			else {
				request.setAttribute("destination","JoinGame");
				request.setAttribute("result", "Game is already ongoing :(");
				request.getRequestDispatcher("/ActionResult.jsp");
			}
		}
		else {
			request.setAttribute("destination","JoinGame");
			request.setAttribute("result", "Game is already ongoing :(");
			request.getRequestDispatcher("/ActionResult.jsp");
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
