package httpSessionAndRedirect;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter pw=response.getWriter();
		String name=request.getParameter("name");
		String s1=request.getParameter("id");
		int id=Integer.parseInt(s1);
		
		HttpSession hs=request.getSession();
		hs.setAttribute("name", name);
		hs.setAttribute("id", id);
		boolean bool= LoginDao.login(request, response);
		if(bool==true)
		{
			pw.println("Login Successfull");
		}
		else
		{
			pw.println("Login UnSuccessfull");
		}
	}

}
