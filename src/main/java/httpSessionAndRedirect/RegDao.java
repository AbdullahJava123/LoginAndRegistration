package httpSessionAndRedirect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class RegDao implements ServletContextListener {

	static Connection con;

	@Override
	public void contextInitialized(ServletContextEvent context)  {
		ServletContext sc = context.getServletContext();
		String driver = sc.getInitParameter("driver");
		String url = sc.getInitParameter("url");
		String uName = sc.getInitParameter("username");
		String pass = sc.getInitParameter("password");
		System.out.println(uName);
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, uName, pass);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public  static int register(HttpServletRequest request, HttpServletResponse response) {
		int result = 0;
		if(con==null)
		{
			System.out.println("connection is null");
			return result;
		}
		try {
			HttpSession hs = request.getSession();
			
			PreparedStatement ps = con.prepareStatement("insert into empServletContext values(?,?,?,?,?,?)");
			
			
			ps.setString(1, (String) hs.getAttribute("name"));
			ps.setInt(2, (int) hs.getAttribute("id"));
			ps.setString(3, (String) hs.getAttribute("mail"));
			ps.setLong(4, (long) hs.getAttribute("phno"));
			ps.setString(5, (String) hs.getAttribute("state"));
			ps.setString(6, (String) hs.getAttribute("country"));
			result = ps.executeUpdate();

		} catch (Exception e) {
			e.getStackTrace();
		}
		return result;

	}

}
