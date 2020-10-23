package web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import records.RecordManager;

/**
 * Servlet implementation class ConcreteRecordServlet
 */
//@WebServlet("/ConcreteRecordServlet")
@WebServlet(
		urlPatterns = { "/ConcreteRecordServlet", "/recordDetails" } 		
)
public class ConcreteRecordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String SHOW_JSP = "/showRecord.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConcreteRecordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String recordId = request.getParameter("recordId");	
		request.setAttribute("record", getRecordManager().getRecordWithDetails(recordId));		
		request.getRequestDispatcher(SHOW_JSP).forward(request, response);
	}
	
	private RecordManager getRecordManager() {
		return (RecordManager) getServletContext().getAttribute("recordManager");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
