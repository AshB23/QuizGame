package bllayer;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pslayer.FileManager;






/**
 * Servlet implementation class ActionResult
 */
@WebServlet("/ActionResult")
public class ActionResult extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActionResult() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path = getServletContext().getRealPath("/WEB-INF/JSONFiles");
		request.setAttribute("destination", "CreateGame");
		
		if (request.getParameterMap().containsKey("en1")){
			int counter=20;
			for (int i=0;i<20;i++) {
				if (request.getParameter("ch"+i).isEmpty()||request.getParameter("en"+i).isEmpty()) {
					counter--;
				}
			}
			String title =request.getParameter("title");
			String[]ch = new String[counter];
			String[]en=new String[counter];
			for (int i=0;i<20;i++) {
				if (!(request.getParameter("ch"+i).isEmpty()||request.getParameter("en"+i).isEmpty())) {
					ch[i]=request.getParameter("ch"+i);
					en[i]=request.getParameter("en"+i);
				}
			}		
			if (counter<4) {
				request.setAttribute("result", "FAILURE: QuizSet needs a minimum of 4 questions");
			}
			else { 
				request.setAttribute("result",(FileManager.makeQuizSet(title, ch, en, path)));	
			}
		}
		
		else if(request.getParameterMap().containsKey("selectedQuizSet")) {
			System.out.println("Deleting quizset...");
			request.setAttribute("result",(FileManager.delQuizSet(request.getParameter("selectedQuizSet"), path)));
			
		}
		
		else {
			request.setAttribute("result", "FAILURE: Wrong path");
			System.out.println("Wrong Path");
		}
		request.getRequestDispatcher("/ActionResult.jsp").forward(request,response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
