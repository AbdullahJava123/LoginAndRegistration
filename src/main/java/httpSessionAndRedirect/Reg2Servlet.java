package httpSessionAndRedirect;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class Reg2Servlet
 */
public class Reg2Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Reg2Servlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String mail=request.getParameter("mail");
		String s1=request.getParameter("phno");
		long phno=Long.parseLong(s1);
		HttpSession hs=request.getSession();
		System.out.println("Reg2 Servlet");
		System.out.println((String)hs.getAttribute("name"));
		System.out.println((Integer)hs.getAttribute("id"));
		hs.setAttribute("mail", mail);
		hs.setAttribute("phno", phno);
		
		response.sendRedirect("reg3.html");
	}

}
