package httpSessionAndRedirect;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class Reg3Servlet
 */
public class Reg3Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Reg3Servlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		String state = request.getParameter("state");
		String country = request.getParameter("country");

		HttpSession hs = request.getSession();
//		System.out.println("Reg3 Servlet");
//		System.out.println((String)hs.getAttribute("name"));
		hs.setAttribute("state", state);
		hs.setAttribute("country", country);
		
		int k = RegDao.register(request, response);
		
		if (k > 0) {
			pw.println("One Record Inserted Successfully");
		} else {
			pw.println("Record Insertion failed");
		}

	}
}
