package bllayer;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pslayer.FileManager;
import pslayer.GameManager;

/**
 * Servlet implementation class CreateGame
 */
@WebServlet("/CreateGame")
public class CreateGame extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateGame() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		GameManager.setState(0);
		String[]qs=FileManager.getQuizSets(getServletContext().getRealPath("/WEB-INF/JSONFiles"));
		for (int i=0;i<qs.length;i++) {
			System.out.println(qs[i]);
			request.setAttribute("qs"+i, qs[i]);
		}
		request.setAttribute("num",(int)qs.length);
		if (qs.length>0) {
			request.setAttribute("empty",false);
		}
		else {
			request.setAttribute("empty", true);
		}
		
		request.getRequestDispatcher("/CreateGame.jsp").forward(request,response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
