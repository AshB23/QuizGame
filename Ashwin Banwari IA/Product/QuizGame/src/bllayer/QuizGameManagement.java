package bllayer;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pslayer.FileManager;

/**
 * Servlet implementation class QuizGameManagement
 */
@WebServlet("/QuizGameManagement")
public class QuizGameManagement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuizGameManagement() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action=request.getParameter("action"); //Hold the parameters of the GET/POST request.
		if (action.equals("Create New QuizSet")) {  //Redirect user towards QuizSet creation JSP page.
			request.getRequestDispatcher("/NewQuizSet.jsp").forward(request,response); 
		}
		else if(action.equals("Delete A QuizSet")) {  //Redirect user towards QuizSet deletion JSP page.
			String[]qs=FileManager.getQuizSets(getServletContext().getRealPath("/WEB-INF/JSONFiles"));
			for (int i=0;i<qs.length;i++) {
				System.out.println(qs[i]);  
				request.setAttribute("qs"+i, qs[i]);   //Give JSP page information on what QuizSet-containing JSP  
			}                                          //files are able to be deleted for drop-down list selection.
			request.setAttribute("num",(int)qs.length);
			if (qs.length>0) {
				request.setAttribute("empty",false); //Check if any QuizSets are able to be deleted to disable the
			}                                        // "Delete QuizSet" button
			else {
				request.setAttribute("empty", true);
			}
			request.getRequestDispatcher("/DelQuizSet.jsp").forward(request,response); //redirection to quizset deletion JSP page
		}	
		else {                                                          //Check if no proper action specified in GET/POST request.
			response.getWriter().append("Please visit QuizGame/CreateGame for the proper path. ");
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response); //No difference needed between GET and POST requests.
	}
}
