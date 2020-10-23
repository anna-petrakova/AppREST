package web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import records.RecordManager;

/**
 * Servlet implementation class RecordServlet
 */
//@WebServlet("/RecordServlet")
@WebServlet(
		urlPatterns = { "/RecordServlet", "/records", RecordServlet.URL_MAPPING + "/*" } 		
)
public class RecordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String URL_MAPPING = "/records";
	private static final String TABLE_JSP = "/table.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecordServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("records", getRecordManager().getRecords());
		request.getRequestDispatcher(TABLE_JSP).forward(request, response);
	}
	
	private RecordManager getRecordManager() {
		return (RecordManager) getServletContext().getAttribute("recordManager");
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String _id = request.getParameter("_id");
		System.out.println(_id);
	}

}
