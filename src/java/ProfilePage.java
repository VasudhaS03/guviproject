import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/ProfilePage"})
public class ProfilePage extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
         
            String username = request.getParameter("username");
            String email = request.getParameter("eml");
            String age = request.getParameter("age");
            String dob = request.getParameter("birthday");
           
           Class.forName("com.mysql.jdbc.Driver");
           Connection con=DriverManager.getConnection("jdbc:mysql://localhost/guvi","root","root123");
           
           PreparedStatement pst=con.prepareStatement("update signup_details set age=?,dob=? where username = ?");
           
           pst.setString(1, age);
           pst.setString(2, dob);
           pst.setString(3,username)  ;       
           
           int rs=pst.executeUpdate();
            
            if(rs>0)
            {
                out.print("<html> <body onload=\"alert('Changes have been saved')\"></body> </html>");
                out.print("<html> <body> <a href = 'profile.html'> Back </a> </body> </html>");
                
            }
            else
            {
                out.print("not edited");
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        
    }
}
