package httpSessionAndRedirect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginDao extends HttpServlet
{
	static Connection con;
   @Override
public void init(ServletConfig config) throws ServletException 
   {
	   ServletContext sc=config.getServletContext();
	   String driver=sc.getInitParameter("driver");
	   String url=sc.getInitParameter("url1");
	   String uName=sc.getInitParameter("username");
	   String pass=sc.getInitParameter("password");
		try 
		{
			Class.forName(driver);
			 con=DriverManager.getConnection(url,uName,pass);
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
}
	public static boolean login(HttpServletRequest request, HttpServletResponse response)
	{
		boolean result=false;
		try 
		{
			HttpSession hs=request.getSession();
			PreparedStatement ps=con.prepareStatement("select * from empServletContext where name=? and id=?");
			ps.setString(1, (String) hs.getAttribute("name"));
			ps.setInt(2, (int) hs.getAttribute("id"));
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				if(hs.getAttribute("name").equals(rs.getString(1)) && hs.getAttribute("id").equals(rs.getString(2)))
				{
					result=true;
				}
			}
		} catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
